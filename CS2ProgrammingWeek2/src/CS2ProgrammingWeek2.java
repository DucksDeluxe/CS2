import java.util.Arrays;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek2 
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
	// Directions: Return true if the array contains, somewhere,
	// three increasing consecutive numbers like ....4, 5, 6,... or
	// 23, 24, 25.

	//	FindThreeIncreasingNumbers({1, 4, 5, 6, 2}) → true
	//	FindThreeIncreasingNumbers({1, 2, 3}) → true
	//	FindThreeIncreasingNumbers({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns true if there are three increasing consecutive numbers
	 * 		returns false if there are not three increasing consecutive numbers
	 */
	static boolean FindThreeIncreasingNumbers(int[] NumberList) 
	{
		// make sure we don't get dupped
		if ( NumberList.length < 3 )
			return false;
		
		// loop through array
		for(int i=0; i<NumberList.length; i++)
		{
			// are there two more values?
			if (i+2 < NumberList.length)
				// are the next two values increasing and consecutive?
				if (NumberList[i] == NumberList[i+1] - 1 && NumberList[i] == NumberList[i+2] - 2)
					// condition found
					return true;
		}
		// condition not found
		return false;
	}

	//	Problem #2
	//	For each multiple of 10 in the given array, change all the values 
	//	following it to be that multiple of 10, until encountering another 
	//	multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

	//	multiplesOfTen({2, 10, 3, 4, 20, 5}) → {2, 10, 10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 20, 2}) → {10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 9, 20}) → {10, 10, 10, 20}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list of the same numbers changed to multiples of
	 * 		ten as they are encountered.
	 */
	static int[] multiplesOfTen(int[] NumberList) 
	{
		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		int nMult = 0;
		
		// loop through array
		for (int i=0; i<NumberList.length; i++)
		{
			// if val i is mult of 10
			if (NumberList[i] % 10 == 0)
			{
				// store the multiple
				nMult = NumberList[i];
			}
			
			// if val i is not a mult of 10 && we have a mult to change to
			else if (NumberList[i] % 10 != 0 && nMult != 0)
			{
				// perform the change
				NumberList[i] = nMult;
			}
			
			// if val i is not a mult of 10 && we haven't seen a mult yet
			else
				continue;
		}
		return NumberList;
	}	

	
	//	Problem #3
	//	We'll say that an element in an array is "alone" if there are 
	//	values before and after it, and those values are different 
	//	from it. Return a version of the given array where every instance 
	//	of the given value which is alone is replaced by whichever 
	//	value to its left or right is larger.

	//	CheckForAloneNumbers({1, 2, 3}, 2) → {1, 3, 3}
	//	CheckForAloneNumbers({1, 2, 3, 2, 5, 2}, 2) → {1, 3, 3, 5, 5, 2}
	//	CheckForAloneNumbers({3, 4}, 3) → {3, 4}
	
	/**
	 * 
	 * @param NumberList, changingNumber
	 * 		int[] list containing some numbers.
	 * 		int value of the number that should change in the array.
	 * 
	 * @return NumberList
	 * 		int[] list of numbers where every occurrence of changingNumber
	 * 		has been replaced by the larger of its two neighbors.
	 */
	static int[] CheckForAloneNumbers(int[] NumberList, int changingNumber) 
	{
		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		// loop through array (jump the first and last number)
		for (int i=1; i<NumberList.length - 1; i++)
		{
			// if val i is our target val
			if (NumberList[i] == changingNumber)
			{
				// if val i is "alone"
				if (NumberList[i-1] != changingNumber || NumberList[i+1] != changingNumber)
				{
					// is the next value higher?
					if (NumberList[i-1] < NumberList[i+1])
					{
						// change to higher value
						NumberList[i] = NumberList[i+1];
					}
					// the previous value is higher or equal to the following
					else
					{
						// change to higher value
						NumberList[i] = NumberList[i-1];
					}
				}
			}
		}
		return NumberList;
	}	

	//	Problem #4
	//	Return a version of the given array where each zero value in 
	//	the array is replaced by the largest odd value to the right 
	//	of the zero in the array. If there is no odd value to the 
	//	right of the zero, leave the zero as a zero. 

	//	ReplaceZerosWithLargestOdd({0, 5, 0, 3}) → {5, 5, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 4, 0, 3}) → {3, 4, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 1, 0}) → {1, 1, 0}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list containing the numbers where the zeros have been
	 * 		replaced with the largest odd number to the right of them.
	 */
	public static int[] ReplaceZerosWithLargestOdd(int[] NumberList) 
	{
		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		int nMaxOddRight;
		
		// loop through array
		for (int i=0; i<NumberList.length; i++)
		{
			// if val i is a 0 and not the last value of the list
			if (NumberList[i] == 0 && i+1 != NumberList.length)
			{
				// assume 0 is the max
				nMaxOddRight = 0;
				// loop through the rest of the array
				for (int j=i; j<NumberList.length; j++)
				{
					// is val j is odd and greater than my current max?
					if (NumberList[j] % 2 != 0 && NumberList[j] > nMaxOddRight)
					{
						// update max odd value
						nMaxOddRight = NumberList[j];
					}
				}
				// update val i with max
				NumberList[i] = nMaxOddRight;
			}
		}
		return NumberList;
	}
	
	//	Problem #5
	//	Given start and end numbers, return a new array containing 
	//	the sequence of integers from start up to but not including end, 
	//	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number 
	//	will be greater or equal to the start number. 
	//	Note that a length-0 array is valid. 

	//	CreateIncreasingArray(5, 10) → {5, 6, 7, 8, 9}
	//	CreateIncreasingArray(11, 18) → {11, 12, 13, 14, 15, 16, 17}
	//	CreateIncreasingArray(1, 3) → {1, 2}	
	
	/**
	 * 
	 * @param start, end
	 * 		Two integers stating the start and end of the sequence.
	 * 
	 * @return NumberList
	 * 		int [] containg numbers ranging from start to end
	 * 		in order from least to greatest.
	 */
	static int[] CreateIncreasingArray(int start, int end) 
	{
		// make sure we don't get dupped
		if ( end <= start  || start + 1 == end)
		{
			int[] emptyArray = {};
			return emptyArray;
		}
		
		int nLength = end-start;
		java.util.ArrayList<Integer> nArrayList = new java.util.ArrayList<Integer>();
		int val = start;
		
		// loop through array
		for (int i=0; i<nLength; i++)
		{
			// update array list with next val
			nArrayList.add(val);
			// increment val
			val++;
		}
		
		// create an array to hold values
		int[] nArray = new int[nArrayList.size()];
		// loop through arraylist
		for (int i=0; i < nArrayList.size(); i++)
			// copy over to array
			nArray[i] = nArrayList.get(i).intValue();
				
		return nArray;
	}
	
	//	Problem #6
	//	Given a non-empty array of ints, return a new array containing 
	//	the elements from the original array that come before the 
	//	first 4 in the original array. The original array will contain 
	//	at least one 4. Note that it is valid in java to create 
	//	an array of length 0.  

	//	CopyNumbersBeforeFour({1, 2, 4, 1}) → {1, 2}
	//	CopyNumbersBeforeFour({3, 1, 4}) → {3, 1}
	//	CopyNumbersBeforeFour({1, 4, 4}) → {1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return AbridgedList
	 * 		int[] list containing all the numbers that appeared
	 * 		before the first 4 in the array.
	 */
	static int[] CopyNumbersBeforeFour(int[] NumberList) 
	{
		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		// since we don't know how long our new array needs to be, let's use this
		java.util.ArrayList<Integer> nArrayList = new java.util.ArrayList<Integer>();
		// loop through array
		for (int i=0; i<NumberList.length; i++)
		{
			// if val is 4, get out
			if (NumberList[i] == 4)
			{
				break;
			}
			// if val is not 4
			else
			{
				// update ReturnList
				nArrayList.add(NumberList[i]);
			}
		}
		
		// create an array to hold values
		int[] nArray = new int[nArrayList.size()];
		// loop through arraylist
		for (int i=0; i < nArrayList.size(); i++)
			// copy over to array
			nArray[i] = nArrayList.get(i).intValue();
		
		return nArray;
	}
	
	//	Problem #7
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the zeros 
	//	are grouped at the start of the array. The order of the 
	//	non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
	//	{0 ,0, 1, 1}. You may modify and return the 
	//	given array or make a new array.  

	//	MoveZerosToFront({1, 0, 0, 1}) → {0, 0, 1, 1}
	//	MoveZerosToFront({0, 1, 1, 0, 1}) → {0, 0, 1, 1, 1}
	//	MoveZerosToFront({1, 0}) → {0, 1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the zeros moved to the front.
	 */
	static int[] MoveZerosToFront(int[] NumberList) 
	{
		boolean bIsDirty;

		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		// pass over array until no further action is needed
		do
		{
			// fresh start
			bIsDirty = false;
			// loop through array
			for (int i=0; i<NumberList.length; i++)
			{
				// if val i is 0 and not in the first position
				if (NumberList[i] == 0 && i != 0)
				{
					// if the previous position is not a zero
					if (NumberList[i-1] != 0)
					{
						// swap val i with val i-1
						NumberList[i] = NumberList[i-1];
						// move the 0 to val i-1
						NumberList[i-1] = 0;
						// note that we need to run do-loop again
						bIsDirty = true;
					}
				}
			}
		}while(bIsDirty);
		
		return NumberList;
	}
	
	//	Problem #8
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the even numbers 
	//	come before all the odd numbers. Other than that, the 
	//	numbers can be in any order. You may modify and 
	//	return the given array, or make a new array.  

	//	EvenFrontOddBack({1, 0, 1, 0, 0, 1, 1}) → {0, 0, 0, 1, 1, 1, 1}
	//	EvenFrontOddBack({3, 3, 2}) → {2, 3, 3}
	//	EvenFrontOddBack({2, 2, 2}) → {2, 2, 2}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the even numbers in the front and the
	 * 		odd numbers in the back.
	 */
	static int[] EvenFrontOddBack(int[] NumberList) 
	{
		boolean bIsDirty;
		int nSwap;

		// make sure we don't get dupped
		if ( NumberList.length == 0 )
			return NumberList;
		
		// pass over array until no further action is needed
		do
		{
			// fresh start
			bIsDirty = false;
			// loop through array
			for (int i=1; i<NumberList.length; i++)
			{
				// if previous is odd and current is even
				if (NumberList[i-1] % 2 != 0 && NumberList[i] % 2 == 0)
				{
					// copy val i to swap
					nSwap = NumberList[i];
					// move previous to current
					NumberList[i] = NumberList[i-1];
					// move swap to previous
					NumberList[i-1] = nSwap;
					// note that we need to run do-loop again
					bIsDirty = true;
				}
			}
		}while(bIsDirty);
		
		return NumberList;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	///////////////////////////////////////////
	//
	// Utility methods
	//
	//
	///////////////////////////////////////////

	/**
	 * Ask how many days late an assignment was turned in.
	 * 
	 * @return
	 * Return a number that is number of days * 10. This is the
	 *    penalty for turning in late.
	 */
	static double AskHowManyDaysLate()
	{
		Scanner in = new Scanner(System.in);
		System.out.print("How many days late was this assignment turned in? ");
		int nDaysLate = in.nextInt();
		in.close();
		double dPenaltyPercent = 0.0;
		if( nDaysLate > 0 )
		{
			dPenaltyPercent = (double)nDaysLate * 20;
		}
		return( dPenaltyPercent );
	}
	
	public static void main(String[] args)
	{
		
	}
	
}