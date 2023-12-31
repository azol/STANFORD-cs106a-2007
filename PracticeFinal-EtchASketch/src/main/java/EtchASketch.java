/*
 * File: EtchASketch.java
 * ----------------------
 * This program solves the Etch-a-Sketch problem from the practice final
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EtchASketch extends GraphicsProgram {

	/** Cross size */
	private static final double CROSS_SIZE = 10;

	/** Step size */
	private static final double STEP_SIZE = 20;

	/** Initialize the application */
	public void init() {
		add(new JButton("North"), SOUTH);
		add(new JButton("South"), SOUTH);
		add(new JButton("East"), SOUTH);
		add(new JButton("West"), SOUTH);
		x = getWidth() / 2;
		y = getHeight() / 2;
		double delta = CROSS_SIZE / 2;
		cross = new GCompound();
		cross.add(new GLine(-delta, -delta, delta, delta));
		cross.add(new GLine(-delta, delta, delta, -delta));
		add(cross, x, y);
		addActionListeners();
	}

	/** Called when an action event is detected */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("North")) {
			moveCross(0, -STEP_SIZE);
		} else if (cmd.equals("South")) {
			moveCross(0, STEP_SIZE);
		} else if (cmd.equals("East")) {
			moveCross(STEP_SIZE, 0);
		} else if (cmd.equals("West")) {
			moveCross(-STEP_SIZE, 0);
		}
	}
	/**
	 * Moves the cross and adds a red line to the canvas connecting its
	 * old and new positions.
	 */
	private void moveCross(double dx, double dy) {
		GLine line = new GLine(x, y, x + dx, y + dy);
		line.setColor(Color.RED);
		add(line);
		x += dx;
		y += dy;
		cross.move(dx, dy);
	}

	/* Private instance variables */
	private GCompound cross;
	private double x, y;

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new EtchASketch().start(args);
	}
}
