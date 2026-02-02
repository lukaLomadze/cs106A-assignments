/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run(){
		while(frontIsClear()){
			fillColumn();
			backToFirstStreet();
			moveToNextColumn();
		}
		fillColumn();
	}

	
	//moveToNextColumn : karel move from one column to another
	private void moveToNextColumn() {
	for(int l = 0; l<4; l++){
		move();
		}
		
	}

	//backToFirstRow : karel return at the bottom
	private void backToFirstStreet() {
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnLeft();
	}

	//fillColumn : karel fixes one column
	private void fillColumn() {
		turnLeft();
		while(frontIsClear()){
			if(noBeepersPresent())putBeeper() ;
			move();
		}
		if(noBeepersPresent())putBeeper() ;
	
	}
	
}
