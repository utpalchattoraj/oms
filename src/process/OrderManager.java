package process;

import config.ConfigManager;
import messages.Message;
import messages.NewOrderMessage;
import messages.RejectMessage;
import messages.Side;

public class OrderManager {

    public static OrderManager INSTANCE = new OrderManager();

    private OrderManager () {
    }

    OrderManager getInstance() {
        return INSTANCE;
    }

    public Message processMessage(Message m) {

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

        } else {
            RejectMessage rej = new RejectMessage();
            message = rej;
            rej.setClOrdId(newOrder.getClOrdId());
            rej.setSymbol(newOrder.getSymbol());
            rej.setText(rejectReason);
        }
        return message;
    }

    private String validate(NewOrderMessage newOrder) {

        if (!ConfigManager.getInstance().isValidInstrument( newOrder.getSymbol())) {
           return "Invalid instrument " + newOrder.getSymbol();
        }

        //Check side only Buy and Sell, no specials like ShortSell
        Side side = newOrder.getSide();
        if (side == null) {
            return "Invalid side";
        }

        long quantity = newOrder.getOrderQty();
        System.out.println (quantity);

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
