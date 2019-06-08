package messages;

public interface Message {

    MessageType getMessageType ();

    void setSymbol(String symbol);

    //Convenience method just for the ConsoleClient usage
    String toFixString();

    void setClOrdId(String clientOrderId);
}
