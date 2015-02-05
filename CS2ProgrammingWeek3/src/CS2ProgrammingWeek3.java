import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek3 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "VanWinkle,Justin,J3338546");
	}
	
	//	Problem #1
	//	Given a string and a non-empty substring sub, compute recursively if at 
	//	least n copies of sub appear in the string somewhere, possibly with 
	//	overlapping. N will be non-negative.

	//	subCopies("catcowcat", "cat", 2) ? true
	//	subCopies("catcowcat", "cow", 2) ? false
	//	subCopies("catcowcat", "cow", 1) ? true
	
		//jcv: example of overlapping sub: ATATA is string ATA is sub.  There are two occurrences here.
		//jcv: recursively call at sub+1 after you found the first sub.
	
	/**
	 * 
	 * @param str, sub, n
	 * 		String str contains the original string to be tested against
	 * 		String sub contains the string that is used to test against str
	 * 		int n specifies how many copies of sub to check for
	 * 
	 * @return
	 * 		returns true if there are n copies of sub in str
	 * 		returns false if there are not n copies of sub in str
	 */
	static boolean subCopies(String str, String sub, int n) 
	{
		int nSubIndex = -1;
		
		/***PROTECTION***/
		// there can't be negative instances
		if ( n < 0 )
			return false;
		// get empty string but checking for other
		else if (str == "" && sub != "")
			return false;
		// what if we get and check for an empty string?
		else if (str == "" && sub == "" && n == 1)
			return true;
		/***END PROTECTION***/
		
		// locate the first sub in str
		nSubIndex = str.indexOf(sub);
		
		/***BASE CASES*///
		// Base Case #1
		// n != 0 && no sub was found
		if (nSubIndex == -1 && n != 0)
		{
			return false;
		}
		
		// Base Case #2
		// n = 0 && a sub was found
		// this would mean we have at least n+1 subs
		else if (n == 0 && nSubIndex != -1)
		{
			return false;
		}
		
		// Base Case #3
		// n = 0 && no sub found
		else if (n == 0 && nSubIndex == -1)
		{
			return true;
		}
		/***END BASE CASES***/
		
		// if there should be another sub and it has been located
		if (n != 0 && nSubIndex != -1)
		{
			// create a new string to pass down starting at nSubIndex + 1
			String newStr = str.substring(nSubIndex + 1);
			// make a new call with the new string and one less sub
			subCopies(newStr, sub, n-1);
		}
		// feeding the eclipse monster
		return true;
	}

	//	Problem #2
	//	Given a non-negative int n, return the sum of its digits recursively 
	//	(no loops). Note that mod (%) by 10 yields the rightmost 
	//	digit (126 % 10 is 6), while divide (/) by 10 removes the 
	//	rightmost digit (126 / 10 is 12).

	//	sumDigitsInNumber(126) ? 9
	//	sumDigitsInNumber(49) ? 13
	//	sumDigitsInNumber(12) ? 3
	
	/**
	 * 
	 * @param n
	 * 		int n contains integer to deal with.
	 * 
	 * @return integer
	 * 		integer that is the sum of each digit in int n.
	 */
	static int sumDigitsInNumber(int n) 
	{
		int nDigit;
		int nSum = 0;
		
		// Base Case
		// n has nothing left
		if ( n == 0 )
		{
			return 0;
		}
		
		// get lowest digit
		nDigit = n % 10;
		
		// strip n of lowest digit
		n /= 10;
		
		// step down with new n and grab that value
		nSum = sumDigitsInNumber(n);
		
		// add the stripped digit to the sum
		nSum += nDigit;
		
		// pass the sum back up
		return nSum;
	}	

	//	Problem #3
	//	Given base and n that are both 1 or more, compute recursively (no loops) 
	//	the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

	//	exponential(3, 1) ? 3
	//	exponential(3, 2) ? 9
	//	exponential(3, 3) ? 27
	
	/**
	 * 
	 * @param base, n
	 * 		int base containing the base of the term
	 * 		int n containing the exponent of the term
	 * 
	 * @return integer
	 * 		integer that corresponds with equating base to the n power
	 */
	static int exponential(int base, int n) 
	{
		// Base Case
		// n = 1
		if ( n == 1 )
		{
			return base;
		}
		
		// return product of the base and the next step down
		return base * exponential(base, n-1);
		
		
	}	

	//	Problem #4
	//	Given a string, compute recursively (no loops) a new string 
	//	where all the lowercase 'x' chars have been changed to 'y' chars. 

	//	changeXtoY("codex") ? "codey"
	//	changeXtoY("xxhixx") ? "yyhiyy"
	//	changeXtoY("xhixhix") ? "yhiyhiy"
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of chars to deal with
	 * 
	 * @return String
	 * 		String of characters where the lowercase x's have been changed to y's
	 */
	public static String changeXtoY(String str) 
	{		
		// if no x in string, we are done
		if (str.indexOf("x") == -1)
		{
			return str;
		}
		// replace the first x with a y
		String strNew = str.replaceFirst("x", "y");
		
		// step down and pass the result up
		return changeXtoY(strNew);		
	}
	
	//	Problem #5
	//	Given an array of ints, compute recursively if the array 
	//	contains a 6. We'll use the convention of considering only 
	//	the part of the array that begins at the given index. 
	//	In this way, a recursive call can pass index+1 to move down 
	//	the array. The initial call will pass in index as 0. 

	//	find6({1, 6, 4}, 0) ? true
	//	find6({1, 4}, 0) ? false
	//	find6({6}, 0) ? true	
	
	/**
	 * 
	 * @param nums, index
	 * 		int[] list containing the original array of numbers
	 * 		int containing the position to start in nums
	 * 
	 * @return boolean
	 * 		returns true if a 6 is found in the array
	 * 		returns false if no 6 is found in the array
	 */
	static boolean find6(int[] nums, int index) 
	{
		// Base Case #1
		// there was no six
		if (index == nums.length)
			return false;
		
		// Base Case #2
		// we found a 6!
		if( nums[index] == 6)
			return true;
		
		// this one isn't a 6
		if ( nums[index] != 6)
			// step down and return findings
			return find6(nums, index+1);
		
		// feed the eclipse monster!
		return true;
	}
	
	//	Problem #6
	//	Given a string, compute recursively a new string where all 
	//	the adjacent chars are now separated by a "*".   

	//	insertAsterisk("hello") ? "h*e*l*l*o"
	//	insertAsterisk("abc") ? "a*b*c"
	//	insertAsterisk("ab") ? "a*b"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return AbridgedList
	 * 		String with an asterisk between each char
	 */
	static String insertAsterisk(String str) 
	{
	//jcv: according to TA Michael G.: put asterisk between whitespaces, too.
				
		// check for null String
		if (str.length() < 1)
			return str;
		
		// grab the first char and give it an asterisk
		String strReturn = str.substring(0, 0) + "*";
		
		// do we need to make another call?
		if (str.length() > 2)
		{
		// send the rest of the string down a step and concat the result
		strReturn.concat(insertAsterisk(str.substring(1)));
		}
		// no more calls to make
		else
		{
			return str.substring(1);
		}
		// send back the findings
		return strReturn;
	}
	
	//	Problem #7
	//	We'll say that a "pair" in a string is two instances of 
	//	a char separated by a char. So "AxA" the A's make a pair. 
	//	Pair's can overlap, so "AxAxA" contains 3 pairs -- 2 for 
	//	A and 1 for x. Recursively compute the number of 
	//	pairs in the given string.  

	//	numberPairs("axa") ? 1
	//	numberPairs("axax") ? 2
	//	numberPairs("axbx") ? 1
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars provided
	 * 
	 * @return 
	 * 		int with the number of pairs as defined above
	 */
	static int numberPairs(String str) 
	{
	//jcv: must be matching case, length 3 strings for pairs
		
		// Base case
		// not enough letters left to search
		if (str.length() < 3)
			return 0;
		
		// Do the first three chars contain a match?
		if (str.charAt(0) == str.charAt(2))
			// remove first char, step down, and return result + everything below
			return 1 + numberPairs(str.substring(1));
		
		// no match found here
		// remove first char, step down, and return result of everything below
		return numberPairs(str.substring(1));
	}
	
	//	Problem #8
	//	Given a string, return recursively a "cleaned" string where 
	//	adjacent chars that are the same have been reduced 
	//	to a single char. So "yyzzza" yields "yza".  

	//	reduceChars("yyzzza") ? "yza"
	//	reduceChars("abbbcdd") ? "abcd"
	//	reduceChars("Hello") ? "Helo"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		String with all repeated, adjacent chars are removed
	 */
	static String reduceChars(String str) 
	{
	//jcv: check out startswith and endswith
		
		String strReturn;
		char cStrip;
		
		// Base Case
		// no comparisons left
		if ( str.length() < 2 )
			return str;
		
		// do the first two match?
		if (str.charAt(0) == str.charAt(1))
		{
			// peel off the first char
			// check at the next char and pass the result up
			return reduceChars(str.substring(1));
		}
		
		// there was no match
		// grab the first char
		cStrip = str.charAt(0);
		
		// step down to grab the next char and return the result, building the string on the way up
		return cStrip + reduceChars(str.substring(1));
	}
	
	//	Problem #9
	//	Given a string, return true if it is a nesting of zero or more 
	//	pairs of parenthesis, like "(())" or "((()))". Suggestion: 
	//	check the first and last chars, and then recur on what's inside them.  

	//	nestedParens("(())") ? true
	//	nestedParens("((()))") ? true
	//	nestedParens("(((x))") ? false
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		returns true if there are zero or more pairs of parenthesis
	 * 		returns false if there are not zero or more pairs of parenthesis
	 */
	
	
	//TODO attempt this!
	//static boolean nestedParens(String str) 
	//{
	//}
	
	
	
	//	Problem #10

	//	Given a string and a non-empty substring sub, compute 
	//	recursively the largest substring which starts and 
	//	ends with sub and return its length.  

	//	subStrSub("catcowcat", "cat") ? 9
	//	subStrSub("catcowcat", "cow") ? 3
	//	subStrSub("cccatcowcatxx", "cat") ? 9
	
	/**
	 * 
	 * @param str, sub
	 * 		String containing the original chars to be tested against
	 * 		String containing the original chars to test with
	 * 
	 * @return 
	 * 		integer containing the largest number of chars in string 
	 * 		that start and end with sub
	 */
	static int subStrSub(String str, String sub) 
	{
	//jcv: start left and right.  left, look for sub - that's the start.  right, look for sub - that's the end.  
		
		// if we aren't starting with sub
		if (!str.startsWith(sub))
		{
			// cut out everything in front of sub and call a step down with it
			// then return the results
			System.out.println(str.substring(str.indexOf(sub)));
			return subStrSub(str.substring(str.indexOf(sub)), sub);
		}
		
		// if we don't end with sub
		if (!str.endsWith(sub))
		{
			// cut out everything beyond sub and call a step down with it
			// then return the results
			System.out.println(str.substring(0, str.lastIndexOf(sub) + sub.length()));
			return subStrSub(str.substring(0, str.lastIndexOf(sub) + sub.length()), sub);
		}
		
		// we cut out the fluff - count and return!
		return str.length();
			
		
//		// safety
//		if (str.isEmpty() || sub.isEmpty())
//		{
//			return 0;
//		}
//	
//		// Base Case
//		// we found the largest substring
//		if ( str.startsWith(sub) && str.endsWith(sub) )
//		{
//			return str.length();
//		}
//		
//		// we haven't found anything
//		else if ( !(str.startsWith(sub) &&  str.endsWith(sub)))
//		{
//			// it just isn't here...
//			if (str.length() == 1)
//			{
//				return 0;
//			}
//			// make sure we don't pass our outer boundaries
//			if (str.length() >=2)
//			{
//				// call step down with a new substring
//				System.out.println(str.lastIndexOf(sub));
//				return subStrSub(str.substring(str.indexOf(sub), str.lastIndexOf(sub) + sub.length() - 2), sub);
//			}
//		}
//		
//		// we have the end but not the start
//		else if ( !str.startsWith(sub) && str.endsWith(sub) )
//		{
//			// step in with the first char stripped off
//			return subStrSub(str.substring(1), sub);
//		}
//		
//		// we have the start but not the end
//		else if ( str.startsWith(sub) && !str.endsWith(sub) && str.length() >= 2)
//		{
//			// step in with the last char stripped off
//			return subStrSub(str.substring(0, str.length()-2), sub);
//		}
//		// something went MAJORLY wrong!
//		return 0;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		
		assert subCopies("catcowcat", "cat", 2) == true;
		//assert subCopies("catcowcat", "cow", 2) == false;
		assert subCopies("catcowcat", "cow", 1) == true;
		
		assert sumDigitsInNumber(126) == 9;
		assert	sumDigitsInNumber(49) == 13;
		assert	sumDigitsInNumber(12) == 3;
		
		assert	exponential(3, 1) == 3;
		assert	exponential(3, 2) == 9;
		assert	exponential(3, 3) == 27;
	
		System.out.println(changeXtoY("codex"));
		//assert changeXtoY("codex") == "codey";
		System.out.println(changeXtoY("xxhixx"));
		//assert changeXtoY("xxhixx") == "yyhiyy";
		System.out.println(changeXtoY("xhixhix"));
		//assert changeXtoY("xhixhix") == "yhiyhiy";
		
		int[] array1 = {1, 6, 4};
		int[] array2 = {1, 4};
		int[] array3 = {6};
		assert find6(array1, 0) == true;
		assert find6(array2, 0) == false;
		assert find6(array3, 0) == true;	
		
		System.out.println(insertAsterisk("hello"));
		//assert	insertAsterisk("hello") == "h*e*l*l*o";
		System.out.println(insertAsterisk("abc"));
		//assert	insertAsterisk("abc") == "a*b*c";
		System.out.println(insertAsterisk("ab"));
		//assert	insertAsterisk("ab") == "a*b";
		
		assert	numberPairs("axa") == 1;
		assert	numberPairs("axax") == 2;
		assert	numberPairs("axbx") == 1;
		
		System.out.println(reduceChars("yyzzza"));
		//assert	reduceChars("yyzzza") == "yza";
		System.out.println(reduceChars("abbbcdd"));
		//assert	reduceChars("abbbcdd") == "abcd";
		System.out.println(reduceChars("Hello"));
		//assert	reduceChars("Hello") == "Helo";
		
		//	nestedParens("(())") == true;
		//	nestedParens("((()))") == true;
		//	nestedParens("(((x))") == false;
		
		assert	subStrSub("catcowcat", "cat") == 9;
		assert	subStrSub("catcowcat", "cow") == 3;
		assert	subStrSub("cccatcowcatxx", "cat") == 9;
	}
	
}