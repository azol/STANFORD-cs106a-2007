/*
 * File: Breakout.java
 * -------------------
 * Name: Artem Zolochevskiy
 * Section Leader:
 *
 * This file implements the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

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
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** My own constants */
	private static final int PAUSE_TIME = 10;
	private static final boolean VISUALIZE_COLLISIONS = false;
	private static final int VISUALIZE_COLLISIONS_PAUSE = 200;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		setupGame();
		playGame();
	}

	/* Starts the game.
	 * NTURNS turns.
	 *
	 * Checks for turn results.
	 */
	private void playGame() {
		for (int i = 1; i <= NTURNS; i++) {
			waitForClick();

			/* Remove info label (if exists) and plays turn */
			if (infoMessageLabel != null) remove(infoMessageLabel);
			playTurn();

			/* Breaks if win */
			if (brickCounter == 0 ) {
				removeAll();
				infoMessage("You win!");
				break;
			} else {
				/* Looses if three turns */
				if (i == 3) {
					removeAll();
					infoMessage("You loose.");
					break;
				}
				infoMessage("Turn " + i + " of " + NTURNS + " is over. Click to play.");
			}
		}
	}

	// Plays one turn.
	private void playTurn() {
		createBall();
		setInitialVelocity();
		moveBall();
		remove(ball);
	}

	// Creates a ball and puts it in the center of the window.
	private void createBall() {
		double ballSize = 2 * BALL_RADIUS;
		double x = (WIDTH - ballSize) / 2;
		double y = (HEIGHT - ballSize) / 2;
		ball = new GOval(ballSize, ballSize);
		ball.setFilled(true);
		add(ball, x, y);
	}

	/* This method sets vx to be a random double in the range 1.0 to 3.0
	 * and then makes it negative half the time.
	 */
	private void setInitialVelocity() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vy;
	}

	// Moves ball
	private void moveBall() {
		while (brickCounter != 0) {
			ball.move(vx, vy);

			/* Checks walls */
			if (ball.getY() >= HEIGHT - 2 * BALL_RADIUS) break; /* Bottom wall */
			if (ball.getY() <= 0) vy = -vy;						/* Top wall */
			if ((ball.getX() >= WIDTH - 2 * BALL_RADIUS  ||
				 ball.getX() <= 0)) vx = -vx;					/* Right or left wall */

			/* Checks for objects (collisions) */
			GObject collider = getCollidingObject();

			/* If it is the paddle, you need to bounce the ball so that it starts traveling up.
			 * Don't reverse negative vy (moving up) to avoid "glued" ball effect.
			 */
			if (collider == paddle) {
				if (vy > 0)  vy = -vy;
			} else if (collider != null) {
				remove(collider);
				brickCounter--;

				/* I'm not happy with this code to change the velocity.
				 * if (vy < 0) vy = -vy // reverse negaive vy (moving up) -> positive (moving down).
				 */
				vy = -vy;
			}

			pause(PAUSE_TIME);
		}
	}

	/**
	 * Shows a message in the center of the window.
	 * @param str The message to show.
	 */
	private void infoMessage(String str) {
		infoMessageLabel = new GLabel(str);
		infoMessageLabel.setFont("Serif-28");
		double x = (WIDTH - infoMessageLabel.getWidth()) / 2;
		double y = (HEIGHT + infoMessageLabel.getAscent()) / 2;
		add(infoMessageLabel, x, y);
	}

	/* Checks for collisions.
	 * Checks four corner points on the square in which the ball is inscribed.
	 * Returns the object involved in the collision, if any, and null otherwise.
	 */
	private GObject getCollidingObject() {
		double x = ball.getX();
		double y = ball.getY();

		GPoint collisionPointUpperLeft = new GPoint(x, y);
		GPoint collisionPointUpperRight = new GPoint(x + 2 * BALL_RADIUS, y);
		GPoint collisionPointBottomLeft = new GPoint(x, y + 2 * BALL_RADIUS);
		GPoint collisionPointBottomRight = new GPoint(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);

		if (getElementAt(collisionPointUpperLeft) != null) {
			visualizeCollision(collisionPointUpperLeft.getX(), collisionPointUpperLeft.getY());
			return getElementAt(collisionPointUpperLeft);
		}
		if (getElementAt(collisionPointUpperRight) != null) {
			visualizeCollision(collisionPointUpperRight.getX(), collisionPointUpperRight.getY());
			return getElementAt(collisionPointUpperRight);
		}
		if (getElementAt(collisionPointBottomLeft) != null) {
			visualizeCollision(collisionPointBottomLeft.getX(), collisionPointBottomLeft.getY());
			return getElementAt(collisionPointBottomLeft);
		}
		if (getElementAt(collisionPointBottomRight) != null) {
			visualizeCollision(collisionPointBottomRight.getX(), collisionPointBottomRight.getY());
			return getElementAt(collisionPointBottomRight);
		}
		return null;
	}

	private void visualizeCollision(double x, double y) {
		if (VISUALIZE_COLLISIONS) {
			int pause = VISUALIZE_COLLISIONS_PAUSE;
			for (int i = 0; i < 2; i ++) {
				GRect colPoint =  new GRect(x, y, 5, 5);
				colPoint.setColor(Color.GRAY);
				colPoint.setFilled(true);
				add(colPoint);
				pause(pause);
				remove(colPoint);
				pause(pause);
			}
		}
	}

	/* Sets up the game.
	 * Draws bricks and creates the paddle.
	 */
	private void setupGame() {
		setupBricks();
		createPaddle();
	}

	/* Sets up the bricks.
	 * Creates the centered rows of bricks at the top of the game.
	 *
	 * BRICK_WIDTH is not double, so its calculation is not
	 * 100% OK and I do need to center the bricks.
	 */
	private void setupBricks() {
		/* Calculates initial x position */
		double rowWidth = NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;
		double xFirtsColumn = (WIDTH - rowWidth) / 2.0;

		/* Calculates horizontal and vertical step */
		double dx = BRICK_WIDTH + BRICK_SEP;
		double dy = BRICK_HEIGHT + BRICK_SEP;

		/* Draws bricks filled with a proper color */
		for (int i = 0; i < NBRICK_ROWS; i++) {
			double y = i * dy + BRICK_Y_OFFSET;
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				double x = j * dx + xFirtsColumn;

				/* Chooses brick's color */
				Color color;

				/* My first solution */
				/*
				if (i >= 8 ) {
					color = Color.CYAN;
				} else if (i >= 6 ) {
					color = Color.GREEN;
				} else if (i >= 4 ) {
					color = Color.YELLOW;
				} else if (i >= 2 ) {
					color = Color.ORANGE;
				} else if (i >= 0 ) {
					color = Color.RED;
				}
				else {
					color = Color.BLACK;	// not used "other" color
				}
				*/

				/* Better solution. */
				switch (i % NBRICK_ROWS) {
				case  0:
				case  1: color = Color.RED; break;
				case  2:
				case  3: color = Color.ORANGE; break;
				case  4:
				case  5: color = Color.YELLOW; break;
				case  6:
				case  7: color = Color.GREEN; break;
				default: color = Color.CYAN; break;
				}

				/* Draws one brick */
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setColor(color);
				brick.setFilled(true);
				add(brick, x, y);
			}
		}
	}

	// Creates the paddle.
	private void createPaddle() {
		double x = (WIDTH - PADDLE_WIDTH) / 2;
		double y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, x, y);
	}

	/* This method is called everytime user moves mouse.
	 * Doesn't let the paddle move off the edge of the window.
	 * Changes horizontal position only.
	 */
	public void mouseMoved(MouseEvent e) {
		/* My first solution. Mouse is the upper left corner.
		 * Left edge doesn't need to be tracked.
		 */
		/*
		if (e.getX() <= WIDTH - PADDLE_WIDTH) {
			paddle.setLocation(e.getX(), paddle.getY());
		}
		*/

		/* Second solution. Mouse is in the center of the paddle */
		double xLeftmost = PADDLE_WIDTH / 2;
		double xRightmost = WIDTH - PADDLE_WIDTH / 2;
		if (e.getX() >= xLeftmost && e.getX() <= xRightmost) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, paddle.getY());
		}
	}

	/* Private instance variables */
	private GRect paddle;		/* Ivar for paddle to access to it from listener method */
	private double vx, vy;		/* Ball's velocity */
	private GOval ball;			/* Used in many methods: createBall/moveBall/playTurn */

	GLabel infoMessageLabel;	/* To be able to remove it */

	/* Not sure if I need */
	int brickCounter = NBRICK_ROWS * NBRICKS_PER_ROW;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Breakout().start(args);
	}
}
