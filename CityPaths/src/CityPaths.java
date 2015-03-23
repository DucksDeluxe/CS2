// Justin VanWinkle
// J3338546

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CityPaths 
{
	
	private int m_nCitySize = 10;
	private List[] m_nIntersections;
	private List[] m_nBadIntersections;
	
	public CityPaths(String filename) throws Exception 
	{
		Scanner in = new Scanner(new File("input.txt"));
		
		// get # test cases
		int nNumTestCases = in.nextInt();
		
		for(int nDataSet=0; nDataSet<nNumTestCases; nDataSet++)
		{
			// get # bad intersections
			int nNumBadIntersections = in.nextInt();
			
			// array of lists containing bad intersections
			m_nBadIntersections = new List[m_nCitySize];
			for(int i=0; i<m_nCitySize; i++)
			{
				m_nBadIntersections[i] = new LinkedList<>();
			}
			
			// populate bad intersections
			for (int i=0; i<nNumBadIntersections; i++)
			{
				int x = in.nextInt();
				int y = in.nextInt();
				m_nBadIntersections[x].add(0, y);		
			}
			
			// create city
			m_nIntersections = new List[m_nCitySize];
			// populate intersections
			for (int i=0; i<m_nCitySize; i++)
			{
				m_nIntersections[i] = new LinkedList<>();
				for(int j=0; j<m_nCitySize; j++)
				{
					// exclude bad intersections
					if(m_nBadIntersections[i].contains(j))
						continue;
					else
						m_nIntersections[i].add(0, j);;
				}
			}
			
			// get number of walks
			int nNumStartStops = in.nextInt();
			
			
			System.out.println("Data Set " + (nDataSet+1) + ":\n");
			
			// get start and stop positions of each walk
			for(int i=0; i<nNumStartStops; i++)
			{				
				Output(nDataSet+1, i+1, Walk(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
			}
			System.out.println("");
		}
		in.close();
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
	public void Output(int nDataSet, int nCase, int nPaths)
	{
		if( nPaths == 1 )
			System.out.println("  Test Case " + nCase + ": Nick can take " + nPaths + " perfect path.");
		else
			System.out.println("  Test Case " + nCase + ": Nick can take " + nPaths + " perfect paths.");
	}

	public static void main(String[] args)  throws Exception
	{
		//System.out.println(System.getProperty("user.dir"));
		CityPaths p = new CityPaths("input.txt");
		
		
	}

}
