package singmm01_CS161_lab5;

import java.util.*;

/*
* Montana Singer
* CS161
* Spring 2019
* Lab 5
*
*/ 


//
public class Lambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		Random randomNumber = new Random();
		Functionable action;
		int inp;
		int count;
		
		action = (int t) -> ((t-1)*(t-1)*(t-1)) + ((t+1)*(t+1)*(t+1));
		
		System.out.println("Type in an integer");
		inp = keyboard.nextInt();
		
		System.out.println("lambda test #1 for input " + inp + " is    " + action.function(inp));
	
		/*
		 * Test #1 does not have a smallest positive integer for which the result is negative
		 * because the expression can only be negative when the t value is negative.
		 */
		
		action = (int t) -> 2*t;
		
		count = 0;
		
		do
		{
			
			inp = randomNumber.nextInt((Integer.MAX_VALUE/9*5 - 0) + 1) + 0;
			count++;

		} while(action.function(inp) > 0);
		
		System.out.println("lambda test #2 made " + count + " steps;" +
							" result for input " + inp + " is " + action.function(inp));
		
		
		
		
		
		
		
		
		

	} //End main method

} //End Lambda class
