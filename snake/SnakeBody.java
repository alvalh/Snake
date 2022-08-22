package snake;

import java.awt.*;

public class SnakeBody  {

	private SnakeBody next;
	private SnakeBody prev;
	public int x;
	public int y;

	public SnakeBody(int x, int y) {
		//super(x, y, 10, 10);
		this.x = x;
		this.y = y;
	}

	public void setNextBody(SnakeBody body) {
		this.next = body;
	}

	public void setPrevBody(SnakeBody body) {
		this.prev = body;
	}


	public SnakeBody getNextBody() {
		return this.next;
	}

	public SnakeBody getPrevBody() {
		return this.prev;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 10, 10);
	}
}
