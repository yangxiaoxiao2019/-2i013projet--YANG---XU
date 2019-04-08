import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Loup extends Agent
{
	public int speed=2;
	private boolean maladie;
	private int energie = 20;
	private int spriteLength = 32;
	private Image loupSprite;
	private int oldX;
	private int oldY;
	public Loup(int x, int y,int type){
		super(x,y,type);
		oldX=x;
		oldY=y;
		this.maladie=false;
		try{
			loupSprite= ImageIO.read(new File("loup.jpg"));
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
			g2.drawImage(loupSprite,spriteLength*x,spriteLength*y,spriteLength,spriteLength, null);
			step();
		}
		
	}
	
	public void moinsEnergie(){
		this.energie--;
	}
	public void ajouteEnergie(){
		this.energie++;
	}
	public int getEnergie(){
		return energie;
	}
	public void step(){
		oldX=x;
		oldY=y;
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
	public void retourne(){
		x=oldX;
		y=oldY;
	}
	public boolean meetL(Lapin l){
		if(this.getVie() &&l.getVie()&& x-l.getX()==0 && y-l.getY()==0){
			if(this.energie < 21)
				energie++;
			return true;
		}
		else{
			energie--;	
		}
		return false;
	}
	public void meetLapin(java.util.ArrayList<Lapin> lapin){
		for(int i=0;i<lapin.size();i++){
		
			if(meetL(lapin.get(i))){
				lapin.get(i).changeVie();
				lapin.remove(i);
				i--;
				
			}
		}

	}


}
