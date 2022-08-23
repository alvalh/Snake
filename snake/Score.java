package snake;

import java.awt.*;

public class Score {
	
	private int score = 0;
	static int HEIGHT;
	static int WIDTH;
	
	public Score(int height, int width) {
		Score.HEIGHT = height;
		Score.WIDTH = width;
	}
	
	public void increaseScore() {
		this.score++;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,30));
		
		g.drawString(String.valueOf(score), WIDTH/2, 30);
	}
}
