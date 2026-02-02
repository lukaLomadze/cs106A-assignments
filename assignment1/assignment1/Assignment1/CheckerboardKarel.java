/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */


import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run(){

	singleColumn();
	multyColumn();

	}
	
// multyColumn : method works if number of avenues is more than one
	private void multyColumn() {
		while(leftIsClear()){
			moveOnHorizon();
			turn();
		}
		moveOnHorizon();	
	}	
		
// turn : karel turns right or left
	private void turn() {
	if(facingEast()){
		moveToNextStreetFromRight(); 
	  }else{
		  moveToNextStreetFromLeft();
	  }
	
	}
	
//moveToNextStreetFromRight : karel jumps to the next street when it faces east
	private void moveToNextStreetFromRight() {
		if(beepersPresent()){
			//karel checks and if at the and of the street beeper present next line starts with empty place
			jumpOnNextStreet();
			move();
		}else{
			jumpOnNextStreet(); 
		}
	}

//jumpOnNextStreet :  karel jumps to the next street
	private void jumpOnNextStreet() {
		turnLeft();
		move();
		turnLeft();
	}


	//moveToNextStreetFromLeft :  karel jumps to the next street when it faces west
	private void moveToNextStreetFromLeft() {
		 turnRight();
		  if(frontIsClear()){
			  move();
			  turnRight();
		  } 
		
	}
	
// moveOnHorizon : karel move on one line and fills it with beepers like a chess board
	private void moveOnHorizon(){
	
		while (frontIsClear() ){
			putBeeper();
			move();	
			if(frontIsClear()){
				move();
				if(frontIsBlocked()){
					putBeeper();
				}
			}
		}	
	}

	// singleColumn : method works iff number of avenues is one
	private void singleColumn() {
		
		if(frontIsBlocked()){
			turnLeft();
			if(frontIsBlocked()){
				putBeeper();
			}
			moveOnHorizon();
		}	
	}

	
	
}
