package project02_MontanaSinger;
import java.util.Scanner;
import java.io.*;

/**
 * Montana Singer
 * CS 160 - 07, Fall Semester 2018
 * Project 2: Examination Statistics
 * 
 * Description. This class gets file input from the user and calculates
 * various statistics about the data and outputs that data into a file
 * whose name is designated by the user.
 * 
 */

public class ExamStatistics {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//named constants to store lower score limits for letter grade groups
		final int limitGradeA = 85;
		final int limitGradeB = 75;
		final int limitGradeC = 65;
		final int limitGradeD = 55;
		
		//Declare counting variables
		int numberOfScores = 0;
		int totalNumberOfA = 0;
		int totalNumberOfB = 0;
		int totalNumberOfC = 0;
		int totalNumberOfD = 0;
		int totalNumberOfF = 0;
		
		//Score value variables
		int minScore;
		int maxScore;
		int nextScore;
		
		//Declare double variables
		double sum = 0;
		double average = 0;
		double psd = 0;
		
		//Declare strings
		String outputMessage;
		String inputFileName;
		
		
		
		
		//declare scanner for reading console
		Scanner keyboard = new Scanner(System.in);
		
		//Declare file object
		File inputFile;
		
		//Loop to check if input is valid
		//If not it asks for user to re-input a valid file name
		do
		{
			//Asks user to input file name
			System.out.println("Enter the file name");
			//Stores user input in a string
			inputFileName = keyboard.nextLine();
			
			//Assigns the file object to the user input
			inputFile = new File (inputFileName);
				
		} while(!inputFile.exists());
		
		
		
		
		
		//Declare Scanner to read file.
		Scanner reader;
		
		//Scanner object reads file that the user input.
		reader = new Scanner(inputFile);
		
			
			//Used to determine the min and max value in the file.
			maxScore = Integer.MAX_VALUE;
			minScore = Integer.MIN_VALUE;
			
			//Initialize min and max.
			maxScore = 0;
			minScore = 101;
		
			
			//Begin loop to read, count, and sum the scores.
			//The loop also determines min and max scores.
			//It counts the occurrences of scores in the grade groups.
			//Input value are checked and wrong input values are ignored.
			while (reader.hasNext())
			{
				
				//Checks if the input is an int
				//If not, the program skips it
				while (!reader.hasNextInt())
				{
					reader.next();
				}
				
				//Reads integers in file
				nextScore = reader.nextInt();
				
				//Tests if the integer is between 0-100.
				if ((nextScore >= 0) && (nextScore <= 100))
				{
					//Calculates the sum of the scores.
					sum = sum + nextScore;
					//Calculates the number of scores.
					numberOfScores++;
				
					//Count the number of scores that belong in their
					//respective grade group.
				
					//Number of A grades.
					if (nextScore >= limitGradeA)
					{
						totalNumberOfA++;
					}
				
					//Number of B grades.
					else if (nextScore >= limitGradeB)
					{
						totalNumberOfB++;
					}
				
					//Number of C grades.
					else if (nextScore >= limitGradeC)
					{
						totalNumberOfC++;
					}
			
					//Number of D grades.
					else if (nextScore >= limitGradeD)
					{
						totalNumberOfD++;
					}
			
					//Number of F grades.
					else if (nextScore < limitGradeD)
					{
						totalNumberOfF++;
					}
				
						//Calculate max score.
						if (nextScore > maxScore)
						{
							maxScore = nextScore;
						}
					
						//Calculate min score.
						if (nextScore < minScore)
						{
							minScore = nextScore;
						}
				
				}
					
			} //End while loop
		
				
			
		//Compute the average
		average = (sum) / numberOfScores;	
		
		
		
		//Declare and initiate scanner for the second reading of the file.
		Scanner psdInput = new Scanner(inputFile);
		
		//Used to store the next score for the second reading of the file.
		int psdScore;
		
		//Loop for the calculation of the psd
		//The integer index ensures that the loop only counts
		//to the max number of integers in the file.
		for (int index = 0; index < numberOfScores; index++)
		{
			
			//Validation that data is integer.
			//If data is not an integer, it skips over it.
			while (!psdInput.hasNextInt())
			{
				psdInput.next();
			}
			
			//Get next score in file.
			psdScore = psdInput.nextInt();
			
			//Checks if score is between 0-100
			if ((psdScore >= 0) && (psdScore <= 100))
			{
				//Calculation for numerator of psd.
				psd = psd + (Math.pow((average - psdScore), 2));
			}
			
			
		} //End for loop.
		
		
		
		//Divide psd numerator by number of scores.
		psd = psd / numberOfScores;
		//Take the square root of psd to get actual value for psd.
		psd = Math.sqrt(psd);
				
		
		
		//Declare and initialize doubles to store the 
		//percentage of each grade (ie: as in 80% of the grades were A's)
		double percentageOfA = (double)totalNumberOfA / (double)numberOfScores * 100;
		double percentageOfB = (double)totalNumberOfB / (double)numberOfScores * 100;
		double percentageOfC = (double)totalNumberOfC / (double)numberOfScores * 100;
		double percentageOfD = (double)totalNumberOfD / (double)numberOfScores * 100;
		double percentageOfF = (double)totalNumberOfF / (double)numberOfScores * 100;
		
		//Build the output message.
		// \r\n ensures that notepad will display \n 
		outputMessage = "Exam Statistics" + "\r\n\r\n" +
						"Total: " + numberOfScores + "\r\n" +
						"Average score: " + String.format("%.2f", average) + "\r\n" +
						"Population standard deviation of the scores: " + String.format("%.2f", psd) + "\r\n\r\n" +
						"# of A, 85-100:  " + totalNumberOfA + "  " + String.format("%.2f", percentageOfA) + "%" + "\r\n" + 
						"# of B, 75--84:  " + totalNumberOfB + "  " + String.format("%.2f", percentageOfB) + "%" + "\r\n" +
						"# of C, 65--74:  " + totalNumberOfC + "  " + String.format("%.2f", percentageOfC) + "%" + "\r\n" +
						"# of D, 55--64:  " + totalNumberOfD + "  " + String.format("%.2f", percentageOfD) + "%" + "\r\n" +
						"# of F, 00--54:  " + totalNumberOfF + "  " + String.format("%.2f", percentageOfF) + "%" + "\r\n\r\n" +
						"Minimum score: " + minScore + "\r\n" +
						"Maximum score: " + maxScore;
						
		
		//Print output message to console.
		System.out.println(outputMessage);
		
		
		//Asks the user to input the file name for the output file.
		System.out.println("Enter the filename to be written:");
		
		
		//Declare a string to hold user input for output file name.
		String outputFileName;
	
		//The output file is written.
		outputFileName = keyboard.nextLine();
		PrintWriter outputFile = new PrintWriter(outputFileName);
		
		//The output message is printed to the output file.
		outputFile.println(outputMessage);
		//The file is closed.
		outputFile.close();

		
	}
	
}
