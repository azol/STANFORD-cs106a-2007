/*
 * File: ProgramHierarchy.java
 * Name: Artem Zolochevskiy
 * Section Leader:
 * ---------------------------
 * This file is the solution file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {
	/* Width and height of boxes. */
	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 50;

	/* Name of superclass. */
	private static final String SUPERCLASS_NAME = "Program";

	/* Space between hierarchy levels. */
	private static final double HIERARCHY_LEVEL_INSET = 60;

	/* Number of subclasses. */
	private static final int N_SUBCLASSES = 3;

	/* Vertical space between subclasses. */
	private static final double SUBCLASSES_INSET = 30;

	/* Names of subclasses. */
	private static final String SUBCLASS_NAME_1 = "GraphicsProgram";
	private static final String SUBCLASS_NAME_2 = "ConsoleProgram";
	private static final String SUBCLASS_NAME_3 = "DialogProgram";

	public void run() {
		double hierarchyHeight = 2 * BOX_WIDTH + HIERARCHY_LEVEL_INSET;
		double xSuperclass = (getWidth() - BOX_WIDTH) / 2;
		double ySuperclass = (getHeight() - hierarchyHeight) / 2;
		drawSuperclass(xSuperclass, ySuperclass, SUPERCLASS_NAME);

		double hierarchyWidth = N_SUBCLASSES * BOX_WIDTH + (N_SUBCLASSES - 1) * SUBCLASSES_INSET;
		double xSubclasses = (getWidth() - hierarchyWidth) / 2;
		double ySubclasses = ySuperclass + BOX_HEIGHT + HIERARCHY_LEVEL_INSET;
		drawSubclasses(xSubclasses, ySubclasses, SUBCLASSES_INSET, N_SUBCLASSES);

	}

	private void drawSuperclass(double x, double y, String className) {
		drawClassBox(x, y, className);

	}

	private void drawSubclasses(double x, double y, double inset, int nSubclasses) {
		for (int i = 0; i < N_SUBCLASSES; i++){
			/* Chooses a proper label. Left right order. */
			String subclassName;
			switch (i) {
				case  0: subclassName = SUBCLASS_NAME_1;  break;
				case  1: subclassName = SUBCLASS_NAME_2;  break;
				case  2: subclassName = SUBCLASS_NAME_3;  break;
				default: subclassName = "Unknown"; break;
			}

			/* add box */
			double xSubclass = x + i * (BOX_WIDTH + inset);
			drawClassBox(xSubclass, y, subclassName);

			/* add lines */
			double x1Line = xSubclass + BOX_WIDTH / 2;
			double x2Line = getWidth() / 2;
			double y2Line = y - HIERARCHY_LEVEL_INSET;
			add(new GLine(x1Line, y, x2Line, y2Line));
		}
	}

	private void drawClassBox(double x, double y, String className) {
		add(new GRect(x, y, BOX_WIDTH, BOX_HEIGHT));
		/* Adds label centered inside box */
		GLabel label = new GLabel(className);
		double xLabel = x + BOX_WIDTH / 2 - label.getWidth() / 2;
		double yLabel = y + BOX_HEIGHT / 2 + label.getAscent() / 2;
		label.move(xLabel, yLabel);
		add(label);
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new ProgramHierarchy().start(args);
	}
}
