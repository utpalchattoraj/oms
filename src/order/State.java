package order;

import messages.Message;
import messages.MessageType;

public enum State {
    Open,
    Rejected,
    Cancelled,
    Amended,
    Expired,
    PFill,
    FFill;

    public static State next(State currentState, Message m) {
        State newState = null;
        if (currentState == Open || currentState == Amended ) {
            switch (m.getMessageType()) {
                case CancelAccept:
                    return Cancelled;
                case AmendAccept:
                    return Amended;
                case Trade:
                    return FFill;
            }
        }
        return newState;
    }
}
