import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
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
		return("VanWinkle,Justin,J3338546");
	}
	
	//	Problem #1
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
		
 		int nCount = 0;
		String strLower = str.toLowerCase();
		
		//check for null string
		if( str.isEmpty() )
			return nCount;
		
		//iterate through the string
		for( int i=0; i<strLower.length(); i++)
		{
			// if this letter is a "y" or "z"
			if( ( strLower.charAt(i) == 'y' || strLower.charAt(i) == 'z' ) )
			{
				// if this isn't the last position
				if ( i+1 < strLower.length() )
				{
					// if a letter does not follow this y or z
					if( !Character.isLetter( strLower.charAt( i+1 ) ) )
					{
						// include it in the count
						++nCount;
					}				
				}
				// this y or z is in the last position
				else
					++nCount;
			}
		}
		return nCount;
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		int nLocation;
		
		// case insensitivity
		String strRem = remove.toLowerCase();
		String strLower = base.toLowerCase();
		String strBase = base;
		String strReturn = "";
		
		
		// check for empty base
		if( base.isEmpty() )
			return base;
		// check for empty remove
		if( remove.isEmpty() )
			return base;
		
		while(true)
		{
			// find first occurrence of remove string
			nLocation = strLower.indexOf(strRem);
			
			// if there is an occurrence not at index 0
			if( nLocation > 0 )
			{
				// grab everything before it
				strReturn += strBase.substring( 0, nLocation );
				// remove from the base
				strBase = strBase.substring(nLocation);
			}
			// if there is an occurrence at index 0
			else if ( nLocation == 0 )
			{
				// remove that occurrence
				strBase = strBase.substring( strRem.length() );
			}
			// solution found
			else
			{
				// append the rest to the return string
				strReturn += strBase;
				return strReturn;
			}
			
			// set new case insensitive string for searching
			strLower = strBase.toLowerCase();
		}
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true
	
	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
		int nIsCount = 0;
		int nNotCount = 0;
		int nIsIndex = 0;
		int nNotIndex = 0;
		
		// loop until there are no more occurrences
		while ( str.indexOf("is", nIsIndex) != -1 || str.indexOf("not", nNotIndex) != -1)
		{
			// if there is an occurrence of "is" after current location
			if( str.indexOf("is", nIsIndex)  != -1 )
			{
				// count it
				nIsCount++;
				// get that index and step past it
				nIsIndex = str.indexOf("is", nIsIndex) + 1;
			}
			
			// if there is an occurrence of "not" after current location
			if ( str.indexOf("not", nNotIndex) != -1 )
			{
				// count it
				nNotCount++;
				// get that index and step past it
				nNotIndex = str.indexOf("not", nNotIndex) + 1;
			}
		}
		// if the counts match
		if (nIsCount == nNotCount)
			return true;
		else 
			return false;
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
		
		//find each "g"
		// look left and right for another
		// if you find a lone "g", return false
		// else return true
		
		boolean bHappy = true;
		int nIndex = 0;
		
		// there is no g
		if( str.indexOf("g") == -1 )
			return false;
		// there is only one letter
		if( str.length() < 2 )
			return false;
		
		// while there is another "g"
		while( str.indexOf("g", nIndex) != -1)
		{
			// get the index
			nIndex = str.indexOf("g", nIndex);
			
			// if this is not the first letter
			if( nIndex != 0 )
			{
				if( str.charAt(nIndex - 1) == 'g' )
				{
					nIndex++;
					continue;
				}
			}
			// if this is not the last letter
			if( nIndex + 1 < str.length() )
			{
				// if that g has a g neighbor
				if( str.charAt(nIndex+1) == 'g' )
				{
					nIndex++;
					continue;
					
				}
				// if that g doesn't have a g neighbor
				else
					return false;
			}
			// we are looking at the last letter
			else if( str.charAt(nIndex - 1) == 'g')
			{
				nIndex++;
				continue;
			}
			// lone g
			else 
				return false;
					
		}
		
		// all are happy
		return bHappy;
		
		
	}
	
	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
		// at each letter, check if the next two match
		// if so, increment count.
		
		int nCount = 0;
		
		// less than 3 letters?
		if( str.length() < 3 )
			return nCount;
		
		// loop through
		for( int i=0; i<str.length() - 2; i++ )
		{
			// if both characters after this position match this positon
			if( str.charAt(i) == str.charAt(i+1) && str.charAt(i) == str.charAt(i+2) )
				// increment
				nCount++;
		}
		return nCount;
	}
	
	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
		int sum = 0;
		
		// loop through string
		for( int i=0; i<str.length(); i++)
		{
			// is this a char?
			if( !Character.isDigit( str.charAt(i) ) )
				continue;
			// is this a digit?
			if( Integer.parseInt( String.valueOf( str.charAt(i) ) ) > 0 
					&& Integer.parseInt( String.valueOf( str.charAt(i) ) ) < 9);
			{
				// add it to the sum
				sum += Integer.parseInt( String.valueOf( str.charAt(i) ) );
			}
		}
		return sum;
	}
	
	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	static String beginningAndEndOfString(String string) 
	{
		// we know the substring can't contain more than half of the original string
		// split string in half
		// compare string 1 to string 2
		// if no match, remove str1's last val and str2's first val
		// repeat until match found or end of strings
		
		String strLeft;
		String strRight;
		
		// less than 2 chars
		if( string.length() < 2 )
			return "";
		
		// if the string has even number of characters
		if( string.length() % 2 == 0 )
		{
			// split it right down the middle
			strLeft = string.substring(0, string.length() / 2);
			strRight = string.substring(string.length() / 2);
		}
		// if string has odd number
		else
		{
			// split left and right of middle char
			strLeft = string.substring(0, string.length() / 2);
			strRight = string.substring(string.length() / 2 + 1);
		}
		
		// loop the length of the left and right strings
		for( int i=0; i<strLeft.length(); i++)
		{
			// if the strings are equal
			if( strLeft.compareTo(strRight) == 0 )
				return strLeft;
			// if the strings are not equal
			else
			{
				// trim inner char from both strings
				strLeft = strLeft.substring(0, strLeft.length() - 1);
				strRight = strRight.substring(1);
			}
		}
		
		// no solution found
		return "";
		
		
	}
	
	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
		int nCount = 0;
		
		// less than two digits
		if ( string.length() < 2 )
			return string;
		
		// iterate over the string
		for( int i=0; i<string.length(); i++ )
		{
			// walk inward and compare
			if( string.charAt(i) == string.charAt( string.length() - 1 - i ) )
			{
				// track how many matches there are
				nCount++;
			}
			else
				break;
		}
		
		//return new substring
		return ( string.substring(0, nCount) );
		
	}
	
	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
		int nMax = 1;
		int nCurrent = 1;
		
		// string length of 0
		if( str.length() == 0 )
			return 0;
		
		
		// loop through string
		for( int i=0; i<str.length() - 1; i++)
		{
			// if adjacents match
			if( str.charAt(i) == str.charAt(i+1))
				// count them
				nCurrent++;
			// if no match
			else
			{
				// if current count is greater than max count
				if (nMax < nCurrent)
					// update max
					nMax = nCurrent;
				// reset current
				nCurrent = 1;
			}
		}
		
		// if current is greater than max
		if( nMax < nCurrent)
			// update max
			nMax = nCurrent;
		
		return nMax;
		
		
	}
	
	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
		// search string for number
		// find length of number
		// parse number
		// add to sum
		// repeat until end of string
		// return sum
		
		int nSum = 0;
		String strNum;
		
		// iterate over sting
		for( int i=0; i<str.length(); i++ )
		{
			// clear
			strNum = "";
			
			// if we found a digit
			if( Character.isDigit(str.charAt(i) ) )
			{
				// grab it
				strNum += String.valueOf(str.charAt(i));
				// how many follow?
				for( int j=i+1; j<str.length(); j++ )
				{
					// if this is a digit
					if( Character.isDigit(str.charAt(j) ) )
						// grab it
						strNum += String.valueOf(str.charAt(j));
					else
					{
						break;
					}
					// update i
					i = j;
				}
			}
			// add new val to sum
			if ( strNum.length() != 0 )
				nSum += Integer.parseInt(strNum);
		}
		return nSum;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{		
		assert wordEndYZ("fez day") == 2;
		assert wordEndYZ("day fez") == 2;
		assert wordEndYZ("day fyyyz") == 2;
		assert wordEndYZ("y z y1 z1 y69 z65 65z 69y 64yz x122") == 9;
		assert wordEndYZ("test") == 0;
		assert wordEndYZ("") == 0;
		
		assert removeFromBase("Hello there", "llo").equals("He there");
		assert removeFromBase("Hello there", "e").equals("Hllo thr");
		assert removeFromBase("Hello there", "x").equals("Hello there");
		assert removeFromBase("Hello there", "H").equals("ello tere");
		assert removeFromBase("xxx", "xx").equals("x");
		
		assert equalAppearance("This is not") == false;
		assert equalAppearance("This is notnot") == true;
		assert equalAppearance("noisxxnotyynotxisi") == true;
		assert equalAppearance("isnotisnotis") == false; 
		assert equalAppearance("") == true;
		
		assert gisHappy("xxggxx") == true;
		assert gisHappy("xxgxx") == false;
		assert gisHappy("xxggyygxx") == false;
		assert gisHappy("gxg") == false;
		assert gisHappy("ggx") == true;
		assert gisHappy("xgg") == true;
		assert gisHappy("g") == false;
		assert gisHappy("gg") == true;
		assert gisHappy("ggg") == true;
		assert gisHappy("") == false;
		
		assert numberOfTriples("abcXXXabc") == 1;
		assert numberOfTriples("xxxabyyyycd") == 3;
		assert numberOfTriples("a") == 0;
		assert numberOfTriples("xxyy") == 0;
		
		assert addUpDigits("aa1bc2d3") == 6;
		assert addUpDigits("aa11b33") == 8;
		assert addUpDigits("Chocolate") == 0;
		assert addUpDigits("") == 0;
		assert addUpDigits(" ") == 0;
		assert addUpDigits("tes4 5") == 9;
		assert addUpDigits("1 5") == 6;
		assert addUpDigits(" 1") == 1;
		
		assert beginningAndEndOfString("abXYab").equals("ab");
		assert beginningAndEndOfString("xx").equals("x");
		assert beginningAndEndOfString("xxx").equals("x");
		assert beginningAndEndOfString("hannah").equals("");
		
		assert beginningMirrorEnd("abXYZba").equals("ab");
		assert beginningMirrorEnd("abca").equals("a");
		assert beginningMirrorEnd("aba").equals("aba");
		assert beginningMirrorEnd("").equals("");
		assert beginningMirrorEnd("hanah").equals("hanah");
		assert beginningMirrorEnd("hannah").equals("hannah");
		assert beginningMirrorEnd("a").equals("a");
		assert beginningMirrorEnd("ab").equals("");
		assert beginningMirrorEnd("aufielkjf").equals("");
		
		assert largestBlock("hoopla") == 2;
		assert largestBlock("abbCCCddBBBxx") == 3;
		assert largestBlock("") == 0;
		assert largestBlock("a") == 1;
		assert largestBlock("abcdefghi") == 1;
		assert largestBlock("aa") == 2;
		assert largestBlock("aaa") == 3;
		
		assert addUpNumbers("abc123xyz") == 123;
		assert addUpNumbers("aa11b33") == 44;
		assert addUpNumbers("7 11") == 18;
		assert addUpNumbers("1") == 1;
	}
	
}