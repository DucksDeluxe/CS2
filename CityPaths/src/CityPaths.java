import java.util.Iterator;
import java.util.Scanner;

public class CityPaths 
{
	Scanner in = new Scanner("input.txt");
	private int nTestCases;
	
	public CityPaths() 
	{
		
		nTestCases = in.nextInt();
		
		
	}
	
	public void BuildCity()
	{
		
	}
	
	public void Walk()
	{
		
	}
	
	/**
	 * 
	 * @param nDataSet
	 * @param CaseAndPaths
	 */
	public void Output(int nDataSet, int[][] CaseAndPaths)
	{
		System.out.println("Data Set " + nDataSet + ":\n\n");
		
		for (int i = 0; i < CaseAndPaths.length; i++)
		{
			if (CaseAndPaths[i][1] != 1)
				System.out.println("  Test Case " + CaseAndPaths[i][0] + ": Nick can take " + CaseAndPaths[i][1] + " perfect paths." );
			else 
				System.out.println("  Test Case " + CaseAndPaths[i][0] + ": Nick can take " + CaseAndPaths[i][1] + " perfect path." );
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
