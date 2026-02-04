
/*
 * File: Breakout.java
 * -------------------
 * Name:LUKA LOMADZE
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private GRect paddle;
	private GOval ball;
	private GObject broken;
	// private GObject secondObj;
	private RandomGenerator gen = RandomGenerator.getInstance();
	private int vx;
	private int vy = 3;
	private int PAUSE = 10;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void init() {

		drawBricks();

		drawBall();
	}

	public void run() {
		drawPaddle();
		moveBall();
	}

	// moveBall this method includes all actions of the ball
	private void moveBall() {
		int brokenBricks = 0;

		for (int i = 0; i < NTURNS; i++) {
			setSpeed();
			addMouseListeners();
			brokenBricks += ballMovement(brokenBricks);

			if (brokenBricks == NBRICKS_PER_ROW * NBRICK_ROWS) {
				break;
			}
		}
		if (brokenBricks < NBRICKS_PER_ROW * NBRICK_ROWS) {
			removeAll();
			theEnd("you lost");
		}

	}

	// ballMovement this method drives the ball and delate bricks
	private int ballMovement(int brokenBricks) {
		int countBrokenBricks = 0;
		while (ball.getY() <= getHeight()) {
			if (countBrokenBricks + brokenBricks == NBRICKS_PER_ROW * NBRICK_ROWS) {
				pause(100);
				removeAll();
				theEnd("you won");
				break;
			}
			ball.move(vx, vy);
			pause(PAUSE);
			if (ball.getX() <= 0 || ball.getX() >= WIDTH - BALL_RADIUS * 2) {
				vx = -vx;
			}
			if (ball.getY() <= 0) {
				vy = -vy;
			}
			getObj();
			countBrokenBricks += delateBroken();
		}
		return countBrokenBricks;
	}

	// setSpeed this method choose speed of the ball from randomizer
	private void setSpeed() {
		ball.setLocation(getWidth() / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS);
		vx = gen.nextInt(1, 6);
		if (gen.nextBoolean()) {
			vx *= -1;
		}
	}

	// delateBroken this method delates brick which was touched by ball
	private int delateBroken() {
		if (broken != null && broken != paddle) {
			remove(broken);
			broken = null;
			return 1;
		}
		return 0;
	}

	// getObj this method pick object which is touched by ball
	private void getObj() {
		if (getElementAt(ball.getX(), ball.getY()) != null && vy < 0) {
			broken = getElementAt(ball.getX(), ball.getY());
			changeDirection();
		} else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()) != null && vy < 0) {
			broken = getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
			changeDirection();
		} else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null) {
			broken = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
			changeDirection();
		} else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null) {
			broken = getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
			changeDirection();
		}
		paddleAndBall();
	}

	// paddleAndBall this method make sure that ball does not get stuck in paddle
	private void paddleAndBall() {
		if (broken == paddle && ball.getY() + BALL_RADIUS * 2 > paddle.getY()) {

			double difference = ball.getY() - paddle.getY() + BALL_RADIUS * 2;
			if (difference > 0 && difference <= 10) {
				ball.move(0, -difference);
			} else {
				ball.move(0, difference);
			}
		}
	}

	// changeDirection this methid changes the direction of ball after it
	// crashed brick or paddle
	private void changeDirection() {

		if (getElementAt(ball.getX() - 1, ball.getY() + BALL_RADIUS) == broken) {
			vx = -vx;
			System.out.println(5);
		} else {
			vy = -vy;
		}

	}

	// theEnd this method writes the result of a player . if he won or lost the
	// game
	private void theEnd(String string) {
		GLabel result = new GLabel(string);
		result.setFont(Font.DIALOG);
		add(result, (WIDTH - result.getWidth()) / 2, (getHeight() - result.getAscent()) / 2);
	}

	// drawBall this method adds ball on canvas
	private void drawBall() {
		ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball, (getWidth() - BALL_RADIUS) / 2, (getHeight() - BALL_RADIUS) / 2);
	}

	// drawPaddle this method adds paddle on canvas
	private void drawPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		int x = (WIDTH - PADDLE_WIDTH) / 2;
		int y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		add(paddle, x, y);
	}

	// drawBricks this method draws all the bricks
	private void drawBricks() {
		for (int t = 0; t < NBRICK_ROWS; t++) {
			for (int i = 0; i < NBRICKS_PER_ROW; i++) {
				drawOneBrick(i, t);
			}
		}
	}

	// mouseMoved this method is for moving paddle
	public void mouseMoved(MouseEvent e) {
		int x = e.getX() - PADDLE_WIDTH / 2;
		int y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		if (x <= WIDTH - PADDLE_WIDTH && x >= 0) {
			paddle.setLocation(x, y);
		}
	}

	// drawOneBrick this method draw one brick according to parameters
	private void drawOneBrick(int i, int t) {
		double sepFromCell = (WIDTH - BRICK_WIDTH * NBRICKS_PER_ROW - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2;
		double x = sepFromCell + i * (BRICK_WIDTH + BRICK_SEP);
		int y = BRICK_Y_OFFSET + t * (BRICK_HEIGHT + BRICK_SEP);
		GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
		rect.setFilled(true);
		rect.setColor(choseColor(t));
		add(rect, x, y);
	}

	// choseColor this method choose brick's color according to the row number
	private Color choseColor(int t) {
		if (t % 10 == 1 || t % 10 == 0) {
			return Color.red;
		} else if (t % 10 == 3 || t % 10 == 2) {
			return Color.orange;
		} else if (t % 10 == 5 || t % 10 == 4) {
			return Color.yellow;
		} else if (t % 10 == 7 || t % 10 == 6) {
			return Color.green;
		} else {
			return Color.cyan;
		}
	}

}
