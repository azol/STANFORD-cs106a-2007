/*
 * File: IsGooglewhack.java
 * ------------------------
 */

import acm.program.*;

public class IsGooglewhack extends ConsoleProgram {

	public void run() {
		String[] scallywags = googleSearch("scallywags");
		println("scallywags is:");
		printStringArray(scallywags);

		String[] ambidextrous = googleSearch("ambidextrous");
		println("ambidextrous is:");
		printStringArray(ambidextrous);

		if (isGooglewhack("scallywags", "ambidextrous")) {
			println("there is exactly one web page containing both scallywags and ambidextrous");
		}
	}

	public String[] googleSearch(String word) {
		if (word.equals("ambidextrous")) {
			String[] strArr = {
				"http://www.theatlantic.com/unbound/wordgame/wg906.htm"
				};
			return strArr;
		}
		String[] strArr = {
			"http://www.scallywags.ca/",
			"http://www.effect.net.au/scallywags/",
			"http://www.scallywags1.freeserve.co.uk/",
			"http://www.scallywagsbaby.com/",
			"http://www.sfsf.com.au/ScallywagsCoaches/",
			"http://www.theatlantic.com/unbound/wordgame/wg906.htm",
			"http://www.maisemoregardens.co.uk/emsworth.htm"
			};
		return strArr;
	}

	private void printStringArray(String[] arrayOfStrings) {
		for (int i = 0; i < arrayOfStrings.length; i++) {
			println(arrayOfStrings[i]);
		}
	}

	/**
	 * Method: isGooglewhack(word1, word2)
	 *
	 * Returns true if word1 and word2 appear on exactly one web page,
	 * as reported by googleSearch.
	 */
	private boolean isGooglewhack(String word1, String word2) {
		String[] pages1 = googleSearch(word1);
		String[] pages2 = googleSearch(word2);
		int matches = 0;
		for (int i = 0; i < pages1.length; i++) {
			if (findStringInArray(pages1[i], pages2) != -1) {
				matches++;
				if (matches > 1) return false;
			}
		}
		return (matches == 1);
	}

	/**
	 * Method: findStringInArray(key, array) *
	 *
	 * Returns the index of the first occurrence of key in the array.
	 * If key does not appear in the array, findStringInArray
	 * returns -1.
	 */
	private int findStringInArray(String key, String[] array) {
		for (int i = 0; i < array.length; i++) {
			if (key.equals(array[i])) return i;
		}
		return -1;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new IsGooglewhack().start(args);
	}
}
