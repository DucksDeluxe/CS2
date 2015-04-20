//Justin VanWinkle

import java.util.*;

public class CandyStore 
{
	public static void main(String[] args) 
	{
		Scanner stdin = new Scanner(System.in);

		int numTypes = stdin.nextInt();
		int cost = (int)(stdin.nextDouble() * 100 + .01);

		while (numTypes != 0) 
		{
			int[] dp = new int[cost+1];

			// iterate thru cases
			for (int i=0; i<numTypes; i++) 
			{
				// grab the calories and price
				int cal = stdin.nextInt();
				int thisPrice = (int)(stdin.nextDouble()*100+.01);

				// take as many as possible
				for (int j=thisPrice; j<dp.length; j++)
					dp[j] = Math.max(dp[j], dp[j-thisPrice]+cal);
			}
			// output result and go to next case
			System.out.println(dp[dp.length-1]);
			numTypes = stdin.nextInt();
			cost = (int)(stdin.nextDouble()*100+.01);
		}
	}
}