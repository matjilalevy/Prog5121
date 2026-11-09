package com.mycompany.chatapplication;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest 
{
    // Tests if message ID is not more than 10 characters
    @Test
    public void testCheckMessageID() 
    {
        Message msg = new Message(1, "1234567", "User's message");
        assertTrue(msg.checkMessageID());
    }
    
    // Tests if the recipient cell number starts with + and less than 13 int
    @Test
    public void testCheckRecipientCell() 
    {
        assertEquals("correctFormate", Message.checkRecipientCell("+27731234567"));
    }
    
    // Tests if hash is created
    @Test
    public void testCreateMessageHash() 
    {
        Message msg = new Message(1, "+1234", "This has to be 100%");
        String hash = msg.createMessageHash();
        assertNotNull(hash);
    }
    
    // Tests if total messages incrementing are returned
    @Test
    public void testReturnTotalMessages() 
    {
        int total = Message.returnTotalMessages();
        assertTrue(total >= 0);
    }
    
    // Tests if messages can be printed
    @Test
    public void testPrintMessages() 
    {
        Message msg = new Message(1, "+27838884567", "Test print");
        msg.sendMessageForTest();
        
        String result = Message.printMessages();
        assertTrue(result.contains("ALL SENT MESSAGES"));
    }
    
    // Tests if message details are returned correctly
    @Test
    public void testGetMessageDetails() 
    {
        Message msg = new Message(1, "+27731234567", "Hi Sir");
        String details = msg.getMessageDetails();
        assertNotNull(details);
        assertTrue(details.contains("Hi Sir"));
    }
    // 1. Test: Sent Messages array correctly populated
    @Test
    public void testSentMessagesArrayPopulated() 
    {
        Message msg1 = new Message(1, "+27838884567", "Did you get the cake?");
        Message msg2 = new Message(2, "+27838884567", "It is dinner time!");
        msg1.sendMessageForTest();
        msg2.sendMessageForTest();
        
        String result = Message.printMessages();
        assertTrue(result.contains("Did you get the cake?") && result.contains("It is dinner time!"));
    }
    
    // 2. Test: Display the longest message
    @Test
    public void testDisplayLongestMessage() 
    {
        Message msg1 = new Message(1, "+27838884567", "Short msg");
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg1.storeMessageForTest();
        msg2.storeMessageForTest();
        
        assertNotNull(Message.storedMessageArray[1]);
    }
    
    // 3. Test: Search for messageID
    @Test
    public void testSearchByMessageID() 
    {
        Message msg = new Message(4, "+27838884567", "It is dinner time!");
        msg.storeMessageForTest();
        String expectedID = msg.getMessageID();
        
        assertNotNull(expectedID);
    }
    
    // 4. Test: Search all the messages sent or stored regarding a particular recipient
    @Test
    public void testSearchByRecipient() 
    {
        Message msg1 = new Message(1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg2 = new Message(2, "+27838884567", "Ok, I am leaving without you.");
        msg1.storeMessageForTest();
        msg2.storeMessageForTest();
        
        assertTrue(msg1.recipient.equals("+27838884567"));
        assertTrue(msg2.recipient.equals("+27838884567"));
    }
    
    // 5. Test: Delete a message using a message hash
    @Test
    public void testDeleteByMessageHash() 
    {
        Message msg = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time");
        msg.storeMessageForTest();
        String hash = msg.createMessageHash();
        
        assertNotNull(hash);
        Message.deleteStoredMessageByHash(hash);
    }
    
    // 6. Test: Display Report
    @Test
    public void testDisplayReport() 
    {
        Message msg = new Message(1, "+27838884567", "Test report message");
        msg.sendMessageForTest();
        
        String report = Message.printMessages();
        assertNotNull(report);
    }
}
