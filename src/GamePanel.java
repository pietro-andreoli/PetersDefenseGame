
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
/**
 * @author Peter
 *
 */

public class GamePanel extends JPanel  {
	JButton startButton;
	JTextField nameText;
	JRadioButton easy;
	JRadioButton medium;
	JRadioButton hard;
	ButtonGroup difficulties;
	static long startTime=System.currentTimeMillis();
	//The GameLoop variables
	Thread thread;
	static int fps=60;
	static long targetTime=1000/fps; //the target amount of time we want it to take to make one loop in the game
	//end of GameLoop variables
	static boolean gameState=false; //false = game is off, true = game is on
	String playerName;
	static boolean gameModeEasy;//easy = 1 medium = 2 hard = 3
	static boolean gameModeMedium;
	boolean gameModeHard;
	//static UserCharacter user=new UserCharacter(GameFrame.FRAME_WIDTH/2-UserCharacter.characterWidth, GameFrame.FRAME_HEIGHT/2-UserCharacter.characterHeight);
	static UserCharacter user;
	static PowerUpTokens powerUp1;
	static PowerUpTokens powerUp2;
	static PowerUpTokens powerUp3;
	static ArrayList<PowerUpTokens> powerUpList= new ArrayList<PowerUpTokens>();
	static ArrayList<EnemyCharacter> enemyList= new ArrayList<EnemyCharacter>();
	static ArrayList<UserBullet> bullets = new ArrayList<UserBullet>();
	TopBar topBar=new TopBar();
	//The following are the power up properties of the user
	static boolean invincible=false;
	static boolean recovering=false;//if the user gets hit, they get a few seconds of invincibility
	static boolean isFast=false;
	int totalEnemies =20;
	int totalPowerUps=10;
	static Timer recover;
	static double recoveryTimeCount=0;
	
	
	public GamePanel(){
		startButton = new JButton("Start");
		startButton.setBounds(GameFrame.FRAME_WIDTH/2-40, GameFrame.FRAME_HEIGHT-200, 80, 30);
		add(startButton);
		//***REMINDER*** FIX THIS JLabel
		final JLabel enterName = new JLabel("Enter Name");
		enterName.setBounds(GameFrame.FRAME_WIDTH/2-40, GameFrame.FRAME_HEIGHT/2-50, 80, 30);
		add(enterName);
		
		nameText = new JTextField(15);
		nameText.setBounds(GameFrame.FRAME_WIDTH/2-50, 360, 100, 23);
		add(nameText);
		//creating the radio buttons for difficulty
		easy=new JRadioButton("Easy");
		medium=new JRadioButton("Medium");
		hard=new JRadioButton("Hard");
		difficulties = new ButtonGroup();
		difficulties.add(easy);
		difficulties.add(medium);
		difficulties.add(hard);
		easy.setSelected(true);
		easy.setBounds(GameFrame.FRAME_WIDTH/2-40, GameFrame.FRAME_HEIGHT-300, 70, 20);
		medium.setBounds(GameFrame.FRAME_WIDTH/2-40, GameFrame.FRAME_HEIGHT-280, 70, 20);
		hard.setBounds(GameFrame.FRAME_WIDTH/2-40, GameFrame.FRAME_HEIGHT-260, 70, 20);
		add(easy);
		add(medium);
		add(hard);
		
		class Animate implements ActionListener {
			//timer that animates the enemies
			public void actionPerformed(ActionEvent e) {
				//checks for collision
				MovingComponent.checkForEnemyCollision();
				
				for(EnemyCharacter enemy : enemyList){
					if(enemy.type==0){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH){
								enemy.side=2;
							}else{
								enemy.setLocation(enemy.body.getX()+4, enemy.body.getY());
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.yPos>GameFrame.FRAME_HEIGHT){
								enemy.side=3;
								
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+4);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos<=0){
								enemy.side=0;
							}else{
								enemy.setLocation(enemy.body.getX()-4, enemy.body.getY());
							}
						}
						else if(enemy.side==3){
							if(enemy.yPos<=0){
								enemy.side=1;
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-4);
							}
						}
						
					}// end of if enemy == 0 
					else if(enemy.type==1){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH){
								enemy.side=2;
							}else{
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY());
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.yPos>GameFrame.FRAME_HEIGHT){
								enemy.side=3;
								
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+2);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos<=0){
								enemy.side=0;
							}else{
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getY());
							}
						}
						else if(enemy.side==3){
							if(enemy.yPos<=0){
								enemy.side=1;
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-2);
							}
						}
					}else if(enemy.type==2){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH){
								enemy.side=3;
							}else{
								//int diff=GameFrame.FRAME_WIDTH-enemy.body.getX()
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getX()+ Math.sqrt(enemy.body.getX()+20));
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.yPos>GameFrame.FRAME_HEIGHT){
								enemy.side=3;
								
							}else{
								enemy.setLocation(enemy.body.getY()+Math.sqrt(enemy.body.getY()+10), enemy.body.getY()+2 );
								//enemy.setLocation(enemy.body.getX()+Math.cos((double)(enemy.body.getY()+10)), enemy.body.getY()+2);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos<=0){
								enemy.side=0;
							}else{
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getX()+Math.sqrt(enemy.body.getX()-10));
							}
						}
						else if(enemy.side==3){
							if(enemy.yPos<=0){
								enemy.side=1;
							}else{
								enemy.setLocation(enemy.body.getY()+Math.sqrt(enemy.body.getY()-10), enemy.body.getY()-2 );
							}
						}
					}
					
				}//end of enemy character animation
				for(int i =0; i<enemyList.size();i++){
					for(int j=0; j<bullets.size();j++){
						if(enemyList.get(i).isHit(bullets.get(j))){
							enemyList.remove(i);
							bullets.remove(j);
							repaint();
						}
					}
				}
				
			}

		}
		//adds the timer and starts the timer
		final Timer animate = new Timer(1, new Animate());
		
		class BulletAnimate implements ActionListener {
			//timer that animates the bullets
			public void actionPerformed(ActionEvent e) {
				for(UserBullet b : bullets){
					if(b.xPos>GameFrame.FRAME_WIDTH || b.yPos>GameFrame.FRAME_HEIGHT || b.xPos<=0 || b.yPos<=0){
						//do nothing
					}else{
						if(b.mouseOrBullet=="mouse"){
							b.setPosition(b.xPos-1, user.yPos-b.trajectory*(user.xPos-b.xPos+1));
						}else if(b.mouseOrBullet=="bullet"){
							b.setPosition(b.xPos+1, user.yPos-b.trajectory*(user.xPos-b.xPos+1));
							
						}
					}
					
					repaint();
				}
			}
		}
		final Timer bulletAnimate = new Timer(1, new BulletAnimate());
		
		//the listener that adds action to startButton
		class MyListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//sets the player name to the inputted text at the beginning fo the game
				playerName=nameText.getText();
				//if theres no name entered, simply put player
				if(playerName.length()==0)
					playerName="Player";
				//sets the game to on
				gameState=true;
				//checks which difficulty was selected, then sets the game mode to that difficulty
				gameModeEasy=easy.isSelected();
				gameModeMedium=medium.isSelected();
				gameModeHard=hard.isSelected();
				System.out.println(playerName);
				//Checks the current difficulty and prints to console
				if(gameModeEasy){
				System.out.println("The Game Difficulty is Set to Easy");
				}else if(gameModeMedium){
					System.out.println("The Game Difficulty is Set to Medium");
				}else{
					System.out.println("The Game Difficulty is Set to Hard");
				}
				//gets rid of the text field, JButton and Radio Buttons
				startButton.setVisible(false);
				nameText.setVisible(false);
				easy.setVisible(false);
				medium.setVisible(false);
				hard.setVisible(false);
				enterName.setVisible(false);
				//Now that the game is started, create the user
				 user=new UserCharacter(GameFrame.FRAME_WIDTH/2-UserCharacter.characterWidth, GameFrame.FRAME_HEIGHT/2-UserCharacter.characterHeight);
				 
				 //now that the game has started, create the enemies. This big block of code just makes an enemy, checks if the enemy overlaps with anyone, thena adds to list.		 
				 while(enemyList.size()<totalEnemies){
					 boolean overlaps=false;
					 EnemyCharacter enemy=new EnemyCharacter();
					 for(EnemyCharacter l : enemyList){
						 if(enemy.overlapsOtherEnemy(l)){
							 overlaps=true;
							 break;
						 }//close if
					 }//close for
					 if(!overlaps)
						 enemyList.add(enemy);
				 }//close while
				 
				 
				//Adds 10 power ups to the list
				 int count=0;
				 while(powerUpList.size()<totalPowerUps){
					 boolean overlaps=false;
					 PowerUpTokens pUp= new PowerUpTokens(count);
					 for(PowerUpTokens l : powerUpList){
						 if(pUp.overlapsOtherPowerUp(l)){
							 overlaps=true;
							 break;
						 }//close if
					 }//close for
					 if(!overlaps)
						 powerUpList.add(pUp);
				 }

				 repaint();
				 addMouseListener(new UserBullet());
				 animate.start();
				 bulletAnimate.start();
			}//closes actionPerformed

		}//closes MyListener
		//Adds action to the startButton
		MyListener listener = new MyListener();
		startButton.addActionListener(listener);
		
			
		
		//This class just repaints the screen every 0.01 seconds
		class RepaintTimerListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				repaint();
			}

		}
		//adds the timer and starts the timer
		Timer repaintFrame = new Timer(10, new RepaintTimerListener());
		repaintFrame.start();
		//this timer is for the recovery animation
		class RecoveryTimer implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				if(user.visible)
					user.visible=false;
				else user.visible=true;
				repaint();
				recoveryTimeCount++;
			}

		}
		//adds the timer and starts the timer
		 recover = new Timer(150, new RecoveryTimer());
		repaintFrame.start();
		
	}//closes GamePanel constructor
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		//if the start button has been clicked (the gameState is now true) then draw the user token
		if(gameState){
			//if the user is visible, then draw the user
			
			if(user.visible){
				user.draw(g2);
				if(recoveryTimeCount>(1000/150)){
					GamePanel.recover.stop();
					recoveryTimeCount=0;
				}
			}
			
				for(UserBullet b : bullets){
					b.draw(g2);
				}
			
			topBar.draw(g2);
			//if the user is in recovery mode, flash upon hit
			if(recovering){
				GamePanel.recover.start();
			}
			//draw all powerups
			for(PowerUpTokens powerUps : powerUpList){
				powerUps.draw(g2);
			}
			//draw all enemies
			for(EnemyCharacter list : enemyList){
				list.draw(g2);
			}
			
		}
	}//closes paint method
	
	/**Gets the games current difficulty
	 * @return the difficulty in integer form. 1 = easy, 2 = medium, 3 = hard
	 */
	public static int getDifficulty(){
		if(gameModeEasy){
				return 1;
			}else if(gameModeMedium){
				return 2;
			}else{
				return 3;
			}
	}
	
	
	
	



	
}















