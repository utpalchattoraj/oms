package messages;

import javafx.geometry.Side;

public interface Message {

    MessageType getMessageType ();

    void setSymbol(String symbol);

    //Convenience method just for the ConsoleClient usage
    String toFixString();

    void setClOrdId(String clientOrderId);

    void setOrderQty(long qty);

    void setPrice(double price);

    void setSide(messages.Side buy);

    void setAccount(String account);
}
