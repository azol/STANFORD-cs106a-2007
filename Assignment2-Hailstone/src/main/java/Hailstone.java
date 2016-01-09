/*
 * File: Hailstone.java
 * Name: Artem Zolochevskiy
 * Section Leader:
 * --------------------
 * This file is the solution file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a number: ");
		int counter = 0;
		while (n != 1) {
			if (n % 2 != 0) {
				println(n +" is odd, so I make 3n + 1: " + (3 * n + 1));
				n = 3 * n + 1;
			} else {
				println(n +" is even, so I take half: " + n / 2);
				n /= 2;
			}
			counter++;
		}
		println("The process took " + counter + " to reach " + n);
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Hailstone().start(args);
	}
}
