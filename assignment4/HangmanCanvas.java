
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private GLabel text = new GLabel("");
	private GLabel wrongChars = new GLabel("");

	/** Resets the display so that only the scaffold appears */
	// this method remove full body and restart canvas
	public void reset() {
		removeAll();
		drawScaffold();
		text.setLabel("");
		wrongChars.setLabel("");
		text.setFont("-20");
		add(text, getWidth() / 2 - BEAM_LENGTH, 60 + SCAFFOLD_HEIGHT);
		wrongChars.setFont("-12");
		add(wrongChars, getWidth() / 2 - BEAM_LENGTH, 80 + SCAFFOLD_HEIGHT);
		
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	// when this method is called current word changes on canvas
	public void displayWord(String word) {

		text.setLabel(word);
		
		
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	// when this method is called wrong symbol is printed on canvas and part of a body appears 
	public void noteIncorrectGuess(char letter) {
		String wronged = wrongChars.getLabel() + letter;
		wrongChars.setLabel(wronged);
		int x = getWidth() / 2;
		if(wronged.length() == 1){
		GOval face = new GOval(HEAD_RADIUS * 2,HEAD_RADIUS * 2);
		 add(face,x - HEAD_RADIUS ,30 + ROPE_LENGTH);
		}else if(wronged.length() == 2){
			GLine body = new GLine(x ,30 + ROPE_LENGTH + HEAD_RADIUS * 2, x, 30 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		 add(body);
		}else if(wronged.length() == 3){
			drawHand(-1);
			
		}else if(wronged.length() == 4){
			drawHand(1);
		}else if(wronged.length() == 5){
			drawLeg(-1);
		}else if(wronged.length() == 6){
			drawLeg(1);
		}else if(wronged.length() == 7){
			drawFeet(-1);
		}else {
			drawFeet(1);
		}

	}
//this method draws left or right foot 
	private void drawFeet(int i) {
		int x = getWidth()/2 + HIP_WIDTH * i;
		int y = 30 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH ;
		GLine feet = new GLine( x , y, x + FOOT_LENGTH * i, y );
		add(feet);
	}
//this method draws left or right leg 
	private void drawLeg(int i) {
		int x = getWidth() / 2;
		int y = 30 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH ;
		GLine hip = new GLine(x ,y , x + HIP_WIDTH * i , y);
		add(hip);
		GLine leg = new GLine ( x + HIP_WIDTH * i , y,  x + HIP_WIDTH * i, y + LEG_LENGTH);
		add(leg);
	}
// this method draws left or right hand 
	private void drawHand(int i) {
		int x = getWidth() / 2;
		int y = 30 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		GLine upper = new GLine(x,y, x +  UPPER_ARM_LENGTH * i, y);
		add(upper);
		GLine lower = new GLine(x +  UPPER_ARM_LENGTH * i, y , x +  UPPER_ARM_LENGTH * i, y + LOWER_ARM_LENGTH);
		add(lower);
	}
// this method draws full scaffold
	private void drawScaffold() {
		int x = getWidth() / 2;
		GLine vertical = new GLine(x - BEAM_LENGTH, 30, x - BEAM_LENGTH, 30 + SCAFFOLD_HEIGHT);
		add(vertical);
		GLine horisontal = new GLine(x - BEAM_LENGTH, 30, x, 30);
		add(horisontal);
		GLine rope = new GLine(x, 30, x, 30 + ROPE_LENGTH);
		add(rope);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
