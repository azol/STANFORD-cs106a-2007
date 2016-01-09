/*
 * File: PythagoreanTheorem.java
 * Name:
 * Section Leader:
 * -----------------------------
 * This file is the solution file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem");
		int a = readInt("a : ");
		int b = readInt("b : ");
		println("c = " + calculatePythagoreanTheoremC(a, b));
	}

	private double calculatePythagoreanTheoremC(int a, int b) {
		return Math.sqrt((a * a + b * b));
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new PythagoreanTheorem().start(args);
	}
}
