
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;

	// minimum interval between landing times
	int m_nK = 3;
	
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
        	// allocate new node
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Insert iff it passes the k test
        	if( PassesKTest(nKeyValue, objNode.GetKeyValue()))
        	{
        		// Set the left node of this object by recursively walking left.
        		objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        		// Set this object as the parent node of its child
        		objNode.GetLeftNode().SetParentNode(objNode);
        	}
        	// Don't insert iff fails k test
        	else
        	{
        		return objNode;
        	}
        }
        
        // Here we need to walk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Insert iff it passes the k test
        	if( PassesKTest(nKeyValue, objNode.GetKeyValue()))
        	{
        		// Set the right node of this object by recursively walking right.
        		objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        		
        		// Set this object as the parent of its child
        		objNode.GetRightNode().SetParentNode(objNode);	
        	}
        	// Don't insert iff fails k test
        	else 
        	{
        		return objNode;
        	}
        }
        
		// recalculate subtree sizes
		ResetSubTreeSizes(objNode, true);
//		ResetRanks(objNode);
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
    	// if we can't find it, return null
    	if ( Search(nKeyValue) == null)
    		return null;
    	
    	// If no children, return null
    	if( !bHasLeftChild(objNode) && !bHasRightChild(objNode))
    	{
    		// point parent's pointer to null instead of this object

    		// this object is the root
    		if( objNode.GetParentNode() == null )
    		{
    			// replace root with max of left it exists
    			if( GetMaxNode( objNode.GetLeftNode() ) != null )
    			{
    				// copy
    				objNode.SetKeyValue(GetMaxNode(objNode.GetLeftNode()).GetKeyValue());
    				objNode.setM_nRank(GetMaxNode(objNode.GetLeftNode()).getM_nRank());
    				
    				// delete old node
    				Delete(GetMaxNode(objNode.GetLeftNode()).GetKeyValue(), GetMaxNode(objNode.GetLeftNode()));
    				// update ranks
//    				ResetRanks(objNode.GetRightNode());
    				
    				
    			}	
    			// left didn't exist, get min of right
    			else if( GetMinNode(objNode.GetRightNode()) != null )
    			{
    				// copy
    				objNode.SetKeyValue(GetMinNode(objNode.GetRightNode()).GetKeyValue());
    				objNode.setM_nRank(GetMinNode(objNode.GetRightNode()).getM_nRank());
    				
    				// delete old node
    				Delete(GetMinNode(objNode.GetLeftNode()).GetKeyValue(), GetMinNode(objNode.GetLeftNode()));
    				
    				// update ranks
//    				ResetRanks(objNode.GetRightNode());

    			}
    				
    					
    		}
    		// This object is right of parent
    		if( objNode.GetParentNode().GetKeyValue() < objNode.GetKeyValue() )
    			// Make parent's right pointer point null
    			objNode.GetParentNode().SetRightNode(null);
    		// This object is left of parent
    		else
    			objNode.GetParentNode().SetLeftNode(null);
    		
    		// recalculate subtree sizes
    		ResetSubTreeSizes(objNode.GetParentNode(), false);
//    		ResetRanks(objNode);
    		
    		return null;
    	}
    	// If only 1 child, update its parent and return that child
    	else if( objNode.GetLeftNode() != null ^ objNode.GetRightNode() != null )
    	{
    		// if left node
    		if( objNode.GetLeftNode() != null)
    		{
    			// point child to parent's parent
    			objNode.GetLeftNode().SetParentNode(objNode.GetParentNode());
    			
    			if( objNode.GetParentNode().GetRightNode() == objNode )
    			{
    				objNode.GetParentNode().SetRightNode(objNode.GetLeftNode());
    			}
    			else
    			{
    				// point parent's parent to child
    				objNode.GetParentNode().SetLeftNode(objNode.GetLeftNode());
    			}
    			
    			// recalculate subtree sizes
        		ResetSubTreeSizes(objNode.GetParentNode(), false);
        		// update rank
//        		ResetRanks(objNode);
        		
    			return objNode.GetLeftNode();
    		}
    		// if right node
    		else
    		{
    			// link child to parent's parent
    			objNode.GetRightNode().SetParentNode(objNode.GetParentNode());
    			
    			if( objNode.GetParentNode().GetRightNode() == objNode )
    			{
    				objNode.GetParentNode().SetRightNode(objNode.GetRightNode());
    			}
    			else
    			{
    				// point parent's parent to child
    				objNode.GetParentNode().SetLeftNode(objNode.GetLeftNode());
    			}
    			
    			// recalculate subtree sizes
        		ResetSubTreeSizes(objNode.GetParentNode(), false);
//        		ResetRanks(objNode);
        		
    			return objNode.GetRightNode();
    		}
    	}
    	// There are 2 children: copy min of right node into deleting node
    	// and call delete on the node that was copied from
    	else
    	{
    		// copy the key value
    		objNode.SetKeyValue(GetMinNode(objNode.GetRightNode()).GetKeyValue());
    		
    		// recalculate subtree sizes
    		//ResetSubTreeSizes(GetMinNode(objNode).GetParentNode(), false);
//    		ResetRanks(objNode);
    		
    		// delete the node that was copied and pass its return up the stack
    		return Delete ( GetMinNode(objNode.GetRightNode()).GetKeyValue(), GetMinNode(objNode.GetRightNode()) );
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
    	if( bHasLeftChild(objNode) )
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
    	if( bHasRightChild(objNode) )
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
    	if( java.lang.Math.abs(nInsert - nCompare) >= m_nK )
    		return true;
    	else
    		return false;
    }

	public int getM_nK() {
		return m_nK;
	}

	public void setM_nK(int m_nK) {
		this.m_nK = m_nK;
	}
	
	//private void ResetRanks( BSTNode objNode )
	{
		
		/*
		// base case (child of leaf)
		if( objNode == null )
			return;
		
		// if the root is passed in, it's the only node
		if( objNode.GetParentNode() == null )
		{
			// it gets rank = 1
			objNode.setM_nRank(1);
		}
		
		// if this is the left node of its parent
		else if( objNode.GetParentNode().GetLeftNode() == objNode )
		{
			// its rank is the subsize of parent - 1
			objNode.setM_nRank(objNode.GetParentNode().getM_nRank() - 1);
		}
		
		// if this is the right node of its parent
		else if( objNode.GetParentNode().GetRightNode() == objNode )
		{
			// check for left child
			if( bHasLeftChild(objNode) )
			{	
				// its rank is the sum of its left child's rank and subsize
				objNode.setM_nRank(objNode.GetLeftNode().getM_nRank()
					+ objNode.GetLeftNode().getM_nSubTreeSize() );
			}
			// if there is no left, then this is parent rank + 1
			else
			{
				objNode.setM_nRank(objNode.GetParentNode().getM_nRank() + 1);
			}
		}
		
		// reset rank all the way down the tree as needed
		ResetRanks(objNode.GetLeftNode());
		ResetRanks(objNode.GetRightNode());
		
		return;
		*/
	}
	
	// This should modify subtree size by stepping up the parents and adding or decreasing
	/**
	 * 
	 * @param objNode the node that is being inserted or deleted
	 * @param bAddifTrue
	 * @return
	 */
	private void ResetSubTreeSizes( BSTNode objNode, boolean bAddifTrue )
	{
		// base case (No parent at the root node)
		if( objNode == null )
			return;
		
		// For insertion
		if( bAddifTrue )
		{
			// check for children
			// if both children
			if( objNode.GetLeftNode() != null && objNode.GetRightNode() != null )
			{
				// increase the size of this subtree
				objNode.setM_nSubTreeSize(objNode.GetLeftNode().getM_nSubTreeSize()
						+ objNode.GetRightNode().getM_nSubTreeSize() + 1);
			}
			// only one child
			else if( objNode.GetLeftNode() != null ^ objNode.GetRightNode() != null )
			{
				// only left
				if( objNode.GetLeftNode() != null )
					objNode.setM_nSubTreeSize(objNode.GetLeftNode().getM_nSubTreeSize() + 1);
				// only right
				else
					objNode.setM_nSubTreeSize(objNode.GetRightNode().getM_nSubTreeSize() + 1);
			}
			
			// recursively do the same for the parent
			ResetSubTreeSizes(objNode.GetParentNode(), bAddifTrue);
		}
		
		// For deletion
		else
		{
			// decrease the size of this subtree
			objNode.setM_nSubTreeSize(objNode.getM_nSubTreeSize() - 1);
			
			// recursively do the same for the parent
			ResetSubTreeSizes(objNode.GetParentNode(), bAddifTrue);
		}
		return;
	}
	
	public boolean bHasLeftChild( BSTNode objNode )
	{
		if( objNode.GetLeftNode() != null )
			return true;
		else 
			return false;
	}
	public boolean bHasRightChild( BSTNode objNode )
	{
		if( objNode.GetRightNode() != null )
			return true;
		else 
			return false;
	}
}
