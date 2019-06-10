package messages;

public class AmendAcceptMessage implements Message {

    private String _symbol;
    private String _clOrdId;
    private String _origClOrdId;

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
        return "35=8; 150=5; 39=5; 11=" + _clOrdId + "; 41=" + _origClOrdId + "; 55=" + _symbol + ";";
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
    }

    @Override
    public void setPrice(double price) {
    }

    @Override
    public void setSide(Side side) {
    }

    @Override
    public void setAccount(String account) {

    }
}
