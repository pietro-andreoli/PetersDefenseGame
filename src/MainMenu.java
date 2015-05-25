import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MainMenu extends JPanel {

	public MainMenu(){
		JButton survival=new JButton("Survival");
		survival.setBounds(230, 230, 80, 35);
		JButton maze=new JButton("Maze");
		maze.setBounds(320, 230, 80, 35);
		JButton snake=new JButton("Snake");
		snake.setBounds(410, 230, 80, 35);
		class SurvivalButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				Viewer.panel = new GamePanel();
				Viewer.panel.setBackground(Color.WHITE);
				MoveKeyBinding.keyBind(Viewer.panel);
				Viewer.panel.setLayout(null);
				Viewer.frame.remove(Viewer.mainMenu);
				//when you remove a component it invalidates the hierarchy. To re-validate it, you must us the following method
				Viewer.frame.validate();
				Viewer.frame.add(Viewer.panel);
				Viewer.frame.validate();
				Viewer.frame.repaint();
				Viewer.panel.repaint();
			}
		}
			survival.addActionListener(new SurvivalButtonListener());
			add(survival);
			
			
		class MazeButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
					Viewer.mazePanel = new MazeGamePanel();
					Viewer.mazePanel.setBackground(Color.WHITE);
					//MoveKeyBinding.keyBind(Viewer.mazePanel); *****FIX*****
					Viewer.mazePanel.setLayout(null);
					Viewer.frame.remove(Viewer.mainMenu);
					//when you remove a component it invalidates the hierarchy. To re-validate it, you must us the following method
					Viewer.frame.validate();
					Viewer.frame.add(Viewer.mazePanel);
					Viewer.frame.validate();
					Viewer.frame.repaint();
					Viewer.mazePanel.repaint();
				}
			}
				maze.addActionListener(new MazeButtonListener());
				add(maze);		
		class SnakeButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
					Viewer.snakePanel = new SnakeGamePanel();
					Viewer.snakePanel.setBackground(Color.BLACK);
					SnakeMoveKeyBinding.keyBind(Viewer.snakePanel); 
					Viewer.snakePanel.setLayout(null);
					Viewer.frame.remove(Viewer.mainMenu);
					//when you remove a component it invalidates the hierarchy. To re-validate it, you must us the following method
					Viewer.frame.validate();
					Viewer.frame.add(Viewer.snakePanel);
					Viewer.frame.validate();
					Viewer.frame.repaint();
					Viewer.snakePanel.repaint();
					Viewer.choose="Snake";
						}
					}
						snake.addActionListener(new SnakeButtonListener());
						add(snake);	
	}
}
