import java.math.BigInteger;


public class QuadraticHashing
{
	int m_nTableSize = 1009;
	DataObject[] m_ObjectArray;
	
	public QuadraticHashing()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public QuadraticHashing(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	
	/**
	 * 
	 * @param strKey
	 * @param objData
	 */
	public void put( String strKey, DataObject objData )
	{
		// track the number of items in the list
		int count = 0;
		
		// count how many items are in the list
		// let's count every time we put, just for safety
 		for ( int i=0; i<m_nTableSize; i++ )
		{
			// is there an object here?
			if ( m_ObjectArray[i] != null )
				// update the counter
				count++;
		}
		
		// table at or more than half full?
		if ( 2*count >= m_nTableSize )
		{
			// resize and rehash
			
			// move old table aside
			DataObject[] m_OldObjectArray = m_ObjectArray;
			
			// create new table
			m_nTableSize += m_nTableSize;
			m_nTableSize = Utility.NextPrime(m_nTableSize);
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
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		int i = 1;
		// quadratically probe
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			// no duplicates allowed
			if (m_ObjectArray[(int)lHash].m_strKey == objData.m_strKey)
				return;
			// need to wrap?
			if ( lHash < m_nTableSize )
				// quadratic increment
				lHash = (lHash + i * i++);
			// wrap at end of table
			if ( lHash >= m_nTableSize )
				lHash = 0;				
		}
		
		// stick the objData at the open spot
		m_ObjectArray[(int)lHash] = objData;
	}
	


/**
 * 
 * @param strKey
 * @returns the first DataObject found that contains strKey
 */
	public DataObject get( String strKey )
	{
		int i = 1;
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		// track start
		long lStart = lHash;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] == null
				|| m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != strKey )
		{
			// don't wrap yet
			if ( lHash < m_nTableSize )
				// quadratic increment
				lHash = (lHash + i * i++);
			// wrap at end of table
			else if ( lHash >= m_nTableSize - 1 )
				lHash = 0;
			
			// hash not found
			if ( lHash == lStart )
				return null;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
}