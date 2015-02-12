import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

///////////////////////////////////////////
//
// Flood fill recitation assignment
// Justin VanWinkle
// J3338546
//
///////////////////////////////////////////

public class Floodfill extends Applet implements MouseListener
{
	BufferedImage m_objShape;
	Color m_objSelectedColor = Color.blue;
	MediaTracker tracker = new MediaTracker(this);
	int m_nTestShapeY = 100;
	int m_nTestShapeX = 100;
	int m_nUpperLeftX = 10;
	int m_nUpperLeftY = 10;
	int m_nColorWidth = 50;
	int m_nColorHeight = 50;
	int m_nLowerRightX;
	int m_nLowerRightY;
	int targetColor;	
	static Color[] m_Colors =
	{
		Color.blue, Color.red, Color.green, Color.yellow,
		Color.gray, Color.magenta, Color.orange, Color.cyan
	};
	
	/**
	 * 
	 */
	public void init()
	{
		// add a mouse listener to this class
		addMouseListener(this);
		// set the size of the applet frame
		setSize(1020,700);
		// set the background color to show borders of image object
		setBackground(Color.lightGray);
       
		try 
		{
			// set image object
			m_objShape = ImageIO.read(Floodfill.class.getResourceAsStream("Untitled.png"));
		} 
        catch (IOException e1) 
        {
        }
		// add image to MediaTracker list
		tracker.addImage(m_objShape, 100);
		try 
		{
			// load image and wait for completion
			tracker.waitForAll();
		} 
		catch (InterruptedException e) 
		{		
		}
	}
	
	/**
	 * 
	 * @param g contains the context for rendering
	 */
	public void paint(Graphics g)
	{	
		// draw palate
		DrawColors( g );
		// draw image
		DrawTestShape( g );
	}
		
	/**
	 * 
	 * @param canvas contains the context for rendering
	 */
	void DrawColors( Graphics canvas )
	{
		// traverse the palate array
		for( int i=0; i<m_Colors.length; i++ )
		{
			// set color to be used
			canvas.setColor( m_Colors[i] );
			// create a block with set color
			canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
					m_nColorWidth, m_nColorHeight );
			// set color to black
			canvas.setColor( Color.black );
			// draw border around block
			canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
					m_nColorWidth, m_nColorHeight );
			m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
			m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
		}
	}

	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent ms) 
	{
		// clicking in the palate
		if( ms.getX() >= m_nUpperLeftX &&
			ms.getY() >= m_nUpperLeftY &&
			ms.getX() < m_nLowerRightX &&
			ms.getY() < m_nLowerRightY )
		{
			// get the index of the color clicked
			int nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
			// is the index in range?
			if( nColorIndex >= 0 && nColorIndex <= 7 )
			{
				// select the color
				m_objSelectedColor = m_Colors[nColorIndex];
			}
		}
		// clicked on the image
		else if( ms.getX() >= m_nTestShapeX &&
			ms.getY() >= m_nTestShapeY &&
			ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
			ms.getY() < m_nTestShapeY + m_objShape.getHeight())
		{
			// get the color of the pixel you clicked
			targetColor = GetPixel(ms.getX()-100, ms.getY()-100);
			// flood fill starting at the pixel clicked
			DoFloodFill( ms.getX() - m_nTestShapeX, ms.getY()- m_nTestShapeY);
		}
		
		// let's allow the user to change the background if they click on it
		else
			this.setBackground(m_objSelectedColor);
	}

	/**
	 * 
	 * @param x is a coordinate in the x direction
	 * @param y is a coordinate in the y direction
	 */
	private void DoFloodFill(int x, int y) 
	{	
		// base case (boundary of image)
		if (y < 0 || y > m_objShape.getHeight()-1)
			return;
		
		// base case (pixel clicked is same color as selected)
		if (targetColor == m_objSelectedColor.getRGB())
			return;
		
		// base case (pixel passed is already selected color)
		if (m_objSelectedColor.getRGB() == GetPixel(x, y))
			return;
		
		// index to traverse left
		int left = x;
		// iterate left changing only target pixels
		while ( GetPixel(left, y) == targetColor )
		{
			// change the current pixel 
			SetPixel(left, y, m_objSelectedColor.getRGB());
			left--;
			// boundary
			if (left < 0)
				break;
		}
		
		// index to traverse right
		int right = x+1;
		// iterate right changing only target pixels
		while ( GetPixel(right, y) == targetColor )
		{
			// change the current pixel
			SetPixel(right, y, m_objSelectedColor.getRGB());
			right++;
			// boundary
			if (right > m_objShape.getWidth()-1)
				break;
		}
		
		// iterate over row for recursive calls
		for (left += 1; left < right ; left++)
		{
			// call up
			DoFloodFill(left,y+1);
			//call down
			DoFloodFill(left,y-1);
		}
		// make sure we get the picture
		repaint();
	}

	/**
	 * 
	 * @param canvas contains the context for rendering
	 */
	void DrawTestShape( Graphics canvas )
	{
		// draw image starting at (100,100)px
		canvas.drawImage(m_objShape, 100, 100, null);
	}
	
	/**
	 * 
	 * @param x is a coordinate in the x direction
	 * @param y is a coordinate in the y direction
	 * @param canvas contains the context for rendering
	 */
	void SetPixel( int x, int y, Graphics canvas )
	{
		// draw line between points
		canvas.drawLine(x, y, x, y);
	}
	
	/**
	 * 
	 * @param x is a coordinate in the x direction
	 * @param y is a coordinate in the y direction
	 * @param nColor is an RGB value
	 */
	void SetPixel( int x, int y, int nColor )
	{
		// set the color of the pixel at (x,y)
		m_objShape.setRGB(x, y, nColor);
	}
	
	/**
	 * 
	 * @param x is a coordinate in the x direction
	 * @param y is a coordinate in the y direction
	 * @returns an integer containing the RGB value of the passed coordinate
	 */
	public int GetPixel( int x, int y )
	{
		return( m_objShape.getRGB(x, y) );
	}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {			
		}
}

