/*
 * File: CheckUpperLeftCorner.java
 * -------------------------------
 */

import acm.program.*;

public class CheckUpperLeftCorner extends ConsoleProgram {

	public void run() {
		println("Method: checkUpperLeftCorner");
		println("");
		println("This method checks the upper left corner of a Sudoku array "
			+ "to see if it correctly contains one copy of each digit "
			+ "between 1 and 9. If so, the method returns true. If it "
			+ "contains values that are duplicated or out of range, the "
			+ "method returns false.");
		println("");
		println("  private boolean checkUpperLeftCorner(int[][] matrix) {");
		println("    boolean[] alreadyUsed = new boolean[10];");
		println("    for (int i = 0; i < 3; i++) {");
		println("      for (int j = 0; j < 3; j++) {");
			println("        int digit = matrix[i][j];");
			println("        if (digit < 1 || digit > 9) return false;");
			println("        if (alreadyUsed[digit]) return false;");
			println("        alreadyUsed[digit] = true;");
		println("      }");
		println("    }");
		println("    return true;");
		println("  }");
	}

	/**
	 * Method: checkUpperLeftCorner
	 *
	 * This method checks the upper left corner of a Sudoku array
	 * to see if it correctly contains one copy of each digit
	 * between 1 and 9. If so, the method returns true. If it
	 * contains values that are duplicated or out of range, the
	 * method returns false.
	 */
	private boolean checkUpperLeftCorner(int[][] matrix) {
		boolean[] alreadyUsed = new boolean[10];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int digit = matrix[i][j];
				if (digit < 1 || digit > 9) return false;
				if (alreadyUsed[digit]) return false;
				alreadyUsed[digit] = true;
			}
		}
		return true;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new CheckUpperLeftCorner().start(args);
	}
}
