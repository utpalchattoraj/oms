package sessions;

import messages.Message;
import messages.MessageFactory;

import java.util.Queue;
import java.util.StringTokenizer;

/** This class helps to parse the incoming FIX messages from the client */

public class Client {

    final String FIX_MESSAGE_TYPE_TOKEN = "35";
    final String FIX_SYMBOL_TOKEN = "55";
    final String FIX_PRICE_TOKEN = "44";
    final String FIX_SIDE_TOKEN  = "54";
    final String FIX_CLORDID_TOKEN  = "11";

    protected Queue<Message> _inQueue;
    protected Queue<Message> _outQueue;

    Client(Queue<Message> inQueue , Queue<Message> outQueue) {
        _inQueue = inQueue;
        _outQueue = outQueue;
    }

    // Assumption the input is delimited by ';' character to break down the message
    // Assumption first token is always the message type
    Message parse (String input) {
        Message m = null;
        StringTokenizer st = new StringTokenizer(input, ";");

        while (st.hasMoreElements()) {
            String token = st.nextElement().toString();

            StringTokenizer st2 = new StringTokenizer(token, "=");
            String tk2 = st2.nextToken().trim();
            String tk3 = st2.nextToken().trim();

                switch (tk2) {
                    case FIX_MESSAGE_TYPE_TOKEN:
                        m = MessageFactory.createMessage(tk3);
                        break;
                    case FIX_SYMBOL_TOKEN:
                        m.setSymbol(tk3);
                        break;
                    case FIX_CLORDID_TOKEN:
                        m.setClOrdId(tk3);
                        break;
                    default:
                        break;
                }
         }
        return m;
    }

    public void start() {
    }
}
