package lab06_MontanaSinger;
import java.util.Scanner;
import java.io.*;

/*
 * Montana Singer
 * CS 160-06 Fall 2018
 * Lab 6
 * 
 */

public class LoopFilesPennies {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		//Declare variables
		int days;
		int pennies;
		double dollars;
		double wages;
		
		int currentDay = 1;
		
		
		
		System.out.println("Enter an integer number between 21 and 30 for the number of days worked:");
		
		//Declare scanner to read console input.
		Scanner keyboard = new Scanner(System.in);
		
		days = keyboard.nextInt();
		
		//If the input is not between 21 and 30
		//the user is asked to input a valid amount of days
		while(!((days >= 21) && (days <= 30)))
		{
			System.out.println("Input is not a valid amount of days, re-enter a valid amount of days:");
			days = keyboard.nextInt();
			
		}
		
		
		//Print the title for the data
		System.out.println("Pay Rate for Option II vs Option I:" + "\n" + 
						   "Days worked" + "       Option II Cents" + "           Option I");

		
		
		
		
	//The file objects are declared here because the data that has to be 
	//written to the file is within the loop that prints the data to the console
		
		//Open wages.txt file in append mode
		FileWriter file = new FileWriter("wages.txt", true);
		
		//Assign printwriter to the file in append mode
		PrintWriter writeToFile = new PrintWriter(file);
		
		//Write the title for the data to the file
		writeToFile.append("\r\n\r\n" + "Pay Rate for Option II vs Option I:" + "\n" +
						   "Days worked" + "     Option II Cents" + "      Option I $" + "\r\n");
		
		
		
		
		
		//Give values their respective starting values
		pennies = 1;
		dollars = 1000;
		
		//Begin for loop to calculate Option 1 pennies per day
		for (currentDay = 1; currentDay <= days; currentDay++)
		{
			
			System.out.printf("day " + currentDay + ":" + "\t\t\t" + String.format("%,-12d", pennies) + "        " + 
							  String.format("%,.2f", dollars) + "\n");
			
				
				//If statement to write to the data to the file
				//from day 21 to 30
				if (currentDay >= 21 && currentDay <= 30)
				{
					writeToFile.append(String.format("day " + currentDay + ":" + "\t\t\t" + String.format("%,-12d", pennies) + "        " + 
							String.format("%,.2f", dollars) + "\n"));
				}
			
			pennies = pennies * 2;
			dollars = dollars + 1000;
			
		}
		
		//This calculation is due to the fact that days must start at 1
		//and the counting begins at 0, thus, there is an extra iteration
		//of the calculation for pennies and dollars that is not 
		//displayed in console but is still calculated
		pennies = pennies / 2;
		dollars = dollars - (double)1000;
		
		
		//Assign wages the dollar value that corresponds to pennies
		wages = pennies / (double)100;
		
		//Print the comparison between wages
		System.out.printf("\n" + "For %d days worked the CS major earned $%,.2f and the XX major earned $%,.2f", days, wages, dollars);
		
		
		//Experience summary
		String comment = ("The choice by the CS major was justifiable if the person would be working for " +
					 	  "23 days or more, as that is the point in which it becomes more profitable " +
					 	  "to choose that option.");

		
		//Write the conclusion statement to the file
		writeToFile.append(String.format("\n" + "For %d days the CS major earned $%,.2f while the XX major earned $%,.2f", days, wages, dollars));
			
		//Write the comment to the file in append mode
		writeToFile.append("\r\n\r\n" + comment);
		
		//Close the file
		writeToFile.close();

		
	}


}
