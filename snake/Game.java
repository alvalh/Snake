package snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable {
	JPanel panel = new JPanel();

	static final int WIDTH = 300;
	static final int HEIGHT = 300;
	static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
	Image image;
	Graphics graphics;
	Thread thread;

	Snake snake;
	
	Game() {
		newSnake();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		thread = new Thread(this);
		thread.start();
	}

	public void newSnake() {
		snake = new Snake(HEIGHT/2, WIDTH/2, HEIGHT, WIDTH);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void move() {
		snake.move();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		snake.draw(g);
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 8;
		// / 64

		double delta = 0;

		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while (delta >= 1) {
				move();
				repaint();
				delta--;
			}
		}
	}
}
