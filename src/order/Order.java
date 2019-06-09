package order;

import messages.Side;

public class Order {

    private String _symbol;
    private long _quantity;
    private double _price;
    private Side _side;
    private State _state;
    private String _account;

    Order (String symbol, Side side, long quantity, double price, String account) {
        _symbol = symbol;
        _side = side;
        _quantity = quantity;
        _price = price;
        _state = State.Open;
        _account = account;
    }

    @Override
    public String toString() {
        return "Symbol " + _symbol + ", Side " + _side + ", Account " + _account
                + ", State " + _state;
    }

    public String getSymbol() {
        return _symbol;
    }

    public long getQuantity() {
        return _quantity;
    }

    public void setQuantity(long quantity) {
        _quantity = quantity;
    }

    public double getPrice() {
        return _price;
    }

    public Side getSide() {
        return _side;
    }

    public State getState() {
        return _state;
    }

    public void setState(State state) {
        _state = state;
    }
}
