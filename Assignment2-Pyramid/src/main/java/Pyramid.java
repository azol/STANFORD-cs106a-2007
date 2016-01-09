/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * This file is the solution file for the Pyramid problem.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		double y = (getHeight() - BRICK_HEIGHT);
		for (int i = BRICKS_IN_BASE; i >= 1; i--) {
			double x = (getWidth() - BRICK_WIDTH * i) / 2;
			for ( int j = 0; j < i; j++) {
				GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
				x += BRICK_WIDTH;
			}
			y -= BRICK_HEIGHT;
		}
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Pyramid().start(args);
	}
}
