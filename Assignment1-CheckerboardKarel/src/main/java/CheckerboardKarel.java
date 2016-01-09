/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	/*
	 * Karel goes each horizontal row (from left to right)
	 * filling them (odd and even rows) to draw a checkerboard.
	 */
	public void run() {
		placeBeepersOddRow();
		returnToRowStart();
		while (leftIsClear()) {
			goToRowAbove();
			fillRowWithBeepers();
		}
	}

	/*
	 * Move to the row above.
	 * Precondition: Karel is at the beginning of the row facing east.
	 * Postcondition: Karel is at the beginning of the row above facing east.
	 */
	private void goToRowAbove() {
		turnLeft();
		move();
		turnRight();
	}

	/*
	 * Fill the row with beepers and return to the beginning of the row.
	 * Precondition: Karel is at the beginning of a not filled row facing east.
	 * Postcondition: Karel is at the beginning of the filled row facing east.
	 */
	private void fillRowWithBeepers() {
		placeBeepersRow();
		returnToRowStart();
	}

	/*
	 * Place one beepers row (beepers are placed next nearest).
	 * Recognize odd/even rows by checking the beeper below.
	 * Precondition: Karel is at the beginning of a not filled row facing east.
	 * 				 There is a filled row bottom.
	 * Postcondition: Karel is at the end of the filled row facing east.
	 */
	private void placeBeepersRow() {
		goToPreviousRow();
		if (beepersPresent()) {
			goToRowAbove();
			placeBeepersEvenRow();
		} else {
			goToRowAbove();
			placeBeepersOddRow();
		}
	}

	/*
	 * Move one row below.
	 * Precondition: Karel is at the beginning of a row facing east.
	 * Postcondition: Karel is at the beginning of a row below facing east.
	 */
	private void goToPreviousRow() {
		turnRight();
		move();
		turnLeft();
	}

	/*
	 * Place an odd beepers row (beepers are placed next nearest).
	 * Odd row starts with a beeper.
	 * Precondition: Karel is at the beginning of a not filled odd row facing east.
	 * Postcondition: Karel is at the end of the filled odd row facing east.
	 */
	private void placeBeepersOddRow() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}

	/*
	 * Place an even beepers row (beepers are placed next nearest).
	 * Even row starts with an empty corner.
	 * Precondition: Karel is at the beginning of a not filled even row facing east.
	 * Postcondition: Karel is at the end of the filled even row facing east.
	 */
	private void placeBeepersEvenRow() {
		while (frontIsClear()) {
			move();
			putBeeper();
			if (frontIsClear()) {
				move();
			}
		}
	}

	/*
	 * Return to row start.
	 * Precondition: Karel is at the end of a row facing east.
	 * Postcondition: Karel is at the beginning of a row facing east.
	 */
	private void returnToRowStart() {
		turnAround();
		goToWall();
		turnAround();
	}

	/*
	 * Move Karel until it is blocked by a wall.
	 * Postcondition: Karel is facing a wall.
	 */
	private void goToWall() {
		while (frontIsClear()) {
			move();
		}
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new CheckerboardKarel().start(args);
	}
}
