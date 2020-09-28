package brick;

import java.awt.Color;
import java.awt.Graphics;
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
	
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballDirX = -1;
	private int ballDirY = -2;
	
	public Gameplay() {
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
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100,8);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 10, 10);
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if (new Rectangle(ballposX,ballposY, 10, 10).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballDirY = -ballDirY;
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
		playerX = playerX + 25;
		if (playerX > 595) {
			playerX = 595;
		} 
	}

	private void moveLeft() {
		// TODO Auto-generated method stub
		play = true;
		playerX = playerX - 25;
		if (playerX < 5) {
			playerX = 5;
		} 
	}
	

}
