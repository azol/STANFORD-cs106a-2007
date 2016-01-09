/*
 * File: FlipVertical.java
 * -----------------------
 */

import acm.program.*;
import acm.graphics.*;

public class FlipVertical extends GraphicsProgram {

	public void run() {
		GImage image = new GImage("milkmaid.jpg");
		add(image, 10, 10);
		GImage imageFlipped = flipHorizontal(new GImage("milkmaid.jpg"));
		add(imageFlipped, 250, 10);
	}

	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		for (int row = 0; row < height; row++) {
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
				int temp = array[row][p1];
				array[row][p1] = array[row][p2];
				array[row][p2] = temp;
			}
		}
		return new GImage(array);
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new FlipVertical().start(args);
	}
}
