/*
 * Programmer: Jose A. Valdivia Rojas
 * Class: CMSC204.CRN21479
 * Instructor: Robert Alexander
 * Description: Create an application that will check for valid passwords.  The following rules must be followed to create a valid password.
 *	1.	At least 6 characters long
 *	2.	10 or more characters is a strong password, between 6 and 9 characters is a weak (but acceptable) password.
 *	3.	At least 1 numeric character
 *	4.	At least 1 uppercase alphabetic character
 *	5.	At least 1 lowercase alphabetic character
 *	6.	At least 1 special character
 *	7.	No more than 2 of the same character in a sequence
 *	Hello@123 – OK
 *	AAAbb@123 – not OK
 *	Aaabb@123 – OK

 * Due: 09/15/2021
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 *Print your Name here: Jose A. Valdivia Rojas
 */

/**
 * Date: 09/15/2021
 * This is the Utility Class PasswordCheckerUtility
 * It contains methods to check a password if valid, weak, or invalid
 * @author Jose A. Valdivia
 */


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	
	/**
	 * METHOD 1:
	 * This method will check the validity of one password and return true if the password is valid and throw one or more exceptions if invalid. 
	 * @param passwordString
	 * @return true if valid password, set up to return false if an invalid password, but throws an exception instead.
	 * @throws LengthException - thrown if length is less than 6 characters.
	 * @throws NoDigitException - thrown if no digit.
	 * @throws NoUpperAlphaException - thrown if no uppercase alphabetic.
	 * @throws NoLowerAlphaException - thrown if no lowercase alphabetic.
	 * @throws InvalidSequenceException - thrown if more than 2 of same character.
	 * @throws NoSpecialCharacterException - thrown if no special character.
	 */
	public static boolean isValidPassword(String passwordString) throws LengthException,
	NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, NoSpecialCharacterException{
		
		boolean digit = false;
		boolean upperAlphabetic = false;
		boolean lowerAlphabetic = false;
		boolean specialCharacter = false;
		boolean inValidSequence = false;
		
		//1.	At least 6 characters long
		if (passwordString.length() < 6)
			throw new LengthException(); // validating 1.
//		else {
//			System.out.println(passwordString + " length is " + passwordString.length());
//		}
		
		
		//2.	At least 1 numeric character
		//3.	At least 1 uppercase alphabetic character
		//4.	At least 1 lowercase alphabetic character
		for ( int i=0; i < passwordString.length(); i++) {
			
			// We use the wrapper class to check if it's digit, upper, lowerAlphabetic in the password
			if (Character.isDigit(passwordString.charAt(i)))
					digit = true;
			
			if (Character.isUpperCase(passwordString.charAt(i)))
				upperAlphabetic = true;
			
			if (Character.isLowerCase(passwordString.charAt(i)))
				lowerAlphabetic = true;	
		}
			
		//5.	At least 1 special character
		if (hasSpecialSymbol(passwordString))
			specialCharacter = true;

		//6.	No more than 2 of the same character in a sequence 
		if (digit && upperAlphabetic && lowerAlphabetic &&specialCharacter) {		

			for (int i=0; i < passwordString.length()-2; i++) {
				
				if ( passwordString.charAt(i) == passwordString.charAt(i+1) && 
						passwordString.charAt(i) == passwordString.charAt(i+2) )
					inValidSequence = true;
			}
		}
		
		// Validating 2 to 6
		if (digit == false)
			throw new NoDigitException();
		
		else if (lowerAlphabetic == false)
			throw new NoLowerAlphaException();
		
		else if (upperAlphabetic == false)
			throw new NoUpperAlphaException();
		
		else if (specialCharacter == false)
			throw new NoSpecialCharacterException();
		
		else if (inValidSequence == true)
			throw new InvalidSequenceException();
	
	
		return true;
	 
	}


	
	
	/**
	 * METHOD 2:
	 * This method will check if a password is weak and if so, throw an exception.  
	 * @param passwordString
	 * @return true if length of password is greater than or equal to 6 but less than or equal to 9
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String passwordString) { //throws WeakPasswordException{
		
		boolean weak = false;
		
		
		if  (passwordString.length() >=6 && passwordString.length() <=9) {
			weak = true;
			//throw new WeakPasswordException();
		}
		
			return weak;
			
	}
	
	

	/**
	 * METHOD 3:
	 * This method will check an ArrayList of passwords 
	 * and return an ArrayList with the status of any invalid passwords 
	 * (weak passwords are not considered invalid).  
	 * The ArrayList of invalid passwords will be of the following format:
	 * (password)(blank)(message of exception thrown)
	 * 
	 * @param passwordString arraylist of passwords to check for validity
	 * @return arraylist of invalid passwords. It will not return weak passwords.
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwordString){
		
		ArrayList<String> invalidPasswordsArray = new ArrayList<>(passwordString.size());
		String invalidPassword = "";
		
		// For loop to check each passwords in the ArrayList
		for (int i=0; i<passwordString.size(); i++)
		{
			// try-catch to catch exception of each invalid passwords
			try {
				// if password is invalid
				if ( isValidPassword (passwordString.get(i))  )
					System.out.println("\n");
			// catch password exception message and store it in a new ArrayList
			}catch (Exception e){
				invalidPassword = passwordString.get(i) + " ";
				invalidPassword += e.getMessage();
				invalidPasswordsArray.add(invalidPassword);
			}

		} 
		return invalidPasswordsArray;
	}
	
	
	
	/**
	 * Method to check if string has a symbol:
	 * check if the string only contains from 'a' to '9' but no special character
	 * if that is true, return !true = false, because it does not contain special character
	 * @param passwordString
	 * @return true if contains special character, otherwise return false
	 */
	private static boolean hasSpecialSymbol(String passwordString) {
	
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(passwordString);
		return (!matcher.matches());
	}
	
	
	
}
