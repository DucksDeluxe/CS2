import java.util.Arrays;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek10
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
	//	Consider the leftmost and rightmost appearances of some value in an array. 
	//	We'll say that the "span" is the number of elements between the two inclusive. 
	//	A single value has a span of 1. Returns the largest span found in the 
	//	given array. (Efficiency is not a priority.) 

	//	maximumSpan({1, 2, 1, 1, 3}) == 4
	//	maximumSpan({1, 4, 2, 1, 4, 1, 4}) == 6
	//	maximumSpan({1, 4, 2, 1, 4, 4, 4}) == 6
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int representing the largest span of a specific int in nums
	 */
	static int maximumSpan(int[] nums) 
	{
		int nLargestSpan = 0;
		
		for(int i=0; i<nums.length; i++)
		{
			for(int j=0; j<nums.length; j++)
			{
				if(nums[i] == nums[j])
				{
					if(nLargestSpan < j-i+1)
					{
						nLargestSpan = j-i+1;
					}
				}
			}
		}
		return nLargestSpan;
	}

	//	Problem #2
	//	Given a non-empty array, return true if there is a place to split the 
	//	array so that the sum of the numbers on one side is equal to the sum of 
	//	the numbers on the other side. 

	//	canStabilize({1, 1, 1, 2, 1}) == true
	//	canStabilize({2, 1, 1, 2, 1}) == false
	//	canStabilize({10, 10}) == true
	
	/** 
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return
	 * 		returns true if nums can be split so that sum of one side equals sum on other side
	 * 		returns false if this is not the case
	 */
	static boolean canStabilize(int[] nums) 
	{
		int nSumLeft = 0, nSumRight = 0;
		
		if(nums.length < 2)
			return false;
		// get sum
		for(int i=0; i<nums.length; i++)
		{
			nSumRight += nums[i];
		}
		
		// walk right, adding to left and taking from right
		for(int i=0; i<nums.length; i++)
		{
			nSumLeft += nums[i];
			nSumRight -= nums[i];
						
			if(nSumLeft == nSumRight)
				return true;
			else continue;
		}
		return false;
	}	

	//	Problem #3
	//	Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} 
	//	(spaces added to show the grouping). Note that the length of the array will be 
	//	1 + 2 + 3 ... + n, which is known to sum to exactly n*(n + 1)/2. 

	//	arithmeticSeries(3) == {1, 1, 2, 1, 2, 3}
	//	arithmeticSeries(4) == {1, 1, 2, 1, 2, 3, 1, 2, 3, 4}
	//	arithmeticSeries(2) == {1, 1, 2}
	
	/**
	 * 
	 * @param n
	 * 		int with the value of the length of the series
	 * 
	 * @return
	 * 		int [] containing the arithmetic series starting at 1 and repeating to n
	 */
	static int[] arithmeticSeries(int n) 
	{
		int[] array = new int[n*(n+1)/2];
		int tracker = 0;
		int curNum = 1;
		
		if(n == 0)
		{
			return array;
		}
		
		for(int i=1; i<=n; i++)
		{
			for(int j=tracker; curNum<=i; j++)
			{
				array[j] = curNum++;
				tracker++;
			}
			curNum = 1;
		}
		return array;
	}	

	//	Problem #4
	//	Return an array that contains exactly the same numbers as the given array, 
	//	but rearranged so that every 3 is immediately followed by a 4. Do not move 
	//	the 3's, but every other number may move. The array contains the same number of 
	//	3's and 4's, every 3 has a number after it that is not a 3 or 4, and a 3 appears 
	//	in the array before any 4. 

	//	follow3with4({1, 3, 1, 4}) == {1, 3, 4, 1}
	//	follow3with4({1, 3, 1, 4, 4, 3, 1}) == {1, 3, 4, 1, 1, 3, 4}
	//	follow3with4({3, 2, 2, 4}) == {3, 4, 2, 2}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specified conditions above
	 * 
	 * @return
	 * 		int[] containing a modified array where every occurrence of 3 is followed by a 4
	 */
	static int[] follow3with4(int[] nums) 
	{
		int j=0;
		int[] returnArray = new int[nums.length];
		
		// copy array
		for(int i=0; i<nums.length; i++)
		{
			returnArray[i] = nums[i];
		}
		
		for(int i=0; i<nums.length; i++)
		{
			// find next 4
			if(returnArray[i] == 3 && returnArray[i+1] != 4)
			{
				for(int k=j; k<nums.length; k++)
				{
					// find next 5
					if(returnArray[k] == 4)
					{
						// there are no 5s before this
						j=k+1;
						// if a 5 is placed, don't touch this 5
						if(k > 0)
						{
							if(returnArray[k-1] == 3)
							{
								
								continue;
							}
							else
							{
								returnArray[k] = returnArray[i+1];
								returnArray[i+1] = 4;
								break;
							}
						}
					
						else
						{
							returnArray[k] = returnArray[i+1];
							returnArray[i+1] = 4;
							break;
						}
					}
				}
			}
		}
		return returnArray;
	}
	
	//	Problem #5
	//	Given two arrays of ints sorted in increasing order, outer and inner,
	//	return true if all of the numbers in inner appear in outer. The best 
	//	solution makes only a single "linear" pass of both arrays, taking 
	//	advantage of the fact that both arrays are already in sorted order. 

	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 4}) == true
	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 3, 4}) == false
	//	innerAppearsInOuter({1, 2, 4, 4, 6}, {2, 4}) == true
	
	/**
	 * 
	 * @param outer, inner
	 * 		int[] containing original integers in increasing order
	 * 		int[] containing original integers in increasing order
	 * 
	 * @return
	 * 		returns true if all contents of inner appear in outer
	 * 		returns false if not all of the contents inner appear in outer
	 */
	static boolean innerAppearsInOuter(int[] outer, int[] inner) 
	{
		int i = 0;
		
		for(int j=0; j<outer.length; j++)
		{
			if(outer[j] == inner[i])
			{
				i++;
				if(i == inner.length)
					return true;
				else
					continue;
			}
			
		}
		return false;
	}
	
	//	Problem #6
	//	We'll say that a "mirror" section in an array is a group of contiguous elements 
	//	such that somewhere in the array, the same group appears in reverse order. For 
	//	example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} is length 
	//	3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array. 

	//	maximumMirrorSpan({1, 2, 3, 8, 9, 3, 2, 1}) == 3
	//	maximumMirrorSpan({1, 2, 1, 4}) == 3
	//	maximumMirrorSpan({7, 1, 2, 9, 7, 2, 1}) == 2
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the value of the maximum span of ints that are mirrored in nums
	 */
	static int maximumMirrorSpan(int[] nums) 
	{
		int curSpan = 0;
		int maxSpan = 0;
		
		for(int i=0; i<nums.length; i++)
		{
			for(int j=nums.length-1; j>=0; j--)
			{
				if(nums[i] == nums[j])
				{
					curSpan++;
					i++;
					if(i >= nums.length)
						break;
				}
				else
					if(curSpan > maxSpan)
					{
						maxSpan = curSpan;
						curSpan = 0;
					}
					else
					{
						curSpan = 0;
					}
			}
		}
		if(curSpan > maxSpan)
			maxSpan = curSpan;
		
		return maxSpan;
	}
	
	//	Problem #7
	//	(This is a slightly harder version of the fix34 problem.) Return an array that contains 
	//	exactly the same numbers as the given array, but rearranged so that every 4 is immediately 
	//	followed by a 5. Do not move the 4's, but every other number may move. The array contains 
	//	the same number of 4's and 5's, and every 4 has a number after it that is not a 4. In this 
	//	version, 5's may appear anywhere in the original array. 

	//	follow4with5({5, 4, 9, 4, 9, 5}) == {9, 4, 5, 4, 5, 9}
	//	follow4with5({1, 4, 1, 5}) == {1, 4, 5, 1}
	//	follow4with5({1, 4, 1, 5, 5, 4, 1}) == {1, 4, 5, 1, 1, 4, 5}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specifications declared above
	 * 
	 * @return 
	 * 		int[] containing a modified nums where every 4 is followed by a 5
	 */
	static int[] follow4with5(int[] nums) 
	{
		int j=0;
		int[] returnArray = new int[nums.length];
		
		// copy array
		for(int i=0; i<nums.length; i++)
		{
			returnArray[i] = nums[i];
		}
		
		for(int i=0; i<nums.length; i++)
		{
			// find next 4
			if(returnArray[i] == 4 && returnArray[i+1] != 5)
			{
				for(int k=j; k<nums.length; k++)
				{
					// find next 5
					if(returnArray[k] == 5)
					{
						// there are no 5s before this
						j=k+1;
						// if a 5 is placed, don't touch this 5
						if(k > 0)
						{
							if(returnArray[k-1] == 4)
							{
								
								continue;
							}
							else
							{
								returnArray[k] = returnArray[i+1];
								returnArray[i+1] = 5;
								break;
							}
						}
					
						else
						{
							returnArray[k] = returnArray[i+1];
							returnArray[i+1] = 5;
							break;
						}
					}
				}
			}
		}
		return returnArray;
	}
	
	//	Problem #8
	//	Given n>=0, create an array length n*n with the following pattern, shown here 
	//	for n=3 : {0, 0, 1,    0, 2, 1,    3, 2, 1} (spaces added to show the 3 groups). 

	//	reverseArithSeries(3) == {0, 0, 1, 0, 2, 1, 3, 2, 1}
	//	reverseArithSeries(2) == {0, 1, 2, 1}
	//	reverseArithSeries(4) == {0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1}
	
	/**
	 * 
	 * @param n
	 * 		int containing original integer value
	 * 
	 * @return 
	 * 		int [] containing the arithmetic series in reverse order
	 */
	static int[] reverseArithSeries(int n) 
	{
		int[] series = new int[n*n];
		
		int sequence = 1;
		int val = n;
		
		for(int i=0; i<series.length; i++)
		{
			if(val <= sequence)
			{
				series[i] = val;
				val--;
			}
			else
			{
				series[i] = 0;
				val--;
			}
			
			if(val <= 0)
			{
				val = n;
				sequence++;
			}
		}
		return series;
	}
	
	//	Problem #9
	//	Say that a "clump" in an array is a series of 2 or more adjacent elements of 
	//	the same value. Return the number of clumps in the given array. 

	//	largestClump({1, 2, 2, 3, 4, 4}) == 2
	//	largestClump({1, 1, 2, 1, 1}) == 2
	//	largestClump({1, 1, 1, 1, 1}) == 1
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the number of the largest clump in nums
	 */
	static int largestClump(int[] nums) 
	{
		int count = 0;
		int prevClump;
		
		for(int i=1; i<nums.length; i++)
		{
			if(nums[i] == nums[i-1])
			{
				prevClump = i;
				count++;
				i++;
				if(i >= nums.length)
					break;
			
				
				while(nums[i] == nums[prevClump])
				{
					i++;
					if(i >= nums.length)
						break;
				}
			}
		}
		return count;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		int[] array01 = {1, 2, 1, 1, 3};
		assert maximumSpan(array01) == 4;
		int[] array02 = {1, 4, 2, 1, 4, 1, 4};
		assert maximumSpan(array02) == 6;
		int[] array03 = {1, 4, 2, 1, 4, 4, 4};
		assert maximumSpan(array03) == 6;
		
		int[] array04 = {1, 1, 1, 2, 1};
		assert canStabilize(array04) == true;
		int[] array05 = {2, 1, 1, 2, 1};
		assert canStabilize(array05) == false;
		int[] array06 = {10, 10};
		assert canStabilize(array06) == true;
		
		printArray(arithmeticSeries(3));// == {1, 1, 2, 1, 2, 3};
		printArray(arithmeticSeries(4));// == {1, 1, 2, 1, 2, 3, 1, 2, 3, 4};
		printArray(arithmeticSeries(2));// == {1, 1, 2};
		System.out.println("");
		
		int[] array07 = {1, 3, 1, 4};
		printArray(follow3with4(array07));// == {1, 3, 4, 1};
		int[] array08 = {1, 3, 1, 4, 4, 3, 1};
		printArray(follow3with4(array08));// == {1, 3, 4, 1, 1, 3, 4};
		int[] array09 = {3, 2, 2, 4};
		printArray(follow3with4(array09));// == {3, 4, 2, 2};
		System.out.println("");
		
		int[] array10 = {1, 2, 4, 6};
		int[] array11 = {2, 4};
		assert innerAppearsInOuter(array10, array11) == true;
		int[] array21 = {1, 2, 4, 6};
		int[] array22 = {2, 3, 4};
		assert innerAppearsInOuter(array21, array22) == false;
		int[] array23 = {1, 2, 4, 4, 6};
		int[] array24 = {2, 4};
		assert innerAppearsInOuter(array23, array24) == true;
		
		int[] array12 = {1, 2, 3, 8, 9, 3, 2, 1};
		assert maximumMirrorSpan(array12) == 3;
		int[]array13 = {1, 2, 1, 4};
		assert maximumMirrorSpan(array13) == 3;
		int[] array14 = {7, 1, 2, 9, 7, 2, 1};
		assert maximumMirrorSpan(array14) == 2;
		
		int[] array15 = {5, 4, 9, 4, 9, 5};
		printArray(follow4with5(array15));// == {9, 4, 5, 4, 5, 9};
		int[] array16 = {1, 4, 1, 5};
		printArray(follow4with5(array16));// == {1, 4, 5, 1};
		int[] array17 = {1, 4, 1, 5, 5, 4, 1};
		printArray(follow4with5(array17));// == {1, 4, 5, 1, 1, 4, 5};
		System.out.println("");
		
		printArray(reverseArithSeries(3));// == {0, 0, 1, 0, 2, 1, 3, 2, 1};
		printArray(reverseArithSeries(2));// == {0, 1, 2, 1};
		printArray(reverseArithSeries(4));// == {0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1};
		System.out.println("");
		
		int[] array18 = {1, 2, 2, 3, 4, 4};
		assert largestClump(array18) == 2;
		int[] array19 = {1, 1, 2, 1, 1};
		assert largestClump(array19) == 2;
		int[] array20 = {1, 1, 1, 1, 1};
		assert largestClump(array20) == 1;
		System.out.println("");		
	}
	
	public static void printArray(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println("");
	}
	
	public void printNum(int num)
	{
		System.out.println(num);
	}
	
	public void printBool(boolean bool)
	{
		System.out.println(bool);
	}
	
}