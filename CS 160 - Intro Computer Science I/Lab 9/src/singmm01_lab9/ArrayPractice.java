package singmm01_lab9;

import java.util.Arrays;
import java.util.Random;

/*
* Montana Singer
* CS 160–07, Fall Semester 2018
* Lab 9
* 
* The purpose of this class is to fill each array
* with elements and also create the methods to display them
* 
*/

public class ArrayPractice {
	
	private int[] numbers;
	private Rectangle[] boxes;
	private String[] listOfNames;
	private final int baseLength = 10;
	
	//Constructor
	public ArrayPractice()
	{
		//Instantiates all array fields to length baseLength
		numbers = new int[baseLength];
		boxes = new Rectangle[baseLength];
		listOfNames = new String[baseLength];
	}
	
	//Another constructor
	public ArrayPractice(int lengthOfNumbers, int lengthOfBoxes, String[] initialize_listOfNames)
	{
		//Instantiates numbers and boxes to the parameters
		numbers = new int[lengthOfNumbers];
		boxes = new Rectangle[lengthOfBoxes];
		
		//Assigns the third parameter to the string array
		listOfNames = initialize_listOfNames;
		
		//Call the methods
		loadNumbers();
		loadBoxes();
	}
	
	
	//Populate the numbers array with random integers
	public void loadNumbers()
	{
		//Declare a random object
		Random random = new Random(); 
		int randomInt;
		
		//Assigns a number for each part of the array
		for (int index = 0; index < numbers.length; index++)
		{
			//Assign a random number between -100 and 100
			randomInt = random.nextInt(200)-100;
			//Assign random numbers to each element of the array
			numbers[index] = randomInt;
		}
	}
	
	
	public void loadBoxes()
	{
		
		for (int index2 = 0; index2 < boxes.length; index2++)
		{

			//Declare randome and rectangle variables
			Random random2 = new Random();
			Rectangle rectangle = new Rectangle();
			
			//For each iteration a rectangle object is instantiated
			//with randomly selected length and width between 0-1
			double randomDouble = random2.nextDouble();
			rectangle.setLength(randomDouble);
			
			randomDouble = random2.nextDouble();
			rectangle.setWidth(randomDouble);
			
			//Assigns the rectangle to each element of the boxes array
			boxes[index2] = rectangle;
			
		}
	}
	
	
	public void displayNumbers()
	{
		//System.out.println(Arrays.toString(numbers));
		Arrays.stream(numbers).forEach(System.out::println);
	}
	
	
	public void displayBoxes()
	{
		//System.out.println(boxes.toString());
		Arrays.stream(boxes).forEach(System.out::println);
	}
	
	
	public void displayList()
	{
		//System.out.println(Arrays.toString(listOfNames));
		Arrays.stream(listOfNames).forEach(System.out::println);
	}

} //End ArrayPractice class
