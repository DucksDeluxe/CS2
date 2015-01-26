import java.io.Console;
import java.io.IOException;


///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////



public class CS2ProgrammingWeek1 
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

	// Directions: Return the number of even ints in the given 
	//   array. Note: the % "mod" operator computes the remainder, 
	//   e.g. 5 % 2 is 1. 

	// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
	// CountEvenNumbersInArray({2, 2, 0}) → 3
	// CountEvenNumbersInArray({1, 3, 5}) → 0
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		int with the number of even numbers in NumberList
	 */
	static int CountEvenNumbersInArray(int[] NumberList) 
	{
		
		int nCount = 0;	// var to store count
		int nModResult = 0;	// var to store mod result
		
		// loop through the entire array
		for (int i = 0; i < NumberList.length; i++)
		{
			// get the modulus of the value at index i
			nModResult = NumberList[i] % 2;
			
			// if modulus is 0, then value i is even.
			if (nModResult == 0)
			{
				// increment our number of evens
				nCount++;
			}
		}
		// return count of even numbers
		return nCount;
	}

	// Given an array of ints, return true if the array contains no 
	//   1's and no 3's.

	// LookForLucky13({0, 2, 4}) → true
	// LookForLucky13({1, 2, 3}) → false
	// LookForLucky13({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns false if there is a 1 or 3 in the list.
	 * 		returns true if there are no 1s or 3s in the list.
	 */
	static boolean LookForLucky13(int[] NumberList) 
	{
		
		// loop through array
		for(int i = 0; i < NumberList.length; i++)
		{
			// if the value of the currently indexed value is a 1 or 3
			if (NumberList[i] == 1 || NumberList[i] == 3)
			{
				// 1 or 3 found - return false
				return false;
			}
		}
		// 1 or 3 not found - return true
		return true;
	}	

	// Given arrays NumberList1 and NumberList2 of the same length, 
	//   for every element in NumberList1, consider the 
	//   corresponding element in NumberList2 (at the same index). 
	//   Return the count of the number of times that the two 
	//   elements differ by 2 or less, but are not equal. 

	// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
	// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
	// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
	
	static int MatchUpLists(int[] NumberList1, int[] NumberList2) 
	{
		// variable to store count
		int nCount = 0;
		
		// loop through arrays
		for (int i = 0; i < NumberList1.length; i++)
		{
			// if the difference is less than or equal to 2
			if (Math.abs(NumberList1[i] - NumberList2[i]) <= 2)
			{
				// if the numbers are equal
				if (NumberList1[i] == NumberList2[i])
				{
					// don't count
					continue;
				}
				// increment count
				nCount++;
			}
		}
		// return final count
		return nCount;
	}	

	// Given an array of ints, return true if the array 
	//   contains either 3 even or 3 odd values all next 
	//   to each other. 

	// ModThreeNumbers({2, 1, 3, 5}) → true
	// ModThreeNumbers({2, 1, 2, 5}) → false
	// ModThreeNumbers({2, 4, 2, 5}) → true
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		return true if there are three consecutive evens
	 * 			or three consecutive odds
	 * 
	 * 		otherwise returns false
	 */
	public static boolean ModThreeNumbers(int[] NumberList) 
	{
		// variable to store count of consecutive odds or evens
		int nCount = 0;
		// flags for previous and current numbers shows false if even, true if odd
		boolean bPrevFlag = false;
		boolean bCurrFlag = false;
		
		// loop through array
		for (int i = 0; i < NumberList.length; i++)
		{			
			// if current number is even
			if (NumberList[i] % 2 == 0)
			{
				// set matching flag
				bCurrFlag = false;
			}
			// otherwise, current number is odd
			else
			{
				// set matching flag
				bCurrFlag = true;
			}
			
			// if the current and previous are even
			if (bCurrFlag == false && bPrevFlag == false)
			{
				// increment count
				nCount++;
			}
			// if the current and previous are odd
			else if (bCurrFlag == true && bPrevFlag == true)
			{
				// increment odd count
				nCount++;
			}
			// otherwise, the flags don't match
			else
			{
				// this is the first consecutive of its type
				nCount = 1;
				// update the previous value's flag
				bPrevFlag = bCurrFlag;
			}
			
			// are we there yet?
			if (nCount == 3)
				// we found 3 in a row!
				return true;
		}
		
		// we didn't find 3 in a row
		return false;
	}

	// Return the "centered" average of an array of ints, 
	//   which we'll say is the mean average of the values, 
	//   except ignoring the largest and smallest values in 
	//   the array. If there are multiple copies of the 
	//   smallest value, ignore just one copy, and likewise 
	//   for the largest value. Use int division to produce 
	//   the final average. You may assume that the array is 
	//   length 3 or more. 

	// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
	// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
	// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		Average of the list of numbers without the
	 * 		first of the lowest numbers and the last of the
	 * 		highest numbers.
	 */
	static int FindCenteredAverage(int[] NumberList) 
	{
		int nLowIndex = 0;
		int nHighIndex = 0;
		int nSum = 0;
		int nResult = 0;
		
		// loop through NumberList array to search for outer values
		for (int i = 0; i < NumberList.length; i++)
		{
			// is the current number lower than our stored number?
			if(NumberList[nLowIndex] > NumberList[i])
				// update with the index of new lowest number
				nLowIndex = i;
			// is the current number higher than our stored number?
			if(NumberList[nHighIndex] < NumberList[i])
				// update with the index of new highest number
				nHighIndex = i;
		}
		
		// loop through NumberList to copy and remove outer values
		for (int i = 0; i < NumberList.length; i++)
		{
			// if index is not at an outer value
			if (i == nLowIndex || i == nHighIndex)
				// skip it
				continue;
			// otherwise
			else
				// include it in the sum
				nSum += NumberList[i];
		}
		// compute the average
		nResult = nSum / (NumberList.length - 2);	
		return nResult;
	}
	
	// Given an array of ints, return true if every 2 that 
	//   appears in the array is next to another 2. 

	// LookForTwoTwo({4, 2, 2, 3}) → true
	// LookForTwoTwo({2, 2, 4}) → true
	// LookForTwoTwo({2, 2, 4, 2}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		true if every 2 is adjacent to another 2
	 * 		otherwise false
	 */
	static boolean LookForTwoTwo(int[] NumberList) 
	{
		// loop through array
		for (int i = 0; i < NumberList.length; i++)
		{
			// is the current number a 2?
			if (NumberList[i] == 2)
			{
				// is the following number inbounds?
				if(i + 1 < NumberList.length)
				{
					// is that number a 2?
					if (NumberList[i+1] == 2)
					{
						// advance
						i++;
						continue;
					}
					// otherwise
					else
						// there's a lone 2
						return false;
				}
				// Then that makes a lone 2
				else
				{
					// return as such
					return false;
				}
			}
		}
		// return true if all 2s come in pairs or if no 2s are found
		return true;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args) throws IOException
	{
		boolean bResult = false;
		int nResult = 0;		
		
		//////////////////////////////////////////////////
		// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
		int[] nArray1 = {2,1,2,3,4};
		nResult = CountEvenNumbersInArray(nArray1);
		assert nResult == 3;
		
		// CountEvenNumbersInArray({2, 2, 0}) → 3
		int[] nArray2 = {2,2,0};
		nResult = CountEvenNumbersInArray(nArray2);
		assert nResult == 3;
		
		// CountEvenNumbersInArray({1, 3, 5}) → 0
		int[] nArray3 = {1,3,5};
		nResult = CountEvenNumbersInArray(nArray3);
		assert (nResult == 0);
		////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////
		// LookForLucky13({0, 2, 4}) → true
		int[] nArray4 = {0,2,4};
		bResult = LookForLucky13(nArray4);
		assert bResult == true;
		
		// LookForLucky13({1, 2, 3}) → false
		int[] nArray5 = {1,2,3};
		bResult = LookForLucky13(nArray5);
		assert bResult == false;
		
		// LookForLucky13({1, 2, 4}) → false
		int[] nArray6 = {1,2,4};
		bResult = LookForLucky13(nArray6);
		assert bResult == false;
		////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////
		// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
		int[] nArray7 = {1,2,3};
		int[] nArray8 = {2,3,10};
		nResult = MatchUpLists(nArray7, nArray8);
		assert nResult == 2;
		
		// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
		int[] nArray9 = {1,2,3};
		int[] nArray10 = {2,3,5};
		nResult = MatchUpLists(nArray9, nArray10);
		assert nResult == 3;

		// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
		int[] nArray11 = {1,2,3};
		int[] nArray12 = {2,3,3};
		nResult = MatchUpLists(nArray11, nArray12);
		assert nResult == 2;
		////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////
		// ModThreeNumbers({2, 1, 3, 5}) → true
		int[] nArray13 = {2,1,3,5};
		bResult = ModThreeNumbers(nArray13);
		assert bResult == true;
		
		// ModThreeNumbers({2, 1, 2, 5}) → false
		int[] nArray14 = {2,1,2,5};
		bResult = ModThreeNumbers(nArray14);
		assert bResult == false;

		// ModThreeNumbers({2, 4, 2, 5}) → true
		int[] nArray15 = {2,4,2,5};
		bResult = ModThreeNumbers(nArray15);
		assert bResult == true;
		///////////////////////////////////////////////////
		
		///////////////////////////////////////////////////
		// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
		int[] nArray16 = {1,2,3,4,100};
		nResult = FindCenteredAverage(nArray16);
		assert nResult == 3;
		
		// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
		int[] nArray17 = {1,1,5,5,10,8,7};
		nResult = FindCenteredAverage(nArray17);
		assert nResult == 5;
		
		// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
		int[] nArray18 = {-10,-4,-2,-4,-2,0};
		nResult = FindCenteredAverage(nArray18);
		assert nResult == -3;
		
		///////////////////////////////////////////////////

		///////////////////////////////////////////////////
		// LookForTwoTwo({4, 2, 2, 3}) → true
		int[] nArray19 = {4,2,2,3};
		bResult = LookForTwoTwo(nArray19);
		assert bResult == true;
		
		// LookForTwoTwo({2, 2, 4}) → true
		int[] nArray20 = {2,2,2};
		bResult = LookForTwoTwo(nArray20);
		assert bResult == true;
		
		// LookForTwoTwo({2, 2, 4, 2}) → false
		int[] nArray21 = {2,2,4,2};
		bResult = LookForTwoTwo(nArray21);
		assert bResult == false;		
		///////////////////////////////////////////////////
	}
}