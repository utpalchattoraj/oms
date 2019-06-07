package sessions;

import messages.Message;
import messages.MessageFactory;

import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;

public class Client extends Thread{

    final String FIX_MESSAGE_TYPE_TOKEN = "35";
    final String FIX_SYMBOL_TOKEN = "55";
    final String FIX_PRICE_TOKEN = "44";
    final String FIX_SIDE_TOKEN  = "54";

    private BlockingQueue<Message> _queue;

    Client(BlockingQueue<Message> queue ) {
        _queue = queue;
    }

    // Assumption the input is delimited by ';' character to break down the message
    // Assumption first token is always the message type
    Message parse (String input) {
        Message m = null;
        StringTokenizer st = new StringTokenizer(input, ";");

        while (st.hasMoreElements()) {
            String token = st.nextElement().toString();
            StringTokenizer st2 = new StringTokenizer(token, "=");
            while (st2.hasMoreElements()) {
                String tk2 = st2.nextElement().toString();
                System.out.println ("FIRST " + tk2);
                System.out.println ("second" + st2.nextToken());
                switch (tk2) {
                    case FIX_MESSAGE_TYPE_TOKEN:
                        m = MessageFactory.getInstance().createMessage(st2.nextElement().toString());
                        break;
                    default:
                    st2.nextElement();
                }
            }
        }

        return m;
    }

    void processMessage( Message m) {
       _queue.add( m);
    }

    @Override
    public void run() {

    }
}
