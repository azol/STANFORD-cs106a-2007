/*
 * File: Yahtzee.java
 * ------------------
 * This program plays the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	private static int NOT_USED_CATEGORY_MARKER = -1;

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	/**
	 * Plays game consisting of N_SCORING_CATEGORIES rounds.
	 */
	private void playGame() {
		// rgen.setSeed(4);
		initScores();

		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			playRound();
		}

		showResult();
	}

	/**
	 * Initializes scores multidimensional array to hold score values for players.
	 * Category index '0' is unused to simplify array handling.
	 * All other category indexes become NOT_USED_CATEGORY_MARKER as initial value
	 * to make it possible to check the state (used / not used) of the category.
	 */
	private void initScores() {
		scores = new int [nPlayers][N_CATEGORIES + 1];

		for (int i = 0; i < nPlayers; i++) {
			for (int j = 1; j < scores[i].length; j++) {
				scores[i][j] = NOT_USED_CATEGORY_MARKER;
			}
		}
	}

	/**
	 * Plays one round.
	 * A round of the game consists of each player taking a turn.
	 */
	private void playRound() {
		for (int i = 0; i < nPlayers; i++) {
			playTurn(i);
		}
	}

	/**
	 * Plays a turn consisting of tree rolls for the player <code>playerID</code>.
	 * @param playerID The player ID.
	 */
	private void playTurn(int playerID) {
		initDice();
		for (int i = 0; i < 3; i ++) {
			playRoll(playerID, i);
		}
		assignCategory(playerID);
	}

	/**
	 * Initializes the dice to contain zeros.
	 */
	private void initDice() {
		dice = new int[N_DICE];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = 0;
		}
	}

	/**
	 * Plays a roll for player <code>playerID</code>.
	 * First roll (identified by rollNo equals to 0) has a slightly different message.
	 * @param playerID The plater ID.
	 * @param rollNo The roll No.
	 */
	private void playRoll(int playerID, int rollNo) {
		if (rollNo == 0) {
			display.printMessage(playerNames[playerID]
			                     + "'s turn! Click the \"Roll Dice\" button to roll the dice.");
			display.waitForPlayerToClickRoll(playerID + 1);
		} else {
			display.printMessage("Select the dice tou wish to re-roll and click \"Roll Again\"");
			display.waitForPlayerToSelectDice();
		}
		generateDiceCombunation();
		display.displayDice(dice);
	}

	/**
	 * Performs dice roll/reroll.
	 * Die to reroll is identified by isDieSelected().
	 * Full dice roll is identified by zero value set by initDice method at the beginning of each turn.
	 */
	private void generateDiceCombunation() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			} else if (dice[i] == 0) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
	}

	/**
	 * Assigns roll result for the player <code>playerID</code> to the category and displays resulted scorecard.
	 * @param playerID The player ID.
	 */
	private void assignCategory(int playerID) {
		display.printMessage("Select a category for this roll");

		/* Prevents selecting used category */
		int selectedCategory;
		while (true) {
			selectedCategory = display.waitForPlayerToSelectCategory();
			if (scores[playerID][selectedCategory] == NOT_USED_CATEGORY_MARKER) break;
			display.printMessage("You already pocked that category, Please choose another one");
		}

		/* Updates scores array */
		scores[playerID][selectedCategory] = calculateScore(dice, selectedCategory);
		calculateUpperScore(playerID);
		calculateLowerScore(playerID);
		calculateTotal(playerID);

		/* Displays roll results */
		display.updateScorecard(selectedCategory, playerID + 1, scores[playerID][selectedCategory]);
		display.updateScorecard(TOTAL, playerID + 1, scores[playerID][TOTAL]);
	}

	/**
	 * Checks to see whether the dice array fits the category.
	 *
	 * Checks to see whether the dice array fits the category. This method is provided only as a temporary
	 * expedient to help you get your program working. Your final program should not call this method.
	 *
	 * @param dice 	An array of dice values, whose indices range from 0 to <code>N_DICE - 1</code>
	 * @param category The category number, as defined in <code>YahtzeeConstants</code>
	 * @return <code>true</code> if the dice fit the category, and <code>false</code> otherwise
	 */
	private boolean checkCategory(int[] dice, int category) {
		switch (category) {
		case            ONES:
		case            TWOS:
		case          THREES:
		case           FOURS:
		case           FIVES:
		case           SIXES:
		case          CHANCE: return true;
		case THREE_OF_A_KIND: return checkForOfKind(dice, 3);
		case  FOUR_OF_A_KIND: return checkForOfKind(dice, 4);
		case      FULL_HOUSE: return (checkForOfKind(dice, 3) && checkForOfKind(dice, 2));
		case         YAHTZEE: return checkForOfKind(dice, 5);
		case  SMALL_STRAIGHT: return isSmallStraigth(dice);
		case  LARGE_STRAIGHT: return isLargeStraigth(dice);
		}
		return false;
	}


	/**
	 * Calculates score based on dice combination and selected category.
	 * @param dice The dice combination.
	 * @param category The selected category.
	 * @return The score for the category if combination fits, otherwise '0';
	 */
	private int calculateScore(int[] dice, int category) {
		switch (category) {
		case            ONES: if (checkCategory(dice, category)) return calculateSumSame(1);
		case            TWOS: if (checkCategory(dice, category)) return calculateSumSame(2);
		case          THREES: if (checkCategory(dice, category)) return calculateSumSame(3);
		case           FOURS: if (checkCategory(dice, category)) return calculateSumSame(4);
		case           FIVES: if (checkCategory(dice, category)) return calculateSumSame(5);
		case           SIXES: if (checkCategory(dice, category)) return calculateSumSame(6);
		case          CHANCE:
		case THREE_OF_A_KIND:
		case  FOUR_OF_A_KIND: if (checkCategory(dice, category)) return calculateDiceSum();
		case      FULL_HOUSE: if (checkCategory(dice, category)) return 25;
		case         YAHTZEE: if (checkCategory(dice, category)) return 50;
		case  SMALL_STRAIGHT: if (checkCategory(dice, category)) return 30;
		case  LARGE_STRAIGHT: if (checkCategory(dice, category)) return 40;
		default: return 0;
		}
	}

	private boolean isLargeStraigth(int[] array) {
		return
		   checkConsecutive(array, 1, 5)
		|| checkConsecutive(array, 2, 6);
	}

	private boolean isSmallStraigth(int[] array) {
		return
		   checkConsecutive(array, 1, 4)
		|| checkConsecutive(array, 2, 5)
		|| checkConsecutive(array, 3, 6);
	}

	private boolean checkConsecutive(int[] array, int start, int end) {
		for (int i = start; i <= end; i++) {
			if(!findNum(array, i)) return false;
		}
		return true;
	}

	private boolean findNum(int[] array, int num) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num) {
				return true;
			}
		}
		return false;
	}

	/* Checks whether <code>array</code> contains
	 * <code>noSameValues</code> same values.
	 */
	private boolean checkForOfKind(int[] array, int noSameValues) {
		int[] frequencyTable = new int[7]; // '0' index unused

		for (int i = 0; i < array.length; i++) {
			frequencyTable[array[i]] += 1;
			if (frequencyTable[array[i]] == noSameValues) return true;
		}
		return false;
	}

	/**
	 * Calculates the sum of the same values on the dice.
	 * @param value The value to calculate.
	 * @return The sum of the same values.
	 */
	private int calculateSumSame(int value) {
		int sum = 0;
		for (int i = 0; i < dice.length; i++) {
			if (dice[i] == value)
				sum += dice[i];
		}
		return sum;
	}

	/**
	 * Calculates the sum of the values showing on the dice.
	 * @return The sum of the values showing on the dice.
	 */
	private int calculateDiceSum() {
		int sum = 0;
		for (int i = 0; i < dice.length; i++) {
			sum += dice[i];
		}
		return sum;
	}

	/* Calculates and stores upper score value */
	private void calculateUpperScore(int playerID) {
		int sum = 0;
		for (int i = ONES; i <= SIXES; i++) {
			if (scores[playerID][i] != NOT_USED_CATEGORY_MARKER)
				sum += scores[playerID][i];
		}
		scores[playerID][UPPER_SCORE] = sum;
	}

	/* Calculates and stores lower score value */
	private void calculateLowerScore(int playerID) {
		int sum = 0;
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			if (scores[playerID][i] != NOT_USED_CATEGORY_MARKER)
				sum += scores[playerID][i];
		}
		scores[playerID][LOWER_SCORE] = sum;
	}

	/* The total score for each player is computed by adding together
	 * the upper score, the bonus (if any), and the lower score.
	 * Note: UPPER_BONUS is calculated at the and of the game.
	 */
	private void calculateTotal(int playerID) {
		int bonus =
			(scores[playerID][UPPER_BONUS] == NOT_USED_CATEGORY_MARKER) ? 0 : scores[playerID][UPPER_BONUS];
		scores[playerID][TOTAL] = scores[playerID][UPPER_SCORE]
		                         + bonus
		                         + scores[playerID][LOWER_SCORE];
	}

	/* At the end of the game, the values in upper categories are added
	 * to generated the value in the entry labeled Upper Score.
	 * Moreover, if a player's score for the upper section ends up totaling 63
	 * or more, that player is awarded a 35-point bonus on the next line.
	 * The scores in the lower section of the scorecard are also added together
	 * to generate the entry labeled Lower Score.
	 * The total score for each player is then computed by adding together
	 * the upper score, the bonus (if any), and the lower score.
	 */
	private void showResult() {
		calculateEndTotal();
		displayAllCalculatedScores();
		generateWinnerMessage();
	}

	/**
	 * Checks for UPPER_BONUS and adds to total if >= 63
	 */
	private void calculateEndTotal() {
		for (int i = 0; i < nPlayers; i++) {
			if (scores[i][UPPER_SCORE] >= 63) {
				scores[i][UPPER_BONUS] = 35;
				calculateTotal(i);
			}
		}
	}

	/**
	 * Updates scorecard with all calculated values: UPPER_SCORE, UPPER_BONUS, LOWER_SCORE, TOTAL.
	 */
	private void displayAllCalculatedScores() {
		for (int i = 0; i < nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE, i + 1, scores[i][UPPER_SCORE]);
			if (scores[i][UPPER_BONUS] != NOT_USED_CATEGORY_MARKER) {
				display.updateScorecard(UPPER_BONUS, i + 1, scores[i][UPPER_BONUS]);
			}
			display.updateScorecard(LOWER_SCORE, i + 1, scores[i][LOWER_SCORE]);
			display.updateScorecard(TOTAL, i + 1, scores[i][TOTAL]);
		}
	}

	/**
	 * Compares totals and generates the winner message.
	 * The player with the total highest score is declared the winner.
	 */
	private void generateWinnerMessage() {
		int winscore = 0;
		String name = "";
		for (int k = 0; k < nPlayers; k++) {
			if (winscore < scores[k][TOTAL]) {
				winscore = scores[k][TOTAL];
				name = playerNames[k];
			}
		}
		display.printMessage("Congratulations, " + name +", you're the winner with score of " + winscore);
	}

	/* My private instance variables */
	private int[] dice;
	private int[][] scores;

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
