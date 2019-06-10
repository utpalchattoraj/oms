package messages;

public class AmendOrderMessage implements Message {

    private String _symbol;
    private String _clOrdId;
    private String _origClOrdId;
    private long _quantity;
    private double _price;
    private Side _side;
    private String _account;

    @Override
    public MessageType getMessageType() {
        return MessageType.AmendOrder;
    }

    @Override
    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    @Override
    public String toFixString() {
        return "35=G; 55=" + _symbol + ";";
    }

    @Override
    public void setClOrdId(String clientOrderId) {
       _clOrdId = clientOrderId;
    }

    @Override
    public void setOrigClOrdId(String clientOrderId) {
        _origClOrdId = clientOrderId;
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
        _account = account;
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

    public String getAccount() {
        return _account;
    }

    public String getOrigClOrdId() {
        return _origClOrdId;
    }
}
