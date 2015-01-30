import java.util.Scanner;

public class CS2RecitationWeek3 
{
    ///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
        /*
            Note: All of the following assignments must be completed without the use of any loops including (for, while, and do-while).
            You may not utilize them at any point in your programming to help enhance your use of recursion.
        */
    
    
    
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "VanWinkle,Justin,J3338546");
	}    
        
        /*  Directions: Recursive function that returns the value of a desired Fibonatchi number
            Note: For all recursive functions you should always be some calls that do
            not result in a recursive call without one your function will end up in an
            infinite loop.
        */
        //FibonatchiNumber(1) -> 1
        //FibonatchiNumber(7) -> 13
        //FibonatchiNumber(17) -> 1597
    
        /*
           @param FibonatchiNumber
                            int of desired fibonatchi number
           @return
                            int value of the fibonatchi number
        */
        
        static int getFibonatchiNumber(int FibonatchiNumber)
        {
        	// half of base case
        	// also serves as boundary protection
        	if ( FibonatchiNumber <=0 )
        	{
        		return 0;
        	}
        	// other half of base case
        	// without this, we would be stuck at 0
        	else if ( FibonatchiNumber == 1)
        	{
        		return 1;
        	}
        	
        	// return the sum of the sequential step
        	return getFibonatchiNumber(FibonatchiNumber-1) + getFibonatchiNumber(FibonatchiNumber-2);
        }
    
        
        //  Directions: Recursive function that returns the value of a desired factorial
      
        //FactorialNumber(1) -> 1
        //FactorialNumber(7) -> 5040
        //FactorialNumber(12) -> 479001600
    
        /*
           @param FactorialNumber
                            int of desired Factorial number
           @return
                            int value of the Factoiral number
        */
    
        static int getFactorialNumber(int FactorialNumber)
        {
        	// NOT BASE CASE - this would break our value since negative factorials are undefined
        	// serves as boundary protection only
        	if ( FactorialNumber < 0 )
        	{
        		// can't return + or - infinity, so 0 is the next best thing
        		return 0;
        	}
        	// base case
        	else if ( FactorialNumber == 0 )
        	{
        		// 0! = 1
        		return 1;
        	}
        	
        	// for each step of the sequence, return the multiplicative
        	// from the previous step in
        	return FactorialNumber * getFactorialNumber(FactorialNumber-1);
        }
    
        /*  Directions: Recursive function that adds up the the squares of a range of two integer values
            Example: SumSquaresOfRange(5, 10);
            (5*5)+(6*6)+(7*7)+(8*8)+(9*9)+(10*10)=355
        */
      
        
        /*
           @param int x , int y
                            two integer values of the desired ranage
           @return
                            int value of the sum of all the numbers in the range squared
        */
        
        static int SumSquaresOfRange(int x , int y)
        {
        	// according to T.A. Michael G:
        	// if y < x, swap x and y
        	if ( y < x )
        	{
        		// copy x
        		int swap = x;
        		// put y in x
        		x = y;
        		// put the old x in y
        		y = swap;
        	}
        	
        	// base case - don't pass highest value
        	if ( x == y )
        	{
        		// return square
        		return x * x;
        	}
        	
        	// return x*x + the next step in the sequence
        	return SumSquaresOfRange(x+1, y) + x*x;
        }
	
        public static void main(String[] args)
		{
        	System.out.println(getFibonatchiNumber(-5));
        	System.out.println(getFibonatchiNumber(-1));
        	System.out.println(getFibonatchiNumber(0));
        	System.out.println(getFibonatchiNumber(1));
        	System.out.println(getFibonatchiNumber(2));
        	System.out.println(getFibonatchiNumber(7));
        	System.out.println(getFibonatchiNumber(17));
        	
        	System.out.println(getFactorialNumber(-5));
        	System.out.println(getFactorialNumber(-1));
        	System.out.println(getFactorialNumber(0));
        	System.out.println(getFactorialNumber(1));
        	System.out.println(getFactorialNumber(7));
        	System.out.println(getFactorialNumber(12));
        	
        	System.out.println(SumSquaresOfRange(5, 10));
        	System.out.println(SumSquaresOfRange(10, 5));
        	System.out.println(SumSquaresOfRange(-1, 1));
        	System.out.println(SumSquaresOfRange(0, 0));
        	System.out.println(SumSquaresOfRange(1, -1));
        	System.out.println(SumSquaresOfRange(5, 5));
        	System.out.println(SumSquaresOfRange(-10, -5));
        	System.out.println(SumSquaresOfRange(-5, -10));
        	System.out.println(SumSquaresOfRange(-5, 5));
        	System.out.println(SumSquaresOfRange(5, -5));

        }//End of main

}
