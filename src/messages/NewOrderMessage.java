package messages;

public class NewOrderMessage implements Message {

    private String _symbol;

    @Override
    public MessageType getMessageType() {
        return MessageType.NewOrder;
    }

    @Override
    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    public String getSymbol() {
        return _symbol;
    }
}
