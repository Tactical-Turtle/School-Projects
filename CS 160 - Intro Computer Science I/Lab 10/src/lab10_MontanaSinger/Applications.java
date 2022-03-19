package lab10_MontanaSinger;

import java.util.*;

/*
* Montana Singer
* CS160–07, Fall 2018
* Lab 10
* 
* The purpose of this class is to control the main logic for the program
* and print the box and box copy
* 
*/
public class Applications {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	int size = 10;
	
	//Declare and instantiate a string array of length "size" that holds names
	String[] names = new String[] {"Bob", "Jamie", "Gabe", "Caleb", "Lisa", "Pepe", "Anita", "Jenna", "Maria", "Jody"};
	
	
	//Declare and instantiate a boolean array of length "size" that
	//shows either true or false
	boolean[] trueOrFalse = new boolean[size];	
	
	
	//Fills the boolean array with randomly selected true or false
	for (int index = 0; index < trueOrFalse.length; index++)
	{
		//Declare random variable for use in the for loop
		boolean coinFlip = Math.random() < 0.5;
		
		// 50/50 chance, true and false assigned to the index
		if (coinFlip == true)
		{
			trueOrFalse[index] = true;
		}
		
		else 
		{
			trueOrFalse[index] = false;
		}
	
	} //End for loop
	

	//Declare and instantiates a Letter array of length size
	Letter[] letters = new Letter[size];
	
	//For loop to load the letters array with strings
	for (int index2 = 0; index2 < letters.length; index2++)
	{
		//Loop applies the initializer constructor for the letters elements
		//parameters are the String and boolean array indexes
		letters[index2] = new Letter(names[index2], trueOrFalse[index2]);
		
	} //End for loop
	
	
	//Declare a random variable for the int parameter in the POBox object
	Random random = new Random();
	int randomNumber = random.nextInt((8998) + 1) + 1001;	
	
	//Instantiates a POBox object named box
	//parameters = letters array, integer between 1001-9999
	POBox box = new POBox(letters, randomNumber);
	
	//Create deep copy of box using the copy constructor of POBox class
	POBox boxClone = new POBox (box);
	
	System.out.println("Check if the copy is deep! \n" + 
					   "Apply operator == to the 0 index entries of the letters array and its clone:");
	
	//Applies == operator in the index entries of the letters
	//field of box and boxClone
	System.out.println(box.getLetters() == boxClone.getLetters());
	System.out.println("");
	
	
	//Prints the return value of the toString method for box and boxClone
	System.out.println("box:");
	System.out.println(box.toString());
	
	System.out.println("boxClone:");
	System.out.println(boxClone.toString());
	
	
	//Applies the equals method to compare box and boxClone
	System.out.println("\nbox and its copy tested for equality: " + box.equals(boxClone));
		

	} //End main method

} //End Applications class
