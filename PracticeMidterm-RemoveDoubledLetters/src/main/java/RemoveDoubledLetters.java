/*
 * File: RemoveDoubledLetters.java
 * -------------------------------
 * Method removeDoubledLetters takes a string as its argument and returns a new
 * string with all doubled letters in the string replaced by a single letter.
 */

import acm.program.*;

public class RemoveDoubledLetters extends ConsoleProgram {

	public void run() {
		println("removeDoubledLetters(\"tresidder\") is: "
			+ removeDoubledLetters("tresidder"));
		println("removeDoubledLetters(\"bookkeeper\") is: "
			+ removeDoubledLetters("bookkeeper"));
	}

	/*
	 * Removes any doubled letters from a string.
	 */
	private String removeDoubledLetters(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (i == 0 || ch != str.charAt(i - 1)) {
				result += ch;
			}
		}
		return result;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new RemoveDoubledLetters().start(args);
	}
}
