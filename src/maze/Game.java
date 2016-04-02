package maze;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame window = new JFrame("The Amazing Maze of Doom");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
}