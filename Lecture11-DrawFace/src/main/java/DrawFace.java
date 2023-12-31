/*
 * File: DrawFace.java
 * -------------------
 * This program draws a GFace in the middle of the screen.
 */

import acm.program.*;
import acm.graphics.*;

public class DrawFace extends GraphicsProgram {

	/** Width of face */
	private static final int FACE_WIDTH = 100;

	/** Height of face */
	private static final int FACE_HEIGHT = 200;
	public void run() {
		GFace face = new GFace(FACE_WIDTH, FACE_HEIGHT);
		add(face, (getWidth() - FACE_WIDTH) / 2,
				(getHeight() - FACE_HEIGHT) / 2);
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new DrawFace().start(args);
	}
}
