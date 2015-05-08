import java.awt.Color;

import javax.swing.JFrame;


public class Viewer {
	static GamePanel panel;
	static MainMenu mainMenu;
	public static String choose;
	public static JFrame frame = new GameFrame();
	public static MazeGamePanel mazePanel;
	public static SnakeGamePanel snakePanel;
	
	public static void main(String[] args) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Defence Game");
		mainMenu=new MainMenu();
		mainMenu.setLayout(null);
		frame.add(mainMenu);
		
		
		frame.setSize(GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		

		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
	}
}
