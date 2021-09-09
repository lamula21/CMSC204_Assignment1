
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;
	
	
	@Before
	public void setUp() throws Exception {
		String[] pass = {"Contra#22", "casa",  "L@samigasD3lasAmigas", "AeaGilDeGil", "S0ymuyG0zu!", 
				"Immmen123@", "fr@z@d@DeTelet0n", "V@MOS21", "achicarlabomba1",	"unNicol@sCagu3", "Vegan1tosDeG1les"};
		
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(pass));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Contra#22"));
			PasswordCheckerUtility.isValidPassword("co#22");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("unNicol@sCagu3"));
			PasswordCheckerUtility.isValidPassword("unicol@scagu3");
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("unNicol@sCagu3"));
			PasswordCheckerUtility.isValidPassword("UNNICOL@SCAGU3");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password is weak
	 * This test should not throw anything because it's valid password
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("V@MOs21"));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("V@MOs21");
			assertTrue(weakPwd);
		}
//		catch(WeakPasswordException e) {
//			System.out.println(e.getMessage());
//			assertTrue("The password is OK but weak - it contains fewer than 10 characters.",true);
//		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Immen123@"));
			PasswordCheckerUtility.isValidPassword("Immmen123@");
			assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("S0ymuyG0zu!"));
			PasswordCheckerUtility.isValidPassword("SoymuyGozu!");
			assertTrue("Did not throw NoDigitException",false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("L@samigasD3lasAmigas"));
			PasswordCheckerUtility.isValidPassword("L@samigasD3lasAmigas");
			assertTrue("Did not throw a Exception",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception",false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "casa");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("6 characters long"));
		//assertEquals(scan.nextLine(), " The password must contain at least 6 characters long.");
		
		scan = new Scanner(results.get(1)); 
		assertEquals(scan.next(), "AeaGilDeGil");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("one digit"));
		//assertEquals(scan.nextLine(), " The password must contain at least one digit.");
	
		scan = new Scanner(results.get(2)); 
		assertEquals(scan.next(), "Immmen123@");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("more than two"));
		//assertEquals(scan.nextLine(), " The password cannot contain more than two of the same character in sequence.");
		
		scan = new Scanner(results.get(3)); 
		assertEquals(scan.next(), "V@MOS21");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("one lowercase"));
		//assertEquals(scan.nextLine(), " The password must contain at least one lowercase alphabetic character.");
		
		scan = new Scanner(results.get(4)); 
		assertEquals(scan.next(), "achicarlabomba1");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("one uppercase"));
		//assertEquals(scan.nextLine(), " The password must contain at least one uppercase alphabetic character.");
		
		scan = new Scanner(results.get(5)); 
		assertEquals(scan.next(), "Vegan1tosDeG1les");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special"));
		//assertEquals(scan.nextLine(), " The password must contain at least one special character.");
	}
	
}
