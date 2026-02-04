/*
 * File: Target.java
 * Name: luka lomadze
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final double RADIUS = 2.54;
	public void run() {
		
		double bigR = RADIUS * 28.3;
		double x = getWidth()/2 - bigR;
		double y = getHeight()/2 - bigR;
		double gap = bigR /3; // gap is how much radius should decrease after one circle
		drawTarget(x , y, bigR, gap);
	
	}
	
	// drawTarget - this method draws 3 circle that look like target
	private void drawTarget(double x, double y,  double bigR, double gap ) {
		for (int i = 0; i < 3;i++){
			drawCircle(x+ i * gap, y + i * gap , bigR - gap * i, i);
		}
		
	}

	// drawCircle- this method draws circle based on given size and color
	private void drawCircle(double x, double y, double r, int i) {
		GOval circle = new GOval(2* r,2 * r);
		circle.setFilled(true);
		circle.setColor(chooseColor(i));
		add(circle, x, y);
		
	}
	
	// chooseColor - decide what color should circle should be 
	private Color chooseColor(int i) {
		if(i % 2 == 0){
		return Color.RED;
		}else{
			return Color.WHITE;
		}
	}
	
}
