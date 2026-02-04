/*
 * File: Pyramid.java
 * Name: luka lomadze
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		double y = getHeight() - BRICK_HEIGHT;
		double x = getWidth()/2 - BRICKS_IN_BASE * BRICK_WIDTH / 2; 
		// x,y is calculated for the pyramid to be on the bottom center
		drawPyramid(x, y);
		
	}

	//drawPyramid - this method draws rows one after anothet till number of bricks become 1 on the top
	private void drawPyramid(double x, double y) {
		for (int i =0; i < BRICKS_IN_BASE ; i++){
			drawOneRow(x, y, i);
		}
		
	}

	// drawPyramid - this methodd draws rows of bricks . each time method is called in for loop number of bricks decreases by 1
	private void drawOneRow(double x, double y, int i) {
		for (int t = 0; t < BRICKS_IN_BASE-i ; t++){ // BRICKS_IN_BASE-i calculate how many bricks should be in a row
			GRect brick = new GRect(BRICK_WIDTH , BRICK_HEIGHT );
			add(brick, x + t * BRICK_WIDTH + i * BRICK_WIDTH/2 ,y - i * BRICK_HEIGHT );	// brick's coordinates are changed based on their rows
		}
		
	}
	
	
	
	
	
	
}

