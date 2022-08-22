package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food{
	
	private Random random;
	private int maxX;
	private int maxY;
	private int x;
	private int y;
	
	public Food(int height, int width) {
		this.maxX = width/10;
		this.maxY = height/10;
		
		random = new Random();
	}
	
	
	public void spawnFood(boolean[][] position) {
		this.x = random.nextInt(maxX)*10;
		this.y = random.nextInt(maxY)*10;
		
		position[x][y] = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 10, 10);
	}
}
