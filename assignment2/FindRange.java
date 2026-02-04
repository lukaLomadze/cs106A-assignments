/*
 * File: FindRange.java
 * Name: luka lomadze
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL =0;
	private int min =0 ;
	private int max =0 ;
	
	public void run() {
		int a = 0;	
		while(true){
			int x = readInt(" ? ");
			if(x == SENTINEL){
				break;
			}
			
			if(a==0){
				min = x;
				max = x;
				a++;
			}
			compareNums(x);
		}
		
		writeResult(a);	
	}
	
	// compareNums - this method choose min and max numbers
	private void compareNums(int x) {
		if(min > x){
			min = x;
		}
		if(max < x){
			max = x;
		}	
	}

	// writeResult - this method show results based on entered numbers
	private void writeResult(int a) {
		if(a != 0){
		println("max is : " + max);
		println("min is : " + min);
		}else{
			println("there is no appropriate numbers!");
		}
	}
	
	
}

