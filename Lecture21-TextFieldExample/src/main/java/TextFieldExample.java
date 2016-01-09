/* File: TextFieldExample.java
 * ---------------------------
 * This class displays a greeting whenever a name is entered
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class TextFieldExample extends ConsoleProgram {

	public void init() {
		nameField = new JTextField(10);
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField) {
			println("Hello, " + nameField.getText()); }
	}

/* Private instance variables */
	private JTextField nameField;

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new TextFieldExample().start(args);
	}
}
