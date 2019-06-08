package messages;

public class NewOrderMessage implements Message {

    private String _symbol;
    private String _clOrdId;
    private long _quantity;
    private double _price;
    private messages.Side _side;

    @Override
    public MessageType getMessageType() {
        return MessageType.NewOrder;
    }

    @Override
    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    @Override
    public String toFixString() {
        return "35=D; 55=" + _symbol + ";";
    }

    @Override
    public void setClOrdId(String clientOrderId) {
       _clOrdId = clientOrderId;
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
    public void setSide(messages.Side side) {
        _side = side;
    }

    public long getOrderQty() {
        return _quantity;
    }

    public double getPrice() {
        return _price;
    }

    public Side getSide() {
        return _side;
    }

    public String getClOrdId() {
        return _clOrdId;
    }

    public String getSymbol() {
        return _symbol;
    }
}
