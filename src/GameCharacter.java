import java.awt.geom.Ellipse2D;


public class GameCharacter {
	Ellipse2D.Double body;
	
	
	/**Sets the x Position of the body
	 * @param x the x position you want
	 */
	public void setX(int x){
		this.body.x=x;
	}
	/**Moves the body of the game character to the right by x units
	 * @param x the number of units you want to move the body by
	 */
	public void moveRight(int x){
		this.body.x+=x;
	}
	public void moveUp(int y) {
		this.body.y-=y;
	}
}
