import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public class PowerUpTokens   {
	Ellipse2D.Double body;
	final int width=10;
	final int height=10;
	int xPos;
	int yPos;
	int type;//1 == RandomTeleport, 2 == Invincibility
	int term;//term of token in the array list
	
	/**Constructor that takes in an x and y position, as well as a type of token
	 * @param x x position
	 * @param y y position
	 * @param t the type of power up. 1 = RandomTeleport, 2 = InvincibilityToken, 3 = FastUserToken
	 */
	public PowerUpTokens(int x, int y, int t){
		xPos=x;
		yPos=y;
		type = t;
		body= new Ellipse2D.Double(x, y, width, height);
	}
	public PowerUpTokens(int x, int y, int t, int term){
		xPos=x;
		yPos=y;
		this.term=term;
		type = t;
		body= new Ellipse2D.Double(x, y, width, height);
	}
	public PowerUpTokens(int term){
		Random r = new Random();
		xPos=r.nextInt(r.nextInt((GameFrame.FRAME_WIDTH-(int)width)+1)+0);
		yPos=r.nextInt(r.nextInt((GameFrame.FRAME_HEIGHT-(int)height)+1)+0);
		this.term=term;
		type = r.nextInt((3-1)+1)+1;
		body= new Ellipse2D.Double(xPos, yPos, width, height);
	}
	
	/**Sets the location of the token
	 * @param x the x pos
	 * @param y the y pos
	 */
	public void setLocation(int x, int y){
		xPos=x;
		yPos=y;
	}
	
	
	

	public boolean intersectsUser(){
		
		double distance= Math.abs((this.body.x+this.body.width/2)-(GamePanel.user.body.x+GamePanel.user.body.width/2));
		double totalRadius = this.body.width/2 + GamePanel.user.body.width/2;
		if(distance < totalRadius )
			return true;
		else return false;
		
	}
	public boolean overlapsOtherPowerUp(PowerUpTokens other){
		if(this != other){
			double distanceX= Math.abs((other.body.x+other.body.width/2)-(this.body.x+this.body.width/2));
			double distanceY= Math.abs((other.body.y+other.body.height/2)-(this.body.y+this.body.height/2));
			double totalRadius = other.body.width/2 + this.body.width/2;
			if(distanceX < totalRadius && distanceY < totalRadius )
				return true;
			}
		
	return false;
	}
	//Drawing the tokens
	public void draw(Graphics2D g2) {
		Ellipse2D.Double body =new Ellipse2D.Double(xPos, yPos, width, height);
		//if statements that checks which token type it should be drawing
		if(type==1){
			g2.setColor(Color.GREEN);
			g2.fill(body);
			g2.draw(body);
			g2.setColor(Color.BLACK);
			g2.drawString("R", xPos+1, yPos+10);
			
		}else if(type == 2){
			g2.setColor(Color.YELLOW);
			g2.fill(body);
			g2.draw(body);
			g2.setColor(Color.BLACK);
			g2.drawString("I", xPos+4, yPos+10);
		}else if(type ==3){
			g2.setColor(Color.ORANGE);
			g2.fill(body);
			g2.draw(body);
			g2.setColor(Color.BLACK);
			g2.drawString("F", xPos+2, yPos+10);
		}
		
	}
	
}
