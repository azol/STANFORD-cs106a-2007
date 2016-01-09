/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCanvas;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		initInteractors();
		db = new NameSurferDataBase(NAMES_DATA_FILE);
	}

	private void initInteractors() {
		JLabel nameLabel = new JLabel("Name");
		nameTextField = new JTextField(15);
		nameTextField.addActionListener(this);
		JButton graphButton = new JButton("Graph");
		JButton clearButton = new JButton("Clear");
		graph = new NameSurferGraph();

		add(nameLabel, SOUTH);
		add(nameTextField, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		add(graph);

		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameTextField || e.getActionCommand().equals("Graph")) {
			String entryKey = nameTextField.getText();
			NameSurferEntry entry = db.findEntry(entryKey);
			if (entry != null) graph.addEntry(entry);
		}

		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}

	private JTextField nameTextField;
	private NameSurferDataBase db;
	private NameSurferGraph graph;

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new NameSurfer().start(args);
	}
}
