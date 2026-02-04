/*
 * File: PythagoreanTheorem.java
 * Name: luka lomadze
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		pythagore();	
	}

	// pythagore - this method read the sizes of catheters and call method calculate to calculate hypotenuse
	private void pythagore() {
		println("enter values to compute pythagorean theorem.");
		int a = checkNum("a");
		int b = checkNum("b"); 
		calculate(a , b);

	}
	
	// checkNum - this method tries to get appropriate numbers (positive numbers)
	private int checkNum(String cathet ) {
		int x;
		while(true){
			x = readInt(cathet + " : ");
			if(x > 0){
				break;
			}else{
				println("please enter correct number");
			}
		}
		return x;
	}

	// calculate - this method calculate and print the size of hypotenuse
	private void calculate(int a, int b) {
		int hypo = a * a + b * b;
		double c = Math.sqrt(hypo);
		println("c = " + c);
	}
	
}
