//Justin VanWinkle
//COP3503C


import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EightQueens extends Applet implements MouseListener, MouseMotionListener, Runnable, ActionListener
{
	private static final long serialVersionUID = 1L;
	private Image m_imgLogo;
	private MediaTracker tracker = new MediaTracker(this);
	private Button m_btnSolve = new Button("Solve");

	//INTEGERS
	private static final int NUMROWS = 8;
	private static final int NUMCOLUMNS = NUMROWS;
	private static final int SQUAREWIDTH = 51;
	private static final int SQUAREHEIGHT = SQUAREWIDTH;
	private static final int BOARDLEFT = 50;
	private static final int BOARDTOP = BOARDLEFT;
	private int m_nBoard[][] = new int[NUMROWS][NUMCOLUMNS];
	private int m_nSleepTime = 20;
	private int m_nCurrentSolution = 0;
	//Boolean
	private boolean m_bSolving = false;
	private boolean m_bClash = false;
	//Strings
	private String m_strStatus = "";
	private String m_strSolving = "Solving";
	private String m_strSolved = "Solved";
	private String m_strUserSolve = "You're solving";
	private String m_strUserSolved = "You solved it!";
	private String m_strClear = "Clear";
	private String m_strSolve = "Solve";
	private String m_strRules = "Place 8 Queens, each in separate rows, columns, and diagonals.";
	private String LOGO_DIRECTORY = "queen.png";
	//Double Buffering
	private Graphics bufferGraphics;
	private Image offScreen;
	private Dimension dim;	
	private Label m_lblStatus = new Label(m_strUserSolve);
	
	/**
	 * 
	 */
	public void init()
	{
		addMouseListener(this);
		setSize(500, 500);
		setM_strStatus(m_strRules);
		
		add(m_btnSolve);
		m_btnSolve.addActionListener( this );
		add(m_lblStatus);
		
		// buffering
		dim = getSize();
		offScreen = createImage(dim.width, dim.height);
		bufferGraphics = getOffScreen().getGraphics();
		
		// load image
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
		// Get a fresh board for solving
		ClearBoard();
		repaint();
		setM_bSolving(true);

		// are we asking to clear the board?
		if (m_btnSolve.getLabel() == m_strClear)
		{
			setM_bSolving(false);
			m_btnSolve.setLabel(m_strSolve);
			m_btnSolve.setEnabled(true);
			m_lblStatus.setText(m_strUserSolve);
			return;
		}
		
		else if(m_btnSolve.getLabel() == m_strSolve)
		{
			m_lblStatus.setText(m_strSolving);
			// Solve it
			try 
			{
				m_nBoard[m_nCurrentSolution][0] = 1;
				while(isM_bSolving())
				{
					Solver(1);
					setM_bSolving(false);
					m_btnSolve.setEnabled(true);
					
					Thread.sleep(m_nSleepTime);
				}
				if(m_nCurrentSolution < NUMROWS)
					m_nCurrentSolution++;
				else 
					m_nCurrentSolution = 0;
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g)
	{	
		// Do all this in the background
		DrawBoard( g );
		bufferGraphics.drawString(m_strStatus, BOARDLEFT, BOARDTOP-5);
		bufferGraphics.setColor( Color.RED );
		setM_bClash(false);
		CheckColumns( g );
		CheckRows( g );
		CheckDiagonal1( g );
		CheckDiagonal2( g );
		CheckUserSolution();
		
		bufferGraphics.setColor( Color.BLUE );
		
		// bring it all to the foreground
		g.drawImage(offScreen,0,0,this);
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void CheckUserSolution() 
	{
		int count = 0;
		if (m_lblStatus.getText() == m_strUserSolve && !isM_bClash())
			
		{
			for(int i=0; i<NUMCOLUMNS; i++)
				for(int j=0; j<NUMROWS; j++)
					if (m_nBoard[i][j] != 0)
						count++;
			if (count == NUMCOLUMNS)
			{
				m_btnSolve.setLabel(m_strClear);
				m_lblStatus.setText(m_strUserSolved);
			}
		}
	}

	private boolean Solver(int nColumn) throws InterruptedException 
	{
		int nRow = 0;

		// base case
		if (nColumn >= NUMCOLUMNS)
		{
			m_lblStatus.setText(m_strSolved);
			m_btnSolve.setLabel(m_strClear);
			return true;
		}
		
		while(true)
		{
			// toggle
			m_nBoard[nRow][nColumn] ^= 1;
			repaint();
			Thread.sleep(m_nSleepTime);
			
			// if clashed
			if(isM_bClash())
			{
				// undo
				m_nBoard[nRow][nColumn] ^= 1;
				setM_bClash(false);
				
				// move to next row
				nRow++;
						
				// no solution found here
				if (nRow == NUMROWS)
					return false;
				
						continue;
			}
			else if(!isM_bClash())
			{
				if( Solver(nColumn + 1) == false)
				{
					// undo
					m_nBoard[nRow][nColumn] ^= 1;
					setM_bClash(false);
					
					// move to next row
					nRow++;
					
					if (nRow == NUMROWS)
						return false;
					
					continue;
				}
				else
					return true;
			}	
		}	
	}

	private void ClearBoard() 
	{	
		for (int i=0; i<NUMCOLUMNS; i++)
		{
			for (int j=0; j<NUMROWS; j++)
			{
				m_nBoard[i][j] = 0;
			}
		}
	}

	private void DrawBoard(Graphics g) 
	{
		for (int i=0; i<NUMROWS; i++)
		{
			for (int j=0; j<NUMCOLUMNS; j++)
			{	
				bufferGraphics.clearRect(BOARDLEFT+j*SQUAREWIDTH, 
						BOARDTOP+i*SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
		
				if( m_nBoard[i][j] != 0 )
				{
					bufferGraphics.drawImage( m_imgLogo,
						BOARDLEFT + j * SQUAREWIDTH + 6, 
						BOARDTOP + i * SQUAREHEIGHT + 6, 
						null );
				}
			}
		}
		
		
		
		bufferGraphics.setColor( Color.BLACK );
		for (int i=0; i<NUMROWS; i++)
		{
			for (int j=0; j<NUMCOLUMNS; j++)
			{	
				bufferGraphics.drawRect(BOARDLEFT+j*SQUAREWIDTH, 
						BOARDTOP+i*SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
		
				if( m_nBoard[i][j] != 0 )
				{
					bufferGraphics.drawImage( getM_imgLogo(),
						BOARDLEFT + j * SQUAREWIDTH + 6, 
						BOARDTOP + i * SQUAREHEIGHT + 6, 
						null );
				}
			}
		}
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
				bufferGraphics.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				setM_bClash(true);
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
				bufferGraphics.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
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
				bufferGraphics.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				setM_bClash(true);
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
				bufferGraphics.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				setM_bClash(true);
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
				bufferGraphics.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				setM_bClash(true);
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
				bufferGraphics.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				setM_bClash(true);
			}		
		}		
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	/**
	 * Clicking on board
	 */
	@Override
	public void mousePressed(MouseEvent ms) {
		int nColumnClicked;
		int nRowClicked;
		
		// if solving or solved, don't let user edit board
		if ( m_lblStatus.getText() == m_strSolving ||
				m_lblStatus.getText() == m_strSolved ||
				m_lblStatus.getText() == m_strUserSolved )
			return;
		
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

	
	/**
	 * Clicking Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		Thread m_objThread = new Thread(this);
		m_objThread.start();
		
		m_btnSolve.setEnabled(false);
		setM_bClash(false);
		
		
	}

	public Image getOffScreen() {
		return offScreen;
	}

	public void setOffScreen(Image offScreen) {
		this.offScreen = offScreen;
	}

	public Image getM_imgLogo() {
		return m_imgLogo;
	}

	public void setM_imgLogo(Image m_imgLogo) {
		this.m_imgLogo = m_imgLogo;
	}

	public boolean isM_bClash() {
		return m_bClash;
	}

	public void setM_bClash(boolean m_bClash) {
		this.m_bClash = m_bClash;
	}

	public boolean isM_bSolving() {
		return m_bSolving;
	}

	public void setM_bSolving(boolean m_bSolving) {
		this.m_bSolving = m_bSolving;
	}

	public String getM_strStatus() {
		return m_strStatus;
	}

	public void setM_strStatus(String m_strStatus) {
		this.m_strStatus = m_strStatus;
	}

	public Graphics getBufferGraphics() {
		return bufferGraphics;
	}

	public void setBufferGraphics(Graphics bufferGraphics) {
		this.bufferGraphics = bufferGraphics;
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
	
}
