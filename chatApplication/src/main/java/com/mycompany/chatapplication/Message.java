/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.util.Random;

import java.util.Random;

public class Message {
    
    // Declerating my public variables
    public String messageID;
    public int messageCounter;
    public String recipient;
    public String textMessage;
    public String signHash;
    
    static int sentMessage = 0;
    static String[] savedMessages = new String[50];
    static int numberOfMessages = 0;
    
    // Method that creats the 10 random numbers of the message id
    public Message(int count, String receiver, String text) {
        // Generate random messageID 
        Random randomNumberCreation = new Random();
        int randomNum = randomNumberCreation.nextInt(999999999);
        messageID = "" + randomNum;
        
        messageCounter = count;
        recipient = receiver;
        textMessage = text;
        
        // Creating the message Hash
        String firstTwo = messageID.substring(0, 2);
        String[] words = textMessage.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        signHash = firstTwo + ":" + messageCounter + ":" + firstWord + lastWord;
        signHash = signHash.toUpperCase();
    }
    
    // Method that checks if the message id is realy 10 degits
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
    
    // This method checks if the users recipient cell number meets the requirment, which must contain the international code and be less than 9 after the + sign
    public static String checkRecipientCell( String cellNumber ) {
        if (cellNumber .startsWith("+") && cellNumber .length() <= 13) {
            return "correctFormate";
        } else {
            return "incorrectFormate";
        }
    }
    
    // // Method used to create and alocate the hash sign 
    public String createMessageHash() {
        return signHash;
    }
    
    // Method that displays the users choice to choose from
    public String sendMessage() {
        java.util.Scanner input = new java.util.Scanner(System.in);
        
        System.out.println("\nWhat do you want to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.print("Your choice: ");
        
        int choice = input.nextInt();
        
        if (choice == 1) {
            sentMessage = sentMessage + 1;
            savedMessages[numberOfMessages] = getMessageDetails();
            numberOfMessages = numberOfMessages + 1;
            return "Message sent successfully!";
        } else if (choice == 3) {
            storeMessage();
            return "Message stored successfully!";
        } else if (choice == 2) {
            return "Message deleted!";
        } else {
            return "Please choose between (1 , 2, and 0)";
        }
    }
    
    // This method is used to safe all the messages count
    public static String printMessages() {
        if (numberOfMessages == 0) {
            return "No messages sent yet.";
        }
        
        String allMessages = "\nALL SENT MESSAGES\n";
        for (int i = 0; i < numberOfMessages; i++) {
            allMessages = allMessages + savedMessages[i] + "\n";
            allMessages = allMessages + "\n";
        }
        return allMessages;
    }
    
    // Method that counts all number of messages.
    public static int returnTotalMessages() {
        return sentMessage;
    }
    
   // Mathod that stores all the messages in the json file.
    public void storeMessage() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
            writer.write("{\n");
            writer.write("  \"Message ID\": \"" + messageID + "\",\n");
            writer.write("  \"Message Hash\": \"" + signHash + "\",\n");
            writer.write("  \"Recipient\": \"" + recipient + "\",\n");
            writer.write("  \"Message\": \"" + textMessage + "\"\n");
            writer.write("},\n");
            writer.close();
        } catch (Exception error) {
            System.out.println("Could not save message.");
        }
    }
    
    // helps the method messageID(), reciever cellphone number, and the hash sign.
    public String getMessageDetails() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + signHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + textMessage;
    }
    
    // Method that checks if the cell number is correct on the junit test  
    public static boolean cellNumber2check(String cellNumber) 
    {
        if (cellNumber.startsWith("+") && cellNumber.length() <= 10) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }
      // Getteer method to help counting the total message of message calling.
    public int getMessageCount() 
    {
        return messageCounter;
    }

     // Getter method to help the message to generate the random message id 
    public String getMessageID() 
    {
       return messageID;
    }
}