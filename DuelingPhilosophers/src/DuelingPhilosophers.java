import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

// Justin VanWinkle

public class DuelingPhilosophers {

		public Scanner sc;
	    public PrintStream ps;
	
	    public static final int MAXCOUNT = 1000000;
	
	
	    public void Compute() throws Exception
	    {
	    	// scanner and print stream
	        sc = new Scanner( System.in ); 
	        ps = System.out; 
	             
	        // prereq[i] defines the number of essays that must come before this one
	        int prereq[] = new int[MAXCOUNT];
	        
	        // postreq[i] defines the number of essays that must come after this one
	        LinkedList<Integer> postreq[] = new LinkedList[MAXCOUNT];
	        
	        // Initialize postreqs
	        for( int i=0; i<MAXCOUNT; i++ )
	        {
	            postreq[i] = new LinkedList<Integer>();
	        }
	        
	        // list of all essays that can come next
	        LinkedList<Integer> zeros = new LinkedList<Integer>();
	                        
	        for(;;)
	        {
	            // get n and m
	            int n = sc.nextInt();
	            int m = sc.nextInt();
	            if( n==0 ) break;
	            
	            // Initialize data set
	            for( int i=0; i<n; i++ )
	            {
	                prereq[i] = 0;
	                postreq[i].clear();
	            }
	            
	            // get dependencies
	            // a defines term in b
	            for(int i=0; i<m; i++)
	            {
	                int a = sc.nextInt()-1;
	                int b = sc.nextInt()-1;                
	
	                ++prereq[b];           
	                postreq[a].add( b );           
	            }
	            
	            // initialize zeros
	            zeros.clear();
	            for( int i=0; i<n; i++ ) if( prereq[i]==0 ) 
	            {
	                zeros.add( i );
	            }

	            // start with only a single solution
	            int answer = 1;
	            
	            for( int i=0; i<n; i++ )
	            {
	                // If there are no essays with zero prereq,
	                if( zeros.size()==0 )
	                {
	                    // there's a loop
	                    answer = 0;
	                    break;
	                }
	                else if( zeros.size() > 1 )
	                {
	                	// if multiple essays have no prereq, there are multiple possible solutions
	                	// but there could still be a loop
	                    answer = 2;
	                }
	                
	                // So, grab the next essay
	                int essay = zeros.removeFirst();
	                
	                // run through essays, taking care of postreqs
	                for( int j : postreq[essay] )
	                {
	                    --prereq[j];
	                    if( prereq[j]==0 )
	                    {
	                        zeros.add( j );
	                    }
	                }
	            }
	            
	            // output our solution
	            ps.println( answer );
	        }
	    }
	    
	    public static void main( String[] args ) throws Exception
	    {
	        new DuelingPhilosophers().Compute();
	    }
	}