import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class UserCharacterSnake {
	static int width;
	static int height;
	static int xPos;
	static int yPos;
	Rectangle body;
	public UserCharacterSnake(){
		width=10;
		height=10;
		xPos=(GameFrame.FRAME_WIDTH/2)-(width/2);
		yPos=(GameFrame.FRAME_HEIGHT/2)-(height/2);
		body=new Rectangle(xPos, yPos,width, height );
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
