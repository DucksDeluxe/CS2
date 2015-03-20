import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class CityPaths 
{
	
	private int m_nCitySize = 10;
	private LinkedList<Integer>[] m_nIntersections;
	private LinkedList<Integer>[] m_nBadIntersections;
	
	public CityPaths(String filename) throws Exception 
	{
		Scanner in = new Scanner(new File(filename));
		
		// get # test cases
		int nNumTestCases = in.nextInt();
		// get # bad intersections
		int nNumBadIntersections = in.nextInt();
		
		// array of lists containing bad intersections
		m_nBadIntersections = new LinkedList[m_nCitySize];
		// populate bad intersections
		for (int i=0; i<nNumBadIntersections; i++)
		{
			m_nBadIntersections[in.nextInt()].add(in.nextInt());		
		}
		
		// create city
		m_nIntersections = new LinkedList[m_nCitySize];
		// populate intersections
		for (int i=0; i<m_nCitySize; i++)
		{
			for(int j=0; j<m_nCitySize; j++)
			{
				// exclude bad intersections
				if(m_nBadIntersections[i].contains(j))
					continue;
				else
					m_nIntersections[i].add(j);
			}
		}
		
		// get number of walks
		int nNumStartStops = in.nextInt();
		int[] nStartX = new int[nNumStartStops];
		int[] nStartY = new int[nNumStartStops];
		int[] nStopX = new int[nNumStartStops];
		int[] nStopY = new int[nNumStartStops];
		
		// get start and stop positions of each walk
		for(int i=0; i<nNumStartStops; i++)
		{
			nStartX[i] = in.nextInt();
			nStartY[i] = in.nextInt();
			nStopX[i] = in.nextInt();
			nStopY[i] = in.nextInt();
		}
		
	}
	
	
	public int Walk(int curX, int curY, int goalX, int goalY)
	{
		
		int stackTotal = 0;
		
		// BASE CASE
		// complete path found
		if(curX == goalX && curY == goalY)
		{
			stackTotal++;
		}
		
		// BASE CASE
		// this is a bad intersection
		if(!m_nIntersections[curX].contains(curY))
		{
			return 0;
		}
		
		// if left and above goal:
		// check the paths right and below
		if(curX < goalX && curY < goalY)
		{
			stackTotal += Walk(curX, curY+1, goalX, goalY);
			stackTotal += Walk(curX+1, curY, goalX, goalY);
		}
		
		// if left and below goal:
		// check paths right and above
		if(curX < goalX && curY > goalY)
		{
			stackTotal += Walk(curX, curY-1, goalX, goalY);
			stackTotal += Walk(curX+1, curY, goalX, goalY);
		}
		
		// if right and above goal:
		// check paths left and below
		if(curX > goalX && curY < goalY)
		{
			stackTotal += Walk(curX, curY+1, goalX, goalY);
			stackTotal += Walk(curX-1, curY, goalX, goalY);
		}
		
		// if right and below goal:
		// check paths left and above
		if(curX > goalX && curY > goalY)
		{
			stackTotal += Walk(curX, curY-1, goalX, goalY);
			stackTotal += Walk(curX-1, curY, goalX, goalY);
		}
		
		// if directly above
		// check below
		if(curX == goalX && curY < goalY)
		{
			stackTotal += Walk(curX, curY+1, goalX, goalY);
		}
		
		// if directly below
		// check above
		if(curX == goalX && curY > goalY)
		{
			stackTotal += Walk(curX, curY-1, goalX, goalY);
		}
		
		// if directly left
		// check right
		if(curX < goalX && curY == goalY)
		{
			stackTotal += Walk(curX+1, curY, goalX, goalY);
		}
		
		// if directly right
		// check left
		if(curX > goalX && curY == goalY)
		{
			stackTotal += Walk(curX-1, curY, goalX, goalY);
		}
		
		return stackTotal;
		
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

	public static void main(String[] args) 
	{
		
		
		
	}

}
