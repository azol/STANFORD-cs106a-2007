/*
 * File: FindRange.java
 * Name: Artem Zolochevskiy
 * Section Leader:
 * --------------------
 * This file is the solution file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int SENTINEL = 0;

	public void run() {
		println("This program finds the largest and smallest numbers.");

		int largest = 0;
		int smallest = 0;
		int counter = 0;
		while (true) {
			int n = readInt("? ");
			if ( n == SENTINEL) {
				if (counter == 0) println("You haven't entered any values."); /* Check if the user enters SENTINEL only */
				if (counter == 1) smallest = largest; /* Check if the user enters only one value */
				break;
			}
			if ( n > largest) {
				smallest = largest;
				largest = n;
			} else {
				smallest = (smallest < n) ? smallest : n;
			}
			counter++;
		}

		println("smallest: " + smallest);
		println("largest: " + largest);
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new FindRange().start(args);
	}
}
