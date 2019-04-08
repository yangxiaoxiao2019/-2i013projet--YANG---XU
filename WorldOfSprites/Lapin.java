import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Lapin extends Agent
{
	private boolean maladie;
	private int spriteLength = 32;
	private Image lapinSprite;
	public Lapin(int x, int y,int type){
		super(x,y,type);
		this.maladie=false;
		try{
			lapinSprite= ImageIO.read(new File("lapin.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	public void paint(Graphics g){
		if(this.getVie()){
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(lapinSprite,spriteLength*x,spriteLength*y,spriteLength,spriteLength,null);
			step();
		}
	}
	public Image getImage(){
		return lapinSprite;
	}
	/*public void setMalade(){
		this.maladie=true;
	}*/

	public void step(){
		orient=(int)(Math.random()*4.0)+1;
		// 1 en north , 2 en est , 3 en sud , 4 en ouest
		switch(orient){
			case 1:{
				if(y==0){
					 y=22;
				}else{ y--; }
				break;
			}
			case 2:{
					if(x==45){
						x=25;
					}else{ x++; }
			
				break;
			}
			case 3:{

				
					
					 if(y==22){
							y=0;
						}
						else{	y++;}
					
				
				break;
			}
			case 4:{
				
					if(x==25){
						x=45;
					}else{ x--; }
				break;
			}
		
		}		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, spriteLength, spriteLength);
	}
}
