package messages;

public class RejectMessage implements Message {

    private String _symbol;
    private String _clOrdId;
    private String _text;

    @Override
    public MessageType getMessageType() {
        return MessageType.Reject;
    }

    @Override
    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    @Override
    public String toFixString() {
        return "35=8; 150=8; 39=8; 55=" + _symbol + "; 11=" +_clOrdId + "; 58=" + _text + ";";
    }

    @Override
    public void setClOrdId(String clientOrderId) {
        _clOrdId = clientOrderId;
    }

    @Override
    public void setOrigClOrdId(String clientOrderId) {

    }

    @Override
    public void setOrderQty(long qty) {
    //NA
    }


    @Override
    public void setPrice(double price) {
        //NA
    }

    @Override
    public void setSide(Side buy) {

    }

    @Override
    public void setAccount(String account) {

    }

    public void setText(String text) {
        _text = text;
    }

    public String getText() {
        return _text;
    }
}
