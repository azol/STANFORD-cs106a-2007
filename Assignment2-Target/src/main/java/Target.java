/*
 * File: Target.java
 * Name: Artem Zolochevskiy
 * Section Leader:
 * -----------------
 * This file is the solution file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	/* Define number of circles, outer and inner radius in inches. */
	private static final int N_CIRCLES = 3;
	private static final double OUTER_RADIUS = 1;
	private static final double INNER_RADIUS = 0.3;

	/* Pixels per inch */
	private static final double PIXELS_PER_INCH = 72;

	public void run() {
		double cx = getWidth() / 2;
		double cy = getHeight() / 2;
		double radiusStep = inchToPixel((OUTER_RADIUS - INNER_RADIUS) / (N_CIRCLES - 1));
		for (int i = 0; i < N_CIRCLES; i++) {
			add(createFilledCircle(cx, cy,
					inchToPixel(OUTER_RADIUS) - radiusStep * i,
					(i % 2 == 0 ) ? Color.RED : Color.WHITE));
		}
	}

	/*
	 * Returns pixels.
	 */
	private double inchToPixel(double i) {
		return i * PIXELS_PER_INCH;
	}

	/*
	 * Creates a circular GOval object centered at (x, y) with radius r.
	 * The GOval is set to be filled and colored in the specified color.
	 */
	private GOval createFilledCircle(double x, double y, double r,
			Color color) {
		GOval circle = new GOval(x - r, y - r, 2 * r, 2 * r);
		circle.setColor(color);
		circle.setFilled(true);
		return circle;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Target().start(args);
	}
}
