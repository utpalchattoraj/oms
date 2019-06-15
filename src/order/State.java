package order;

import messages.Message;

public enum State {
    Open,
    Rejected,
    Cancelled,
    Amended,
    Expired,
    PFill,
    FFill;

    State next (State currentState, Message m) {
        State newState = null;
        return newState;
    }
}
