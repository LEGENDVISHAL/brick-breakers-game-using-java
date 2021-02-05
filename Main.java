package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		GamePlay gamePlay = new GamePlay();
		
		window.setSize(707, 600);
		window.setTitle("!! Brick Breakers !!");
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.add(gamePlay);
		
		
		
	}

}
