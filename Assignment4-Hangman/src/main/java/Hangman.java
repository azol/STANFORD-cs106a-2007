/*
 * File: Hangman.java
 * ------------------
 * This program plays the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	private static final int NGUESSES = 8;
	private static final char HIDE_SIGN = '-';

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		lexicon = new HangmanLexicon();
		playGame();
	}

	private void playGame() {
		println("Welcome to Hangman!"); /* Can be moved to run/extra method in case of multiple games */
		canvas.reset();					/* Can be moved to run/extra method in case of multiple games */

		/** Sets up initial values. Simple enough to not write extra method. */
		secretWord = lexicon.getWord(rgen.nextInt(0, lexicon.getWordCount() - 1));
		partiallyGuessedWord = getInitialHiddenString(secretWord);
		//println("Secret word is: " + secretWord); /* To debug */

		/** Game loop. */
		guessCounter = NGUESSES;
		while (!gameOver()) {
			canvas.displayWord(partiallyGuessedWord);
			println("The word now looks like this: " + partiallyGuessedWord);
			println("You have " + guessCounter + " guesses left.");

			char charGuess = readUserChar();

			if (secretWord.indexOf(charGuess) == -1) {
				println("There are no " + charGuess +"\'s in the word");
				canvas.noteIncorrectGuess(charGuess);
				guessCounter--;
			} else {
				println("That guess is correct");
				partiallyGuessedWord = entHide(partiallyGuessedWord, charGuess);
			}
		}

		printGameResultMessage();
	}

	/**
	 * Reads a user input and checks it for
	 * legality (see isOneLetter below). If the user enters an
	 * illegal input, the user is asked to reenter a valid one.
	 *
	 * @return The upper case version of user input character.
	 */
	private char readUserChar() {
		String userInput = readLine("Your guess: ");
		while (!isOneLetter(userInput)) {
			println("Your guess is illegel.");
			userInput = readLine("Reenter your guess: ");
		}
		return userInput.toUpperCase().charAt(0);
	}

	/**
	* Checks to see whether a string is legal, which means it meets
	* the following conditions (single letter):
	* (1) It is a single sign.
	* (2) It is a letter.
	*/
	private boolean isOneLetter(String str) {
		if (str.length() > 1) return false;
		return (Character.isLetter(str.charAt(0)));
	}


	/**
	 * Returns a row of signs HIDE_SIGN - one for each letter in the string <code>str</code>.
	 * @param str The string to count the amount of HIDE_SIGNs.
	 * @return The string with length of <code>str</code> made of HIDE_SIGNs.
	 */
	private String getInitialHiddenString(String str) {
		String strHidden = "";
		for (int i = 0; i < secretWord.length(); i++) {
			strHidden += HIDE_SIGN;
		}
		return strHidden;
	}

	/**
	 * Enthides all occurrences of <code>ch</code> in the hidden string <code>str</code>.
	 * @param str The string to process.
	 * @param ch The character to enthide.
	 * @return The string with all occurrences of character <code>ch</code>.
	 */
	public String entHide(String str, char ch) {
		String strToSearchIn = secretWord;
		String entdashedString = str;
		while (true) {
			int index = strToSearchIn.indexOf(ch);
			if (index == -1) {
				return entdashedString;
			}
			entdashedString = entdashedString.substring(0, index)
								+ ch
								+ entdashedString.substring(index + 1);
			strToSearchIn = strToSearchIn.substring(0, index)
								+ HIDE_SIGN
								+ strToSearchIn.substring(index + 1);
		}
	}

	/** prints lose or win message based on the game result */
	private void printGameResultMessage() {
		if (partiallyGuessedWord.equals(secretWord)) {
			println("You guessed the word: " + secretWord);
			println("You win");
			canvas.displayWord(secretWord);
		} else if (guessCounter == 0) {
			println("You're completely hung. ");
			println("The word was: " + secretWord);
			println("You lose. ");
		} else {
			println("You're in the middle of the game. "); /* Will not be shown. Just for me to distinguish */
		}
	}

	/**
	 * determines if game is over -- true if either the user has correctly
	 * guessed all the letters in the word or the user has made eight incorrect
	 * guesses.
	 */
	private boolean gameOver() {
		return (partiallyGuessedWord.equals(secretWord)) || (guessCounter == 0);
	}

	private HangmanLexicon lexicon;
	private String secretWord;
	private String partiallyGuessedWord;
	private int guessCounter;

	private HangmanCanvas canvas;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Hangman().start(args);
	}
}
