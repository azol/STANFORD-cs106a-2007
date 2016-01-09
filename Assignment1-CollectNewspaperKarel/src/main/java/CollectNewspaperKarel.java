/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		goToDoor();
		pickNewspaper();
		returnToInitialPosition();
	}

	/*
	 * Karel starts off in the northwest corner of its 3x3 house and
	 * moves to the door in the middle of the east wall.
	 * Postcondition: Karel is at the door's corner
	 * 					(facing east).
	 */
	private void goToDoor() {
		turnRight();
		move();
		turnLeft();
		move();
		move();
	}

	/*
	 * Karel picks up a newspaper.
	 * Picking up a newspaper is like standing and using a hand to pick it up.
	 * Precondition: Karel is facing the newspaper corner
	 * Postcondition: Karel is facing former newspaper corner
	 */
	private void pickNewspaper() {
		move();
		pickBeeper();
		turnAround();
		move();
		turnAround();
	}

	/*
	 * Karel returns to its initial position
	 * Precondition: Karel is at the door's corner
	 * 					(facing east)
	 * Postcondition: Karel is at the northwest corner of its house
	 * 					(facing east)
	 */
	private void returnToInitialPosition() {
		turnAround();
		goFromDoorToStart();
		turnAround();
	}

	/*
	 * Karel moves from the door to it's initial corner
	 * Karels square 3x3 house with the door the the middle of the east wall
	 * allows us to reuse goToDoor() method.
	 * Precondition: Karel is at the door's corner
	 * 					(facing west)
	 * Postcondition: Karel is at the initial corner
	 * 					(facing west)
	 */
	private void goFromDoorToStart() {
		goToDoor();
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new CollectNewspaperKarel().start(args);
	}
}
