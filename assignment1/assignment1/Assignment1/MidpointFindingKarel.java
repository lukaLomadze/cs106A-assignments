/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	
	public void run(){
		toTopCenter();
		toBottomCenter();	
		
	}
	
	// toTopCenter : karel move to topcenter as world is square
	private void toTopCenter() {
		
		while(leftIsClear()){
			 knightmove();
			
			turnRight();
		}
		turnRight();
		
	}
	
	//toTopCenter : karel move like a knight
	private void knightmove() {
		move();
		turnLeft();
		move();
		if(frontIsClear()){
			move();
		}	
	}
	
	//toBottomCenter : karel move from topcenter to bottomcenter and puts beeper
	private void toBottomCenter() {
		while(frontIsClear()){
			move();
		}
		putBeeper();
		
	}

}
