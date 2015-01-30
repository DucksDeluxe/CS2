import java.util.Random;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek2
{

	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////

	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "VanWinkle,Justin,J3338546");
	}
	
	/**
	 * @param DataIn is an array of integers
	 * @return DataOut contains the integers from DataIn, except sorted
	 */
	static int[] DoBubbleSort( int[] DataIn )
	{
			boolean bDirty;
			int nSwap;
			
			// if we receive an array of length 0, there is nothing to sort
			if (DataIn.length == 0)
				return DataIn;
			
			// create an array that we will sort and pass back
			int[] DataOut = new int[DataIn.length];
			
			// copy data over to our new array
			for (int i=0; i<DataIn.length; i++)
			{
				// copy directly
				DataOut[i] = DataIn[i];
			}
			
			// pass over the whole array until it is completely sorted
			do
			{
				// fresh start
				bDirty = false;
				
				// loop through array (last element doesn't have a value to compare, so skip it)
				for (int i=0; i<DataOut.length - 1; i++)
				{
					// is the next element less than the current?
					if ( DataOut[i] > DataOut[i+1] )
					{
						// note that we need to run again
						bDirty = true;
						
						// copy val i
						nSwap = DataOut[i];
						// move val i+1 over
						DataOut[i] = DataOut[i+1];
						// replace val i+1 with swap val
						DataOut[i+1] = nSwap;
					}	
				}
			}while(bDirty);
			
			// we are done
			return DataOut;

	}
    
	/**
	 * 
	 * @param DataIn is an array of integers
	 * @return DataOut is an array of integers that have been sorted
	 */
    static int[] DoInsertSort( int[] DataIn )
	{
			int nSwap;
			int j;
			
			// if we receive an array of length 0, there is nothing to sort
			if (DataIn.length == 0)
				return DataIn;
			
			// create an array that we will sort and pass back
			int[] DataOut = new int[DataIn.length];
			
			// copy data over to our new array
			for (int i=0; i<DataIn.length; i++)
			{
				// copy directly
				DataOut[i] = DataIn[i];
			}
			
			// loop through array
			for (int i=1; i<DataOut.length; i++)
			{
				j = i;
				
				// swap the value all the way to its proper position
				while ( DataOut[j] < DataOut[j-1])
				{
					// swap
					nSwap = DataOut[j];
					DataOut[j] = DataOut[j-1];
					DataOut[j-1] = nSwap;
					j--;
					if (j < 1)
						break;
				}

			}
			return DataOut;
	}

    /**
     * 
     * @param DataIn is an array of integers
     * @return DataOut is a min-heap
     */
    	static int[] DoHeapSort( int[] DataIn )
	{
		// added in recitation
		nHeap = DataIn;
		heapSize = DataIn.length;
		BuildHeap();
		return nHeap;
	}

	// The heap (min-heap).
	static int[] nHeap;			// from array, parent of node = (nodeIndex - 1) / 2
								// from array, left child of node = nodeIndex * 2 + 1
								// from array, right child = nodeIndex * 2 + 2
	
	// Randomly generated numbers to be used as input.
	static int[] nRandomNumbers;

	// The size of the heap, nHeap. The size of the heap may be smaller than
	// nHeap.length. The heap is comprised of the elements at indexes between 0
	// and heapSize-1 of nHeap.
	static int heapSize;

	// Methods to get index of parent, left child, and right child of node at
	// index nIndex of the heap  (zero-based index). The indexes outputted by
	// GetLeft and GetRight must be verified to actually exist within the heap
	// (use heapSize to determine this).

	/**
	 * 
	 * @param nIndex
	 * @return
	 */
	private static int GetParentIndex(int nIndex)
	{
			// return parent
			return (nIndex - 1) / 2;		
	}

	/**
	 * 
	 * @param nIndex
	 * @return
	 */
	static int GetLeft( int nIndex )
	{
		// show there is no child
		if (nIndex * 2 + 1 >= nHeap.length - 1)
			return -1;
		
		// return left child
		return nIndex * 2 + 1;
	}
	
	/**
	 * 
	 * @param nIndex
	 * @return
	 */
	static int GetRight( int nIndex )
	{
		// show there is no child
		if (nIndex * 2 + 2 > nHeap.length - 1)
			return -1;
		
		// return right child
		return nIndex * 2 + 2;
	}

	// Function to swap two numbers in an array. Auxiliary method to SiftUp and
	// Heapify.
	/**
	 * 
	 * @param arr is an array of integers
	 * @param i is an index
	 * @param j in an index
	 */
	public static void swap(int arr[], int i, int j)
	{
		
		int nSwap;
		// store val i
		nSwap = arr[i];
		// swap val j into slot i
		arr[i] = arr[j];
		// drop old val i into slot j
		arr[j] = nSwap;
	}
	
	/**
	 * 
	 */
	static void BuildHeap()
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left

		// Input: static field heapSize 

		// Output: modified nHeap such that it represents a min-heap  

		// Complexity: O(heapSize)

		// Start at the parent of the last node (i.e., the right-most,
		// bottom-most node in the tree that is not a leaf)
		//    - the last node is heapSize - 1
		//    - the parent of any node is (nodeIdx - 1) / 2
		//    - therefore the parent of the last node is
		//      (heapSize - 2) / 2
		//    - equivalent to GetParentIndex(heapSize-1)

		// Algorithm:

		// for i = GetParentIndex(heapSize-1) DOWN TO  0:
		//      Heapify(i)
		
		// loop through heap
		for (int i = GetParentIndex(heapSize-1); i > -1; i--)
		{
			// heapify each element
			Heapify(i);
		}
	}
	
	/**
	 * 
	 * @param nIndex is an index value
	 */
	static void Heapify( int nIndex )
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left

		// Input: nIndex, an index into nHeap

		// Output: modified nHeap such that element nIndex has been "sifted
		// down" until that element is less than its children or that element
		// is a leaf 

		// Another name for this method might be "SiftDown".

		// Algorithm:

		// If nIndex is a leaf node (has no children), then there is nothing to
		// do (use heapSize to determine this)
		
		// leftIdx <- GetLeft(nIndex)
		// rightIdx <- GetRight(nIndex)

		// smallest <- either the index leftIdx, rightIdx, or nIndex whose
		// corresponding heap node value is the smallest out of these three
		// (however, ensure that leftIdx and rightIdx are actually children;
		// use heapSize to determine this)
		
		// if smallest != nIndex:
		//    COMMENT: This means the node at nIndex has a child smaller than
		//    itself at index smallest. This smallest child needs to come up
		//    and replace the node at nIndex, and the node at nIndex needs to
		//    come down to replace the smallest child. We then repeat the
		//    process recursively at index smallest.

		//    swap node values at indexes smallest and nIndex
		//    Heapify(smallest)

		// do you know who your children are?
		int iL = GetLeft(nIndex);
		int iR = GetRight(nIndex);
	
		// don't have kids?
		if ( iL == -1 && iR == -1)
		{
			// let's heapify your neighbor
			Heapify(nIndex-1);
		}
		
		// do you have two children?
		else if ( iL != -1 && iR != -1)
		{
			// left child is smallest?
			if (nHeap[iL] < nHeap[iR])
			{
				// child smaller than parent?
				if (nHeap[iL] < nHeap[nIndex])
				{
					// swap
					swap(nHeap, iL, nIndex);
					// repeat
					Heapify(iL);
				}
			}
			// right child is smallest?
			else if (nHeap[iR] < nHeap[iL])
			{
				// child smaller than parent?
				if (nHeap[iR] < nHeap[nIndex])
				{
					// swap
					swap(nHeap, iR, nIndex);					
					// repeat
					Heapify(iR);
				}
			}
		}
		
		// left child but no right
		else if ( iL != -1 && iR == -1)
		{
			// child smaller than parent?
			if (nHeap[iL] < nHeap[nIndex])
			{
				// swap
				swap(nHeap, iL, nIndex);
				// repeat
				Heapify(iL);
			}
		}
	}


	// The following methods, AddElement and its auxiliary method SiftUp, are
	// not used with DoHeapSort. 

	// DoHeapSort uses BuildHeap instead, which is a more efficient way (O(n))
	// to build a heap starting from a list of numbers than AddElement. The way
	// to build a heap using AddElement is to start with an empty heap and
	// repeatedly add each element into the heap. This way is O(nlogn).
	// However, AddElement is useful to add new elements into an existing heap
	// once it has been built with BuildHeap.

	/**
	 * 
	 * @param nNumber is a value to be added to the heap
	 */
	static void AddElement( int nNumber )
	{
		// Input: static fields nHeap, heapSize. nHeap must already be a valid
		// min-heap.

		// Input: nNumber, a new node to add to the heap

		// Output: heap with nNumber added, heapSize inceased by 1

		// Complexity: O(log(heapSize))

		// Precondition: heapSize+1 <= nHeap.length
		// If this is not the case, before running the algorithm, create a new
		// array that is double the size of nHeap, copy the elements of nHeap
		// into it, and assign nHeap to be the new larger array

		// is there enough room?
		if(!(heapSize+1 <= nHeap.length))
		{
			int i = 0;
			// create new array
			int[] newHeap = new int[nHeap.length*2];
			// loop thru arrays
			while (i < nHeap.length)
			{
				// copy data
				newHeap[i] = nHeap[i];
				i++;
			}
			// assign new heap
			nHeap = newHeap;
		}
		
		// Algorithm:

		// Increase heapSize by 1
		// Copy nNumber to index heapSize-1 of the heap
		// SiftUp(heapSize-1)
		
		// Copy nNumber to index heapSize of the heap
		nHeap[heapSize] = nNumber;
		// Increase heapSize by 1
		heapSize++;
		// SiftUp
		SiftUp(heapSize-1);
	}
	
	/**
	 * 
	 * @param nNodeIndex is an index
	 */
	static void SiftUp( int nNodeIndex )
	{
		// repeatedly sift up the element at nNodeIndex as long as its parent
		// node is greater or the element is the root (this is similar to
		// Heapify but it moves the element upwards instead of downwards)

		// base case
		if (nNodeIndex == 0)
			return;
		
		// do we need to swap?
		if ( nHeap[nNodeIndex] < nHeap[GetParentIndex(nNodeIndex)])
		{
			swap(nHeap, nNodeIndex, GetParentIndex(nNodeIndex));
			// do it again
			SiftUp(GetParentIndex(nNodeIndex));			
		}
		
		return;
	}

	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////

	/**
	 * 
	 * @param n
	 */
	public static void setRandomArray(int n)
	{
		Random rnd = new Random();
		nRandomNumbers = new int[n];
		for( int i=0; i<nRandomNumbers.length; i++ )
		{
			nRandomNumbers[i] = rnd.nextInt( 500 );
		}
	}
	
	public static void DisplayIntegerArray(int[] array)
	{
		System.out.print("[ ");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.print("]");
		System.out.println(" ");
	}
	
	public static void main(String[] args)
	{
		// Code to test bubble sort.
		setRandomArray(15);
		int[] bubbleStudentOut1 = DoBubbleSort(nRandomNumbers);
		//int[] bubbleStudentOut2 = DoBubbleSort(BubbleIn);
		DisplayIntegerArray(bubbleStudentOut1);
		//DisplayIntegerArray(bubbleStudentOut2);
		
		// Code to test insert sort.
		setRandomArray(15);
		int[] insertStudentOut1 = DoInsertSort(nRandomNumbers);
		//int[] insertStudentOut2 = DoInsertSort(InsertIn);
		DisplayIntegerArray(insertStudentOut1);
		//DisplayIntegerArray(insertStudentOut2);

		// Code to test heap sort.
		setRandomArray(15);
		int[] heapStudentOut1 = DoHeapSort(nRandomNumbers);
		//int[] heapStudentOut2 = DoHeapSort(HeapIn);
		DisplayIntegerArray(heapStudentOut1);
		//DisplayIntegerArray(heapStudentOut2);

		 //Code to test AddElement
		int[] HeapIn ={7,5,3,1,9,0,2,4,6,8,499,0};
		nHeap = new int[HeapIn.length];
		heapSize = 0;
		for (int i = 0; i < HeapIn.length; i++)
		{
			AddElement(HeapIn[i]);
		}

		// Show the array representation of the min-heap
		DisplayIntegerArray(nHeap);
		AddElement(0);
		DisplayIntegerArray(nHeap);

	}
}