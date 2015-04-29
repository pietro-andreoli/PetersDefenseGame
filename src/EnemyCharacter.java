import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public class EnemyCharacter {
	final static int characterWidth=14;
	final static int characterHeight=14;
	Ellipse2D.Double body;
	int type;//The type of enemy
	int xPos;
	int yPos;
	
	
	public EnemyCharacter(){
		int frameHeight=GameFrame.FRAME_HEIGHT;
		int frameWidth=GameFrame.FRAME_WIDTH;
		Random r = new Random();
		int temp=r.nextInt(4);
		if(temp==0){
			body=new Ellipse2D.Double(0, r.nextInt((frameHeight-characterHeight)+1), characterWidth, characterHeight);
		}else if(temp==1){
			body=new Ellipse2D.Double( r.nextInt((frameWidth-characterWidth)+1), 0, characterWidth, characterHeight);
		}else if(temp==2){
			body=new Ellipse2D.Double(frameWidth-characterWidth-characterWidth-4, r.nextInt((frameHeight-characterHeight)+1), characterWidth, characterHeight);
		}else if (temp==3){
			body=new Ellipse2D.Double(r.nextInt((frameWidth-characterWidth)+1), frameHeight-(6*characterHeight), characterWidth, characterHeight);
		}
		type = r.nextInt(4);
		xPos=(int)body.x;
		yPos=(int)body.y;
	}
	
	public EnemyCharacter(int t){
		int frameHeight=GameFrame.FRAME_HEIGHT;
		int frameWidth=GameFrame.FRAME_WIDTH;
		Random r = new Random();
		int temp=r.nextInt(4);
		if(temp==0){
			System.out.println("Enemy added to side 0");
			body=new Ellipse2D.Double(0, r.nextInt((frameHeight-characterHeight)+1), characterWidth, characterHeight);
		}else if(temp==1){
			System.out.println("Enemy added to side 1");
			body=new Ellipse2D.Double( r.nextInt((frameWidth-characterWidth)+1), 0, characterWidth, characterHeight);
		}else if(temp==2){
			System.out.println("Enemy added to side 2");
			body=new Ellipse2D.Double(frameWidth-characterWidth-characterWidth-4, r.nextInt((frameHeight-characterHeight)+1), characterWidth, characterHeight);
		}else if (temp==3){
			System.out.println("Enemy added to side 3");
			body=new Ellipse2D.Double(r.nextInt((frameWidth-characterWidth)+1), frameHeight-(6*characterHeight), characterWidth, characterHeight);
		}
		
		type = t;
		xPos=(int)body.x;
		yPos=(int)body.y;
	}
	
	public EnemyCharacter(int x, int y, int t){
		body=new Ellipse2D.Double(x,y, characterWidth, characterHeight);
		xPos=(int)body.x;
		yPos=(int)body.y;
		type=t;
	}
	
	public boolean overlapsOtherEnemy(EnemyCharacter other){
		
			if(this != other){
				double distanceX= Math.abs((other.body.x+other.body.width/2)-(this.body.x+this.body.width/2));
				double distanceY= Math.abs((other.body.y+other.body.height/2)-(this.body.y+this.body.height/2));
				double totalRadius = other.body.width/2 + this.body.width/2;
				if(distanceX < totalRadius && distanceY < totalRadius )
					return true;
				}
			
		return false;
	}
	public void draw(Graphics2D g2) {
		Ellipse2D.Double body =new Ellipse2D.Double(xPos, yPos, characterWidth, characterHeight);
		g2.setColor(Color.RED);
		g2.fill(body);
		g2.draw(body);
		if(type==0){
			g2.setColor(Color.BLACK);
			g2.drawLine(xPos, yPos+ characterHeight/2, xPos+characterWidth, yPos+characterHeight/2);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos+characterHeight);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos);
		}else if(type == 1){
			g2.setColor(Color.BLACK);
			for(int i =1; i>-2; i--){
			g2.drawLine(xPos, yPos+characterHeight/2-i, xPos+characterWidth, yPos+characterHeight/2-i);
			g2.drawLine(xPos+characterWidth/2-i, yPos, xPos+characterWidth/2-i, yPos+characterHeight);
			}
		}else if(type == 2){
			g2.setColor(Color.BLACK);
			for(int i=0; i<characterWidth;i++){
				g2.drawLine(xPos+i+3, yPos+(int)(Math.sqrt((double)i)+3),xPos+i+3,(int)(yPos+ Math.sqrt((double)i+3)));
				g2.drawLine(xPos+characterWidth-i-3, yPos+characterHeight-(int)(Math.sqrt((double)i)+3),xPos-i+characterWidth-3,(int)(yPos- Math.sqrt((double)i+3))+characterHeight);
			}
			
		}

	}
	
}
