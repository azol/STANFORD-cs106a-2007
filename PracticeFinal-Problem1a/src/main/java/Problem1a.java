/*
 * File: Problem1a.java
 * --------------------
 * We learned that when you pass an object as a parameter into a method, changes
 * that are made to the object persist after the method completes execution.
 * However, if you pass in an int as a parameter and change the value of that
 * parameter in a method, the original int variable that was passed in remains
 * unchanged. Explain why that is.
 */

import acm.program.*;

public class Problem1a extends ConsoleProgram {

	public void run() {
		println("Question:");
		println("We learned that when you pass an object as a parameter into a "
			+ "method, changes that are made to the object persist after the "
			+ "method completes execution. However, if you pass in an int as a "
			+ "parameter and change the value of that parameter in a method, "
			+ "the original int variable that was passed in remains unchanged. "
			+ "Explain why that is.");

		println("Answer:");
		println("When an object is passed into method, a reference to the "
			+ "object (i.e., its address in memory) is what is actually being "
			+ "passed to the method (this is called pass-by-reference). Any "
			+ "changes made to the object are made through that reference "
			+ "(address), so the place in memory where that original object "
			+ "resides is modified. As a result, when the method completes, "
			+ "any changes made to the object persist since the changes were "
			+ "made to the same place in memory as where the original object "
			+ "resided. When an int is passed as a parameter to a method, the "
			+ "method actually receives a copy of the int's value (this is "
			+ "called pass-by-value). Any changes made to that parameter in "
			+ "the method are just changing this copy of the value, so the "
			+ "original int variable that was passed in as a parameter is not "
			+ "modified.");
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Problem1a().start(args);
	}
}
