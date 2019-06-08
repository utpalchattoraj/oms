package sessions;

import messages.Message;

import java.util.Queue;
import java.util.Scanner;

/** This class reads the messages from console
 *  Messages are delimited by ';' character
 *
 *  Sample message for New order single
 *  35=D; 55=STEL.SI; 44=3.2; 54=1;
 */

public class ConsoleClient extends Client{

    public ConsoleClient(Queue<Message> queue ) {
        super (queue);
    }


    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            Message m = parse (in.nextLine());
            processMessage(m);
        }
    }
}
