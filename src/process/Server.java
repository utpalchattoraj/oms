package process;

import config.ConfigManager;
import messages.Message;
import sessions.Client;
import sessions.ConsoleClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {

    private ConfigManager _config;
    private Client _client;
    private OrderManager _orderManager;
    private BlockingQueue<Message> _inQueue;
    private BlockingQueue<Message> _outQueue;
    private Engine _engine;

    public void init() {

        // This is the configuration of the OMS which orders to accept which to reject
        _config = ConfigManager.INSTANCE;

        // Validations and state management
        _orderManager = OrderManager.INSTANCE;

        _engine = Simulator.getInstance();

        // Todo Use a factory method to get the type of client
        // For a ConsoleClient using a blocking queue;
        _inQueue = new LinkedBlockingQueue();
        _outQueue = new LinkedBlockingQueue();
        _client = new ConsoleClient(_inQueue, _outQueue);
    }

    public void start() {

        // Start the client thread as well reading from console
        _client.start();

        processMessages();
    }

    private void processMessages() {
        while (true) {
            try {
                Message m = _inQueue.take();
                Message outMessage = null;
                switch (m.getMessageType()) {
                    case NewOrder:
                    case CancelOrder:
                    case AmendOrder:
                    case Status:
                        outMessage = _orderManager.processMessage (m);
                        break;
                    case KillProcess:
                        System.out.println ("Shutting down");
                        System.exit(0);
                        break;
                }
                if (outMessage != null) {
                    _outQueue.add (outMessage);
                    postProcess(m);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void postProcess(Message m) {
        Message msg = _engine.processMessage(m);
        if (msg != null) {
            _orderManager.processEngineMessage(msg);
            _outQueue.add(msg);
        }
    }
}
