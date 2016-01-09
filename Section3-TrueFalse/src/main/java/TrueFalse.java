/*
 * File: TrueFalse.java
 * --------------------
 * For each of the following statements below, indicate whether it is
 * true or false in Java:
 * a) The value of a local variable named i has no direct relationship with that
 * of a variable named i in its caller.
 * b) The value of a parameter named x has no direct relationship with that of a
 * variable named x in its caller.
 */

import acm.program.*;

public class TrueFalse extends ConsoleProgram {

	public void run() {
		println("For each of the following statements below, indicate whether "
			+ "it is true or false in Java:");
		println("a) The value of a local variable named i has no direct "
			+ "relationship with that of a variable named i in its caller.");
		println("Answer: True");
		println("b) The value of a parameter named x has no direct relationship "
			+ "with that of a variable named x in its caller.");
		println("Answer: True");
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new TrueFalse().start(args);
	}
}
