/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to edit this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

import java.util.Random;
import java.util.Scanner;

public class Message {
    
    // Declaring public variables
    public String messageID;
    public int messageCounter;
    public String recipient;
    public String textMessage;
    public String signHash;
    
    static int sentMessage = 0;
    static String[] savedMessages = new String[50];
    static int numberOfMessages = 0;
    
    //Required Arrays for data storage
    public static String[] sentMessageArray = new String[100];
    public static int sentMessageCount = 0;

    public static String[] disregardedMessageArray = new String[100];
    public static int disregardedMessageCount = 0;

    public static String[] storedMessageArray = new String[100];
    public static int storedMessageCount = 0;

    public static String[] messageHashArray = new String[100];
    public static int messageHashCount = 0;

    public static String[] messageIDArray = new String[100];
    public static int messageIDCount = 0;
    
    //Method that creates the 10 random numbers of the message id
    public Message(int count, String receiver, String text) {
        //Generate random messageID 
        Random randomNumberCreation = new Random();
        int randomNum = randomNumberCreation.nextInt(999999999);
        messageID = "" + randomNum;
        
        messageCounter = count;
        recipient = receiver;
        textMessage = text;
        
        //Creating the message Hash
        String firstTwo = messageID.substring(0, 2);
        String[] words = textMessage.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        signHash = firstTwo + ":" + messageCounter + ":" + firstWord + lastWord;
        signHash = signHash.toUpperCase();
    }
    
    // Method that checks if the message id is really 10 digits
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
    
    // This method checks if the users recipient cell number meets the requirement, which must contain the international code and be less than 9 after the + sign
    public static String checkRecipientCell(String cellNumber) {
        if (cellNumber.startsWith("+") && cellNumber.length() <= 13) {
            return "correctFormate";
        } else {
            return "incorrectFormate";
        }
    }
    
    // Method used to create and allocate the hash sign 
    public String createMessageHash() {
        return signHash;
    }
    
    // Method that displays the users choice to choose from
    public String sendMessage(Scanner scanner) {
        System.out.println("\nWhat do you want to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.print("Your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline buffer
        
        if (choice == 1) {
            sentMessage = sentMessage + 1;
            savedMessages[numberOfMessages] = getMessageDetails();
            numberOfMessages = numberOfMessages + 1;
            
            // Populate arrays for Sent Messages, Message IDs, and Message Hashes
            sentMessageArray[sentMessageCount] = getMessageDetails();
            sentMessageCount = sentMessageCount + 1;
            
            messageIDArray[messageIDCount] = messageID;
            messageIDCount = messageIDCount + 1;
            
            messageHashArray[messageHashCount] = signHash;
            messageHashCount = messageHashCount + 1;
            
            return "Message sent successfully!";
        } else if (choice == 3) {
            storeMessage();
            
            // Populating arrays for Stored Messages, Message IDs, and Message Hashes
            storedMessageArray[storedMessageCount] = getMessageDetails();
            storedMessageCount = storedMessageCount + 1;
            
            messageIDArray[messageIDCount] = messageID;
            messageIDCount = messageIDCount + 1;
            
            messageHashArray[messageHashCount] = signHash;
            messageHashCount = messageHashCount + 1;
            
            return "Message stored successfully!";
        } else if (choice == 2) {
            // Populate disregarded messages array
            disregardedMessageArray[disregardedMessageCount] = getMessageDetails();
            disregardedMessageCount = disregardedMessageCount + 1;
            
            return "Message disregarded and deleted!";
        } else {
            return "Not in the list, please choose between (1, 2, and 3)";
        }
    }
    
    // This method is used to save all the messages count
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
    
    // Method that counts all number of messages
    public static int returnTotalMessages() {
        return sentMessage;
    }
    
    // Method that stores all the messages in the json file
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
    
    // Method to read the JSON file into the stored messages array
    public static void loadStoredMessagesFromJson() {
        try {
            java.io.File file = new java.io.File("messages.json");
            if (file.exists()) {
                java.util.Scanner fileScanner = new java.util.Scanner(file);
                String currentId = "";
                String currentHash = "";
                String currentRecipient = "";
                String currentText = "";
                storedMessageCount = 0;
                
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (line.contains("\"Message ID\":")) {
                        currentId = line.substring(line.indexOf(":") + 2).replace("\"", "").replace(",", "");
                    } else if (line.contains("\"Message Hash\":")) {
                        currentHash = line.substring(line.indexOf(":") + 2).replace("\"", "").replace(",", "");
                    } else if (line.contains("\"Recipient\":")) {
                        currentRecipient = line.substring(line.indexOf(":") + 2).replace("\"", "").replace(",", "");
                    } else if (line.contains("\"Message\":")) {
                        currentText = line.substring(line.indexOf(":") + 2).replace("\"", "").replace(",", "");
                        
                        String details = "Message ID: " + currentId + "\n" +
                                         "Message Hash: " + currentHash + "\n" +
                                         "Recipient: " + currentRecipient + "\n" +
                                         "Message: " + currentText;
                        storedMessageArray[storedMessageCount] = details;
                        storedMessageCount = storedMessageCount + 1;
                    }
                }
                fileScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Could not read JSON file.");
        }
    }

    //Display sender and recipient of all stored messages
    public static void displayStoredSenderAndRecipient() {
        loadStoredMessagesFromJson();
        if (storedMessageCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }
        System.out.println("\n--- SENDER AND RECIPIENT OF STORED MESSAGES ---");
        for (int i = 0; i < storedMessageCount; i++) {
            String details = storedMessageArray[i];
            String recipientVal = "";
            String[] lines = details.split("\n");
            for (int j = 0; j < lines.length; j++) {
                if (lines[j].startsWith("Recipient:")) {
                    recipientVal = lines[j].substring(10).trim();
                }
            }
            System.out.println("Sender: " + ChatApplication.userName + " | Recipient: " + recipientVal);
        }
    }

    //Display the longest stored message
    public static void displayLongestStoredMessage() {
        loadStoredMessagesFromJson();
        if (storedMessageCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }
        String longest = "";
        int maxLength = 0;
        for (int i = 0; i < storedMessageCount; i++) {
            String details = storedMessageArray[i];
            String textVal = "";
            String[] lines = details.split("\n");
            for (int j = 0; j < lines.length; j++) {
                if (lines[j].startsWith("Message:")) {
                    textVal = lines[j].substring(8).trim();
                }
            }
            if (textVal.length() > maxLength) {
                maxLength = textVal.length();
                longest = details;
            }
        }
        System.out.println("\n LONGEST STORED MESSAGE ");
        System.out.println(longest);
    }

    //Search for a message ID and display corresponding recipient and message
    public static void searchStoredMessageByID(String searchID) {
        loadStoredMessagesFromJson();
        boolean found = false;
        for (int i = 0; i < storedMessageCount; i++) {
            String details = storedMessageArray[i];
            if (details.contains("Message ID: " + searchID)) {
                found = true;
                String recipientVal = "";
                String textVal = "";
                String[] lines = details.split("\n");
                for (int j = 0; j < lines.length; j++) {
                    if (lines[j].startsWith("Recipient:")) {
                        recipientVal = lines[j].substring(10).trim();
                    }
                    if (lines[j].startsWith("Message:")) {
                        textVal = lines[j].substring(8).trim();
                    }
                }
                System.out.println("\n MESSAGE FOUND ");
                System.out.println("Recipient: " + recipientVal);
                System.out.println("Message: " + textVal);
            }
        }
        if (!found) {
            System.out.println("Message ID not found.");
        }
    }

    //Search for all messages stored for a particular recipient
    public static void searchStoredMessagesByRecipient(String searchRecipient) {
        loadStoredMessagesFromJson();
        boolean found = false;
        System.out.println("\n--- MESSAGES FOR RECIPIENT: " + searchRecipient + " ---");
        for (int i = 0; i < storedMessageCount; i++) {
            String details = storedMessageArray[i];
            if (details.contains("Recipient: " + searchRecipient)) {
                found = true;
                System.out.println(details);
                System.out.println("-------------------------");
            }
        }
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    //Delete a message using the message hash
    public static void deleteStoredMessageByHash(String searchHash) {
        loadStoredMessagesFromJson();
        boolean found = false;
        String[] tempArray = new String[100];
        int tempCount = 0;

        for (int i = 0; i < storedMessageCount; i++) {
            String details = storedMessageArray[i];
            if (details.contains("Message Hash: " + searchHash)) {
                found = true;
            } else {
                tempArray[tempCount] = details;
                tempCount = tempCount + 1;
            }
        }

        if (found) {
            storedMessageCount = tempCount;
            for (int i = 0; i < storedMessageCount; i++) {
                storedMessageArray[i] = tempArray[i];
            }
            // Rewrite the JSON file after deletion
            try {
                java.io.FileWriter writer = new java.io.FileWriter("messages.json", false);
                for (int i = 0; i < storedMessageCount; i++) {
                    String details = storedMessageArray[i];
                    String id = "";
                    String hash = "";
                    String rec = "";
                    String msg = "";
                    String[] lines = details.split("\n");
                    for (int j = 0; j < lines.length; j++) {
                        if (lines[j].startsWith("Message ID:")) id = lines[j].substring(11).trim();
                        if (lines[j].startsWith("Message Hash:")) hash = lines[j].substring(13).trim();
                        if (lines[j].startsWith("Recipient:")) rec = lines[j].substring(10).trim();
                        if (lines[j].startsWith("Message:")) msg = lines[j].substring(8).trim();
                    }
                    writer.write("{\n");
                    writer.write("  \"Message ID\": \"" + id + "\",\n");
                    writer.write("  \"Message Hash\": \"" + hash + "\",\n");
                    writer.write("  \"Recipient\": \"" + rec + "\",\n");
                    writer.write("  \"Message\": \"" + msg + "\"\n");
                    writer.write("},\n");
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("Error updating JSON file.");
            }
            System.out.println("Message deleted successfully using hash: " + searchHash);
        } else {
            System.out.println("Message hash not found.");
        }
    }

    //Display a report that lists full details of all stored messages
    public static void displayStoredMessagesReport() {
        loadStoredMessagesFromJson();
        if (storedMessageCount == 0) {
            System.out.println("No stored messages to display.");
            return;
        }
        System.out.println("\n ALL STORED MESSAGES ");
        for (int i = 0; i < storedMessageCount; i++) {
            System.out.println(storedMessageArray[i]);
            System.out.println("        ");
        }
    }
    
    // helps the method messageID(), receiver cellphone number, and the hash sign
    public String getMessageDetails() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + signHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + textMessage;
    }
    
    // Method that checks if the cell number is correct on the junit test  
    public static boolean cellNumber2check(String cellNumber) {
        if (cellNumber.startsWith("+") && cellNumber.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // Getter method to help counting the total message of message calling
    public int getMessageCount() {
        return messageCounter;
    }

    // Getter method to help the message to generate the random message id 
    public String getMessageID() {
       return messageID;
    }
    // Test helper method to simulate sending a message without Scanner input
    public void sendMessageForTest() {
        sentMessage = sentMessage + 1;
        savedMessages[numberOfMessages] = getMessageDetails();
        numberOfMessages = numberOfMessages + 1;
        
        sentMessageArray[sentMessageCount] = getMessageDetails();
        sentMessageCount = sentMessageCount + 1;
        
        messageIDArray[messageIDCount] = messageID;
        messageIDCount = messageIDCount + 1;
        
        messageHashArray[messageHashCount] = signHash;
        messageHashCount = messageHashCount + 1;
    }

    //Test helper method to simulate storing a message without Scanner input
    public void storeMessageForTest() {
        storeMessage();
        storedMessageArray[storedMessageCount] = getMessageDetails();
        storedMessageCount = storedMessageCount + 1;
        
        messageIDArray[messageIDCount] = messageID;
        messageIDCount = messageIDCount + 1;
        
        messageHashArray[messageHashCount] = signHash;
        messageHashCount = messageHashCount + 1;
    }
}
