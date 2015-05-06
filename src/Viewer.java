import java.awt.Color;

import javax.swing.JFrame;

/**
 * This program uses a menu to display font effects.
 */
public class Viewer {
	static GamePanel panel;
	static MainMenu mainMenu;
	public static String choose;
	public static JFrame frame = new GameFrame();
	
	public static void main(String[] args) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Defence Game");
		mainMenu=new MainMenu();
		frame.add(mainMenu);
		if(choose=="default"){
			panel = new GamePanel();
			panel.setBackground(Color.WHITE);
			MoveKeyBinding.keyBind(panel);
			panel.setLayout(null);
			frame.add(panel);
		}
		
		frame.setSize(GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		

		frame.setVisible(true);
	}
}
/**
JFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Defence Game");
		
		panel = new GamePanel();
		panel.setBackground(Color.WHITE);
		MoveKeyBinding.keyBind(panel);
		panel.setLayout(null);
		frame.setSize(GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		frame.add(panel);

		frame.setVisible(true);
*/