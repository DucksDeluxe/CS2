import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EightQueens extends Applet implements MouseListener, MouseMotionListener, Runnable, ActionListener
{
	private static final long serialVersionUID = 1L;
	String LOGO_DIRECTORY = "BA.jpg";
	Image m_imgLogo;
	MediaTracker tracker = new MediaTracker(this);
	static final int NUMROWS = 8;
	static final int NUMCOLUMNS = NUMROWS;
	static final int SQUAREWIDTH = 50;
	static final int SQUAREHEIGHT = SQUAREWIDTH;
	static final int BOARDLEFT = 50;
	static final int BOARDTOP = BOARDLEFT;
	int m_nBoard[][] = new int[NUMROWS][NUMCOLUMNS];
	boolean m_bClash = false;
	Button m_btnSolve = new Button("Solve");
	boolean m_bSolving = false;
	
	/**
	 * 
	 */
	public void init()
	{
		addMouseListener(this);
		setSize(1020, 700);
		
		add(m_btnSolve);
		m_btnSolve.addActionListener( this );
						
		try
		{
			m_imgLogo = ImageIO.read(EightQueens.class.getResourceAsStream(LOGO_DIRECTORY));					
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		tracker.addImage(m_imgLogo, 100);
		
		try
		{
			tracker.waitForAll();
		}
		catch(InterruptedException e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	
	/**
	 * 
	 */
	@Override
	public void run() 
	{		
		try 
		{
			while(true)
			{
				if(m_bSolving)
				{
					Solver(0,0);
				}
				m_bSolving = false;
				m_btnSolve.setEnabled(true);
				
				Thread.sleep(30);
			}
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Solved");
	}

	public void paint(Graphics g)
	{
		String m_strStatus = "";
		
		//m_bClash = false;
		DrawBoard( g );
		g.setColor( Color.RED );
		CheckColumns( g );
		CheckRows( g );
		CheckDiagonal1( g );
		CheckDiagonal2( g );
		g.setColor( Color.BLUE );
		
		g.drawString( m_strStatus, BOARDLEFT, BOARDTOP * 8 + 20 );
	}
	
	private void DrawBoard(Graphics g) 
	{
		g.setColor( Color.BLACK );
		for (int i=0; i<NUMROWS; i++)
		{
			for (int j=0; j<NUMCOLUMNS; j++)
			{	
				g.drawRect(BOARDLEFT+j*SQUAREWIDTH, 
						BOARDTOP+i*SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
		
				if( m_nBoard[i][j] != 0 )
				{
					g.drawImage( m_imgLogo,
						BOARDLEFT + j * SQUAREWIDTH + 2, 
						BOARDTOP + i * SQUAREHEIGHT + 2, 
						null );
				}
			}
		}
	}

	private void Solver(int row, int column) throws InterruptedException 
	{
		repaint();
		System.out.println("Solving");

		// base case
		if (column >= NUMCOLUMNS)
			return;
		
		// toggle
		m_nBoard[row][column] ^= 1;
		repaint();
		Thread.sleep(200);
		
		// if clashed
		if(m_bClash)
		{
			//undo
			m_nBoard[row][column] ^= 1;
			repaint();
			Thread.sleep(200);
			m_bClash = false;
			if (row + 1 == NUMROWS)
				return;
			
			// try next row
			Solver(row + 1, column);
		}	
		
		// if no clash
		if(!m_bClash)
			// move to the next column
			Solver(0, column + 1);
		
		repaint();
		return;
		
		
	}

	private void CheckRows(Graphics g) {
		for(  int nRow=0; nRow<NUMROWS; nRow++ )
		{
			int nRowCount = 0;
			for( int nCol=0; nCol<NUMCOLUMNS; nCol++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nRowCount++;
				}
			}
			if( nRowCount > 1 )
			{
				g.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
		
	}

	private void CheckColumns(Graphics g) {
		// Check all columns
		for(  int nCol=0; nCol<NUMCOLUMNS; nCol++ )
		{
			int nColCount = 0;
			for( int nRow=0; nRow<NUMROWS; nRow++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nColCount++;
				}
			}
			if( nColCount > 1 )
			{
				g.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}		
	}

	private void CheckDiagonal1(Graphics g) {
		// Check diagonal 1
		
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = 0;
				
			int nThisRow = nRow;
			int nThisCol = nCol;
	
			int nColCount = 0;
				
			while( nThisCol < NUMCOLUMNS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				g.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	
		for( int nCol=1; nCol<NUMCOLUMNS; nCol++)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;
	
			int nColCount = 0;
				
			while( nThisCol < NUMCOLUMNS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				g.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
		
	}

	private void CheckDiagonal2(Graphics g) {
		// Check diagonal 2
		
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = NUMCOLUMNS - 1;
				
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				g.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}

		for( int nCol=NUMCOLUMNS-1; nCol>=0; nCol--)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				g.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
				
		}
		
	}

	@Override
	public void mousePressed(MouseEvent ms) {
		int nColumnClicked;
		int nRowClicked;
		
		if( ms.getX() >= BOARDLEFT &&
			ms.getY() >= BOARDTOP &&
			ms.getX() < BOARDLEFT + NUMCOLUMNS * SQUAREWIDTH &&
			ms.getY() < BOARDTOP + NUMROWS * SQUAREHEIGHT )
		{
		nColumnClicked = (ms.getX() - BOARDLEFT) / SQUAREWIDTH;
		nRowClicked = (ms.getY() - BOARDTOP) / SQUAREHEIGHT;	
		
		m_nBoard[nRowClicked][nColumnClicked] ^= 1;
		repaint();
				
		}
		
	
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		m_btnSolve.setEnabled(false);
		ClearBoard();
		repaint();
		m_bClash = false;
		m_bSolving = true;
		
		Thread m_objThread = new Thread(this);
		m_objThread.start();
		
		
	}

	private void ClearBoard() {
		// TODO Auto-generated method stub
		
	}

}
