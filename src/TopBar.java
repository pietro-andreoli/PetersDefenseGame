import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.text.DecimalFormat;


public class TopBar {
	static String currTime;
	public static String getTime(){
		if(GamePanel.gameOver)
			return currTime;
		if(GamePanel.gameState){
		currTime= "Time: "+(System.currentTimeMillis()-GamePanel.startTime)/1000;
		return currTime;
		}
		return null;
	}
public void draw(Graphics2D g2){
	//the background of the top bar
	Rectangle bg = new Rectangle(0,0,GameFrame.FRAME_WIDTH, 23);
	g2.setColor(Color.BLACK);
	g2.fill(bg);
	g2.setColor(Color.WHITE);
	g2.drawString("Score: ", 20, 18);
	g2.drawString("Lives: "+GamePanel.user.lives, 100, 18);
	g2.drawString(getTime(), 180, 18);
}
}
