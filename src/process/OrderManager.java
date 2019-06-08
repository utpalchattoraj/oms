package process;

import messages.Message;
import messages.NewOrderMessage;
import messages.RejectMessage;

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
        if (validate (newOrder)) {

        } else {
            RejectMessage rej = new RejectMessage();
            message = rej;
            rej.setClOrdId(newOrder.getClOrdId());
            rej.setSymbol(newOrder.getSymbol());
        }
        return message;
    }

    private boolean validate(NewOrderMessage newOrder) {
        return false;
    }
}
