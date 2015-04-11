
public class CaesarCipher 
{
	static double[] table = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7,
		7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	public CaesarCipher() 
	{
		// TODO Auto-generated constructor stub
	}

	static int let2nat(char c)
	{
		return Character.getNumericValue(c) - 10;
	}
	
	static char nat2let(int code)
	{
		return (char)(code + 97);
	}
	
	static char shiftUp(int shftAmt, char c)
	{
		int code = let2nat(c) + shftAmt;
	
		if(code < 0)
			return c;
		
		if(code > 25)
			code -= 26;
		
		return nat2let(code);
	}
	
	static char shiftDown(int shftAmt, char c)
	{
		if (let2nat(c) < 0)
			return c;
		
		int val = let2nat(c) - shftAmt;
		
		if(val < 0)
			val += 26;
		
		return nat2let(val);
	}
	
	static String encode(int shftAmt, String str)
	{
		String code = "";
		
		for(char c : str.toCharArray())
		{
			code += shiftUp(shftAmt, c);
		}
		
		return code;
	}
	
	static String decode(int shftAmt, String str)
	{
		String original = "";
		
		for(char c : str.toCharArray())
		{
			original += shiftDown(shftAmt, c);
		}
		
		return original;
	}
	
	// count number of lowers
	int lowers(String str)
	{
		int count = 0;
		for(char c : str.toCharArray())
		{
			if(Character.isLowerCase(c))
				count++;			
		}
		return count;
	}
	
	// count number of specified char
	int count(char c, String str)
	{
		int count = 0;
		for(char i : str.toCharArray())
		{
			if(Character.compare(c, i) == 0)
				count++;
		}
		return count; 
	}
	
	// get percentage of one num with respect to another
	double percent(int num1, int num2)
	{
		return 100*(double)num1/num2;
	}
	
	
	double[] freqs(String str)
	{
		double[] list = new double[26];
		
		//iterate thru ascii alphabet
		for(int i=0; i<25; i++)
		{
			list[i] = percent(count((char)(i+97), str), lowers(str));
		}
		
		return list;
	}
	
	double[] rotate(int n, double[] list)
	{
		double[] newList = new double[list.length];
		int j = 0;
		
		for(int i=0; i<list.length-1; i++)
		{
			if(i+n < list.length)
				newList[i] = list[i+n];
			else if(i+n >= list.length)
			{
				newList[i] = list[j];
				j++;
			}
		}		
		return newList;
	}
	
	double chisqr(double[] os)
	{
		
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println(encode(3, "a"));
		System.out.println(decode(3, encode(3, "r")));

	}

}
