/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run(){
		moveToNewspaper();
		pickNewspaper();
		returnFromNewspaper();
	}
	
	//returnFromNewspaper : karel goes from newspaper to home
	private void returnFromNewspaper() {
		threeMove();
		turnRight();
		move();
		turnRight();
	}
	
	//pickNewspaper : karel picks newspaper and turn to the home
	private void pickNewspaper() {
		pickBeeper();
		turnAround();
	}
	
	//moveToNewspaper : karel goes from home to newspaper
	private void moveToNewspaper() {
		turnRight();
		move();
		turnLeft();
		threeMove();
	}
	
	//threeMove : krarel makes three move 
	private void threeMove(){
		for(int l = 0; l<3; l++){
			move();
		}
		
	}

}
