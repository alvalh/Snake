package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake {

	private String direction = "up";
	private int length = 0;
	private boolean[][] screen;
	private boolean gameOver = false;
	GameOver GameOver = new GameOver();
	Food food;
	Score score;

	LinkedList<SnakeBody> snake = new LinkedList();

	public Snake(int x, int y, int height, int width) {
		newSnake(x, y);
		fillScreen(height, width);
		newFood(height, width);
		newScore(height, width);
	}

	public void newSnake(int x, int y) {
		SnakeBody head = new SnakeBody(x, y);
		snake.addFirst(head);
		length++;
	}

	public void fillScreen(int height, int width) {
		screen = new boolean[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				screen[i][j] = false;
			}
		}
	}

	public void newFood(int height, int width) {
		food = new Food(height, width);
		food.spawnFood(screen);
	}

	public void newScore(int height, int width) {
		score = new Score(height, width);
	}

	public void move() {
		SnakeBody prevBody = (SnakeBody) snake.getFirst();
		int x = prevBody.x;
		int y = prevBody.y;

		// decide direction of next
		if (direction == "up") {
			y = y - 10;
		} else if (direction == "down") {
			y = y + 10;
		} else if (direction == "left") {
			x = x - 10;
		} else if (direction == "right") {
			x = x + 10;
		}

		// check if snake goes over border
		x = checkBorderX(x);
		y = checkBorderY(y);

		// add new body
		SnakeBody newBody = new SnakeBody(x, y);
		snake.addFirst(newBody);

		// check collision with self or food
		if (screen[x][y] == true) {
			if (food.getX() == x && food.getY() == y) {
				SnakeBody addedBody = new SnakeBody(food.getX(), food.getY());
				snake.addLast(addedBody);
				length++;

				score.increaseScore();

				food.spawnFood(screen);
			} else {
				gameOver = true;
				gameOver();
			}
		} else {
			screen[x][y] = true;
			screen[snake.getLast().x][snake.getLast().y] = false;

			// delete body to make snake move
			snake.removeLast();
		}

	}

	public int checkBorderY(int y) {
		if (y >= 300) {
			y = y - 300;
		} else if (y < 0) {
			y = y + 300;
		}
		return y;
	}

	public int checkBorderX(int x) {
		if (x >= 300) {
			x = x - 300;
		} else if (x < 0) {
			x = x + 300;
		}
		return x;
	}

	public void gameOver() {
		System.out.println("GAME OVER");
	}

	public void draw(Graphics g) {
		SnakeBody temp = null;
		if (!gameOver) {
			for (int i = 0; i < length; i++) {
				temp = snake.get(i);
				temp.draw(g);
				food.draw(g);
				score.draw(g);
			}
		} else {
			GameOver.draw(g);
		}
		
		//  for (int i = 0; i < 300; i++) { for (int j = 0; j < 300; j++) { if
		 // (screen[i][j]) { debugDraw(g, i, j); } } }
		 
	}

	
	//  public void debugDraw(Graphics g, int x, int y) { g.setColor(Color.BLUE);
	//  g.fillRect(x, y, 10, 10); }
	 
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (direction != "up") {
				direction = "down";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (direction != "down") {
				direction = "up";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (direction != "right") {
				direction = "left";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (direction != "left") {
				direction = "right";
			}
		}
	}

}
