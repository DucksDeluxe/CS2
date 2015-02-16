
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
			m_nTableSize = NextPrime(m_nTableSize);
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
		
		int i = 1;
		// quadratically probe
		while( m_ObjectArray[(int)(lHashIndex)] != null)
		{
			// no duplicates allowed
			if (m_ObjectArray[(int)lHashIndex].m_strKey == objData.m_strKey)
				return;
			// need to wrap?
			if ( lHashIndex < m_nTableSize )
				// quadratic increment
				lHashIndex = (lHashIndex + i * i++);
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
		
		int i = 1;
		while( m_ObjectArray[(int)(lHashIndex)] == null
				|| m_ObjectArray[(int)(lHashIndex)].GetKey() != strKey )
		{
			// don't wrap yet
			if ( lHashIndex < m_nTableSize )
				// quadratic increment
				lHashIndex = (lHashIndex + i * i++) % m_nTableSize;
			// wrap at end of table
			if ( lHashIndex >= m_nTableSize )
				lHashIndex = (0 + i * i++) % m_nTableSize;
			
			// hash not found
			if ( lHashIndex == lStart )
				return null;
		}

		return( m_ObjectArray[(int)(lHashIndex)] );
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
