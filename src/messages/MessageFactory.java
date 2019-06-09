package messages;

import order.Order;

public class MessageFactory {

    private static MessageFactory INSTANCE = new MessageFactory();

    private final static String FIX_NEW_ORDER = "D";
    private final static String FIX_AMEND_ORDER = "G";
    private final static String FIX_CANCEL_ORDER = "F";
    private final static String STATUS = "S";
    private final static String KILL_PROCESS = "K";

    private MessageFactory () {
    }

    public static MessageFactory getInstance() { return INSTANCE ;}

    public static Message createMessage (String messageType) {
        switch (messageType) {
            case FIX_NEW_ORDER:
                return new NewOrderMessage();
            case FIX_CANCEL_ORDER:
                return new CancelOrderMessage();
            case KILL_PROCESS:
                return new KillProcessMessage();
            case STATUS:
                return new StatusMessage("GetStatus");

        }
        return null;
    }

    public Message createRejectMessage(NewOrderMessage newOrder, String rejectReason) {
        RejectMessage rej = new RejectMessage();
        rej.setClOrdId(newOrder.getClOrdId());
        rej.setSymbol(newOrder.getSymbol());
        rej.setText(rejectReason);
        return rej;
    }

    public Message createAcceptMessage(NewOrderMessage newOrder) {
        AcceptMessage msg = new AcceptMessage();
        msg.setClOrdId(newOrder.getClOrdId());
        msg.setSymbol(newOrder.getSymbol());
        msg.setPrice(newOrder.getPrice());
        msg.setSide(newOrder.getSide());
        msg.setOrderQty(newOrder.getOrderQty());
        return msg;
    }

    public Message createCancelAcceptMessage(Order order) {
        CancelAcceptMessage msg = new CancelAcceptMessage();
        msg.setSymbol(order.getSymbol());
        return msg;
    }
}
