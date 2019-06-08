package order;

import messages.Side;

public class Order {

    private String _symbol;
    private long _quantity;
    private double _price;
    private Side _side;
    private State _state;

    Order() {
    }


    public String getSymbol() {
        return _symbol;
    }

    public void setSymbol(String symbol) {
        _symbol = symbol;
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

    public void setPrice(double price) {
        _price = price;
    }

    public Side getSide() {
        return _side;
    }

    public void setSide(Side side) {
        _side = side;
    }

    public State getState() {
        return _state;
    }

    public void setState(State state) {
        _state = state;
    }
}
