package com.mycompany.chatapplication;

import java.util.Scanner;

public class ChatApplication
{

	//DECLERING STATIC VARIABLES
	static String userName;
	static String password;
	static String cellNumber;
	static String firstName;
	static String lastName;

	 public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		registerUser(scanner);

		boolean loginSuccess;
		loginSuccess= false;

		while (!loginSuccess)
		{
			System.out.println("\n");
			System.out.println("WELCOME TO CHAT APP, PLEASE LOGIN");

			System.out.print("Enter username: ");
			String inputUserName = scanner.nextLine();

			System.out.print("Enter password: ");
			String inputPassword = scanner.nextLine();

			loginSuccess = loginUser(inputUserName, inputPassword);

			System.out.println(returnLoginStatus(loginSuccess));

			if (loginSuccess)
			{
				System.out.println("Welcome back " + firstName + "," + lastName + " it is great to see you again.");
                                // After login, start the messaging class
                                MessagingMethod(scanner);
			}
			else
			{

			}
		}
	}
             // runs the messaging app after login
    public static void MessagingMethod(Scanner scanner)
    {
        System.out.println("\n");
        System.out.println("");
        System.out.println("Welcome to QuickChat");
        System.out.println("");
        
        // Ask the user how many message they will like to send
        System.out.print("How many messages would you like to send?: ");
        int maxMessages = scanner.nextInt();
        System.out.print("You will send: " + maxMessages + " Messages Only. ");
        
        
        
        int messageSent = 0;
        boolean loopControl = true;
        
        // This while loop will keep on runing until ke loopControl become false 
        while (loopControl == true)
        {
            System.out.println("\n");
            System.out.println("  QuickChat Menu  ");
            System.out.println("Option 1: Send Messages");
            System.out.println("Option 2: Show recently sent messages");
            System.out.println("Option 3: Quit");
            System.out.print("Choose an option: ");
             
            int Userchoice = scanner.nextInt();
            
            
            // Using if statements to display what the user choosed
            if (Userchoice == 1)
            {
                if (messageSent < maxMessages)
                {
                    sendMessage(scanner, messageSent + 1);
                    messageSent = messageSent + 1;
                    
                    // Check if all messages are done
                    if (messageSent == maxMessages)
                    {
                        System.out.println("\n");
                        System.out.println("You have completed all " + maxMessages + " messages!");
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                        
                    }
                }
                else
                {
                    System.out.println("You have already reached your limit of " + maxMessages + " messages.");
                }
            }
            else if (Userchoice == 2)
            {
                System.out.println("Coming Soon - This feature is still being developed.");
            }
            else if (Userchoice == 3)
            {
                System.out.println("Thank you for using QuickChat. Goodbye!");
                loopControl = false; 
            }
            else
            {
                System.out.println("Incorrect option. Please choose between 1, 2, and 3.");
            }
        }
    }
    
    // This sends one message
    public static void sendMessage(Scanner scanner, int messageNumber)
    {
        System.out.println("\n Sending Message Number " + messageNumber + " .");
        scanner.nextLine();
        // Get recipient cell number
        String recipient = "";
        boolean correctRecipientNumber = false;
        
        while (correctRecipientNumber == false)
        {
            System.out.print("Enter recipient's cell number: ");
             recipient  = scanner.nextLine();
            
            if (Message.checkRecipientCell(recipient).equals("correctFormate"))
            {
                System.out.println("Cell number successfully captured!");
                correctRecipientNumber = true;
            }
            else
            {
                System.out.println("Cell number is incorrectly formated or does not contain an international code. Please correct the number and try again,");
            }
        }
        
        // Get message text
        String messageText = "";
        boolean validMessage = false;
        
        while (validMessage == false)
        {
            System.out.print("Enter your message: ");
            messageText = scanner.nextLine();
            
            if (messageText.length() <= 250)
            {
                System.out.println("Message ready to send!");
                validMessage = true;
            }
            else
            {
                System.out.println("Message exceeds 250 characters by" +messageText.length() + " Please reduce the size");
                
            }
        }
        
        // Create the message object
        Message newMessage = new Message(messageNumber, recipient, messageText);
        
        // Show message preview
        System.out.println("\n Message Details View ");
        System.out.println(newMessage.getMessageDetails());
        System.out.println("\n");
        
        // Ask what to do with the message
        String result = newMessage.sendMessage();
        System.out.println(result);
        
      
    if (result.equals("Message sent successfully!")) 
    {
    System.out.println("\n SENT MESSAGE ");
    System.out.println(newMessage.getMessageDetails());
    System.out.println("");
   }
    }

	//REGISTERING THE USER
	public static void registerUser(Scanner scanner)
	{

		//METHOD REGISTERS THE USER
		while (true)
		{
			System.out.print("Enter username: ");
			userName = scanner.nextLine();

			if (checkUsername(userName))
			{
				System.out.println("Username successfully captured.");
				break;
			}
			else
			{
				System.out.println("Username is not correctly formatted; Please ensure that your username contains an underscore(_) and is no more than five Characters in length ");
			}
		}


		while (true)
		{
			System.out.print("Enter password: ");
			password = scanner.nextLine();

			if (checkPasswordComplexity(password)) {
				System.out.println("Password successfully captured.");
				break;
			} else {
				System.out.println("Password is not correctly formatted; Please ensure that the password contains at least eight Characters, a capital letter, a number, and a special character");
			}
		}


		while (true)
		{
			System.out.print("Enter cell phone number: ");
			cellNumber = scanner.nextLine();

			if (checkCellNumber(cellNumber)) {
				System.out.println("Cell number successfully added.");
				break;
			}
			else
			{
				System.out.println("Cell phone number incorently formatted or does not contain international code");
			}
		}

		// ASKS THE USER FOR HIS || HER FIRST AND LAST NAME
		System.out.print("Enter your first name: ");
		firstName = scanner.nextLine();

		System.out.print("Enter your last name: ");
		lastName = scanner.nextLine();
	}

	
        //METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED USERNAME
	public static boolean checkUsername(String storedUsername)
	{
		return storedUsername.length() <= 5 && storedUsername.contains("_");
	}
	//METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED PASSWORD
	public static boolean checkPasswordComplexity(String userPassword)
	{
		boolean hasUppercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (int i = 0; i < userPassword.length(); i++)
		{
			char c = userPassword.charAt(i);

			if (Character.isUpperCase(c)) hasUppercase = true;
			else if (Character.isDigit(c)) hasDigit = true;
			else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
		}

		return userPassword.length() >= 8 && hasUppercase && hasDigit && hasSpecial;
	}
	//METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED CELL PHONE NUMBER
	public static boolean checkCellNumber(String userCellNumber)
	{
		if (!userCellNumber.startsWith("+27"))
			return false;

		String onlyNumbers = userCellNumber.substring(3);

		if (onlyNumbers.length() != 9)
			return false;

		for (int i = 0; i < onlyNumbers.length(); i++)
		{
			if (!Character.isDigit(onlyNumbers.charAt(i)))
				return false;
		}

		return true;
	}

	
	
    /// @param storedUsername
    /// @param storedPassword
    /// @return  
        //METHOD THAT CHECKS IF THE REGISTRATION DETAILS MATCH THE LOGIN DETAILS
	public static boolean loginUser(String storedUsername, String storedPassword)
	{
		return storedUsername.equals(userName) && storedPassword.equals(password);
	}
	//METHOD THAT DISPLAYS THE FINAL RESULTS OF LOGIN

	public static String returnLoginStatus(boolean logdin)
	{
		if (logdin )
		{
			return "A successful login";
		}
		else
		{
			return "A failed login";
		}


	}     
}
