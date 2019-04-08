import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
public abstract class Agent {	
	int 	x;
	int 	y;
	private int     age;
	private boolean eV;
	int     type;  // numero 10  signifie le poisson.
 	int 	orient;// 1 en north , 2 en est , 3 en sud , 4 en ouest
	

	public Agent( int x, int y,int type )
	{
		this.x=x;
		this.y=y;
		this.type=type;
		this.age=1;
		this.eV=true;
		orient=1;  // initialier l'orient par 1.
	}
	
	abstract public void step();
	abstract public void paint(Graphics g);//,JFrame frame);
	public int getX(){
		return x;		
	}
	public int getY(){
		return y;		
	}
	public void addAge(){
		age++;
	}
	public int getAge(){
		return age;
	}
	public boolean getVie(){
		return eV;	
	}
	public void changeVie(){
		eV=false;
	}
}
