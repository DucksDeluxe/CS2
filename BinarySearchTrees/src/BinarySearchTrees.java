
public class BinarySearchTrees 
{
	
	static BST m_objBST = new BST();
	
	public static void main(String[] args) 
	{
		int[] insert = {49,62,10,93,15,7,36,92,84,29};
		int[] delete = {10};//{84,10,62,93,62,92};
		
		// BUILD BST
		for( int i=0; i<insert.length; i++ )
			m_objBST.Insert(insert[i]);
		
		// DELETE FROM BST
		for( int i=0; i<delete.length; i++ )
			m_objBST.Delete(delete[i]);
		
		// PRINT FROM BST
		for ( int i=0; i<insert.length; i++ )
		{
			if( m_objBST.Search(insert[i]) == null)
				System.out.println(insert[i] + " was not found in BST");
			else
				System.out.println(insert[i] + "=" + m_objBST.Search(insert[i]).GetKeyValue() + " Subtree Size: " + m_objBST.Search(insert[i]).getM_nSubTreeSize() + " Rank: " + m_objBST.Search(insert[i]).getM_nRank());
		}
		
	}
	
}
