/*
 * File: RemoveAllOccurrences.java
 * -------------------------------
 */

import acm.program.*;

public class RemoveAllOccurrences extends ConsoleProgram {

	public void run() {
		println("\"This is a test\" without \"t\" is:");
		println(removeAllOccurrences("This is a test", 't'));
		println("\"Summer is here!\" without \"e\" is:");
		println(removeAllOccurrencesVariant("Summer is here!", 'e'));
		println("\"---0---\" without \"-\" is:");
		println(removeAllOccurrences("---0---", '-'));
	}

	private String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}

	private String removeAllOccurrencesVariant(String str, char ch) {
		while (true) {
			int pos = str.indexOf(ch);
			if (pos >= 0) {
				str = str.substring(0, pos) + str.substring(pos + 1);
			} else break;
		}
		return str;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new RemoveAllOccurrences().start(args);
	}
}
