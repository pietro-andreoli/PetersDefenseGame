import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;


public class MovingComponent extends AbstractAction {
	String direction;
	static int speed=3;
	public MovingComponent(String x){
		direction=x;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(GamePanel.gameState){
		//this chain of if statements checks which key is pressed and moves accordingly
		if(direction.equalsIgnoreCase("rightarrow") || direction.equalsIgnoreCase("DKey")){
			
			GamePanel.user.move("right", speed);
			if(GamePanel.user.anyPowerUpIntersectsUserCheck()){
				//gets the power up the user overlaps
				PowerUpTokens currToken =GamePanel.user.anyPowerUpIntersectsUser();
				//gets the type of that token
				int currTokenType =currToken.type;
				//this string of if statements checks which type the power up is and performs that powerups ability
				if(currTokenType==1){
					GamePanel.user.randomTeleport();
				}else if(currTokenType ==2){
					GamePanel.user.invincible();
				}else if(currTokenType ==3){
					GamePanel.user.increaseSpeed();
				}
				GamePanel.powerUpList.remove(currToken);
			}
			//This checks if the user hit an enemy and then sets the user in 'invinccible mode' for a couple seconds
			checkForEnemyCollision();
			
			Viewer.panel.repaint();
		}else if(direction.equalsIgnoreCase("leftarrow") ||direction.equalsIgnoreCase("AKey")){
			
			GamePanel.user.move("left", speed);
			if(GamePanel.user.anyPowerUpIntersectsUserCheck()){
				//gets the power up the user overlaps
				PowerUpTokens currToken =GamePanel.user.anyPowerUpIntersectsUser();
				//gets the type of that token
				int currTokenType =currToken.type;
				//this string of if statements checks which type the power up is and performs that powerups ability
				if(currTokenType==1){
					GamePanel.user.randomTeleport();
				}else if(currTokenType ==2){
					GamePanel.user.invincible();
				}else if(currTokenType ==3){
					GamePanel.user.increaseSpeed();
				}
				GamePanel.powerUpList.remove(currToken);
			}
			//This checks if the user hit an enemy and then sets the user in 'invinccible mode' for a couple seconds
			checkForEnemyCollision();
			Viewer.panel.repaint();
		}else if(direction.equalsIgnoreCase("uparrow")||direction.equalsIgnoreCase("WKey")){
			//Moves the user
			GamePanel.user.move("up", speed);
			//checks if the user overlaps a token
			if(GamePanel.user.anyPowerUpIntersectsUserCheck()){
				//gets the power up the user overlaps
				PowerUpTokens currToken =GamePanel.user.anyPowerUpIntersectsUser();
				//gets the type of that token
				int currTokenType =currToken.type;
				//this string of if statements checks which type the power up is and performs that powerups ability
				if(currTokenType==1){
					GamePanel.user.randomTeleport();
				}else if(currTokenType ==2){
					GamePanel.user.invincible();
				}else if(currTokenType ==3){
					GamePanel.user.increaseSpeed();
				}
				GamePanel.powerUpList.remove(currToken);
			}
			//This checks if the user hit an enemy and then sets the user in 'invinccible mode' for a couple seconds
			checkForEnemyCollision();
			Viewer.panel.repaint();
		}else if(direction.equalsIgnoreCase("downarrow")||direction.equalsIgnoreCase("SKey")){
			//Moves the user
			GamePanel.user.move("down", speed);
			//checks if the user overlaps a token
			if(GamePanel.user.anyPowerUpIntersectsUserCheck()){
				//gets the power up the user overlaps
				PowerUpTokens currToken =GamePanel.user.anyPowerUpIntersectsUser();
				//gets the type of that token
				int currTokenType =currToken.type;
				//this string of if statements checks which type the power up is and performs that powerups ability
				if(currTokenType==1){
					GamePanel.user.randomTeleport();
				}else if(currTokenType ==2){
					GamePanel.user.invincible();
				}else if(currTokenType ==3){
					GamePanel.user.increaseSpeed();
				}
				GamePanel.powerUpList.remove(currToken);
			}
			//This checks if the user hit an enemy and then sets the user in 'invinccible mode' for a couple seconds
			checkForEnemyCollision();
			Viewer.panel.repaint();
		}
		}
	}
	
	/**This checks if the user hit an enemy and then sets the user in 'invincible mode' for a couple seconds. This also takes away a life from the user.
	 * 
	 */
	public void checkForEnemyCollision(){
		//this checks 
		for(EnemyCharacter l : GamePanel.enemyList){
			if(GamePanel.user.overLapsEnemy(l)){
				System.out.println(GamePanel.invincible);
				if(!GamePanel.recovering && !GamePanel.invincible){
					System.out.println(GamePanel.invincible);
					GamePanel.user.lives--;
					System.out.println("Current Number of Lives"+GamePanel.user.lives);
					GamePanel.user.recoverAfterHit();
					GamePanel.enemyList.remove(l);
					break;
				}
			}
		}
	}
	
	
}