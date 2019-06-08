package sessions;

import messages.Message;
import messages.MessageFactory;

import java.util.Queue;
import java.util.StringTokenizer;

public class Client extends Thread{

    final String FIX_MESSAGE_TYPE_TOKEN = "35";
    final String FIX_SYMBOL_TOKEN = "55";
    final String FIX_PRICE_TOKEN = "44";
    final String FIX_SIDE_TOKEN  = "54";

    private Queue<Message> _queue;

    Client(Queue<Message> queue ) {
        _queue = queue;
    }

    // Assumption the input is delimited by ';' character to break down the message
    // Assumption first token is always the message type
    Message parse (String input) {
        Message m = null;
        StringTokenizer st = new StringTokenizer(input, ";");

        while (st.hasMoreElements()) {
            String token = st.nextElement().toString();

            System.out.println (token);
            StringTokenizer st2 = new StringTokenizer(token, "=");
            String tk2 = st2.nextToken().trim();
            String tk3 = st2.nextToken().trim();

                switch (tk2) {
                    case FIX_MESSAGE_TYPE_TOKEN:
                        m = MessageFactory.getInstance().createMessage(tk3);
                        break;
                    case FIX_SYMBOL_TOKEN:
                        System.out.println ("setting symbol" + tk3);
                        m.setSymbol(tk3);
                        break;
                    default:
                        break;
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
