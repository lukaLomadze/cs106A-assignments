/*
 * File: Hailstone.java
 * Name: luka lomadze
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	private int x;
	public void run() {
		readNum();
		countSteps();
	}
	
	
	// readNum - this method reads positive integer
	private void readNum() {
		while(true){
			 x = readInt("enter int ");
			 if(x > 0){
				 break;
			 }else{
				 println("please enter positive integet");
			 } 
		 }
	}

	// countSteps - this method count how many steps it took to reach 1 and prints each step
	private void countSteps() {
		int y = 0;
		while(x !=1){
			y++;
			if(x % 2 == 0){
				evenNum();
			}else{
				oddNum();
			}
		}
		println("The process took " + y + " steps to reach 1");
		
	}

	
	// oddNum- this method multyplies odd number by 3 and adds 1
	private void oddNum( ) { 	
		println(x +"is odd so i make 3n + 1 : " +( 3 * x + 1));
		x= 3 * x + 1;
	}

	// evenNum - this method divide even number by 2
	private void evenNum() {
		println(x +"is even so i take half : " + x / 2);
		x /= 2;	
	}
}

