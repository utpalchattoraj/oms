package messages;

public class NewOrderMessage implements Message {

    private String _symbol;
    private String _clOrdId;

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

    public String getClOrdId() {
        return _clOrdId;
    }

    public String getSymbol() {
        return _symbol;
    }
}
