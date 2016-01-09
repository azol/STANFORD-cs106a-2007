/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

	private ArrayList<String> wordList;

	public HangmanLexicon() {
		wordList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(LEXICON_FILE));
			while (true) {
				String line = br.readLine();
				if (line == null) break;
				wordList.add(line);
			}
			br.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		/* What is better? (wordList.size() - 1) or getWordCount() - 1 */
		if (index >= 0 && index <= wordList.size() - 1) {
			return wordList.get(index);
		} else {
			throw new ErrorException("getWord: Illegal index");
		}
	}

	private static final String LEXICON_FILE = "ShorterLexicon.txt";
}
