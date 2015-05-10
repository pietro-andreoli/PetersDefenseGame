import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class SnakeToken {
	static int xPos;
	static int yPos;
	static int width = UserCharacterSnake.width;
	static int height = UserCharacterSnake.height;
	static Rectangle body;
	SnakeToken next;
	public SnakeToken(int x, int y){
		xPos=x;
		yPos=y;
		body=new Rectangle(xPos, yPos, width, height);
		next=null;
	}
	public void attatchToEnd(){
		if(SnakeGamePanel.user.next == null){
			SnakeGamePanel.user.next=this;
		}else{
			SnakeToken temp = SnakeGamePanel.user.next;
			while(temp!=null){
				if(temp.next==null){
					temp.next=this;
					break;
				}
				temp=temp.next;
			}
		}
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
