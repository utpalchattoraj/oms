package process;

import messages.Message;

public class MatchingEngine implements Engine {
    private static MatchingEngine ourInstance = new MatchingEngine();

    public static MatchingEngine getInstance() {
        return ourInstance;
    }

    private MatchingEngine() {
    }

    @Override
    public Message processMessage(Message msg) {
       //TODO to implement a real matching engine
        return null;
    }
}
