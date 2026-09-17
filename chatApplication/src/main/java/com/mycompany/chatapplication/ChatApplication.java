package com.mycompany.chatapplication;

import java.util.Scanner;

public class ChatApplication
{

	// Global variables storing account information for the registration
	static String accountUser;
	static String accountPass;
	static String contactNo;
	static String userGivenName;
	static String userSurname;

	 public static void main(String[] args)
	{
            Scanner input = new Scanner(System.in);
            registerUser(input);
            boolean correct;
            correct = false;
            //While correct is still false, it will loop this
            while (!correct)
            {
                System.out.println("WELCOME TO CHAT APP, PLEASE LOGIN");
                System.out.print("Enter username: ");
                String enteredUser = input.nextLine();
                System.out.print("Enter password: ");
                String enteredPass = input.nextLine();
                correct = loginUser(enteredUser, enteredPass);
                System.out.println(returnLoginStatus(correct));
                //Once the correct is true, meaning the user enterd the right information used in registering, it will reasign correct to true
                //The while loop will stop 
                if (correct)
                {
                    System.out.println("Welcome back " + userGivenName + "," + userSurname + " it is great to see you again.");
                }
                else
                {
                    System.out.println("Please  try again...");
                }
            }
	}

	// Handles the user registration workflow and validates each input that the user gives to the system
	public static void registerUser(Scanner scanner)
	{
            // Askes the user for his/ her name and surname
            System.out.print("Enter your first name: ");
            userGivenName = scanner.nextLine();

            System.out.print("Enter your last name: ");
            userSurname = scanner.nextLine();

            // Collects and validates the username until it matches required rules
            while (true)
		{
                    System.out.print("Enter username: ");
                    accountUser = scanner.nextLine();
                    //If User nsme that the user enterd is correctly allign to what we want 
                    if (checkUsername(accountUser))
			{
                            System.out.println("Username successfully captured.");
                            break;
			}
                        //Else if not it will display this text
			else
			{
                            System.out.println("Username is not correctly formatted; Please ensure that your username contains an underscore(_) and is no more than five Characters in length ");
			}
		}
            
            // Usks for a password until the required is fullfiled
            while (true)
            {
		System.out.print("Enter password: ");
		accountPass = scanner.nextLine();

		if (checkPasswordComplexity(accountPass)) 
                {
                    System.out.println("Password successfully captured.");
                    break;
		} 
                else 
                {
                    System.out.println("Password is not correctly formatted; Please ensure that the password contains at least eight Characters, a capital letter, a number, and a special character");
		}
            }
            // It uskd the user for the cell number and checks if its what we want
            while (true)
            {
		System.out.print("Enter cell phone number: ");
		contactNo = scanner.nextLine();
		if (checkCellNumber(contactNo)) 
                {
                    System.out.println("Cell number successfully added.");
                    //If the cell number is corrent it will break the loop 
                    break;
                }
		else
		{
                    //Else the loop will keep on asking for the cell number
                    System.out.println("Cell phone number incorently formatted or does not contain international code");
		}
            }
	}

	
	// Validates length constraints and specific character presence for usernames
	public static boolean checkUsername(String handleToVerify)
	{
            return handleToVerify.length() <= 5 && handleToVerify.contains("_");
	}

	// Verifies password strength by checking length, upper-case letters, digits, and symbols
	public static boolean checkPasswordComplexity(String secretPhrase)
	{
            boolean containsUpper = false;
            boolean containsNumber = false;
            boolean containsSymbol = false;

            for (int idx = 0; idx < secretPhrase.length(); idx++)
            {
		char currentSymbol = secretPhrase.charAt(idx);
		if (Character.isUpperCase(currentSymbol)) containsUpper = true;
		else if (Character.isDigit(currentSymbol)) containsNumber = true;
		else if (!Character.isLetterOrDigit(currentSymbol)) containsSymbol = true;
            }
            return secretPhrase.length() >= 8 && containsUpper && containsNumber && containsSymbol;
	}

	// Ensures phone number begins with the country code and has the exact digit count
	public static boolean checkCellNumber(String mobileDigits)
	{
            if (!mobileDigits.startsWith("+27"))
            return false;

            String localDigits = mobileDigits.substring(3);

            if (localDigits.length() != 9)
            {
		return false;
            }
            for (int pos = 0; pos < localDigits.length(); pos++)
            {
		if (!Character.isDigit(localDigits.charAt(pos)))
		return false;
            }

            return true;
	}

	// Verifies that input entered matches the registered information
	public static boolean loginUser(String typedUser, String typedPass)
	{
            return typedUser.equals(accountUser) && typedPass.equals(accountPass);
	}

	// Returns an informational message if the user is authorised to login or not 
	public static String returnLoginStatus(boolean isAuthorized)
	{
            if (isAuthorized)
            {
		return "A successful login";
            }
            else
            {
		return "A failed login";
            }
	}   
}