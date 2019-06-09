package order;

import messages.NewOrderMessage;

public class OrderFactory {

    public static Order createOrder(NewOrderMessage msg) {
        return new Order(msg.getSymbol(), msg.getSide(), msg.getOrderQty(), msg.getPrice(), msg.getAccount());
    }
}
