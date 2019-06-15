package order;

import messages.Side;

import java.util.LinkedList;
import java.util.List;

public class Order {

    private String _symbol;
    private long _quantity;
    private double _price;
    private Side _side;
    private String _account;
    private long _openQuantity;
    private long _execQuantity;
    private List<State> _states;

    Order (String symbol, Side side, long quantity, double price, String account) {
        _symbol = symbol;
        _side = side;
        _quantity = quantity;
        _price = price;
        _account = account;
        _openQuantity = _quantity;
        _execQuantity = 0;
        _states = new LinkedList<>();
        setState( State.Open );
    }

    @Override
    public String toString() {
        return "Symbol " + _symbol + ", Side " + _side + ", Account " + _account
                + ", State " + getState () + ", Orig Qty " + _quantity + ", Open Qty " + _openQuantity
                + ", Exec Qty " + _execQuantity + ", Price " + _price + printStates();
    }

    private String printStates() {
        StringBuilder msg = new StringBuilder();
        msg.append(" [ ");
        for (int i = 0; i < _states.size(); i++) {
           msg.append(_states.get(i)).append(" ") ;
        }
        msg.append("]");
        return msg.toString();
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

    public void setPrice (double price) {
        _price = price;
    }

    public void setOpenQuantity(long quantity) {
        _openQuantity = quantity;
    }

    public void setExecQuantity(long quantity) {
        _execQuantity = quantity;
    }

    public double getPrice() {
        return _price;
    }

    public Side getSide() {
        return _side;
    }

    public State getState() {
        return _states.get(_states.size() -1 );
    }

    public void setState(State state) {
        _states.add(state);
    }

    public boolean isCompleted() {
        switch (getState()) {
            case Cancelled:
            case FFill:
                return true;
        }
        return false;
    }
}
