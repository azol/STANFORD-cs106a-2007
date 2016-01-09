/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the
	 * interactors in the application, and taking care of any other
	 * initialization that needs to be performed.
	 */
	public void init() {
		/* NORTH region interactors */
		add(new JLabel("Name"), NORTH);
		nameTextfield = new JTextField(TEXT_FIELD_SIZE);
		add(nameTextfield, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);

		/* WEST region interactors */
		changeStatusTextField = new JTextField(TEXT_FIELD_SIZE);
		add(changeStatusTextField, WEST);
		changeStatusTextField.setActionCommand(changeStatusButtonText);
		changeStatusTextField.addActionListener(this);
		add(new JButton(changeStatusButtonText), WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);	// Separator

		changePictureTextField = new JTextField(TEXT_FIELD_SIZE);
		add(changePictureTextField, WEST);
		changePictureTextField.setActionCommand(changePictureButtonText);
		changePictureTextField.addActionListener(this);
		add(new JButton(changePictureButtonText), WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);	// Separator

		addFriendTextField = new JTextField(TEXT_FIELD_SIZE);
		add(addFriendTextField, WEST);
		addFriendTextField.setActionCommand(addFriendButtonText);
		addFriendTextField.addActionListener(this);
		add(new JButton(addFriendButtonText), WEST);

		canvas = new FacePamphletCanvas();
		add(canvas);

		addActionListeners();
    }


    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// Add
		if (e.getActionCommand().equals("Add")) {
			String enteredText = nameTextfield.getText();
			if (!enteredText.equals("")) {
				if (db.containsProfile(enteredText)) {
					currentProfile = db.getProfile(enteredText);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile with name " + enteredText +" already exists");
				} else {
					db.addProfile(new FacePamphletProfile(enteredText));
					currentProfile = db.getProfile(enteredText);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("New profile created");
				}
			}
		}

		// Delete
		if (e.getActionCommand().equals("Delete")) {
			String enteredText = nameTextfield.getText();
			if (!enteredText.equals("")) {
				currentProfile = null;
				if (db.containsProfile(enteredText)) {
					db.deleteProfile(enteredText);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Profile of " + enteredText + " deleted");
				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile with name " + enteredText + " does not exist");
				}
			}
		}

		// Lookup
		if (e.getActionCommand().equals("Lookup")) {
			String enteredText = nameTextfield.getText();
			if (!enteredText.equals(""))
				if (db.containsProfile(enteredText)) {
					currentProfile = db.getProfile(enteredText);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Displaying " + currentProfile.getName());
				} else {
					currentProfile = null;
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile with name " + enteredText + " does not exist");
				}
		}

		// Change status
		if (e.getActionCommand().equals(changeStatusButtonText)) {
			if (!changeStatusTextField.getText().equals("")) {
				if (currentProfile != null) {
					currentProfile.setStatus(changeStatusTextField.getText());
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Status updated to " + currentProfile.getStatus());
				} else {
					canvas.showMessage("Please select a profile to change status");
				}
			}
		}

		// Image
		if (e.getActionCommand().equals(changePictureButtonText)) {
			if (!changePictureTextField.getText().equals("")) {
				if (currentProfile != null) {
					GImage image = null;
					try {
						image = new GImage(changePictureTextField.getText());
					} catch (ErrorException ex) {
						canvas.displayProfile(currentProfile);
						canvas.showMessage("Unable to open image file " + changePictureTextField.getText());
					}
					if (image != null) {
						currentProfile.setImage(image);
						canvas.displayProfile(currentProfile);
						canvas.showMessage("Picture updated");
					}
				} else {
					canvas.showMessage("Please select a profile to change picture");
				}
			}
		}

		// Add friend
		if (e.getActionCommand().equals(addFriendButtonText)) {
			if (!addFriendTextField.getText().equals("")) {
				String enteredText = addFriendTextField.getText();
				if (currentProfile != null) {
					if (db.containsProfile(enteredText)) {
						if (!currentProfile.addFriend(enteredText)) {
							canvas.displayProfile(currentProfile);
							canvas.showMessage(currentProfile.getName() + " already has " + enteredText + " as a friend");
						} else {
							currentProfile.addFriend(enteredText);
							db.getProfile(enteredText).addFriend(currentProfile.getName());
							canvas.displayProfile(currentProfile);
							canvas.showMessage(enteredText + " added to a friend");
						}
					} else {
						canvas.displayProfile(currentProfile);
						canvas.showMessage(enteredText + " does not exists");
					}
				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Please select a profile to add friend");
				}
			}
		}
	}

	private JTextField nameTextfield;
	private JTextField changeStatusTextField;
	private JTextField changePictureTextField;
	private JTextField addFriendTextField;

	private String changeStatusButtonText = "Change Status";
	private String changePictureButtonText = "Change Picture";
	private String addFriendButtonText = "Add Firiend";

	private FacePamphletDatabase db = new FacePamphletDatabase();
	private FacePamphletProfile currentProfile;

	private FacePamphletCanvas canvas;

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new FacePamphlet().start(args);
	}
}
