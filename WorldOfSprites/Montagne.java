import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;

public class Montagne extends JPanel 
{
	private int x;
	private int y;
	private Image montagneSprite;
	private int spriteLength = 128;
	public Montagne(int x, int y){
		this.x=x;
		this.y=y;
		try{
			montagneSprite=ImageIO.read(new File("montagne.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(montagneSprite,spriteLength*x,spriteLength*y,spriteLength,spriteLength,null);
		
	}
}
