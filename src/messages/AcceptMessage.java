package messages;

public class AcceptMessage implements Message {

    private String _symbol;
    private String _clOrdId;
    private Side _side;
    private long _quantity;
    private double _price;

    @Override
    public MessageType getMessageType() {
        return MessageType.Accept;
    }

    @Override
    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    @Override
    public String toFixString() {
        return "35=8; 150=0; 39=0; 55=" + _symbol + "; 11=" +_clOrdId + "; 38=" + _quantity + "; 44=" + _price + ";" ;
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
        _quantity = qty;
    }

    @Override
    public void setPrice(double price) {
        _price = price;
    }

    @Override
    public void setSide(Side side) {
        _side = side;
    }

    @Override
    public void setAccount(String account) {

    }
}
