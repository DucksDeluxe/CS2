
//Justin VanWinkle


public class MakeChange 
{
	
	static int nDenoms[] = { 25, 10, 5, 1 };
	static int nDenoms2[] = { 10, 6, 1 };
	
	static int MakeTheChange( int nSum, int nIndex, int[] nDenominations ) 
	{
		
		if( nSum <= 0 )
		{
			return( 0 );
		}
		
		if( nSum >= nDenominations[nIndex] )
		{
			nSum -= nDenominations[nIndex];
		}
		else
		{
			nSum -= nDenominations[++nIndex];
		}
		
		return( 1 + MakeTheChange( nSum, nIndex, nDenominations  ) );
	}
	
	static int MakeTheChangeOptimally( int nSum, int nIndex, int nCount, int[] nDenominations ) 
	{
		
		if( nSum == 0 )
		{
			return( nCount );
		}
		
		if( nSum < 0 ||
			nIndex >= nDenominations.length )
		{
			return( 0 );
		}
		
		int nCountUsingIndex = MakeTheChangeOptimally( nSum - nDenominations[nIndex], nIndex, nCount + 1, nDenominations );
	    int nCountWithoutUsingIndex = MakeTheChangeOptimally( nSum, nIndex + 1, nCount, nDenominations );
	    
	    if( nCountUsingIndex == 0 ) 
	    {
	        return( nCountWithoutUsingIndex );
	    }
	    
	    if( nCountWithoutUsingIndex == 0 )
	    {
	        return( nCountUsingIndex );
	    }
	    
	    return( Math.min( nCountUsingIndex, nCountWithoutUsingIndex ) );
	}
	
	static int BottomUpMakeTheChangeOptimally( int nSum, int[] nDenominations ) 
	{
		
		if( nSum <= 0 )
		{
			return( 0 );
		}

		int[] nCoins = new int[nSum+1];
		for( int s=1; s<=nSum; s++ )
		{
			nCoins[s] = Integer.MAX_VALUE;
			for( int j=0; j<nDenominations.length; j++ )
			{
				if( nDenominations[j] <= s &&
					1 + nCoins[s-nDenominations[j]] < nCoins[s] )
				{
					nCoins[s] = 1 + nCoins[s-nDenominations[j]];
				}				
			}
		}
		
	    return( nCoins[nSum] );
	}
	
	public static int minCoins(int nChangeDesired, int[] nDenominations)
	{
		int actualAmount;
		int inf = Integer.MAX_VALUE-1;
		
		int[][] nCoins = new int[nDenominations.length + 1][nChangeDesired + 1];		
		
		// initialize with infinity
		for(int j=1; j<nChangeDesired + 1; j++)
		{
			nCoins[0][j] = inf;
		}
		
		for(int nPosition = 1; nPosition <= nDenominations.length; nPosition++)
		{
			for(int nCurAmount = 1; nCurAmount <= nChangeDesired; nCurAmount++)
			{
				// check next option
				if(nCurAmount - nDenominations[nPosition - 1] >= 0)
					actualAmount = nCoins[nPosition][nCurAmount - nDenominations[nPosition - 1]];
				else
					actualAmount = inf;
				
				// if new option fits, place it.  otherwise, carry over.
				nCoins[nPosition][nCurAmount] = Math.min(nCoins[nPosition - 1][nCurAmount], actualAmount + 1);
			}
		}
		
		return nCoins[nDenominations.length][nChangeDesired];
	}
	
	public static void main(String[] args) 
	{
		long lStart = System.currentTimeMillis();
		int nCoins = MakeTheChange( 13, 0, nDenoms2 );
		long lEllapsed = System.currentTimeMillis() - lStart;
		System.out.println( "Coins:" + nCoins + ", Time:" + lEllapsed );
		
		lStart = System.currentTimeMillis();
		nCoins = MakeTheChangeOptimally( 13, 0, 0, nDenoms2 );
		lEllapsed = System.currentTimeMillis() - lStart;
		System.out.println( "Coins:" + nCoins + ", Time:" + lEllapsed );
		
		lStart = System.currentTimeMillis();
		nCoins = BottomUpMakeTheChangeOptimally( 13, nDenoms2 );
		lEllapsed = System.currentTimeMillis() - lStart;
		System.out.println( "Coins:" + nCoins + ", Time:" + lEllapsed );
		
		lStart = System.currentTimeMillis();
		nCoins = minCoins( 13, nDenoms2 );
		lEllapsed = System.currentTimeMillis() - lStart;
		System.out.println( "Coins:" + nCoins + ", Time:" + lEllapsed );
	}
}
