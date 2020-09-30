package brick;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickW;
	public int brickH;
	public MapGenerator(int row, int col) {
		map = new int [row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		brickW = 540/col;
		brickH = 100/row;
		
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * brickW + 80, i * brickH + 60, brickW, brickH);
					
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.black);
					g.drawRect(j * brickW + 80, i * brickH + 60, brickW, brickH);
				}
			}
		}
	}
	
	public void setBRickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
