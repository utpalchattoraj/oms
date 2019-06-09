package messages;

/** This message is a way to print order book status **/
// 35=S arbitrarily made up

public class StatusMessage implements Message{
    private String _text;

    public StatusMessage(String s) {
       _text = s;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.Status;
    }

    @Override
    public void setSymbol(String symbol) {
        //Not implemented
    }

    @Override
    public String toFixString() {
        return _text;
    }

    @Override
    public void setClOrdId(String clientOrderId) {
        //Not implemented
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
