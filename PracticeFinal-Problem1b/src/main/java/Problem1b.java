/*
 * File: Problem1b.java
 * --------------------
 * Suppose that the integer array list has been declared and initialized as
 * follows:
 * private int[] list = { 10, 20, 30, 40, 50 };
 * Given this array, what is the effect of calling the method mystery(list);
 */

import acm.program.*;

public class Problem1b extends ConsoleProgram {

	private int[] list = { 10, 20, 30, 40, 50 };

	public void run() {
		println("private int[] list = { 10, 20, 30, 40, 50 };");
		println("mystery(list);");
		mystery(list);
		println("The program leaves the array in the following state:");
		printArray(list);
	}

	public void mystery(int[] array) {
		int tmp = array[array.length - 1];
		for (int i = 1; i < array.length; i++) {
			array[i] = array[i - 1];
		}
		array[0] = tmp;
	}

	private void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			print("[" + array[i]+ "]");
		}
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new Problem1b().start(args);
	}
}
