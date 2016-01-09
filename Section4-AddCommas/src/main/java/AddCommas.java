/*
 * File: AddCommas.java
 * --------------------
 */

import acm.program.*;

public class AddCommas extends ConsoleProgram {

	public void run() {
		while (true) {
			String digits = readLine("Enter a number: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}

	private String addCommasToNumericString(String digits) {
		String result = "";
		int len = digits.length();
		int nDigits = 0;
		for (int i = len - 1; i >= 0; i--) {
			result = digits.charAt(i) + result;
			nDigits++;
			if (((nDigits % 3) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return result;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new AddCommas().start(args);
	}
}
