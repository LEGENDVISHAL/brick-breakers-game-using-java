package brickBreaker;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private boolean play = false; // it starts the game.
	
	private int score = 0;
	private float ballSpeed =  2f;	// controls the ball speed.
	private boolean selectLevel = false; // for level selection.
	int level = 1;		// selecting the level.
	boolean gameOver = false;
	boolean menu = true;
	boolean win = false;

	
	private Timer timer;
	private int delay = 10;
	
	private int playerX = 310;	//slider variable.
	private int ballposX = 120;	
	private int ballposY = 350;
	private float ballXdir = - ballSpeed;	// ball x direction.
	private float ballYdir = -1.5f * ballSpeed;	// ball y direction.
	
	private LevelGenerator map;
	
	private Font titleFont = new Font ("Comic Sans MS", Font.BOLD, 70);
	private Font title2Font = new Font ("Comic Sans MS", Font.BOLD, 50);
	private Font itemFont = new Font ("Calibri", Font.BOLD, 35);
	private Font statusFont = new Font ("Calibri", Font.BOLD, 25);
	Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
	
	public GamePlay() {
		map = new LevelGenerator(4, 12);
		map.createMap(level);
		addKeyListener(this);	//for taking inputs.
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	
	public void reset() {
		menu = true;
		gameOver = false;
		win = false;
		play = false;
		selectLevel = false;
		score = 0;
		ballposX = 120;	
		ballposY = 350;
		ballXdir =  -ballSpeed;
		ballYdir =  -1.5f * ballSpeed;
		
	}
	
	
	public void paint(Graphics g) {
		if(menu == false)
		{
			//background
			g.setColor(Color.white);
			g.fillRect(1, 1, 692, 592);
			
			map.draw((Graphics2D) g);		// drawing the bricks.
			
			//borders
			g.setColor(Color.white);
			g.fillRect(0, 0, 3, 592);	// left boundary.
			g.fillRect(0, 0, 692, 3);	// top boundary.
			g.fillRect(691, 0, 3, 592);	// right boundary.
			
			//slider
			
			g.setColor(Color.yellow);
			g.fillRect(playerX, 550, 100, 8);
			((Graphics2D) g).setStroke(new BasicStroke(2));
			g.setColor(Color.blue);
			g.drawRect(playerX, 550, 100, 8);
			
			
			//ball
			g.setColor(Color.red);
			g.fillOval(ballposX, ballposY, 15, 15);
			
			
			if(play)
			{   
				g.setColor(Color.blue);
				g.setFont(statusFont);
				g.drawString("Score : "+ score, 570 , 30);		//for printing the score.
				g.drawString("Level : "+ level, 30, 30);
			}
			if(win)
			{
				g.setFont(title2Font);
				g.setColor(Color.black);
				g.drawString("Congrats! You Won", 150, 250);
				
				g.setColor(Color.magenta);
				g.drawString("Congrats! You Won", 147, 247);
				g.setFont(statusFont);
				g.setColor(Color.black);
				g.drawString("Press\"Enter\" to Continue", 225, 300);
				g.setColor(Color.orange);
				g.drawString("Press\"Enter\" to Continue", 223, 299);
			}
			
			if(gameOver)
			{
				g.setFont(titleFont);
				g.setColor(Color.black);
				g.drawString("Game Over!", 160, 250);
				
				g.setColor(Color.magenta);
				g.drawString("Game Over!", 155, 245);
				g.setFont(statusFont);
				g.setColor(Color.black);
				g.drawString("Press\"Enter\" to Continue", 225, 300);
				g.setColor(Color.orange);
				g.drawString("Press\"Enter\" to Continue", 223, 299);
			}
		}
		if(selectLevel)
		{
			g.setFont(titleFont);
			
			g.setColor(Color.white);
			g.fillRect(1, 1, 692, 592);
			
			g.setColor(Color.black);
			g.fillRect(150, 150, 100, 100);
			g.fillRect(300, 150, 100, 100);
			g.fillRect(450, 150, 100, 100);
			g.fillRect(225, 300, 100, 100);
			g.fillRect(375, 300, 100, 100);
			
			g.setColor(Color.red);
			g.fillRect(145, 145, 100, 100);
			g.fillRect(295, 145, 100, 100);
			g.fillRect(445, 145, 100, 100);
			g.fillRect(220, 295, 100, 100);
			g.fillRect(370, 295, 100, 100);
			
			g.setColor(Color.black);
			g.drawString("1", 175, 220);
			g.drawString("2", 325, 220);
			g.drawString("3", 475, 220);
			g.drawString("4", 250, 375);
			g.drawString("5", 400, 375);
			
			g.setColor(Color.white);
			g.drawString("1", 170, 215);
			g.drawString("2", 320, 215);
			g.drawString("3", 470, 215);
			g.drawString("4", 245, 370);
			g.drawString("5", 395, 370);
			
			g.setFont(statusFont);
			g.setColor(Color.black);
			g.drawString("Back [ESC]", 520, 520);
			g.setColor(Color.red);
			g.drawString("Back [ESC]", 518, 519);
			

		}
		if(menu)
		{
			g.setFont(titleFont);
			
			g.setColor(Color.white);
			g.fillRect(1, 1, 692, 592);
			
			g.setColor(Color.DARK_GRAY);
			
			g.drawString("Brick-Breakers", 110, 100);
			g.setColor(Color.red);
			g.drawString("Brick-Breakers", 106, 96);
			
			g.setFont(itemFont);
			

			
			g.setColor(Color.GREEN);
			g.drawRect(236, 196, 250, 75);	//startbutton
			g.drawString("Start Game [S]", 254, 249);
			g.drawRect(236, 296, 250, 75);	//levelselect
			g.drawString(" Select Level [L]", 249, 349);
			g.drawRect(236, 396, 250, 75);	//exitbutton
			g.drawString("  Exit [E]", 299, 449);
			
			g.setColor(Color.red);
			g.drawRect(244, 204, 250, 75);	//startbutton
			g.drawString("Start Game [S]", 256, 251);
			g.drawRect(244, 304, 250, 75);	//levelselect
			g.drawString(" Select Level [L]", 251, 351);
			g.drawRect(244, 404, 250, 75);	//exitbutton
			g.drawString("  Exit [E]", 301, 451);
			
			g.setColor(Color.red);
			g.drawRect(240, 200, 250, 75);	//startbutton
			g.setColor(Color.green);
			g.drawRect(240, 300, 250, 75);	//levelselect
			g.setColor(Color.BLUE);
			g.drawRect(240, 400, 250, 75);	//exitbutton
		}
		
		
		g.dispose();	// it clear all the graphics.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) 
		{
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) 
			{
				ballYdir = -ballYdir;
				if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 40, 8))) 
				{
					if(ballXdir > 0) 
					{
						ballXdir = -ballXdir;
					}
				}
				if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX+40, 550, 20, 8))) 
				{
						ballXdir = -ballXdir;
				}
				if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX+60, 550, 40, 8))) 
				{
					if(ballXdir < 0) 
					{
						ballXdir = -ballXdir;
					}
					
				}
			}
			
			A: for(int i = 0; i<map.row; i++)
			{
				for(int j =0; j<map.col; j++)
				{				
					if(map.map[i][j] > 0)
					{
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							if(score == 5 * map.totalBricks)	// all bricks are destroyed, game won.
							{
								win = true;
								play = false;
								
							}
							break A;
						}
						
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = -ballXdir;	// Rebound the ball for left boundary.
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;	// Rebound the ball for top boundary.
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;	// Rebound the ball for right boundary.
			}
			if(ballposY > 560)
			{
				gameOver = true;	//ball goes below the slider. game over.
			}
		}
		
		repaint();	// repaints the graphics.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(play)
		{
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) // if right key pressed. move right.
			{
				if(playerX >= 600) 
				{
					playerX = 600;
				}
				else 
				{
					moveRight();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) // if left key pressed. move left.
			{
				if(playerX < 10) 
				{
					playerX = 10;
				}
				else 
				{
					moveLeft();	
				}
			}
		}
		else if(menu)
		{
			map.createMap(level);
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				play = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_L)
			{
				selectLevel = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_E)
			{
				System.exit(1);
			}
			
			menu = false;
		}
		else if(selectLevel)
		{
			
			if(e.getKeyCode() == KeyEvent.VK_1)
			{
				level = 1;

			}
			if(e.getKeyCode() == KeyEvent.VK_2)
			{
				level = 2;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_3)
			{
				level = 3;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_4)
			{
				level = 4;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_5)
			{
				level = 5;
				
			}
			map.createMap(level);
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
			{
				menu = true;
			}
			else
			{
				play = true;
			}
			selectLevel = false;
			
				
		}
		if(gameOver || win) 
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				reset();
			}
		}
	}
	public void moveRight() {
		play = true;	// start the game.
		playerX += 20;
	}
	public void moveLeft() {
		play = true;	// starts the game.
		playerX-=20;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
