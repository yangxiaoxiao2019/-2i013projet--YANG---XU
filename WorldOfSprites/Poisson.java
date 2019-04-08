
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Poisson extends Agent
{
	private int spriteLength = 32;
	private Image poissonSprite;
	
	public Poisson(int x, int y,int type){
		super(x,y,type);
		try{
			poissonSprite= ImageIO.read(new File("poisson.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	public void paint(Graphics g)
	{
		if(this.getVie()){
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(poissonSprite,spriteLength*x,spriteLength*y,spriteLength,spriteLength,null);//, frame);
			step();
		}
		
	}
	public Image getImage(){
		return poissonSprite;
	}
	public void step(){
		orient=(int)(Math.random()*4.0)+1;
		switch(orient){
			// 1 en north , 2 en est , 3 en sud , 4 en ouest
			case 1: {
					if(y==12){
						y=22;
					}else{ y--;}
					break;
				
				}
			case 2: {
					if(x==59 ){
						x=46;
					}else{x++;}
					break;
				}
			case 3: {
					if(y==22){
						y=12;
					}else{y++;}
					break;
				}
			case 4: {
					if(x==46){
						x=59;
					}else{x--;}
					break;
				}
		}
		
				
	}


}
