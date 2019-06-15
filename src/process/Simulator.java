package process;

import config.ConfigManager;
import messages.Message;
import messages.MessageType;
import messages.NewOrderMessage;
import messages.TradeMessage;

public class Simulator implements Engine {
    private static Simulator ourInstance = new Simulator();

    public static Simulator getInstance() {
        return ourInstance;
    }

    private Simulator() {
    }

    @Override
    public Message processMessage(Message msg) {
        Message m = null;
        if (msg.getMessageType() == MessageType.NewOrder) {
            NewOrderMessage nos = (NewOrderMessage) msg;
            if (ConfigManager.getInstance().isLiquidInstrument(nos.getSymbol())) {
                m = processTrade (nos);
            }
        }
        return m;
    }

    private TradeMessage processTrade(NewOrderMessage msg) {
        TradeMessage m = new TradeMessage();
        m.setClOrdId(msg.getClOrdId());
        m.setOrderQty(msg.getOrderQty());
        m.setSymbol(msg.getSymbol());

        return m;
    }
}
