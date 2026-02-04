
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

public class FacePamphlet extends Program implements FacePamphletConstants {
	private JButton addName;
	private JButton delateName;
	private JButton lookupName;
	private JButton addStatus;
	private JButton addImage;
	private JButton addFriend;
	private JLabel nameLabel;
	private JTextField name;
	private JTextField status;
	private JTextField image;
	private JTextField friend;
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile = null;
	private FacePamphletCanvas canvas;

	/**
	 * This method has the responsibility for initializing the interactors in
	 * the application, and taking care of any other initialization that needs
	 * to be performed.
	 */
	public void init() {
		initializeNorth();
		initializeSouth();
		addActionListeners();
		database = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);
	}

	// initializeSouth - this method initialize the south side
	private void initializeSouth() {
		addStatus = new JButton("Change Status");
		addImage = new JButton("Change Picture");
		addFriend = new JButton("Add friend");
		status = new JTextField(TEXT_FIELD_SIZE);
		image = new JTextField(TEXT_FIELD_SIZE);
		friend = new JTextField(TEXT_FIELD_SIZE);

		add(status, WEST);
		add(addStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(image, WEST);
		add(addImage, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friend, WEST);
		add(addFriend, WEST);

		status.addActionListener(this);
		image.addActionListener(this);
		friend.addActionListener(this);
	}

	// initializeNorth - this method initialize the North side
	private void initializeNorth() {
		nameLabel = new JLabel("Name");
		name = new JTextField(TEXT_FIELD_SIZE);
		addName = new JButton("Add");
		delateName = new JButton("Delate");
		lookupName = new JButton("Look up");
		add(nameLabel, NORTH);
		add(name, NORTH);
		add(addName, NORTH);
		add(delateName, NORTH);
		add(lookupName, NORTH);
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addName && !name.getText().equals("")) {
			addProfile();
		} else if (e.getSource() == delateName && !name.getText().equals("")) {
			delateProfile();
		} else if (e.getSource() == lookupName && !name.getText().equals("")) {
			lookUpProfile();
		} else if ((e.getSource() == status || e.getActionCommand().equals("Change Status"))
				&& !status.getText().equals("")) {
			changeProfileStatus();
		} else if ((e.getSource() == image || e.getActionCommand().equals("Change Picture"))
				&& !image.getText().equals("")) {
			changeProfileImage();
		} else if ((e.getSource() == friend || e.getActionCommand().equals("Add friend"))
				&& !friend.getText().equals("")) {
			addProfileFriends();
		}

	}

	// addProfileFriends - this method tries to add a friend to current profile
	private void addProfileFriends() {
		if (currentProfile != null) {
			addFriendToCurrentProfile(friend.getText());
		} else {
			canvas.showMessage("Please choose a profile");
		}
	}

	// addFriendToCurrentProfile - this method adds each other in their
	// friendlist
	private void addFriendToCurrentProfile(String text) {
		if (database.containsProfile(text) && !currentProfile.getName().equals(text)) {
			if (currentProfile.addFriend(text)) {
				canvas.displayProfile(currentProfile);
				canvas.showMessage(text + " added as a friend");
			} else {
				canvas.showMessage(text + " is already a friend");
			}
			database.getProfile(text).addFriend(currentProfile.getName());
		} else {

			canvas.showMessage("Profile with this name cannot be found");
		}
	}

	// changeProfileImage - this method changes an image of current profile
	private void changeProfileImage() {
		if (currentProfile != null) {
			try {
				currentProfile.setImage(new GImage(image.getText()));
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Picture updated");
			} catch (ErrorException ex) {
				canvas.showMessage("Picture not found");
			}
		} else {
			canvas.showMessage("Please choose a profile");
		}

	}

	// changeProfileStatus - this method changes a status of a current profile
	private void changeProfileStatus() {
		if (currentProfile != null) {
			currentProfile.setStatus(status.getText());
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Status updated");
		} else {
			canvas.showMessage("Please choose a profile");
		}

	}

	// lookUpProfile - this method tries to find a profile
	private void lookUpProfile() {
		String Name = name.getText();
		if (database.containsProfile(Name)) {
			currentProfile = database.getProfile(Name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Found  " + currentProfile.getName());
		} else {
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Profile with the name  " + Name + " does not exist");
		}

		name.setText("");
	}

	// delateProfile - this method deletes profile
	private void delateProfile() {
		String Name = name.getText();
		if (database.containsProfile(Name)) {
			database.deleteProfile(Name);
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Delated " + Name.toString());
		} else {
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Profile with the name  " + Name + " does not exist");
		}

		name.setText("");
	}

	// addProfile - this method creates new profile
	private void addProfile() {

		String Name = name.getText();
		if (!database.containsProfile(Name)) {
			database.addProfile(new FacePamphletProfile(Name));
			currentProfile = database.getProfile(Name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Added " + currentProfile.getName());
		} else {
			currentProfile = database.getProfile(Name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Profile with the name  " + Name + " already exists");
		}
		name.setText("");
	}

}
