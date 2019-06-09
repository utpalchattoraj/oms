package process;

import com.sun.tools.corba.se.idl.constExpr.Or;
import config.ConfigManager;
import messages.*;
import order.Order;
import order.OrderFactory;

import java.util.HashMap;
import java.util.Map;

class OrderManager {

    static OrderManager INSTANCE = new OrderManager();
    static Map<String, Order> _orders = new HashMap<>();

    private OrderManager () {
    }

    OrderManager getInstance() {
        return INSTANCE;
    }

    Message processMessage(Message m) {

        switch (m.getMessageType()) {
            case NewOrder:
                return processNewOrder(m);
            case Status:
                return processOrderBookStatus();
        }
        return null;
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
}
