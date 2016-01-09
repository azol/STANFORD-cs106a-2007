/*
 * File: HeapStackDiagrams.java
 * ----------------------------
 */

import acm.program.*;
import acm.graphics.*;

public class HeapStackDiagrams extends ConsoleProgram {

	public void run() {
		GCanvas canvas = new GCanvas();
		add(canvas);
		GImage diagram = new GImage("diagram.png");
		canvas.add(diagram);
		println("Rational r = new Rational(1, 2)");
		Rational r = new Rational(1, 2);
		println("r = raiseToPower(r, 3);");
		r = raiseToPower(r, 3);
		println("r ^ 3 = " + r);
	}

	private Rational raiseToPower(Rational x, int n) {
		Rational result = new Rational(1);
		for (int i = 0; i < n; i++) {
			result = result.multiply(x);
		}
		return result; // Diagram at this point
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new HeapStackDiagrams().start(args);
	}
}
