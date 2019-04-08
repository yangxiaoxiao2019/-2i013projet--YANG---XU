

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;



public class SpriteDemo2 extends JPanel {
	
	static ArrayList<Poisson> poisson=new ArrayList<Poisson>();{
		Poisson p = new Poisson(49,9,10);
		poisson.add(p);
	}
	static ArrayList<Montagne> mont=new ArrayList<Montagne>();{
		
		mont.add(new Montagne(1,1));
	}
	//static ArrayList<Loup> loup=new ArrayList<Loup>();
	static ArrayList<Serpent> serpent=new ArrayList<Serpent>();{
		Serpent s= new Serpent(49,3,8);
		serpent.add(s);
	}
	
	static ArrayList<Lapin> lapin=new ArrayList<Lapin>();{
		
		lapin.add(new Lapin(30,5,9));
	}
	static ArrayList<Loup> loup=new ArrayList<Loup>();{
		Loup lo=new Loup(30,8,7);
		loup.add(lo);
	}
	
	
	
	//Lapin la=new Lapin(1,1,9);
	//Serpent s= new Serpent(20,3,8);
	//Loup lo=new Loup(1,2,7);
	public  JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image desertSprite;
	private Image feuSprite;
	private Image cendreSprite;
	private Image magmaSprite;
	
	private int spriteLength = 32;
	public int large=23;
	public int longueur=60;
	
	public int[][] myWorld;

	public SpriteDemo2()
	{
		try
		{
			waterSprite = ImageIO.read(new File("water.png"));
			treeSprite = ImageIO.read(new File("tree.png"));
			grassSprite = ImageIO.read(new File("grass.png"));
			desertSprite = ImageIO.read(new File("desert.jpg"));
			feuSprite = ImageIO.read(new File("feu.jpg"));
			cendreSprite = ImageIO.read(new File("cendre.jpg"));
			magmaSprite = ImageIO.read(new File("magma.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(2000,1000);
		frame.setVisible(true);
		
		myWorld = new int[longueur][large];//[x][y];
		
		for ( int i = 0 ; i != longueur ; i++ )
			for ( int j = 0 ; j != large ; j++ )
			{
				if(i>45 && i<60){
					if(j>=0 && j<12 )
						myWorld[i][j]=3;//3 signifie le desert
					else
						myWorld[i][j]=0;// 0 -> ocean
				}
				if(i<25){
					if(3<i && i<8 && 3<j && j<8 )
							myWorld[i][j]=2;// 2->herbre
					myWorld[i][j] = (int)(Math.random()*2.0+1);
				}
				if(i>=25 && i<=45)
					myWorld[i][j] =2;
				


			}//for-j
	}//SpriteDemo2

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < myWorld.length ; i++ )
			for ( int j = 0 ; j < myWorld[0].length ; j++ )
			{	
				
				if ( myWorld[i][j] == 0 )// l'eau
						g2.drawImage(waterSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 1 )//tree
							g2.drawImage(treeSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 2 )//herbe
							g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 3 ) //desert
							g2.drawImage(desertSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 88 ) //feu
							g2.drawImage(feuSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 44 ) //cendre
							g2.drawImage(cendreSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if ( myWorld[i][j] == 999 ) //magma
							g2.drawImage(magmaSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					
			}
		for(int i=0 ;i<poisson.size();i++){
			poisson.get(i).paint(g);
		}
		for(int i=0 ;i<serpent.size();i++)
		{
			serpent.get(i).paint(g);
		}
		for(int i=0 ;i<lapin.size();i++)
		{
			lapin.get(i).paint(g);
		}
		for(int i=0 ;i<loup.size();i++)
			{
				loup.get(i).paint(g);
			}
		for(int i=0 ;i<mont.size();i++)
			{
				mont.get(i).paint(g);
			}	
	}
	void run(){
		
		int it=0;
		int nombreDePasMaximum=1000;
		int h=longueur;
		int l=large;
		int[][] nouveauTableau=new int[h][l];
		double proFeu;
		while(it!= nombreDePasMaximum){
			it++;
			for(int i=0;i!=h;i++){
				for(int j=0;j!=l;j++){
						

					
					
						if(myWorld[i][j]==2){// herbe
							if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
								nouveauTableau[i][j]=999;
							else
								if(0.001>Math.random())
									if(i<25)
										nouveauTableau[i][j]=1; //1->tree
									else
										nouveauTableau[i][j]=2;// 2->herbre
								else
									nouveauTableau[i][j]=2;// 2->herbre
						}
						else if(myWorld[i][j]==88)//88 ->feu
							nouveauTableau[i][j]=44; //44->cendre
						else if(myWorld[i][j]==44){
							if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
								nouveauTableau[i][j]=999;
							else
								if(0.1>Math.random())
									nouveauTableau[i][j]=2; // il apparais un herbre dans le cendre
								else
									nouveauTableau[i][j]=44;
						}
						else if(myWorld[i][j]==0){//eau
							if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
								nouveauTableau[i][j]=999;
							else
								nouveauTableau[i][j]=0;	
						}
						else if(myWorld[i][j]==3){//desert
							if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
								nouveauTableau[i][j]=999;
							else
								nouveauTableau[i][j]=3;
						}
						else{
							if(myWorld[i][j]==1){// le feu de la foret	
									if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
									nouveauTableau[i][j]=999;
							
									else{
										if(0.001>Math.random())//la propabilite du feu
											nouveauTableau[i][j]=88;
										else if(myWorld[(i+1+h)%h][(j+l)%l]==88 || myWorld[(i-1+h)%h][(j+l)%l]==88 || myWorld[(i+h)%h][(j+1+l)%l]==88 || myWorld[(i+h)%h][(j-1+l)%l]==88 )
											nouveauTableau[i][j]=88;
										else
											nouveauTableau[i][j]=myWorld[i][j];
								}
								
							}
							
							else
								nouveauTableau[i][j]=myWorld[i][j];

						}//else	
						if(3<i && i<8 && 3<j && j<8 )
								if(myWorld[(i+1+h)%h][(j+l)%l]==999 || myWorld[(i-1+h)%h][(j+l)%l]==999 || myWorld[(i+h)%h][(j+1+l)%l]==999 || myWorld[(i+h)%h][(j-1+l)%l]==999 )
									nouveauTableau[i][j]=999;
								else
									nouveauTableau[i][j]=2;// 2->herbre
					
					
				}// for --j
			}// for --i
			for ( int i = 0 ; i != h ; i++ )
						for ( int j = 0 ; j !=l; j++ )
							myWorld[i][j]=nouveauTableau[i][j];
			if(it==250){
				myWorld[7][4]=999;
				
			}	
	
			if(it<250){
				
				for(int i=0 ;i<poisson.size();i++)
				{
					poisson.get(i).addAge();
					if(poisson.get(i).getAge()==12){
						poisson.get(i).changeVie();//poisson mort
						poisson.remove(i);
						i--;
					}
					
				}
				for(int i=0 ;i<serpent.size();i++)
				{
					serpent.get(i).addAge();
					if(serpent.get(i).getAge()==13){
						serpent.get(i).changeVie();//serpent mort
						serpent.remove(i);
						i--;
					}
					
				}
				for(int i=0 ;i<lapin.size();i++)
				{
					lapin.get(i).addAge();
					if(lapin.get(i).getAge()==30){
						lapin.get(i).changeVie();//lapin mort
						lapin.remove(i);
						i--;
					}
					
				}
				for(int i=0 ;i<loup.size();i++)
				{
					loup.get(i).addAge();
					if(loup.get(i).getAge()==40){
						loup.get(i).changeVie();//loup mort
						loup.remove(i);
						i--;
					}
					
				}
				
				for(int i=0 ;i<loup.size();i++){
					loup.get(i).meetLapin(lapin); //loup mange lapin
				}
				
				for(int i=0 ;i<loup.size();i++){
					if(loup.get(i).getEnergie()==0){
						loup.get(i).changeVie();//loup mort
						loup.remove(i);
						i--;
					}

				}
				
				double prcP=Math.random();
				if(prcP<0.1){
					int nouvX=(int)(Math.random()*13+46);
					int nouvY=(int)(Math.random()*10+12);
					poisson.add(new Poisson(nouvX,nouvY,10));
				}
				double prcS=Math.random();
				if(prcS<0.1){
					int nouvX=(int)(Math.random()*13+46);
					int nouvY=(int)(Math.random()*11);
					serpent.add(new Serpent(nouvX,nouvY,8));
				}
				if(prcS<0.07){
					int nouvX=(int)(Math.random()*20+26);
					int nouvY=(int)(Math.random()*23);
					lapin.add(new Lapin(nouvX,nouvY,9));
				}
				double prcL=Math.random();
				if(prcL<0.07){
					int nouvX=(int)(Math.random()*20+26);
					int nouvY=(int)(Math.random()*23);
					loup.add(new Loup(nouvX,nouvY,7));
				}
				
			}
			else{

				for(int i=0 ;i<poisson.size();i++)
				{
						poisson.remove(i);
					
				}
				for(int i=0 ;i<mont.size();i++)
				{
						mont.remove(i);
					
				}
				for(int i=0 ;i<serpent.size();i++)
				{
						serpent.remove(i);
					
					
				}
				for(int i=0 ;i<lapin.size();i++)
				{
					
						lapin.remove(i);
					
					
				}
				for(int i=0 ;i<loup.size();i++)
				{
					
						loup.remove(i);
						
					
				}


			}
			try {
			  Thread.sleep(100);
			} catch (Exception e)
			{

			}
			System.out.println(it+"world");
			repaint();

		}//while



	}

	public static void main(String[] args) {
		SpriteDemo2 world=new SpriteDemo2();
		world.run();
		
	}
}
