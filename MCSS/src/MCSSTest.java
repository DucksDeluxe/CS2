public class MCSSTest 
{

	static int MCSS1(int[] List)
	{
		int nMax = 0, nSum = 0;
				
		for(int i=0, j=0; j<List.length; j++ )
		{
				// running sum
				nSum += List[j];
				
				// update
				if( nSum > nMax )
				{
					nMax = nSum;
				}
				// if the sum goes negative
				else if(nSum<0)
				{
					// then a better sequence exists after this
					i=j+1;
					nSum = 0;
				}
		}
		
		return( nMax );
	}
	
	static int MCSS2(int[] List)
	{
		int nMax = 0, nSum = 0;
		
		
		return( nMax );
	}

	static int MCSS3(int[] List)
	{
		int nMax = 0, nSum = 0, i = 0;

		
		return( nMax );
	}
	
	static boolean LookForAMatch1( String strNameToMatch )
	{
		for( int i=0; i<MCSSLists.ListTwo.length; i++ )
		{
			if( strNameToMatch.equalsIgnoreCase( MCSSLists.ListTwo[i] ))
			{
				return( true );
			}
		}
		return( false );
	}
	
	static int Start = 0;
	static boolean LookForAMatch2( String strNameToMatch )
	{

		return( false );
	}
	
	static boolean LookForAMatch3( String strNameToMatch )
	{
		
		return( false );
	}
	
	static public void main(String[] args)
	{
		// These lists are used later.
		MCSSLists.GenerateRandomLists();
		
		// Start the clock.	
		long start = System.currentTimeMillis();
		
		int nMax = MCSS1( MCSSLists.ListSeven );
		System.out.println( "The max is " + nMax );
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
		
	}
	
}
