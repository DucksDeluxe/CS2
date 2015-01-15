import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek1 
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
	
	// Write a method that recursively prints out all the 
	//   items in a linked list, in regular order. Fill in 
	//   the prototype below:

	static void PrintRec(DataStruct ds) 
	{
		// print value in current node
		System.out.println(ds.KeyValue);
		// if there is a next node
		if (ds.Next != null)
		{
			// recall this method with the next node
			PrintRec(ds.Next);
		}
	}

	// Write a function that recursively prints out all 
	//   the items in a linked list, in reverse order. 
	//   Fill in the prototype below:

	static void PrintBackRec(DataStruct ds) 
	{
		// print value in current node
		System.out.println(ds.KeyValue);
		// if there is a previous node
		if (ds.Prev != null)
		{
		PrintBackRec(ds.Prev);
		}
	}

	// Write a function that iterates through a linked list 
	//   and adds 5 to each even number in the list and 
	//   subtracts 4 from each odd number in the list. For
	//   each member of the list print the before and after values.

	static void EditList(DataStruct ds) 
	{
		// iterate
		while(true)
		{
			// print before value
			System.out.println(ds.KeyValue);
			
			// if even
			if(ds.KeyValue % 2 == 0)
				// add 5
				ds.KeyValue += 5;
			// if odd
			else
				// subtract 4
				ds.KeyValue -= 4;
			
			// print after value
			System.out.println(ds.KeyValue);
			
			// if the next node exists
			if (ds.Next != null)
				// move to next node
				ds = ds.Next;
			// if there are no more nodes
			else
				// break out
				break;
		}
	}
	
	// Write a recursive function that takes in two linked 
	//   lists and determines if the lists are equivalent. 
	//   For two lists to be equivalent, they must have the 
	//   same number of items and each corresponding item must 
	//   be equal. Thus, the lists 3, 5, 7 and 3, 5, 7 are 
	//   equal, but 3, 7, 5 does not equal 3, 5, 7 or 3, 7. 
	//   (Hint: As a base case, two lists are equal if they 
	//   are both NULL, and not equal if one is NULL and the 
	//   other isn’t.) Return 1 if the two lists passed in are 
	//   equal, and 0 otherwise.

	static int EqualLists(DataStruct list1, DataStruct list2) 
	{
		int nMatch = 0;
		
		// if the values match
		if (list1.KeyValue == list2.KeyValue)
		{
			// if there is a next node in both
			if (list1.Next != null && list2.Next != null)
			{
				// call with next nodes
				EqualLists(list1.Next, list2.Next);
				// this one matched, so it passes a 1 up
				return 1;
			}
			// if both are at last node
			else if (list1.Next == null && list2.Next == null) 
				return 1;
			
			// if only one has a next node
			else return 0;
		}
		// the values don't match			
		else return 0;
		
	}

	// Write a function that takes in a pointer to the front 
	//   of a linked list and returns 1 if all the nodes in 
	//   the linked list are in sorted order (from smallest 
	//   to largest, with repeats allowed), and 0 otherwise. 
	//   The prototype is given below:

	static int InOrder(DataStruct list) 
	{
		// iterate through each node
		while (list.Next != null)
		{
			// if the current value is less than or equal to the next value
			if (list.KeyValue <= list.Next.KeyValue)
			{
				// point to the next node
				list = list.Next;
				// do it again
				continue;
			}
			// if the current value is greater than
			else
				// then it's not in order
				return 0;
		}		
		// if you made it here, the list is in order
		return 1;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		DataStruct ds = new DataStruct();
		for( int i=0; i<4; i++ )
		{
			ds.AddToEndOfList(new DataStruct());
		}
		
		DataStruct ds2 = new DataStruct();
		ds2.KeyValue = 1;
		for( int i=0; i<4; i++ )
		{
			DataStruct ds3 = new DataStruct();
			ds3.KeyValue = 2 + i;
			ds2.AddToEndOfList(ds3);
		}

		// Test code here...
		PrintRec( ds );

		PrintBackRec( DataStruct.Last );

		EditList( ds );

		int nEqual = EqualLists( ds, ds2 );
		System.out.println(nEqual);

		int nInOrder = InOrder(ds);
		System.out.println(nInOrder);

		nInOrder = InOrder(ds2);
		System.out.println(nInOrder);



	}

}