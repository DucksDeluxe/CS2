import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek4 
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
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) → true
	//	groupSumsTarget(0, {2, 4, 8}, 14) → true
	//	groupSumsTarget(0, {2, 4, 8}, 9) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{
		
		// base case
		if(target == 0)
			// solution found
			return true;
		
		// traversed to the end of the array
		else if (start == nums.length)
			// time to backtrack
			return false;
		
		// first route
		else if ( groupSumsTarget(start+1, nums, target-nums[start]) )
			// this route is a candidate.
			return true;
		
		// alternate route
		else if( groupSumsTarget(start+1, nums, target) )
			// this route is a candidate
			return true;
		
		// no solution was found
		else return false;
	}

	//	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{
		// base case
		if ( target == 0 )
			// solution found
			return true;
		
		// traversed to end of array
		else if ( start == nums.length )
			// begin backtracking
			return false;
		
		// we need a main and alternate path for all non sixes
		if ( nums[start] != 6 )
		{
			// main path
			if ( groupSumsTarget6(start+1, nums, target-nums[start]) )
				return true;
		
			// alternate path
			else if ( groupSumsTarget6(start+1, nums, target) )
				return true;
		}
		
		// we don't want an alternate path for any sixes
		else if ( nums[start] == 6 )
		{
			// main and only path
			if ( groupSumsTarget6(start+1, nums, target-nums[start]) )
				return true;
		}
			
		// no solution found
		return false;
		
	}	

	//	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
		// base case
		if ( target == 0)
			return true;
		
		// traversed beyond end of array, now backtrack
		else if ( start >= nums.length )
			return false;
		
		// main path
		else if ( groupSumsTargetNoAdj(start+2, nums, target-nums[start]) )
			return true;
		
		// alternate path
		else if ( groupSumsTargetNoAdj(start+1, nums, target) )
			return true;
		
		// no solution found
		return false;
		
	}	

	//	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
		// base case
		if ( target == 0 )
			return true;
		
		// end of array - start backtracking
		else if ( start == nums.length )
			return false;
		
		
		// mult of 5 and next is 1 then we need to skip it.
		else if (nums[start] % 5 == 0 && nums[start+1] == 1)
		{
			if (groupSumsTarget5(start+2, nums, target-nums[start]))
				return true;
		}
		
		// mult of 5 and next is not 1 then we can consider next val
		else if (nums[start] % 5 == 0 && nums[start+1] != 1)
		{
			if ( groupSumsTarget5(start+1, nums, target-nums[start]) )
				return true;
		}
		
		// main route for non mult of 5
		if (nums[start] % 5 != 0)
		{
			if ( groupSumsTarget5(start+1, nums, target-nums[start]) )
				return true;
		}
		// alternate route for non mult of 5
		if ( nums[start] % 5 != 0 )
		{
			if ( groupSumsTarget5(start+1, nums, target) )
				return true;
		}
		
		// no solution found
		return false;
	}
	
	//	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
		int nClump = 1;
		
		// base case
		if ( target == 0 )
			return true;
		
		// end of array, backtrack
		else if ( start == nums.length )
			return false;
		
		// check for clump
		for ( int i = start; i+1 < nums.length; i++ )
		{
			// current val = next val?
			if  (nums[i] == nums[i+1] )
				// count the matching val in the clump
				nClump++;
			else
				break;
		}
		
		// if we are at the start of a clump,
		if ( nClump > 1 )
		{	
			// the main route includes the entire clump
			if ( groupSumsTargetClump(start + nClump, nums, target - nums[start] * nClump) )
				return true;
			// the alternate route disregards the entire clump
			else if ( groupSumsTargetClump(start + nClump, nums, target) )
				return true;
		}
		
		// main route if we are NOT in a clump
		else if ( groupSumsTargetClump(start+1, nums, target-nums[start]) )
			return true;
		// alternate route if we are NOT in a clump
		else if ( groupSumsTargetClump(start+1, nums, target) )
			return true;
		
		// no solution found
		return false;
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    

	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param i is the index of the current value being summed
	 * @param start is the start index of group2
	 * @param nums is an array of integers
	 * @param group1 is a boolean value that is true when group1 is to be summed
	 * 			and false when group2 is to be summed
	 * @return the sum of the selected group
	 */
	static int sum(int i, int start, int[] nums, boolean group1)
	{
		// calculating group 1
		if ( group1 )
		{
			// base case
			if ( i == start )
				return 0;
			// build sum on way back up
			else
				return nums[i] + sum(i+1, start, nums, group1);
		}
		
		// calculating group 2
		else
		{
			// base case
			if ( i == nums.length )
				return 0;
			// build sum on way back up
			else 
				return nums[i] + sum(i+1, start, nums, group1);
		}
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    
	
	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divideArray(int[] nums) 
	{
		// are there enough numbers for 2 groups?
		if (nums.length > 1)
		{
			int start = 1; // the start index of group2
			int group1 = sum(0, start, nums, true);
			int group2 = sum(start, start, nums, false);
			// if a solution is found, we pass it up
			return divideArray1( start, nums, group1, group2);
		}
	
		// cases of null array or length 1 array with a 0
		if (nums.length == 0)
			return true;
		if (nums.length == 1 && nums[0] == 0)
			return true;
		
		// no solution found
		return false;
	}

	/**
	 * 
	 * @param start is the start index of group2
	 * @param nums is an array of integers
	 * @param group1 is the sum of the integers before the start index of group2
	 * @param group2 is the sum of the integers at and beyond the start index of group 2
	 * @return
	 * 		true if a solution is found
	 * 		false if no solution can be found
	 */
	static boolean divideArray1(int start, int[] nums, int group1, int group2)
	{
	
		// base case
		if ( group1 == group2)
			return true;
		
		// end of array (no values in group2)
		if ( start == nums.length )
			return false;
		
		// main route (take start val in group2 and place it in 1)
		if ( divideArray1(start + 1, nums, group1+nums[start], group2-nums[start]) )
			return true;
		
		// alternate route (try grabbing a different value)
		if ( divideArray1(start + 1, nums, group1, group2) )
			return true;
		
		// no solution found
		return false;
	}

	//	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	static boolean oddDivide10(int[] nums) 
	{
		// are there enough numbers for 2 groups?
				if (nums.length > 1)
				{
					int start = 0; // the start index of group2
					int group1 = 0; // start with nothing in group 1
					int group2 = sum(start, start, nums, false); // put everything in group 2
					// if a solution is found, we pass it up
					return oddDivide101( start, nums, group1, group2 );
				}

				// no solution found
				else return false;
	}
	
	static boolean oddDivide101(int start, int[] nums, int group1, int group2)
	{
		// base case
		if ( group1 % 10 == 0 && group2 % 2 == 1 && start == nums.length )
			return true;
		// kinda pointless, but who cares.
		if ( group1 % 2 == 1 && group2 % 10 == 0 && start == nums.length )
			return true;
		
		// out of bounds
		if ( start == nums.length )
			return false;
		
		// main route
		if ( oddDivide101(start+1, nums, group1 + nums[start], group2 - nums[start]) )
			return true;
		
		// alternate route
		if ( oddDivide101(start+1, nums, group1, group2) )
			return true;
		
		// no solution found
		return false;
		
	}
	
	//	Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) → true
	//	divide53({1, 1, 1}) → false
	//	divide53({2, 4, 2}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
		// are there enough numbers for 2 groups?
		if (nums.length > 1)
		{
			int start = 0; // the start index of group2
			int group1 = 0; // start with group1 empty
			int group2 = sum(start, start, nums, false);	// everything starts in group 2
			// if a solution is found, we pass it up
			return divide531( start, nums, group1, group2);
		}

		// cases of null array or length 1 array with a 0
		if (nums.length == 0)
			return true;
		if (nums.length == 1 && nums[0] == 0)
			return true;
		
		// no solution found
		return false;
	}
	
	static boolean divide531(int start, int[] nums, int group5s, int group3s)
	{
		// base case
		if ( group5s == group3s && start == nums.length)
			return true;
		
		// out of bounds
		if ( start >= nums.length )
			return false;
		
		// if the value is a mult of 5
		if ( nums[start] % 5 == 0 )
		{
			// it must go in group5s
			if ( divide531(start+1 , nums, group5s + nums[start], group3s - nums[start]) )
				return true;
		}
		// if the value is a mult of 3 and not also a mult of 5
		else if ( nums[start] % 3 == 0 )
		{
			// it's already in group3s
			if( divide531(start+1, nums, group5s, group3s) )
				return true;
		}	
		
		// if the value is not a mult of 3 or 5
		if ( nums[start] % 5 != 0 && nums[start] % 3 != 0 )
		{
			// main path
			if ( divide531(start+1, nums, group5s+nums[start], group3s-nums[start]) )
			return true;
		
			// alternate path
			if ( divide531(start+1, nums, group5s, group3s)	)
				return true;
		}
		
		// no solution found
		return false;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		int[] nullArray = {};
		
		//	groupSumsTarget(0, {2, 4, 8}, 10) → true
		int[] array01 = {2,4,8};
		assert groupSumsTarget(0, array01, 10) == true;
		//	groupSumsTarget(0, {2, 4, 8}, 14) → true
		int[] array02 = {2,4,8};
		assert groupSumsTarget(0, array02, 14) == true;
		//	groupSumsTarget(0, {2, 4, 8}, 9) → false
		int[] array03 = {2,4,8};
		assert groupSumsTarget(0, array03, 9) == false;		
		// null array,0 == true
		assert groupSumsTarget(0,nullArray, 0) == true;
		int[] array025 = {0,3,5,1,9,7,6,3,8};
		assert groupSumsTarget(0,array025, 18) == true;
		
		//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
		int[] array04 = {5,6,2};
		assert groupSumsTarget6(0, array04, 8) == true;
		//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
		int[] array05 = {5,6,2};
		assert groupSumsTarget6(0, array05, 9) == false;
		//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
		int[] array06 = {5,6,2};
		assert groupSumsTarget6(0, array06, 7) == false;
		// null array,0 == true
		assert groupSumsTarget6(0,nullArray, 0) == true;
		int[] array26 = {1,2,3,4};
		assert groupSumsTarget6(0, array26, 4) == true;
		
		
		//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
		int[] array07 = {2,5,10,4};
		assert groupSumsTargetNoAdj(0, array07, 12) == true;
		//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
		int[] array08 = {2,5,10,4};
		assert groupSumsTargetNoAdj(0, array08, 14) == false;
		//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
		int[] array09 = {2,5,10,4};
		assert groupSumsTargetNoAdj(0, array09, 7) == false;

		
		//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
		int[] array10 = {2,5,10,4};
		assert groupSumsTarget5(0, array10, 19) == true;
		//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
		int[] array11 = {2,5,10,4};
		assert groupSumsTarget5(0, array11, 17) == true;
		//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
		int[] array12 = {2,5,10,4};
		assert groupSumsTarget5(0, array12, 12) == false;
		
		//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
		int[] array13 = {2,4,8};
		assert groupSumsTargetClump(0, array13, 10) == true;
		//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
		int[] array14 = {1,4,4,8,1};
		assert groupSumsTargetClump(0, array14, 16) == true;
		//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
		int[] array15 = {2,4,4,8};
		assert groupSumsTargetClump(0, array15, 14) == false;
		
		//	divideArray({2, 2}) → true
		int[] array16 = {2,2};
		assert divideArray(array16) == true;
		//	divideArray({2, 3}) → false
		int[] array17 = {2,3};
		assert divideArray(array17) == false;
		//	divideArray({5, 2, 3}) → true
		int[] array18 = {5,2,3};
		assert divideArray(array18) == true;
		
		//	oddDivide10({5, 5, 5}) → true
		int[] array19 = {5,5,5};
		assert oddDivide10(array19) == true;
		//	oddDivide10({5, 5, 6}) → false
		int[] array20 = {5,5,6};
		assert oddDivide10(array20) == false;
		//	oddDivide10({5, 5, 6, 1}) → true
		int[] array21 = {5,5,6,1};
		assert oddDivide10(array21) == true;

		//	divide53({1,1}) → true
		int[] array22 = {1,1};
		assert divide53(array22) == true;
		//	divide53({1, 1, 1}) → false
		int[] array23 = {1,1,1};
		assert divide53(array23) == false;
		//	divide53({2, 4, 2}) → true
		int[] array24 = {2,4,2};
		assert divide53(array24) == true;
		
	}
}