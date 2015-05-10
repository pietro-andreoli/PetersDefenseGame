import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class UserCharacterSnake {
	static int width;
	static int height;
	static int xPos;
	static int yPos;
	Rectangle body;
	static int direction;// 0 = no direction, 1= left, 2 = up, 3 = right, 4 = down
	int speed =2;
	SnakeToken next;
	public UserCharacterSnake(){
		width=10;
		height=10;
		xPos=(GameFrame.FRAME_WIDTH/2)-(width/2);
		yPos=(GameFrame.FRAME_HEIGHT/2)-(height/2);
		body=new Rectangle(xPos, yPos,width, height );
		direction=0;
		next=null;
	}
	/**Sets the position of the user to specified coordinates
	 * @param x x position
	 * @param y y position
	 */
	public void setPosition(int x, int y){
		xPos=x;
		yPos=y;
		body.x=x;
		body.y=y;
	}
	
	/**moves the user by a predetermined amount
	 * 
	 */
	public void move(){
		if(direction==0){
			//dont move player
		}else if(direction==1){
			this.setPosition(xPos-speed, yPos);
		}else if (direction==2){
			this.setPosition(xPos, yPos-speed);
		}else if(direction==3){
			this.setPosition(xPos+speed, yPos);
		}else if(direction==4){
			this.setPosition(xPos, yPos+speed);
		}
			
	}
	
	/**Sets the direction of movement for the user
	 * @param d the direction
	 */
	public void setDirection(int d){
		if(d>=0 && d<=4)
			direction=d;
	}
	/**draw method for painting
	 * @param g2 the graphics 2D object 
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.fill(body);
		g2.draw(body);
	}
}
