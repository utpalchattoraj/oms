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

    public static State next(State currentState, Message m) throws StateException {
        State newState = null;
        MessageType type = m.getMessageType();
        if (currentState == Open || currentState == Amended ) {
            switch (type) {
                case CancelOrder:
                    return Cancelled;
                case AmendOrder:
                    return Amended;
                case Trade:
                    return FFill;
            }
        }
        if (isCompleted(currentState) && (type == MessageType.CancelOrder || type == MessageType.AmendOrder)) {
           throw new StateException ("Invalid state transition from current state " + currentState ) ;
        }
        //TODO more state related validations

        return newState;
    }

    static boolean isCompleted(State state) {
        switch (state) {
            case Cancelled:
            case FFill:
                return true;
        }
        return false;
    }

    public static class StateException extends Throwable {
        private String _reason;
        public StateException(String s) {
            _reason = s;
        }

        public String getReason() {
            return _reason;
        }
    }
}
