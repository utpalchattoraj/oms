package messages;

public class AcceptMessage implements Message {

    private String _symbol;
    private String _clOrdId;

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
        return "35=8; 150=0; 39=0; 55=" + _symbol + "; 11=" +_clOrdId + ";";
    }

    @Override
    public void setClOrdId(String clientOrderId) {
        _clOrdId = clientOrderId;
    }
}
