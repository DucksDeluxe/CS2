
public class HashExperiments 
{
	public static void main(String[] args) 
	{
		// Start the clock.	
		long start = System.currentTimeMillis();
		DataObject objData;
		String strSearch = "Justin";
		
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
		///*
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
				//System.out.println(lh.m_ObjectArray[i].m_strKey);
				count++;
			}
		}
		//System.out.println("The count is: " + count);
		
		try
		{
			objData = lh.get(strSearch);
			if (objData != null)
				System.out.println("Linear Result: " + objData.GetKey());
			else
				System.out.println("Linear Result: null");
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		/**/
		///////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////
		// QUADRATIC HASH
		///*
		QuadraticHashing qh = new QuadraticHashing();
		
		for( int i=0; i<Lists.ListOne.length; i++)
		{
		qh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}
		
		/*
		count = 0;
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
		
		try
		{
			objData = qh.get(strSearch);
			if (objData != null)
				System.out.println("Quadratic result: " + objData.GetKey());
			else
				System.out.println("Quadratic Result: null");
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		/**/
		///////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////
		// SEPARATE CHAINING
		///*
		SeparateChaining sc = new SeparateChaining();
		
		for( int i=0; i<Lists.ListOne.length; i++)
		{
		sc.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}
		
		objData = sc.get(strSearch);
		if (objData != null)
			System.out.println( "Chaining result: " + objData.GetKey() );
		else
			System.out.println("Chaining result: null");
		
		/**/
		///////////////////////////////////////////////////////////////////////////
				
		System.out.println("Table size: " + sc.get_m_nTableSize());
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}

}
