package messages;

/** This message acts like a Poison pill , when the server receives this message it shutsdown **/
// 35=K arbitrarily made up

public class KillProcessMessage implements Message{

    @Override
    public MessageType getMessageType() {
        return MessageType.KillProcess;
    }

    @Override
    public void setSymbol(String symbol) {
        //Not implemented
    }
}
