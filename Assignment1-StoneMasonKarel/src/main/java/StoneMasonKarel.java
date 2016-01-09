/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			repairColumn();
			goToNextColumn();
		}
		repairColumn();
	}

	/*
	 * Go to the next column.
	 * The columns are exactly four units apart.
	 * Precondition: Karel is standing at the base of a column facing east.
	 * Postcondition: Karel is standing at the base of the next column facing east.
	 */
	private void goToNextColumn() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

	/*
	 * Repair column: ascend column and place missing stones,
	 * then return to the same corner.
	 * Precondition: Karel is standing at the base of a column facing east.
	 * Postcondition: Karel is standing at the base of a repaired column facing east.
	 */
	private void repairColumn() {
		turnLeft();
		checkForMissingStones();
		turnAround();
		moveToWall();
		turnLeft();
	}

	/*
	 * Check and place missing stones.
	 * Precondition: Karel is standing at the base of a column facing north.
	 * Postcondition: Karel is standing at the top of a repaired column facing north.
	 */
	private void checkForMissingStones() {
		while (frontIsClear()) {
			addMissingStone();
			move();
		}
		addMissingStone();
	}

	/*
	 * Add missing stone.
	 * Check the presence and add a missing stone.
	 * Postcondition: Karel is standing on a corner containing a beeper.
	 */
	private void addMissingStone() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}

	/*
	 * Move Karel until it is blocked by a wall.
	 * Postcondition: Karel is facing a wall.
	 */
	private void moveToWall(){
		while (frontIsClear()) {
			move();
		}
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new StoneMasonKarel().start(args);
	}
}
