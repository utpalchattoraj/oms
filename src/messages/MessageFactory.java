package messages;

public class MessageFactory {

    private static MessageFactory INSTANCE = new MessageFactory();

    private final static String FIX_NEW_ORDER = "D";
    private final static String FIX_AMEND_ORDER = "G";
    private final static String FIX_CANCEL_ORDER = "F";

    private MessageFactory () {
    }

    public static MessageFactory getInstance() { return INSTANCE ;}

    public static Message createMessage (String messageType) {
        switch (messageType) {
            case FIX_NEW_ORDER:
                return new NewOrderMessage();

        }
        return null;
    }

}
