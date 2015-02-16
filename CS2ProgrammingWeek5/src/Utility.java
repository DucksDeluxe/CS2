
public class Utility 
{
	
	public static long HashFromString( String strString)
	{
		long lHashValue = 0;
		
		for( int i=0; i<strString.length(); i++ )
		{
			lHashValue = (long)strString.charAt(i) + (lHashValue << 6) + (lHashValue << 16) - lHashValue;
		}
		
		return( lHashValue & 0x7fffffff );
	}
	
	public static int NextPrime(int nEval) {
		boolean isPrime = true;
		
		// is this value prime?
		for ( int i=2; i<=nEval/2; i++)
		{
			if ( nEval % i == 0 )
			{
				isPrime = false;
				break;
			}
		}
		
		// if not prime, how about the next?
		if (!isPrime)
			return NextPrime(nEval + 1);
		
		// found prime!
		else
			return nEval;
	}
	
	
}
