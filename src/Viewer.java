import java.awt.Color;

import javax.swing.JFrame;

/**
 * This program uses a menu to display font effects.
 */
public class Viewer {
	static GamePanel panel;
	
	
	public static void main(String[] args) {
		JFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Defence Game");
		
		panel = new GamePanel();
		panel.setBackground(Color.WHITE);
		MoveKeyBinding.keyBind(panel);
		panel.setLayout(null);
		frame.add(panel);
		frame.setVisible(true);
	}
}
