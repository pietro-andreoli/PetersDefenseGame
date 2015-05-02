
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This frame has a menu with commands to manipulate the carts and stack
 */
public class GameFrame extends JFrame {

	static final int FRAME_WIDTH = 700;
	static final int FRAME_HEIGHT = 700;

	/**
	 * Constructs the frame.
	 */
	public GameFrame() {

		// Construct menu
		JMenuBar menuBar = new JMenuBar();
		//setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createStackMenu());
		menuBar.add(createListMenu());

	}

	class ExitItemListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	/**
	 * Creates the File menu.
	 * 
	 * @return the menu
	 */
	public JMenu createFileMenu() {
		JMenu menu = new JMenu("Game");
		JMenuItem exitItem = new JMenuItem("Exit Game");
		ActionListener listener = new ExitItemListener();
		exitItem.addActionListener(listener);
		menu.add(newGameItem());
		menu.add(exitItem);

		return menu;
	}

	public JMenu createStackMenu() {
		JMenu menu = new JMenu("Lives: "+GamePanel.user.lives);
		menu.add(pop());
		menu.add(push());
		return menu;
	}

	public JMenu createNewMenuOption() {
		JMenu menu = new JMenu("New");
		return menu;
	}

	public JMenu createListMenu() {
		JMenu menu = new JMenu("List");
		menu.add(addFirst());
		menu.add(addLast());
		menu.add(removeFirst());
		menu.add(removeLast());
		return menu;
	}

	public JMenuItem newGameItem() {
		class Listener implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {

			}

		}

		JMenuItem item = new JMenuItem("Reset Game");
		ActionListener listener = new Listener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem addFirst() {
		class addFirstListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// Look through the array list of railcars
			}
			}
		JMenuItem item = new JMenuItem("AddFirst");
		ActionListener listener = new addFirstListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem addLast() {
		class addLastListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// Look through the railcars
			
			}
		}
		JMenuItem item = new JMenuItem("AddLast");
		ActionListener listener = new addLastListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem removeFirst() {
		class removeFirstListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
			}
		}
		JMenuItem item = new JMenuItem("RemoveFirst");
		ActionListener listener = new removeFirstListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem removeLast() {
		class removeLastListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
			
			}
		}
		JMenuItem item = new JMenuItem("RemoveLast");
		ActionListener listener = new removeLastListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem pop() {
		class popListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				
			}
		}
		JMenuItem item = new JMenuItem("Pop");
		ActionListener listener = new popListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem push() {
		class pushListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
			}
		}
		JMenuItem item = new JMenuItem("Push");
		ActionListener listener = new pushListener();
		item.addActionListener(listener);
		return item;
	}

}
