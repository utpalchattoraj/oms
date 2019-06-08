package sessions;

import messages.Message;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/** This class reads the messages from console
 *  Messages are delimited by ';' character
 *
 *  Sample message for New order single
 *  35=D; 55=STEL.SI; 44=3.2; 54=1;
 */

public class ConsoleClient extends Client{

    private ReceiverThread _receiverThread;
    private TransmitterThread _transmitterThread;

    public ConsoleClient(Queue<Message> inQueue, Queue<Message> outQueue) {
        super (inQueue, outQueue);
        _receiverThread = new ReceiverThread();
        _transmitterThread = new TransmitterThread();
    }

    @Override
    public void start() {
       _receiverThread.start();
       _transmitterThread.start();
    }

    // For receiving messages from client, in this case the console
    class ReceiverThread extends Thread {

        @Override
        public void run() {
            Scanner in = new Scanner(System.in);
            while (true) {
                Message m = parse (in.nextLine());
                _inQueue.add(m);
            }
        }
    }

    // For sending messages back to client
    class TransmitterThread extends Thread {
        @Override
        public void run() {

             while (true) {
                Message m;
                 try {
                     m = (Message) ((BlockingQueue)_outQueue).take();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
        }
    }
}
