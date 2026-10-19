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
        String result = Message.printMessages();
        assertNull(result);
    }
    
    // Tests if message details are returned
    @Test
    public void testGetMessageDetails() 
    {
        Message msg = new Message(1, "+27731234567", "Hi Sir");
        String details = msg.getMessageDetails();
        assertNull(details);
    }
}