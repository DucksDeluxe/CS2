import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;


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
		
		
		DataObject objData;
		try
		{
			objData = lh.get("Justin");
			if (objData != null)
				System.out.println(objData.GetKey());
			else
				System.out.println("null");
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		/**/
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
		
		DataObject objData;
		try
		{
			objData = qh.get("Justin");
			if (objData != null)
				System.out.println(objData.GetKey());
			else
				System.out.println("null");
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
		
		sc.put( Lists.ListTwo[0], new DataObject(Lists.ListTwo[0]));
		sc.put( Lists.ListTwo[1], new DataObject(Lists.ListTwo[1]));
		
		/*
		int count = 0;
		for ( int i=0; i<sc.m_lists.length; i++)
		{
			
		if ( sc.m_lists[i] != null )
		{
		System.out.println(sc.m_lists[i].iterator());
		count++;
		}
		}
		System.out.println("The count is: " + count);
		/**/
		
		/*
		DataObject objData;
		try
		{
		objData = sc.get("Justin");
		if (objData != null)
		System.out.println(objData.GetKey());
		else
		System.out.println("null");
		}
		catch (Exception e)
		{
		System.err.println(e.getMessage());
		}
		/**/
		///////////////////////////////////////////////////////////////////////////
				
		System.out.println(sc.get_m_nTableSize());
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}

}
