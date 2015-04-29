import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Timer;


public class UserCharacter extends GameCharacter{
	final static int characterWidth=20;
	final static int characterHeight=20;
	Ellipse2D.Double body;
	int xPos;
	int yPos;
	public static int lives;
	public static boolean visible=true;
	public UserCharacter(){
		Random xpos = new Random(GameFrame.FRAME_WIDTH);
		Random ypos = new Random(GameFrame.FRAME_HEIGHT);
		body=new Ellipse2D.Double(xpos.nextInt(), ypos.nextInt(), characterWidth, characterHeight);
		xPos=(int)body.x;
		yPos=(int)body.y;
		if(GamePanel.getDifficulty()==1)
			lives=5;
		else if(GamePanel.getDifficulty()==2)
			lives=3;
		else if(GamePanel.getDifficulty()==3)
			lives=1;
		visible=true;
	}
	public UserCharacter(int x, int y){
		body=new Ellipse2D.Double(x,y, characterWidth, characterHeight);
		xPos=(int)body.x;
		yPos=(int)body.y;
		visible=true;
		if(GamePanel.getDifficulty()==1)
			lives=5;
		else if(GamePanel.getDifficulty()==2)
			lives=3;
		else if(GamePanel.getDifficulty()==3)
			lives=1;
		visible=true;
	}
	/**Sets the x Position of the body
	 * @param x the x position you want
	 */
	public void setX(int x){
		this.body.x=x;
	}
	
	public boolean anyPowerUpIntersectsUserCheck(){
		for(PowerUpTokens tokens : GamePanel.powerUpList){
			double distanceX= Math.abs((tokens.body.x+tokens.body.width/2)-(this.body.x+this.body.width/2));
			double distanceY= Math.abs((tokens.body.y+tokens.body.height/2)-(this.body.y+this.body.height/2));
			double totalRadius = tokens.body.width/2 + this.body.width/2;
			if(distanceX < totalRadius && distanceY < totalRadius )
				return true;
			}
		return false;
	}
	
	public PowerUpTokens anyPowerUpIntersectsUser(){
		for(PowerUpTokens tokens : GamePanel.powerUpList){
			double distanceX= Math.abs((tokens.body.x+tokens.body.width/2)-(this.body.x+this.body.width/2));
			double distanceY= Math.abs((tokens.body.y+tokens.body.height/2)-(this.body.y+this.body.height/2));
			double totalRadius = tokens.body.width/2 + this.body.width/2;
			if(distanceX < totalRadius && distanceY < totalRadius )
				return tokens;
			}
		return null;
	}
	
	/**Moves the body of the game character to the right by x units
	 * @param x the number of units you want to move the body by
	 */
	public void move(String direction, int unit){
		if(direction.equalsIgnoreCase("up")){
			this.body.y-=unit;
			this.yPos-=unit;
		}else if(direction.equalsIgnoreCase("down")){
			this.body.y+=unit;
			this.yPos+=unit;
		}else if(direction.equalsIgnoreCase("left")){
			this.body.x-=unit;
			this.xPos-=unit;
		}else if(direction.equalsIgnoreCase("right")){
			this.body.x+=unit;
			this.xPos+=unit;
		}
		
	}
	
	/**Sets the location of the token
	 * @param x the x pos
	 * @param y the y pos
	 */
	public void setLocation(int x, int y){
		xPos=x;
		yPos=y;
		body.x=x;
		body.y=y;
	}
	public boolean overLapsEnemy(EnemyCharacter e){
		double distanceX= Math.abs((e.body.x+e.body.width/2)-(this.body.x+this.body.width/2));
		double distanceY= Math.abs((e.body.y+e.body.height/2)-(this.body.y+this.body.height/2));
		double totalRadius = e.body.width/2 + this.body.width/2;
		if(distanceX < totalRadius && distanceY < totalRadius )
			return true;
		return false;
	}
	public void recoverAfterHit(){
		GamePanel.recovering=true;
		
		//adds the timer and starts the timer
		Timer recover = new Timer(1000, new RecoveryTimer());
		recover.setRepeats(false);
		recover.start();
	}
	
	public void draw(Graphics2D g2) {
		Ellipse2D.Double body =new Ellipse2D.Double(xPos, yPos, characterWidth, characterHeight);
		g2.setColor(Color.BLUE);
		g2.fill(body);
		//if the user has the invincible power up and is fast
		if(GamePanel.invincible && GamePanel.isFast){
			g2.setColor(Color.GREEN);
			//Draws the speed component
			g2.draw(body);
			g2.draw(new Ellipse2D.Double(body.x+1, body.y+1, body.width-1, body.height-1));
			g2.draw(new Ellipse2D.Double(body.x, body.y, body.width-1, body.height-1));
			//draws the invincible component
			g2.drawLine(xPos, yPos+ characterHeight/2, xPos+characterWidth, yPos+characterHeight/2);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos+characterHeight);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos);
		}
		//if the user is invincible
		else if(GamePanel.invincible){
			g2.setColor(Color.GREEN);
			g2.draw(body);
			g2.draw(new Ellipse2D.Double(body.x+1, body.y+1, body.width-1, body.height-1));
			g2.draw(new Ellipse2D.Double(body.x, body.y, body.width-1, body.height-1));
		}
		//if the user is faster than usual
		else if(GamePanel.isFast){
			g2.setColor(Color.GREEN);
			g2.drawLine(xPos, yPos+ characterHeight/2, xPos+characterWidth, yPos+characterHeight/2);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos+characterHeight);
			g2.drawLine(xPos+characterWidth, yPos+characterHeight/2, xPos+characterWidth/2, yPos);
		}
		//the user has now powerups
		else{
			g2.draw(body);
		}
	}
	
	//The following methods are used for powerups
		/**This method is used for the random Teleport powerup. Randomly teleports you somewhere on screen
		 * 
		 */
		public  void randomTeleport(){
			Random r = new Random();
			this.setLocation(r.nextInt((GameFrame.FRAME_WIDTH-(int)(2*this.body.width))+1)+0, r.nextInt((GameFrame.FRAME_HEIGHT-(int)(6*this.body.height))+1)+0);
		}
		
		/**Used in the FastUser power up. Increases speed for 10 seconds, then returns speed to 3
		 * 
		 */
		public void increaseSpeed(){
			MovingComponent.speed+=3;
			GamePanel.isFast=true;
			System.out.println("increasing speed by 3");
			class SpeedTimer implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					MovingComponent.speed-=3;
					System.out.println("decreasing speed by 3");
					if(MovingComponent.speed==3)
						GamePanel.isFast=false;
				}

			}
			//adds the timer and starts the timer
			Timer repaintFrame = new Timer(10000, new SpeedTimer());
			repaintFrame.setRepeats(false);
			repaintFrame.start();
		}
		
		/**This is the method used when the user intersects an invincible power up (type 2 powerup)
		 * 
		 */
		public void invincible(){
			GamePanel.invincible=true;
			class InvincibleTimer implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					GamePanel.invincible=false;
				}

			}
			//adds the timer and starts the timer
			Timer repaintFrame = new Timer(5000, new InvincibleTimer());
			repaintFrame.setRepeats(false);
			repaintFrame.start();
		}
		
	
}
