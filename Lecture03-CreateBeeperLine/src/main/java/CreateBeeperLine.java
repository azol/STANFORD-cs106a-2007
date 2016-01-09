/*
 * File: CreateBeeperLine.java
 * ---------------------------
 */

import stanford.karel.*;

public class CreateBeeperLine extends Karel {

	public void run() {
		createBeeperLine();
	}

	private void createBeeperLine() {
		while (frontIsClear()) {
			putBeeper();
			move();
		}
		putBeeper();
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new CreateBeeperLine().start(args);
	}
}
