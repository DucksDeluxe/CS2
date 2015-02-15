import java.util.Hashtable;


public class HashExperiments 
{
	public static void main(String[] args) 
	{
		// Start the clock.	
		long start = System.currentTimeMillis();
		
		
		///////////////////////////////////////////////////////////////////////////
		// JAVA HASH
		/*
		Hashtable<String,DataObject> ht = new Hashtable<String,DataObject>();
		
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			ht.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}
		*/
		///////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////
		// LINEAR HASH
		/*
		LinearHash lh = new LinearHash();
		
		for( int i=0; i<Lists.ListOne.length; i++)
		{
			lh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}
		
		int count = 0;
		for ( int i=0; i<lh.m_nTableSize; i++)
		{
			if ( lh.m_ObjectArray[i] != null )
			{
				System.out.println(lh.m_ObjectArray[i].m_strKey);
				count++;
			}
		}
		System.out.println("The count is: " + count);
		*/
		///////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////
		// QUADRATIC HASH
		/*
		QuadraticHashing qh = new QuadraticHashing();
		
		for( int i=0; i<Lists.ListOne.length; i++)
		{
		qh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}
		
		int count = 0;
		for ( int i=0; i<qh.m_nTableSize; i++)
		{
		if ( qh.m_ObjectArray[i] != null )
		{
		System.out.println(qh.m_ObjectArray[i].m_strKey);
		count++;
		}
		}
		System.out.println("The count is: " + count);
		*/
		///////////////////////////////////////////////////////////////////////////
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}

}
