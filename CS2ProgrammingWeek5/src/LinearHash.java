
public class LinearHash 
{

	int m_nTableSize = 1000;
	DataObject[] m_ObjectArray;
	
	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void put( String strKey, DataObject objData )
	{
		// track the number of items in the list
		int count = 0;
		
		// count how many items are in the list (I realize this is super inefficient)
 		for ( int i=0; i<m_nTableSize; i++ )
		{
			// is there an object here?
			if ( m_ObjectArray[i] != null )
				// update the counter
				count++;
		}
		
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
			
		// create hash for the passed DataObject based on its strKey
		long lHashIndex = Utility.HashFromString(strKey) % m_nTableSize;
		
		// find next empty slot
		// this provides built in collision handling
		while( m_ObjectArray[(int)(lHashIndex)] != null)
		{
			// no duplicates allowed
			if (m_ObjectArray[(int)lHashIndex].m_strKey == objData.m_strKey)
				return;
			// don't wrap yet
			if ( lHashIndex < m_nTableSize )
				lHashIndex++;
			// wrap at end of table
			if ( lHashIndex >= m_nTableSize )
				lHashIndex = 0;				
		}
		
		// stick the objData at the open spot
		m_ObjectArray[(int)lHashIndex] = objData;
	}
/**
 * 
 * @param strKey
 * @returns the first DataObject found that contains strKey
 */
	public DataObject get( String strKey )
	{
		long lHashIndex = Utility.HashFromString(strKey) % m_nTableSize;
		
		// track start
		long lStart = lHashIndex;
		
		while( m_ObjectArray[(int)(lHashIndex)] == null
				|| m_ObjectArray[(int)(lHashIndex)].GetKey() != strKey )
		{
			// don't wrap yet
			if ( lHashIndex < m_nTableSize )
				lHashIndex++;
			// wrap at end of table
			if ( lHashIndex >= m_nTableSize )
				lHashIndex = 0;
			
			// hash not found
			if ( lHashIndex == lStart )
				return null;
		}

		return( m_ObjectArray[(int)(lHashIndex)] );
	}
}
