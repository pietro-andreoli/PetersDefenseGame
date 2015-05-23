import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SnakeGamePanel extends JPanel  {
	//Image for the menu screen
	BufferedImage snakeScreen;
	static boolean gameState=false;//true = game is running, false = game is not running
	boolean gameOver=false;//true = the game is over, false = game is not over
	Font menuFont=new Font("Helvetica", Font.BOLD, 24);//font on the menu
	String playerName;
	static UserCharacterSnake user;
	final JButton start;
	static SnakeToken currToken;
	static ArrayList<SnakeToken> attatchedTokens=new ArrayList<SnakeToken>();
	
	public SnakeGamePanel(){
		//try catch for importing the Snake menu picture.
		try{
		snakeScreen= ImageIO.read(new File("C:/users/peter/workspace/MyDefenseGame/src/SnakeMenu.jpg"));
		}catch(IOException exception){
			//do nothing
		}
		//Field for entering name
		 final JTextField nameField=new JTextField();
		nameField.setBounds(GameFrame.FRAME_WIDTH/2-59, GameFrame.FRAME_HEIGHT/2+5, 133, 23);
		add(nameField);
		//button for starting game
		 start = new JButton("Start");
		start.setBounds(GameFrame.FRAME_WIDTH/2-44, GameFrame.FRAME_HEIGHT/2+45, 100, 30);
		add(start);
		//class for snake animation
		class AnimationTimer implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(currToken==null){
					currToken= new SnakeToken();
				}
				if(user.direction==1){
					user.setPosition(user.xPos-2, user.yPos);
				}else if(user.direction==2){
					user.setPosition(user.xPos, user.yPos-2);
				}else if(user.direction==3){
					user.setPosition(user.xPos+2, user.yPos);
				}else if(user.direction==4){
					user.setPosition(user.xPos, user.yPos+2);
				}
				if(user.checkCollision()){
					attatchedTokens.add(currToken);
					currToken.attatchToEnd();
					currToken=new SnakeToken();
					System.out.println("broke at timer");
				}
				repaint();
				
			}
			
		}
		final Timer animationTimer = new Timer(17, new AnimationTimer());
		
		
		//action for the start button
		class startButton implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				//get the players name
				playerName=nameField.getText();
				//logic for making sure the player enters a name
				if(playerName.length()==0){
					Toolkit.getDefaultToolkit().beep();
					throw new IllegalArgumentException();
				}
				//create the user
				user= new UserCharacterSnake();
				//sets the game to on
				gameState=true;
				//sets the button and text field invisible
				nameField.setVisible(false);
				start.setVisible(false);
				animationTimer.start();
				repaint();
			}
			
		}
		
		start.addActionListener(new startButton());
		
		
	}//end of constructor
	
	public static void doUpdate(double delta) {
		user.setPosition(user.xPos+1, user.yPos+1);
		System.out.println("x: "+user.xPos + "y: "+user.yPos+"box x: "+user.body.x+" box y: "+user.body.y);
		 System.out.println("lmao");
		 Viewer.snakePanel.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//if the game has not started and the game isnt over (in other words, if we are at the start menu)
		if(!gameState && !gameOver){
			//draw the image for the menu screen
			g2.drawImage(snakeScreen, 0, 0, null);
			g2.setFont(menuFont);
			g2.drawString("Enter Name", GameFrame.FRAME_WIDTH/2-60, GameFrame.FRAME_HEIGHT/2);
		}
		if(gameState && user != null){
			user.draw(g2);
			if(currToken != null){
				currToken.draw(g2);
			}
		}
		
	}
	
}
