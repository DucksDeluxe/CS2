import java.util.LinkedList;

public class SeparateChaining 
{
	private static int m_nTableSize = 127;
	private static LinkedList[] m_lists;
	
	public SeparateChaining()
	{
		this( get_m_nTableSize() );
	}
	
	public SeparateChaining(int nTableSize)
	{
		// create the array of linked lists
		m_lists = new LinkedList[ get_m_nTableSize() ];
		for (int i=0; i<m_lists.length; i++)
			// create a linked list at each index
			m_lists[i] = new LinkedList();
	}
	
	/**
	 * 
	 * @param strKey
	 * @param objData
	 */
	public void put( String strKey, DataObject objData )
	{
		// get the hash index
		long lHashIndex = Utility.HashFromString(strKey) % get_m_nTableSize();
		
		// does this key already exist?
		if (get(strKey) != null)
			// reject the duplicate
			return;
		
		// get the list we hashed to
		LinkedList putList = m_lists[ (int)lHashIndex ];
		// add the DataObject to this list
		putList.add(objData);

		// if we just made a node 10 or more deep, resize
		if ( putList.size() >= 10 )
		{
			// move current list aside
			LinkedList[] oldLists = m_lists;
			// find new table size
			int newSize = NextPrime( 2 * get_m_nTableSize() );
			set_m_nTableSize( newSize );
			// instantiate new array of lists
			m_lists = new LinkedList[ get_m_nTableSize() ];
			// create new lists
 			for (int i=0; i<m_lists.length; i++)
				m_lists[i] = new LinkedList();
			
			// rehash list by list
			for(int i=0; i<oldLists.length; i++)
			{	
				// node by node
				for (int j=0; j<oldLists[i].size(); j++)
				{
					DataObject node = (DataObject)oldLists[i].get(j);
					put (node.m_strKey, node);
				}
			}		
		}
	}
	
/**
 * 
 * @param strKey
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws NoSuchFieldException 
 * @returns the first DataObject found that contains strKey
 */
	
	public DataObject get( String strKey ) 
	{
		// get hash index
		long lHashIndex = Utility.HashFromString(strKey) % get_m_nTableSize();
		
		// obtain the list
		LinkedList getList = new LinkedList(m_lists[(int)lHashIndex]);
		
		// TESTING
		//System.out.println("getList size: " + getList.size());

		int i=0;
		
		// iterate through the list
		while( i < getList.size() )
		{
			
			// obtain the node
			DataObject node = (DataObject)getList.get(i);
			
			//TESTING
			//System.out.println("This node = " + node.m_strKey);
			
			// if we found the match
			if (node.m_strKey == strKey)
				return node;
			// point to the next node
			i++;
		}
		
		// no match found
		return null;
	}
	
	public static int NextPrime(int nEval) 
	{
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
	

	public static int get_m_nTableSize() 
	{
		return m_nTableSize;
	}
	
	public void set_m_nTableSize(int m_nTableSize) 
	{
		this.m_nTableSize = m_nTableSize;
	}
}
