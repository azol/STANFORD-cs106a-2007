/*
 * PrimVsObj.java
 * --------------
 * Let's say a student writes the following line of code in a predicate method
 * (i.e., a method that returns a boolean):
 *
 * public boolean IsNameQ() {
 * 	String name = readLine("Enter name: ");
 * 	return (name == "Q");
 * }
 *
 * The author of this code thinks that the program will return true if the
 * player’s name is "Q". What’s the problem here?
 *
 * Now consider if the code were written as:
 *
 * public boolean IsNameQ() {
 * 	String name = readLine("Enter name: ");
 * 	char ch = name.charAt(0);
 * 	return ((ch == 'Q') && (name.length() == 1));
 * }
 *
 * How is the code above different with respect to checking for equality with
 * the value "Q"?
 */

import acm.program.*;

public class PrimVsObj extends ConsoleProgram {

	public void run() {
		println("Question:");
		println("Let's say a student writes the following line of code in a "
			+ "predicate method (i.e., a method that returns a boolean):");
		println("");
		println("  public boolean IsNameQ() {");
		println("    String name = readLine(\"Enter name: \");");
		println("    return (name == \"Q\");");
		println("  }");
		println("");
		println("The author of this code thinks that the program will return "
			+ "true if the player’s name is \"Q\". What’s the problem here?");
		println("");

		println("Answer:");
		println("In the first example, the student is thinking a little too "
			+ "literally about the expressions they’ve written them, seeing "
			+ "them as what they want them to mean as opposed to what they in "
			+ "fact do mean. The problem lies in the comparison:");
		println("");
		println("  (name == \"Q\")");
		println("");
		println("The correct English translation of this statement is: compare "
			+ "the address of the object name to the address of the constant "
			+ "string \"Q\". In other words, name is a reference to a String "
			+ "object. Since name was read in from the user, this comparison "
			+ "will always return false, as it cannot be the same underlying "
			+ "object as the constant string \"Q\". If we actually want to "
			+ "compare the values held in those String objects, we should "
			+ "write:");
		println("");
		println("  name.equals(\"Q\")");
		println("");
		println("For comparing values,the == operator should only be used with "
			+ "primitive types, such as int, double, boolean, and char. "
			+ "Variables that represent objects (like String) are always "
			+ "references (addresses to some location in memory).");
		println("");

		println("Question:");
		println("Now consider if the code were written as:");
		println("");
		println("  public boolean IsNameQ() {");
		println("    String name = readLine(\"Enter name: \");");
		println("    char ch = name.charAt(0);");
		println("    return ((ch == 'Q') && (name.length() == 1));");
		println("  }");
		println("");
		println("How is the code above different with respect to checking for "
			+ "equality with the value \"Q\"?");
		println("");

		println("Answer:");
		println("In the second example the code actually works as intended. In "
			+ "the expression:");
		println("");
		println("  (ch == 'Q')");
		println("");
		println("we are using the == operator to compare the primitive type "
			+ "char. Se we are comparing primitives (and not object "
			+ "references), the == operator is comparing actual char values "
			+ "rather than memory addresses. This works just as we would want "
			+ "it to.");
	}

	/*
	public boolean IsNameQ() {
		String name = readLine("Enter name: ");
		return (name == "Q");
	}

	public boolean IsNameQ() {
		String name = readLine("Enter name: ");
		char ch = name.charAt(0);
		return ((ch == 'Q') && (name.length() == 1));
	}
	*/

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new PrimVsObj().start(args);
	}
}
