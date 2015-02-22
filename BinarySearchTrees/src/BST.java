import java.math.*;
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;

	// minimum interval between landing times
	private static int m_nK = 3;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
 
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// If the k test passes, continue
        	if( PassesKTest(nKeyValue, objNode.GetKeyValue()) )
        	{
        		// Set the left node of this object by recursively walking left.
        		objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        	}
        	// If the k test fails, don't insert
        	else
        		return null;
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// If the k test passes, continue
        	if( PassesKTest(nKeyValue, objNode.GetKeyValue()) )
        	{
        		// Set the right node of this object by recursively walking right.
        		objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        	}
        	// If the k test fails, don't insert
        	else
        		return null;
        }
        
        return( objNode );
    }
    
    /**
     * 
     * @param nKeyValue
     */
    public void Delete( int nKeyValue )
    {
    	Delete( nKeyValue, Search( nKeyValue ) );
    }
    
    /**
     * 
     * @param nKeyValue
     * @param objNode
     * @return
     */
    protected BSTNode Delete( int nKeyValue, BSTNode objNode )
    {
    	// If no children, return null
    	if( objNode.GetLeftNode() == null && objNode.GetRightNode() == null)
    	{
    		return null;
    	}
    	// If only 1 child, return that child
    	else if( objNode.GetLeftNode() != null ^ objNode.GetRightNode() != null )
    	{
    		// Which child is not null?
    		if( objNode.GetLeftNode() != null)
    		{
    			return objNode.GetLeftNode();
    		}
    		else
    		{
    			return objNode.GetRightNode();
    		}
    	}
    	// There are 2 children: copy min of right node into deleting node
    	// and call delete on the node that was copied from
    	else
    	{
    		// copy the key value
    		objNode.SetKeyValue(GetMinNode(objNode).GetKeyValue());
    		
    		// delete the node that was copied and pass its return up the stack
    		return Delete ( GetMinNode(objNode).GetKeyValue(), GetMinNode(objNode) );
    	}
    }
    
    // This  method find the node with the lowest key value from the passed node
    private BSTNode GetMinNode( BSTNode objNode )
    {  	
    	/*LOGIC NOTES*/
    	// Look for min by keeping left from the passed node
    	// Never go right, because anything right of a parent
    	//		is greater than the parent
    	// When the recursively called node has no left child
    	//		it is the minimum
    	
    	// If there is a left child, recursively call for its min
    	if( objNode.GetLeftNode() != null )
    	{
    		return GetMinNode( objNode.GetLeftNode() );
    	}
    	
    	// If there are no children, this is the min node
    	// return it back up the stack
    	else
    	{
    		return objNode;
    	}
    }
    
    private BSTNode GetMaxNode( BSTNode objNode )
    {
    	// If there is a right node, recursively call for its max
    	if( objNode.GetRightNode() != null )
    	{
    		return GetMaxNode( objNode.GetRightNode() );
    	}
    	
    	// If there are no children, this is the max node
    	// return it back up the stack
    	else
    	{
    		return objNode;
    	}
    }
    
    
    // This method checks that two keys pass the k test
    private boolean PassesKTest ( int nInsert, int nCompare )
    {
    	if( java.lang.Math.abs(nInsert - nCompare) < m_nK )
    		return true;
    	else
    		return false;
    }

	public int getM_nK() {
		return m_nK;
	}

	public void setM_nK(int m_nK) {
		BST.m_nK = m_nK;
	}
    
}
