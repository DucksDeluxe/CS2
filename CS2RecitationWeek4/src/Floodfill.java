import java.applet.Applet;
import java.awt.*;
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
	
		
	public void init()
	{
		addMouseListener(this);
		setSize(1020,700);

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
		public void mouseClicked(MouseEvent ms) {

			
			
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
					ms.getY()>=m_nTestShapeY &&
					ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
					ms.getY() < m_nTestShapeY + m_objShape.getHeight())
			{
					DoFloodFill( ms.getX(), ms.getY());
			}
		}

		private void DoFloodFill(int x, int y) {
			// TODO Auto-generated method stub
			
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

