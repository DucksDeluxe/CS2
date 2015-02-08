import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Floodfill extends Applet implements MouseListener
{
	Color m_objSelectedColor = Color.blue;
	BufferedImage m_objShape;
	MediaTracker tracker = new MediaTracker(this);
	int m_nTestShapeY = 100;
	int m_nTestShapeX = 100;
	static Color[] m_Colors =
	{
		Color.blue, Color.red, Color.green, Color.yellow,
		Color.gray, Color.magenta, Color.orange, Color.cyan
	};
	int m_nUpperLeftX = 10;
	int m_nUpperLeftY = 10;
	int m_nColorWidth = 50;
	int m_nColorHeight = 50;
	int m_nLowerRightX;
	int m_nLowerRightY;
	int targetColor;
	
		
	public void init()
	{
		addMouseListener(this);
		setSize(1020,700);
		setBackground(Color.lightGray);
       
		try 
		{
			m_objShape = ImageIO.read(Floodfill.class.getResourceAsStream("Untitled.png"));
		} 
        catch (IOException e1) 
        {
        }
		tracker.addImage(m_objShape, 100);
		try 
		{
			tracker.waitForAll();
		} 
		catch (InterruptedException e) 
		{		
		}
	}
	
	public void paint(Graphics g)
	{
		DrawColors( g );
		DrawTestShape( g );
	}
		
	void DrawColors( Graphics canvas )
	{
		for( int i=0; i<m_Colors.length; i++ )
		{
			canvas.setColor( m_Colors[i] );
			canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
					m_nColorWidth, m_nColorHeight );
			canvas.setColor( Color.black );
			canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, 
					m_nColorWidth, m_nColorHeight );
			m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
			m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
		}
	}

	@Override
	public void mouseClicked(MouseEvent ms) 
	{
		
		if( ms.getX() >= m_nUpperLeftX &&
			ms.getY() >= m_nUpperLeftY &&
			ms.getX() < m_nLowerRightX &&
			ms.getY() < m_nLowerRightY )
		{
		int nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
			if( nColorIndex >= 0 && nColorIndex <= 7 )
			{
				m_objSelectedColor = m_Colors[nColorIndex];
			}
		}
		else if( ms.getX() >= m_nTestShapeX &&
			ms.getY() >= m_nTestShapeY &&
			ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
			ms.getY() < m_nTestShapeY + m_objShape.getHeight())
		{
			// we can even change borders!
			targetColor = GetPixel(ms.getX()-100, ms.getY()-100);
			DoFloodFill( ms.getX() - m_nTestShapeX, ms.getY()- m_nTestShapeY);
		}
		else
			this.setBackground(m_objSelectedColor);
	}

	private void DoFloodFill(int x, int y) 
	{
		if (y < 0 || y > m_objShape.getHeight()-1)
			return;
		
		if (targetColor == m_objSelectedColor.getRGB())
			return;
		
		if (m_objSelectedColor.getRGB() == GetPixel(x, y))
			return;
		
		int left = x;
		// iterate left changing only target pixels
		while ( GetPixel(left, y) == targetColor )
		{
			// change the current pixel 
			SetPixel(left, y, m_objSelectedColor.getRGB());
			left--;
			if (left < 0)
				break;
		}
		
		int right = x+1;
		// iterate right changing only target pixels
		while ( GetPixel(right, y) == targetColor )
		{
			// change the current pixel
			SetPixel(right, y, m_objSelectedColor.getRGB());
			right++;
			if (right > m_objShape.getWidth()-1)
				break;
		}
		
		for (left += 1; left < right ; left++)
		{
			DoFloodFill(left,y+1);
			DoFloodFill(left,y-1);
		}
		repaint();
	}

	void DrawTestShape( Graphics canvas )
	{
		canvas.drawImage(m_objShape, 100, 100, null);
	}
	
	void SetPixel( int x, int y, Graphics canvas )
	{
		canvas.drawLine(x, y, x, y);
	}
	
	void SetPixel( int x, int y, int nColor )
	{
		m_objShape.setRGB(x, y, nColor);
	}
	
	public int GetPixel( int x, int y )
	{
		return( m_objShape.getRGB(x, y) );
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
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}

