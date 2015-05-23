import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class SnakeToken {
	static int xPos;
	static int yPos;
	static int width = UserCharacterSnake.width;
	static int height = UserCharacterSnake.height;
	static Rectangle body;
	SnakeToken next;
	boolean freeToken=true;
	int direction=0;
	public SnakeToken(int x, int y){
		xPos=x;
		yPos=y;
		body=new Rectangle(xPos, yPos, width, height);
		next=null;
		freeToken=true;
	}
	public SnakeToken(){
		Random random = new Random();
		xPos=random.nextInt(GameFrame.FRAME_WIDTH);
		yPos=random.nextInt(GameFrame.FRAME_HEIGHT);
		body=new Rectangle(xPos, yPos, width, height);
		next=null;
		freeToken=true;
	}
	public void setPosition(int x, int y){
		xPos=x;
		yPos=y;
		body.x=x;
		body.y=y;
	}
	public void attatchToEnd(){
		if(SnakeGamePanel.user.next == null){
			SnakeGamePanel.user.next=this;
			this.freeToken=false;
			if(direction==1){
				this.setPosition(SnakeGamePanel.user.xPos+SnakeGamePanel.user.width+1, SnakeGamePanel.user.yPos);
			}else if(direction==2){
				this.setPosition(SnakeGamePanel.user.xPos, SnakeGamePanel.user.yPos+SnakeGamePanel.user.height+1);
			}else if(direction==3){
				this.setPosition(SnakeGamePanel.user.xPos-SnakeGamePanel.user.width-1, SnakeGamePanel.user.yPos);
			}else if(direction==4){
				this.setPosition(SnakeGamePanel.user.xPos, SnakeGamePanel.user.yPos-SnakeGamePanel.user.height-1);
			}
			System.out.println("broke at attach method");
		}else{
			SnakeToken temp = SnakeGamePanel.user.next;
			while(temp!=null){
				if(temp.next==null){
					temp.next=this;
					this.freeToken=false;
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
