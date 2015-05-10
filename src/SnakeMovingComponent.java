import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class SnakeMovingComponent extends AbstractAction {
	String key;
	public SnakeMovingComponent(String key){
		this.key=key;
	}
	
	public void actionPerformed(ActionEvent e) {
		//if the game is on
		if(SnakeGamePanel.gameState){
			if(key.equalsIgnoreCase("rightArrow") ||  key.equalsIgnoreCase("DKey")){
				System.out.println("right");
			}else if(key.equalsIgnoreCase("upArrow") ||  key.equalsIgnoreCase("wKey")){
				System.out.println("up");
			}else if(key.equalsIgnoreCase("downArrow") ||  key.equalsIgnoreCase("sKey")){
				System.out.println("down");
			}else if(key.equalsIgnoreCase("leftArrow") ||  key.equalsIgnoreCase("aKey")){
				System.out.println("left");
			}
		}
		
	}

}
