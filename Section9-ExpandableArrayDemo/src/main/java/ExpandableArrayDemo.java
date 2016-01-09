/*
 * File: ExpandableArrayDemo.java
 * ------------------------------
 */

import acm.program.*;

public class ExpandableArrayDemo extends ConsoleProgram {

	public void run() {
		ExpandableArray myList = new ExpandableArray();
		myList.set(14, "Bob");
		myList.set(21, "Sally");

		String value = (String) myList.get(14);
		if (value != null) {
			println("Got value: " + value);
		}
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new ExpandableArrayDemo().start(args);
	}
}
