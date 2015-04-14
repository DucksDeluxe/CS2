// Justin VanWinkle

public class LCS {
	
	public static int LCS(String x, String y) 
	{
		int i,j;
		String[] splitX = x.split(" ");
		String[] splitY = y.split(" ");
		int lengthX = splitX.length;
		int lengthY = splitY.length;
		int[][] table = new int[lengthX+1][lengthY+1];
		

		
		for(i=0; i<lengthX+1; i++)
		{
			table[i][0] = 0;
		}
		for(j=0; j<lengthY+1; j++)
		{
			table[0][j] = 0;
		}
		
		for(i=1; i<lengthX+1; i++)
		{
			for(j=1; j<lengthY+1; j++)
			{
				if(splitX[i-1].matches(splitY[j-1]))
					table[i][j] = 1 + table[i-1][j-1];
				else
					table[i][j] = Math.max(table[i][j-1], table[i-1][j]);
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}
		return table[lengthX][lengthY];
	}

	public static void main(String[] args)
	{
  	    String x = "in ni nat jag dep y togo il ru pur de tuh jon stewart";
  	    String y = "nat in jag dep huh tuh il de tuh de huh jon y stewart";

    	System.out.println("LCS Count = " + LCS(x,y));
  	}
}
