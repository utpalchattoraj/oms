package process;

import config.ConfigManager;
import messages.*;
import order.Order;
import order.OrderFactory;
import order.State;
import order.State.StateException;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {

    static OrderManager INSTANCE = new OrderManager();
    private static Map<String, Order> _orders = new HashMap<>();

    private OrderManager () {
    }

    public static OrderManager getInstance() {
        return INSTANCE;
    }

    public Message processMessage(Message m) {

        switch (m.getMessageType()) {
            case NewOrder:
                return processNewOrder(m);
            case CancelOrder:
                return processCancelOrder(m);
            case AmendOrder:
                return processAmendOrder(m);
            case Status:
                return processOrderBookStatus();
        }
        return null;
    }

    private Message processAmendOrder(Message amend) {
        Message m = null;
        AmendOrderMessage amendMessage = (AmendOrderMessage) amend;
        Order order = _orders.get(amendMessage.getOrigClOrdId());
        if (order == null ) {
            // If order not found or Order state is not for cancelling
            CancelRejectMessage rejectMessage = new CancelRejectMessage();
            rejectMessage.setText( "Order not found" );
            rejectMessage.setClOrdId(amendMessage.getClOrdId());
            m = rejectMessage;
        }
        else {
            try {
                order.setState(State.next(order.getState(), amendMessage));
                AmendAcceptMessage msg = (AmendAcceptMessage) MessageFactory.getInstance().createAmendAcceptMessage( order );
                order.setOpenQuantity( amendMessage.getOrderQty());
                order.setPrice(amendMessage .getPrice());
                msg.setClOrdId(amendMessage.getClOrdId());
                msg.setOrigClOrdId(amendMessage.getOrigClOrdId());
                m = msg;
                // For Future amends/cancel to find the order to take action on
                _orders.remove(amendMessage.getOrigClOrdId());
                _orders.put(amendMessage.getClOrdId(), order);
            } catch (StateException e) {
                CancelRejectMessage rejectMessage = new CancelRejectMessage();
                rejectMessage.setText( e.getReason() );
                rejectMessage.setClOrdId(amendMessage.getClOrdId());
                m = rejectMessage;
            }
        }
        return m;
    }

    private Message processCancelOrder(Message cancel) {
        Message m = null;
        CancelOrderMessage cancelMessage = (CancelOrderMessage) cancel;
        Order order = _orders.get(cancelMessage.getOrigClOrdId());
        if (order != null) {
            try {
                order.setState(State.next(order.getState(), cancelMessage));
                m = MessageFactory.getInstance().createCancelAcceptMessage( order );
                order.setOpenQuantity(0);
            } catch (StateException e) {
                CancelRejectMessage rejectMessage = new CancelRejectMessage();
                rejectMessage.setText( e.getReason() );
                rejectMessage.setClOrdId(cancelMessage.getClOrdId());
                m = rejectMessage;
            }
        }
        else {
            CancelRejectMessage rejectMessage = new CancelRejectMessage();
            rejectMessage.setText( "Order not found" );
            rejectMessage.setClOrdId(cancelMessage.getClOrdId());
            m = rejectMessage;
        }
        return m;
    }

    private Message processOrderBookStatus() {
        int count = _orders.size();
        for (Order order : _orders.values()) {
            System.out.println (order);
        }
        StatusMessage m = new StatusMessage("Displaying status of " + count + " orders");
        return m;
    }

    private Message processNewOrder(Message m) {
        Message message = null;
        NewOrderMessage newOrder = (NewOrderMessage) m;
        String rejectReason = validateAndCreate (newOrder);
        if (rejectReason == null) {
            message = MessageFactory.getInstance().createAcceptMessage( newOrder );
        } else {
            message = MessageFactory.getInstance().createRejectMessage( newOrder, rejectReason);
        }
        return message;
    }

    private String validateAndCreate(NewOrderMessage newOrder) {

        if (!ConfigManager.getInstance().isValidInstrument( newOrder.getSymbol())) {
           return "Invalid instrument " + newOrder.getSymbol();
        }

        String clOrdId = newOrder.getClOrdId();
        if (clOrdId == null) {
            return "Missing mandatory Tag 11";
        }

        //Check side only Buy and Sell, no specials like ShortSell
        Side side = newOrder.getSide();
        if (side == null) {
            return "Invalid side";
        }

        long quantity = newOrder.getOrderQty();

        if (quantity <= 0) {
            return "Invalid quantity " + quantity ;
        }

        // Lets assume lot size is 10
        if ((quantity % 10) != 0) {
            return "Lot size invalid should be multiple of 10";
        }

        if (_orders.containsKey(clOrdId)) {
           return "Duplicate clientOrderId Tag 11=" + clOrdId;
        }
        Order order = OrderFactory.createOrder(newOrder);
        _orders.put(clOrdId, order);

        return null;
    }

    //For Junit tests
    public void clear() {
        _orders.clear();;
    }

    private void processTrade(Message m) {
        TradeMessage msg = (TradeMessage) m;
        Order order = _orders.get(msg.getClOrdId());
        try {
            order.setState(State.next(order.getState(), m));
            order.setOpenQuantity(0);
            order.setExecQuantity(msg.getOrderQty());
        } catch (StateException e) {
            e.printStackTrace();
        }
    }

    void processEngineMessage(Message msg) {
        switch (msg.getMessageType()) {
            case Trade:
                processTrade(msg);
                break;
            case Expired:
               break;
        }
    }
}
