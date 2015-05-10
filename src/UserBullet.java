import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;


public class UserBullet implements MouseListener{
	Ellipse2D.Double body ;
	int bulletWidth=4;
	int bulletHeight=4;
	double xPos=GamePanel.user.xPos;
	double yPos=GamePanel.user.yPos;
	double trajectory;
	String mouseOrBullet;//checks which is further along the grid
	public UserBullet(){
		body = new Ellipse2D.Double(xPos, yPos, bulletWidth, bulletHeight);
	}
	
	
	public void setPosition(double x, double y){
		xPos=x;
		body.x=x;
		yPos=y;
		body.y=y;
	}
	
	
	/**Calculates the slope between two points
	 * @param x x position of the user
	 * @param y y position of the user
	 * @param x2 x position of the mouse
	 * @param y2 y position of the mouse
	 * @return the slope
	 */
	public double getSlope(double x, double y, int x2, int y2){
		if(x>x2){
			this.mouseOrBullet="mouse";
			return (y-y2)/(x-x2);
		}
		else if(x2>x){
			this.mouseOrBullet="bullet";
			return ((y2-y)/(x2-x));
		}
		return 0;
	}
	
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.BLACK);
		g2.draw(body);
	}


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		if(GamePanel.gameState){
		//creates a new bullet
		UserBullet b = new UserBullet();
		//shooting mechanics
		//sets the trajectory of the bullet to the slope
		b.trajectory=b.getSlope(b.xPos,b.yPos, e.getX(), e.getY());
		System.out.println(b.trajectory);
		//adds the bullet to the bullet list
		GamePanel.bullets.add(b);
		}
			
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
