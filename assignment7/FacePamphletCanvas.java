
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import acm.util.ErrorException;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	private GLabel message;

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		addMessage();
	}

	/**
	 * This method displays a message string near the bottom of the canvas.
	 * Every time this method is called, the previously displayed message (if
	 * any) is replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		message.setFont(MESSAGE_FONT);
		message.setLabel(msg);
		double x = (getWidth() - message.getWidth()) / 2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		message.setLocation(x, y);
	}

	// addMessage - this method draws a clear Glabel
	private void addMessage() {
		message = new GLabel("");
		add(message);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the
	 * bottom of the screen) and then the given profile is displayed. The
	 * profile display includes the name of the user from the profile, the
	 * corresponding image (or an indication that an image does not exist), the
	 * status of the user, and a list of the user's friends in the social
	 * network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if (profile != null) {
			showName(profile);
			showImage(profile);
			showFriends(profile);
			showstatus(profile);
		}
		addMessage();
	}

	// showstatus this method draws status
	private void showstatus(FacePamphletProfile profile) {
		String status = profile.getStatus();
		if (status.equals("")) {
			addStatus("No current status");
		} else {
			addStatus(profile.getName() + " is " + profile.getStatus());
		}

	}

	// showstatus this method draws status on canvas
	private void addStatus(String stat) {
		GLabel status = new GLabel(stat);
		status.setFont(PROFILE_STATUS_FONT);
		double y = TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT + status.getAscent() + STATUS_MARGIN;
		add(status, LEFT_MARGIN, y);
	}

	// showFriends - this method draws friends on canvas
	private void showFriends(FacePamphletProfile profile) {
		GLabel friend = new GLabel("Friends :");
		friend.setFont(PROFILE_FRIEND_LABEL_FONT);
		double y = TOP_MARGIN + friend.getAscent();
		add(friend, getWidth() / 2, y);
		Iterator<String> friends = profile.getFriends();
		while (friends.hasNext()) {
			GLabel fr = new GLabel(friends.next());
			fr.setFont(PROFILE_FRIEND_FONT);
			y += fr.getHeight() + TOP_MARGIN / 2;
			add(fr, getWidth() / 2, y);
		}
	}

	// showImage this method draws image on canvas
	private void showImage(FacePamphletProfile profile) {
		GImage image = profile.getImage();
		if (image == null) {
			drawRect();
		} else {
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
		}

	}

	// drawRect this method draws rect if there is no image
	private void drawRect() {
		rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(rect, LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
		GLabel noimage = new GLabel("NO IMAGE");
		double x = (rect.getWidth() - noimage.getWidth()) / 2 + rect.getX();
		double y = (rect.getHeight() + noimage.getHeight()) / 2 + rect.getY();
		add(noimage, x, y);
	}

	// showName this method draws name on canvas
	private void showName(FacePamphletProfile profile) {
		name = new GLabel(profile.getName());
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name, LEFT_MARGIN, TOP_MARGIN + name.getAscent());

	}

	private GRect rect;
	private GLabel name;
}
