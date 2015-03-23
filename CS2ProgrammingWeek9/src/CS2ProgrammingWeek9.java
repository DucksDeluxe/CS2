
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek9
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
	//	We want to make a row of bricks that is goal inches long. We have a number of 
	//	small bricks (1 inch each) and big bricks (5 inches each). Return true if it 
	//	is possible to make the goal by choosing from the given bricks. This is a 
	//	little harder than it looks and can be done without any loops.

	//	makeRowOfGoalBricks(3, 1, 8) → true
	//	makeRowOfGoalBricks(3, 1, 9) → false
	//	makeRowOfGoalBricks(3, 2, 10) → true
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1inch bricks available
	 * 		int containing the number of 5inch bricks available
	 * 		int containing the number of inches for the goal
	 * 
	 * @return 
	 * 		returns true if the goal can be reached with the available bricks
	 * 		returns false if the goal cannot be reached with the available bricks
	 */
	static boolean makeRowOfGoalBricks(int small, int big, int goal) 
	{
		if(big == 0)
			return(small >= goal ? true : false);
		
		// enough big to put us within 5 inches of reaching goal
		if ( big >= goal / 5 )
			// we only need the remainder
			return (goal % 5 <= small ? true : false);
			
		// not enough big to put us within 5 inches of reaching goal
		else
			// we need enough small to cover remainder
			return ( (goal % 5)+(goal - big * 5) <= small ? true : false );
	}

	//	Problem #2
	//	Given 3 int values, a b c, return their sum. However, if one of the values 
	//	is the same as another of the values, it does not count towards the sum.

	//	sumExcludingDuplicates(1, 2, 3) → 6
	//	sumExcludingDuplicates(3, 2, 3) → 2
	//	sumExcludingDuplicates(3, 3, 3) → 0
	
	/** 
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where duplicates are not included
	 */
	static int sumExcludingDuplicates(int a, int b, int c) 
	{
		// if there is a match
		if( a==b || a==c || b==c )
		{
			// all match
			if( a==b && a==c )
				return 0;
			// a = b
			else if( a==b )
				return c;
			// a = c
			else if( a==c )
				return b;
			// b = c
			else if( b==c )
				return a;
		}
		// no matches
		return a+b+c;
	}

	//	Problem #3
	//	Given 3 int values, a b c, return their sum. However, if one of the values is 
	//	13 then it does not count towards the sum and values to its right do not 
	//	count. So for example, if b is 13, then both b and c do not count. 

	//	sumExcludingUnluckyNums(1, 2, 3) → 6
	//	sumExcludingUnluckyNums(1, 2, 13) → 3
	//	sumExcludingUnluckyNums(1, 13, 3) → 1
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where values to the right of 13, inclusive, are not included
	 */
	static int sumExcludingUnluckyNums(int a, int b, int c)
	{
		// a = 13
		if ( a==13 )
			return 0;
		// b=13
		if ( b==13 )
			return a;
		// c=13
		if ( c==13 )
			return a+b;
		// no 13s
		return a+b+c;
	}

	//	Problem #4
	//	Given 3 int values, a b c, return their sum. However, if any of the values is a 
	//	teen -- in the range 13..19 inclusive -- then that value counts as 0, except 15 
	//	and 16 do not count as teens. Write a separate helper "public int fixTeen(int n) 
	//	{"that takes in an int value and returns that value fixed for the teen rule. In 
	//	this way, you avoid repeating the teen code 3 times (i.e. "decomposition").

	//	sumExcludingTeens(1, 2, 3) → 6
	//	sumExcludingTeens(2, 13, 1) → 3
	//	sumExcludingTeens(2, 1, 14) → 3
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where teens are not included
	 */
	static int sumExcludingTeens(int a, int b, int c) 
	{
		// return the sum
		return fixTeen(a) + fixTeen(b) + fixTeen(c);
	}
	
	static int fixTeen(int num)
	{
		// check for teen
		if ( num >= 13 && num <= 19 )
			// check for 15 or 16
			if ( num == 15 || num == 16 )
				return num;
			else
				return 0;
		// not a teen
		return num;
	}
	
	//	Problem #5
	//	For this problem, we'll round an int value up to the next multiple of 10 if its rightmost 
	//	digit is 5 or more, so 15 rounds up to 20. Alternately, round down to the previous multiple 
	//	of 10 if its rightmost digit is less than 5, so 12 rounds down to 10. Given 3 ints, 
	//	a b c, return the sum of their rounded values. To avoid code repetition, write a separate 
	//	helper "public int round10(int num) {" and call it 3 times. Write the helper entirely below 
	//	and at the same indent level as roundSum().

	//	roundedSum(16, 17, 18) → 60
	//	roundedSum(12, 13, 14) → 30
	//	roundedSum(6, 4, 4) → 10
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where each value is rounded to the nearest tens place
	 */
	static int roundedSum(int a, int b, int c) 
	{
		// return sum of rounded numbers
		return round(a) + round(b) + round(c);
	}
	
	static int round(int num)
	{
		// less than 5 - round down
		if ( num % 10 < 5 )
			return num - num % 10;
		// 5 or greater - round up
		else
			return num + 10 - (num % 10);
			
	}
	
	//	Problem #6
	//	Given three ints, a b c, return true if one of b or c is "close" (differing from 
	//	a by at most 1), while the other is "far", differing from both other values by 2 
	//	or more. Note: Math.abs(num) computes the absolute value of a number. 

	//	isCloseAndFar(1, 2, 10) → true
	//	isCloseAndFar(1, 2, 3) → false
	//	isCloseAndFar(4, 1, 3) → true
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints with original integers to compute relativity
	 * 
	 * @return 
	 * 		returns true if one of b or c is close to a and if the other is far from both other values
	 */
	static boolean isCloseAndFar(int a, int b, int c) 
	{
		// b close, c far
		if ( Math.abs(a-b) <= 1 && !(Math.abs(a-c) <= 1) && !(Math.abs(b-c) <= 1) )
			return true;
		
		// c close, b far
		else if ( Math.abs(a-c) <= 1 && !(Math.abs(a-b) <= 1) && !(Math.abs(b-c) <= 1) )
			return true;
		
		// condition not met
		else
			return false;
	}
	
	//	Problem #7
	//	Given 2 int values greater than 0, return whichever value is nearest to 21 without 
	//	going over. Return 0 if they both go over. 

	//	blackjack(19, 21) → 21
	//	blackjack(21, 19) → 21
	//	blackjack(19, 22) → 19
	
	/**
	 * 
	 * @param a, b
	 * 		ints representing the values of two cards in a game of black jack
	 * 
	 * @return 
	 * 		returns the value of the int that is closest to 21 without going over
	 */
	static int blackjack(int a, int b) 
	{
		// both over
		if ( a > 21 && b > 21 )
			return 0;
		
		// a over
		else if ( a > 21 )
			return b;
		
		// b over
		else if ( b > 21 )
			return a;
		
		// a is highest
		else if ( a-b > 0 )
			return a;
		
		// b is highest
		else
			return b;
	}
	
	//	Problem #8
	//	Given three ints, a b c, one of them is small, one is medium and one is large. 
	//	Return true if the three values are evenly spaced, so the difference between 
	//	small and medium is the same as the difference between medium and large. 

	//	spacedEvenly(2, 4, 6) → true
	//	spacedEvenly(4, 6, 2) → true
	//	spacedEvenly(4, 6, 3) → false
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing original integers to compute with
	 * 
	 * @return 
	 * 		returns true if the input values are evenly spaced
	 * 		returns false if the input values are not evenly spaced
	 */
	static boolean spacedEvenly(int a, int b, int c) 
	{
		int ab, bc, ac;
		
		// find spacing
		ab = Math.abs(a-b);
		bc = Math.abs(b-c);
		ac = Math.abs(a-c);
		
		// ab = ac
		if ( ab == ac )
			// the other space needs to have a factor of 2
			if( ab == bc*2 || bc == ab*2 )
				return true;
			else
				return false;

		// ab = bc
		else if ( ab == bc )
			// the other space needs to have a factor of 2
			if( ab == ac*2 || ac == ab*2 )
				return true;
			else
				return false;
		
		// ac = bc
		else if ( ac == bc )
			// the other space needs to have a factor of 2
			if( ac == ab*2 || ab == ac*2 )
				return true;
			else
				return false;
		
		// no even spacing
		else
			return false;
	

		
	}
	
	//	Problem #9
	//	We want to make a package of goal kilos of chocolate. We have small bars 
	//	(1 kilo each) and big bars (5 kilos each). Return the number of small bars 
	//	to use, assuming we always use big bars before small bars. Return -1 
	//	if it can't be done.

	//	makeKilosOfChocolate(4, 1, 9) → 4
	//	makeKilosOfChocolate(4, 1, 10) → -1
	//	makeKilosOfChocolate(4, 1, 7) → 2
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1kilo bars available
	 * 		int containing the number of 5kilo bars available
	 * 		int containing the number of kilos for the goal
	 * 
	 * @return 
	 * 		returns the value of the number of small bars needed to meet the goal
	 */
	static int makeKilosOfChocolate(int small, int big, int goal) 
	{

		if(big == 0)
			return(small >= goal ? small : -1);
		
		// enough big to put us within 5 kilos of reaching goal
		if ( big >= goal / 5 )
		{
			// we only need the remainder
			return (goal % 5 <= small ? goal % 5 : -1);
		}
		
		// not enough big to put us within 5 kilos of reaching goal
		else
			// we need enough small to cover remainder
			return ( (goal % 5)+(goal - big * 5) <= small ? (goal % 5)+(goal - big * 5) : -1 );
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		
		assert makeRowOfGoalBricks(3, 1, 8) == true;
		assert makeRowOfGoalBricks(3, 1, 9) == false;
		assert makeRowOfGoalBricks(3, 2, 10) == true;
		assert makeRowOfGoalBricks(8, 0, 8) == true;
		assert makeRowOfGoalBricks(0, 2, 10) == true;
		assert makeRowOfGoalBricks(0, 0, 0) == true;
		assert makeRowOfGoalBricks(3, 2, 0) == true;
		assert makeRowOfGoalBricks(0, 0, 10) == false;

		assert sumExcludingDuplicates(1, 2, 3) == 6;
		assert sumExcludingDuplicates(3, 2, 3) == 2;
		assert sumExcludingDuplicates(3, 3, 3) == 0;
		assert sumExcludingDuplicates(1, 1, 2) == 2;
		assert sumExcludingDuplicates(1, 2, 2) == 1;
		
		assert sumExcludingUnluckyNums(1, 2, 3) == 6;
		assert sumExcludingUnluckyNums(1, 2, 13) == 3;
		assert sumExcludingUnluckyNums(1, 13, 3) == 1;
		assert sumExcludingUnluckyNums(13, 2, 3) == 0;
		
		assert sumExcludingTeens(1, 2, 3) == 6;
		assert sumExcludingTeens(2, 13, 1) == 3;
		assert sumExcludingTeens(2, 1, 14) == 3;
		assert sumExcludingTeens(15, 2, 3) == 20;
		assert sumExcludingTeens(16, 2, 3) == 21;
		assert sumExcludingTeens(1, 2, 3) == 6;
		
		assert roundedSum(16, 17, 18) == 60;
		assert roundedSum(12, 13, 14) == 30;
		assert roundedSum(6, 4, 4) == 10;
		assert roundedSum(1, 2, 3) == 0;
		
		assert isCloseAndFar(1, 2, 10) == true;
		assert isCloseAndFar(1, 2, 3) == false;
		assert isCloseAndFar(4, 1, 3) == true;
		
		assert blackjack(19, 21) == 21;
		assert blackjack(21, 19) == 21;
		assert blackjack(19, 22) == 19;
		assert blackjack(22, 19) == 19;
		
		assert spacedEvenly(2, 4, 6) == true;
		assert spacedEvenly(4, 6, 2) == true;
		assert spacedEvenly(4, 6, 3) == false;
		assert spacedEvenly(2, 6, 4) == true;
		assert spacedEvenly(4, 2, 6) == true;
		assert spacedEvenly(6, 2, 4) == true;
		assert spacedEvenly(4, 6, 2) == true;
		assert spacedEvenly(6, 4, 2) == true;
		
		assert makeKilosOfChocolate(4, 1, 9) == 4;
		assert makeKilosOfChocolate(4, 1, 10) == -1;
		assert makeKilosOfChocolate(4, 1, 7) == 2;
		assert makeKilosOfChocolate(8, 0, 8) == 8;
		assert makeKilosOfChocolate(0, 2, 10) == 0;

	}
}