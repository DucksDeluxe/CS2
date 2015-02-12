
public class LinearHash 
{
	int m_nTableSize = 10000;
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
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			lHash++;
		}
		
		m_ObjectArray[(int)lHash] = objData;
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != strKey)
		{
			lHash++;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
}
