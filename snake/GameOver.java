package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,30));
		
		g.drawString("GAME OVER", 80, 150);
	}
}
