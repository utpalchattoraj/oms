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

    public void setText(String text) {
        _text = text;
    }
}
