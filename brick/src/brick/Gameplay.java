package brick;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 24;
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 220;
	private int ballposY = 350;
	private int ballDirX = 1;
	private int ballDirY = 2;
	
	private MapGenerator map;
	
	public Gameplay() {
		map = new MapGenerator(3, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);
		
		// borders 
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 5, 595); // X Y W H
		g.fillRect(0, 0, 695, 5);
		g.fillRect(695, 0, 5, 595);
		
		// map
		map.draw((Graphics2D)g);
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100,8);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 10, 10);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(""+score, 15, 30);
		
		
		if (ballposY > 570) {
			play = false;
			ballDirX = 0;
			ballDirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over", 280, 300);
			
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 255, 350);
		}
		g.dispose();
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if (new Rectangle(ballposX,ballposY, 10, 10).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballDirY = -ballDirY;
			}
			
			// iterate through every brick. first map is the object and the second is map within mapgenerator
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickW + 80;
						int brickY = i * map.brickH + 60;
						int brickW = map.brickW;
						int brickH = map.brickH;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickW, brickH);
						Rectangle ball = new Rectangle(ballposX, ballposY, 10, 10);
						
						Rectangle brick = rect;
						
						if (ball.intersects(brick)) {
							map.setBRickValue(0, i, j);
							totalBricks--;
							score++;
						
							if (ballposX + 19 <= brick.x || ballposX + 1 >= brick.x + brick.width) {
								ballDirX = -ballDirX;
							} else {
								ballDirY = -ballDirY;
							}
							
							break A;
						}
					}
				}
			}
			ballposX += ballDirX;
			ballposY += ballDirY;
			if (ballposX < 5) {
				ballDirX = -ballDirX;
			}
			if (ballposY < 5) {
				ballDirY = -ballDirY;
			}
			if (ballposX >= 690) {
				ballDirX = -ballDirX;
			}
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		// listen for left and right controls
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft();
		}
		
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		play = true;
		playerX += 25;
		if (playerX > 595) {
			playerX = 595;
		} 
	}

	private void moveLeft() {
		// TODO Auto-generated method stub
		play = true;
		playerX -= 25;
		if (playerX < 5) {
			playerX = 5;
		} 
	}
	

}
