/*
 * File: ProgramHierarchy.java
 * Name: luka lomadze
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int WIDTH = 200;
	private static final int HEIGHT = 50;
	
	public void run() {
		
		
	double x = getWidth()/2;
	double y = getHeight()/2;
	
	 drawHierarchy(x, y);

	}
	
	// drawHierarchy - this method draws full hierarchy
	private void drawHierarchy(double midX ,double midY ) {
		drawRect(midX - WIDTH / 2, midY - HEIGHT * 1.5, "Program");
		/* gap between two rect in a row is 10% of rect's width 
		 *  gap between two rect in a column is 100% of rect's height 
		 */
		for(int i = 0; i< 3; i++){
			double x = midX - WIDTH * 1.6 + WIDTH *i  * 1.1;
			drawRect(x , midY + HEIGHT * 0.5, chooseText(i) );
			drawArrow(midX , midY - HEIGHT * 0.5 , x + WIDTH * 0.5 ,midY + HEIGHT * 0.5);
		}
		
	}
	
	// chooseText - this method decide which text should be in a rect according to the row
	private String chooseText( int i) {
		if(i== 0){
			return "GraphicsProgram";	
		}else if(i ==1){
			return "ConsoleProgram";
		} else {
			return "DialogProgram";
		} 
	}

	//drawArrow - this method draws lines which connect rects
	private void drawArrow(double x, double y, double a, double b) {
	GLine Arrow = new GLine(x , y , a , b);
		add(Arrow);
	}
	
	//drawRect - this method draws rects and calls method to write labels
	private void drawRect(double x, double y, String text) {
		GRect rect = new GRect(WIDTH, HEIGHT);
		add(rect, x , y);
		writeLable(x, y, text );
	}


// writeLable - this method write labels in the center of rect
	private void writeLable(double x, double y, String text) {
		GLabel heading = new GLabel(text);
		add(heading, x + (WIDTH - heading.getWidth())/2 , y + (HEIGHT + heading.getAscent()) / 2.0 );
	}
	
}

