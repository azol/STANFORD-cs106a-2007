/*
 * File: StringExamples.java
 * -------------------------
 * Checking for palindromes.
 * Reversing strings and a simpler version of checking for palindromes.
 * Replace first occurrence
 */

import acm.program.*;

public class StringExamples extends ConsoleProgram {

	public void run() {
		println("using isPalindrome()");
		println("Is \"saippuakivikauppias\" a palindrom? "
				+ isPalindrome("saippuakivikauppias"));
		println("Is \"car\" a palindrom? "
				+ isPalindrome("car"));

		println("using simpleIsPalindrome()");
		println("Is \"saippuakivikauppias\" a palindrom? "
				+ simpleIsPalindrome("saippuakivikauppias"));
		println("Is \"car\" a palindrom? "
				+ simpleIsPalindrome("car"));

		println("using replaceFirstOccurrence()");
		println("\"revel\" with replaced \"e\" is: "
				+ replaceFirstOccurrence("revel", "e", "a"));
	}

	// Checking for palindromes
	public boolean isPalindrome(String str) {
		for(int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - (i + 1))) {
				return false;
			}
		}
		return true;
	}

	// Reversing strings and a simpler version of checking for palindromes
	public String reverseString(String str) {
		String result = "";
		for(int i = 0 ; i < str.length(); i++) {
			result = str.charAt(i) + result;
		}
		return result;
	}

	public boolean simpleIsPalindrome(String str) {
		return (str.equals(reverseString(str)));
	}

	// Replace first occurrence
	public String replaceFirstOccurrence(String str, String orig, String repl) {
		int index = str.indexOf(orig);
		if (index != -1) {
			str = str.substring(0, index)
			+ repl
			+ str.substring(index + orig.length());
		}
		return str;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new StringExamples().start(args);
	}
}
