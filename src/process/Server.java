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
    private BlockingQueue<Message> _queue = new LinkedBlockingQueue();

    public void init() {

        // This is the configuration of the OMS which orders to accept which to reject
        _config = ConfigManager.INSTANCE;

        // Todo Use a factory method to get the type of client
        _client = new ConsoleClient(_queue);
    }

    public void start() {

        // Start the client thread as well reading from console
        _client.start();
    }
}
