package test;

import messages.Message;
import messages.MessageType;
import messages.RejectMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import process.OrderManager;
import sessions.Client;

class OrderManagerTest {

    private Client _client;
    private OrderManager _om;

    OrderManagerTest() {
        _client = new Client();
        _om = OrderManager.getInstance();
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        _om.clear();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testNewOrderAcknowledgement() {
        String nos = "35=D; 11=A1; 1=ABC; 55=STEL.SI; 38=100; 44=1.0; 54=1";
        Message input = _client.parse(nos);
        Message output = _om.processMessage(input);
        Assertions.assertEquals(output.getMessageType(),MessageType.Accept);
    }

    @Test
    public void testNewOrderRejectLotSize() {
        String nos = "35=D; 11=A1; 1=ABC; 55=STEL.SI; 38=101; 44=1.0; 54=1";
        Message input = _client.parse(nos);
        Message output = _om.processMessage(input);
        Assertions.assertEquals(output.getMessageType(),MessageType.Reject);
        RejectMessage msg = (RejectMessage) output;
        Assertions.assertEquals (msg.getText(), "Lot size invalid should be multiple of 10");
    }

    @Test
    public void testNewOrderRejectInvalidInstrument() {
        String nos = "35=D; 11=A1; 1=ABC; 55=IBM.SI; 38=101; 44=1.0; 54=1";
        Message input = _client.parse(nos);
        Message output = _om.processMessage(input);
        Assertions.assertEquals(output.getMessageType(),MessageType.Reject);
        RejectMessage msg = (RejectMessage) output;
        Assertions.assertEquals (msg.getText(), "Invalid instrument IBM.SI");
    }

    @Test
    public void testNewOrderDuplicateRejected() {
        String nos = "35=D; 11=A1; 1=ABC; 55=STEL.SI; 38=100; 44=1.0; 54=1";
        Message input = _client.parse(nos);
        Message output = _om.processMessage(input);
        Assertions.assertEquals(output.getMessageType(), MessageType.Accept);

        // Duplicate tag 11
        String nos2 = "35=D; 11=A1; 1=ABC; 55=STEL.SI; 38=200; 44=1.0; 54=1";
        input = _client.parse(nos);
        output = _om.processMessage(input);
        RejectMessage msg = (RejectMessage) output;
        Assertions.assertEquals(msg.getText(), "Duplicate clientOrderId Tag 11=A1");
    }
}