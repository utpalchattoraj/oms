package process;

import config.ConfigManager;
import messages.*;

class OrderManager {

    static OrderManager INSTANCE = new OrderManager();

    private OrderManager () {
    }

    OrderManager getInstance() {
        return INSTANCE;
    }

    Message processMessage(Message m) {

        switch (m.getMessageType()) {
            case NewOrder:
                return processNewOrder(m);
        }
        return null;
    }

    private Message processNewOrder(Message m) {
        Message message = null;
        NewOrderMessage newOrder = (NewOrderMessage) m;
        String rejectReason = validate (newOrder);
        if (rejectReason == null) {
            message = MessageFactory.getInstance().createAcceptMessage( newOrder );
        } else {
            message = MessageFactory.getInstance().createRejectMessage( newOrder, rejectReason);
        }
        return message;
    }

    private String validate(NewOrderMessage newOrder) {

        if (!ConfigManager.getInstance().isValidInstrument( newOrder.getSymbol())) {
           return "Invalid instrument " + newOrder.getSymbol();
        }

        if (newOrder.getClOrdId() == null) {
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

        return null;
    }
}
