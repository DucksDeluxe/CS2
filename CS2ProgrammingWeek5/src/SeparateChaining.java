import java.util.LinkedList;

public class SeparateChaining 
{
	private static int m_nTableSize = 127;
	//TODO set private
	public LinkedList[] m_lists;
	
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
			m_lists[i] = new LinkedList<>();
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
		
		//TODO reject duplicates (use get to search for a match - be sure to compare strKeys and not hashes)
		
		// get the list we hashed to
		LinkedList putList = m_lists[ (int)lHashIndex ];
		// put the DataObject in this list
		putList.addFirst(objData);

		// if we just made a node 10 or more deep, resize
		if ( putList.size() >= 10 )
		{
			// move current list aside
			LinkedList[] oldLists = m_lists;
			// find new table size
			set_m_nTableSize( Utility.NextPrime( 2 * get_m_nTableSize() ) );
			// instantiate new array of lists
			m_lists = new LinkedList[ get_m_nTableSize() ];
			// create new lists
 			for (int i=0; i<m_lists.length; i++)
				m_lists[i] = new LinkedList<>();
			
			// rehash list by list
			for(int i=0; i<oldLists.length; i++)
			{	
				// node by node
				for (int j=0; j<oldLists[i].size(); j++)
				{
					DataObject newObjData = new DataObject(oldLists[i].removeFirst().toString());
					put (newObjData.m_strKey, newObjData);
				}
			}		
		}
		
		
		/*
		// table more than half full?
		if ( 2*count > m_nTableSize )
		{
			// resize and rehash
			
			// move old table aside
			DataObject[] m_OldObjectArray = m_ObjectArray;
			
			// create new table
			m_nTableSize += m_nTableSize;
			m_ObjectArray = new DataObject[m_nTableSize];
			
			// rehash entire table
			for ( int i=0; i<m_OldObjectArray.length; i++ )
			{
				// hash only slots with data objects
				if(m_OldObjectArray[i] != null)
					put(m_OldObjectArray[i].m_strKey, m_OldObjectArray[i]);
				else 
					continue;
			}
		}
		*/	
		
		/*
		// find next empty slot
		// this provides built in collision handling
		while( m_ObjectArray[(int)(lHashIndex)] != null)
		{
			// no duplicates allowed
			if (m_ObjectArray[(int)lHashIndex].m_strKey == objData.m_strKey)
				return;
			// don't wrap yet
			if ( lHashIndex < get_m_nTableSize() )
				lHashIndex++;
			// wrap at end of table
			if ( lHashIndex >= get_m_nTableSize() )
				lHashIndex = 0;				
		}
		*/
		
		// stick the objData at the open spot
		//m_ObjectArray[(int)lHashIndex] = objData;
	}
	
/**
 * 
 * @param strKey
 * @returns the first DataObject found that contains strKey
 */
	/*
	public DataObject get( String strKey )
	{
		long lHashIndex = Utility.HashFromString(strKey) % get_m_nTableSize();
		
		
		
		// track start
		long lStart = lHashIndex;
		
		while( m_ObjectArray[(int)(lHashIndex)] == null
				|| m_ObjectArray[(int)(lHashIndex)].GetKey() != strKey )
		{
			// don't wrap yet
			if ( lHashIndex < get_m_nTableSize() )
				lHashIndex++;
			// wrap at end of table
			else if ( lHashIndex >= get_m_nTableSize() - 1 )
				lHashIndex = 0;
			
			// hash not found
			if ( lHashIndex == lStart )
				return null;
		}

		return( m_ObjectArray[(int)(lHashIndex)] );
		
	}
	*/

	public static int get_m_nTableSize() 
	{
		return m_nTableSize;
	}
	
	public void set_m_nTableSize(int m_nTableSize) 
	{
		this.m_nTableSize = m_nTableSize;
	}
}
