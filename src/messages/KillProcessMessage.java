package messages;

/** This message acts like a Poison pill , when the server receives this message it shutsdown **/
// 35=K arbitrarily made up

public class KillProcessMessage implements Message{

    @Override
    public MessageType getMessageType() {
        return MessageType.KillProcess;
    }

    @Override
    public void setSymbol(String symbol) {
        //Not implemented
    }

    @Override
    public String toFixString() {
        return "35=K";
    }

    @Override
    public void setClOrdId(String clientOrderId) {
        //Not implemented
    }

    @Override
    public void setOrigClOrdId(String clientOrderId) {

    }

    @Override
    public void setOrderQty(long qty) {

    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public void setSide(Side buy) {

    }

    @Override
    public void setAccount(String account) {

    }
}
