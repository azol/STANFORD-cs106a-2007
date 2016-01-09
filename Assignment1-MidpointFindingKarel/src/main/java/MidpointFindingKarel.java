/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		goToMidpoint();
		putBeeper();
		cleanTemporaryBeepers();
		goToBeeper();
	}

	/*
	 * Move to the center of the square worlds part, then move down up to wall.
	 * Precondition: Karel is on the left bottom corner facing east.
	 * Postcondition: Karel is on the midpoint of the first street facing east.
	 */
	private void goToMidpoint() {
		goToSquareCenter();
		turnRight();
		goToWall();
		turnLeft();
	}

	/*
	 * Move to the center of the square (in the point of diagonals intersection).
	 * Precondition: Karel is on the left bottom corner facing east.
	 * Postcondition: Karel is the center of the square facing east.
	 */
	private void goToSquareCenter() {
		markDoubleDiagonalBottomUp();
		turnAround();
		goToWall();
		turnAround();
		goDiagonalTopDownTillBeeper();
	}

	/*
	 * Mark bottom up diagonal with beepeers.
	 * Need double diagonal to make it work with odd/even sizes squares.
	 * Precondition: Karel is on the left bottom  corner facing east.
	 * Postcondition: Karel is on the upper right corner facing east.
	 */
	private void markDoubleDiagonalBottomUp() {
		while (frontIsClear()) {
			putBeeper();
			move();
			putBeeper();
			turnLeft();
			move();
			turnRight();
		}
		putBeeper();
	}

	/*
	 * Move (top down diagonal) to the point of intersection with the bottom up diagonal.
	 * Precondition: Karel is on the upper left corner facing east.
	 * Postcondition: Karel is in the center of the square facing east.
	 */
	private void goDiagonalTopDownTillBeeper() {
		while (noBeepersPresent()) {
			move();
			turnRight();
			move();
			turnLeft();
		}
	}

	/*
	 * Clean temporary beepers (bottom up diagonal).
	 * Precondition: Karel is on the midpoint of the first street facing east.
	 * Postcondition: Karel is on the upper right corner facing east.
	 */
	private void cleanTemporaryBeepers() {
		goToBottomLeftCorner();
		cleanDoubleDiagonalButtomToTop();
	}

	/*
	 * Move to the bottom left corner.
	 * Precondition: Karel is on the midpoint of the first street facing east.
	 * Postcondition: Karel is on the upper left corner facing east.
	 */
	private void goToBottomLeftCorner() {
		turnAround();
		goToWall();
		turnAround();
	}

	/*
	 * Clean bottom up diagonal from beepers.
	 * Precondition: Karel is on the upper left corner facing east.
	 * Postcondition: Karel is on the upper right corner facing east.
	 */
	private void cleanDoubleDiagonalButtomToTop() {
		while (frontIsClear()) {
			pickBeeper();
			move();
			pickBeeper();
			turnLeft();
			move();
			turnRight();
		}
		pickBeeper();
	}

	/*
	 * Move to the beeper placed as the midpoint marker.
	 * Precondition: Karel is on the upper right corner facing east.
	 * Postcondition: Karel is on the midpoint of the first street facing east.
	 */
	private void goToBeeper() {
		turnRight();
		goToWall();
		turnRight();
		while (noBeepersPresent()) {
			move();
		}
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
		new MidpointFindingKarel().start(args);
	}
}
