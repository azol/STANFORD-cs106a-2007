/*
 * File: MyEtchASketch.java
 * ------------------------
 * This program solves the Etch-a-Sketch problem from the practice final
 */

import acm.program.*;
import acm.graphics.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MyEtchASketch extends GraphicsProgram {

	private static final int CROSS_SIZE = 10;
	private static final int MOVE_STEP = 20;

	public void init() {
		add(new JButton("North"), SOUTH);
		add(new JButton("South"), SOUTH);
		add(new JButton("East"), SOUTH);
		add(new JButton("West"), SOUTH);
		addCross();

		addActionListeners();
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		double currentPointX = cross.getX();
		double currentPointY = cross.getY();
		if (cmd.equals("North")) {
			cross.setLocation(currentPointX, currentPointY - MOVE_STEP);
			drawLine(currentPointX, currentPointY);
		}
		if (cmd.equals("South")) {
			cross.setLocation(cross.getX(), cross.getY() + MOVE_STEP);
			drawLine(currentPointX, currentPointY);
		}
		if (cmd.equals("East")) {
			cross.setLocation(cross.getX() + MOVE_STEP, cross.getY());
			drawLine(currentPointX, currentPointY);
		}
		if (cmd.equals("West")) {
			cross.setLocation(cross.getX() - MOVE_STEP, cross.getY());
			drawLine(currentPointX, currentPointY);
		}
	}

	private void drawLine(double x, double y) {
		GLine line = new GLine(x, y, cross.getX(), cross.getY());
		line.setColor(Color.RED);
		add(line);
	}

	private void addCross() {
		GLine backslash = new GLine(-(CROSS_SIZE / 2), -(CROSS_SIZE / 2), CROSS_SIZE / 2, CROSS_SIZE / 2);
		GLine slash = new GLine(CROSS_SIZE / 2, -(CROSS_SIZE / 2), -(CROSS_SIZE / 2), CROSS_SIZE / 2);
		cross.add(backslash);
		cross.add(slash);
		add(cross, getWidth() / 2, getHeight() / 2);
	}

	private GCompound cross = new GCompound();

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new MyEtchASketch().start(args);
	}
}
