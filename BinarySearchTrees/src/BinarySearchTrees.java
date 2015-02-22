
public class BinarySearchTrees 
{
	
	static BST m_objBST = new BST();
	
	public static void main(String[] args) 
	{
		int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,20,31,32,33,34,35,36,37,38,39,40};
		int[] scrambled = {49,62,10,93,15,7,36,92,84,29};
		int[] delete = {};
		
		// BUILD BST
		for( int i=0; i<scrambled.length; i++ )
			m_objBST.Insert(scrambled[i]);
		
		// DELETE FROM BST
		for( int i=0; i<delete.length; i++ )
			m_objBST.Delete(delete[i]);
		
		// PRINT FROM BST
		for ( int i=0; i<scrambled.length; i++ )
		{
			if( m_objBST.Search(scrambled[i]) == null)
				System.out.println(scrambled[i] + " was not found in BST");
			else
				System.out.println(scrambled[i] + "=" + m_objBST.Search(scrambled[i]).GetKeyValue());
		}
		
	}
	
}
