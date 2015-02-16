
public class DataObject 
{
	public DataObject(String strKey)
	{
		m_strKey = strKey;
	}
	
	String m_strKey = "";
	// Other data here.
	
	public String GetKey() 
	{
		if (m_strKey == null)
			return null;
		else
			return( m_strKey );
	}
}
