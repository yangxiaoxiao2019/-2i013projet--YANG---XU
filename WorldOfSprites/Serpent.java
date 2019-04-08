import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class Serpent extends Agent
{
	private int spriteLength = 32;
	private Image serpentSprite;
	public Serpent(int x, int y,int type){
		super(x,y,type);
		try{
			serpentSprite= ImageIO.read(new File("serpent.jpg"));
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
			g2.drawImage(serpentSprite,spriteLength*x,spriteLength*y,spriteLength,spriteLength,null);
			step();
		}
	}
	public Image getImage(){
		return serpentSprite;
	}
	public void step(){
		orient=(int)(Math.random()*4.0)+1;
		// 1 en north , 2 en est , 3 en sud , 4 en ouest
		switch(orient){
			case 1:{
				if(y<0){
					y=11;
				}else{ y--;}
				break;
			}
			case 2:{
				if(x==59 ){
						x=46;
					}else{x++;}
					break;
				}
			case 3:{
				if(y==11){
					y=0;
				}else{
					y++;
				}
				break;
				}
			case 4:{
				if(x==46){
						x=59;
					}else{x--;}
					break;
				}
		}
	}
	public boolean meetL(Lapin l){
		if(this.getVie() && ((x-l.getX()==0 && y-l.getY()==1) || (x-l.getX()==0 && y-l.getY()==-1) || (x-l.getX()==-1 && y-l.getY()==0) || (x-l.getX()==1 && y-l.getY()==0) || (x-l.getX()==0 && y-l.getY()==0) )  ){
			return true;
		}
		return false;	
	}
	public void meetLapin(java.util.ArrayList<Lapin> lapin){
		for(int i=0;i<lapin.size();i++){
		
			if(meetL(lapin.get(i))){
				lapin.remove(lapin.get(i));
			}
		}

	}


}
