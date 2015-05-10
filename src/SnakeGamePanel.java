import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeGamePanel extends JPanel{
	//Image for the menu screen
	BufferedImage snakeScreen;
	static boolean gameState=false;//true = game is running, false = game is not running
	boolean gameOver=false;//true = the game is over, false = game is not over
	Font menuFont=new Font("Helvetica", Font.BOLD, 24);//font on the menu
	String playerName;
	static UserCharacterSnake user;
	
	
	
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
		final JButton start = new JButton("Start");
		start.setBounds(GameFrame.FRAME_WIDTH/2-44, GameFrame.FRAME_HEIGHT/2+45, 100, 30);
		add(start);
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
				//sets the game to on
				gameState=true;
				//sets the button and text field invisible
				nameField.setVisible(false);
				start.setVisible(false);
				//create the user
				user= new UserCharacterSnake();
				repaint();
				
			}
			
		}
		start.addActionListener(new startButton());
	}//end of constructor
	
	
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
		}
		
	}
}
