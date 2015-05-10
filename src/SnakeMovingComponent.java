import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class SnakeMovingComponent extends AbstractAction {
	String key;
	//Constructor for the moving component
	public SnakeMovingComponent(String key){
		this.key=key;
	}
	
	public void actionPerformed(ActionEvent e) {
		//if the game is on
		if(SnakeGamePanel.gameState){
			if(key.equalsIgnoreCase("rightArrow") ||  key.equalsIgnoreCase("DKey")){
				SnakeGamePanel.user.setDirection(3);
				System.out.println(SnakeGamePanel.user.direction);
			}else if(key.equalsIgnoreCase("upArrow") ||  key.equalsIgnoreCase("wKey")){
				SnakeGamePanel.user.setDirection(2);
				System.out.println(SnakeGamePanel.user.direction);
			}else if(key.equalsIgnoreCase("downArrow") ||  key.equalsIgnoreCase("sKey")){
				SnakeGamePanel.user.setDirection(4);
				System.out.println(SnakeGamePanel.user.direction);
			}else if(key.equalsIgnoreCase("leftArrow") ||  key.equalsIgnoreCase("aKey")){
				SnakeGamePanel.user.setDirection(1);
				System.out.println(SnakeGamePanel.user.direction);
			}
		}
		
	}

}
