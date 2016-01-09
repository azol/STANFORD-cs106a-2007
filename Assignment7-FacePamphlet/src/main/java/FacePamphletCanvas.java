/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas
					implements FacePamphletConstants {

	/**
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}


	/**
	 * This method displays a message string near the bottom of the
	 * canvas.  Every time this method is called, the previously
	 * displayed message (if any) is replaced by the new message text
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}


	/**
	 * This method displays the given profile on the canvas.  The
	 * canvas is first cleared of all existing items (including
	 * messages displayed near the bottom of the screen) and then the
	 * given profile is displayed.  The profile display includes the
	 * name of the user from the profile, the corresponding image
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		if (profile == null) {
			removeAll();
			return;
		}

		removeAll();

		// Name
		GLabel nameLabel = new GLabel(profile.getName());
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(Color.BLUE);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent());

		// Image
		double imageY = nameLabel.getY() + IMAGE_MARGIN;
		if (profile.getImage() == null) {
			GRect imageRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(imageRect, LEFT_MARGIN, imageY);
			GLabel noImageLabel = new GLabel("No Image");
			noImageLabel.setFont(PROFILE_IMAGE_FONT);
			add(noImageLabel, LEFT_MARGIN + (IMAGE_WIDTH - noImageLabel.getWidth()) / 2,
					          imageRect.getY() + (IMAGE_HEIGHT - noImageLabel.getHeight()) / 2 + noImageLabel.getAscent());
		} else {
			GImage image = profile.getImage();
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, imageY);
		}

		// Status
		GLabel status;
		if (profile.getStatus() == "") {
			status = new GLabel("No current status");
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, imageY + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		} else {
			status = new GLabel(profile.getName() + " is " + profile.getStatus());
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, imageY + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		}

		// Friends
		GLabel friendsLabel = new GLabel("Friends:");
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsLabel, getWidth() / 2, imageY);

		Iterator it = profile.getFriends();
		int i = 1;
		while (it.hasNext()) {
			GLabel friend = new GLabel("" + it.next());
			add(friend, friendsLabel.getX(), friendsLabel.getY() + friend.getHeight() * i);
			i++;
		}

		// App message

	}


}
