package process;

import config.ConfigManager;
import messages.Message;
import messages.NewOrderMessage;
import sessions.Client;
import sessions.ConsoleClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {

    private ConfigManager _config;
    private Client _client;
    private BlockingQueue<Message> _inQueue;
    private BlockingQueue<Message> _outQueue;

    public void init() {

        // This is the configuration of the OMS which orders to accept which to reject
        _config = ConfigManager.INSTANCE;

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
                switch (m.getMessageType()) {
                    case NewOrder:
                        processNewOrder (m);
                        break;
                    case KillProcess:
                        System.out.println ("Shutting down");
                        System.exit(0);
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void processNewOrder(Message m) {
        NewOrderMessage newOrder = (NewOrderMessage) m;
        System.out.println(newOrder.getSymbol());
    }
}
