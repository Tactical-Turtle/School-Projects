package singmm01_lab8;

import java.util.Scanner;

/*
* Montana Singer
* CS 160–04, Spring Semester 2018
* Lab 8
* 
* The purpose of this class is to declare most of the
* methods that will be utilized in the main method
*
*/
class Rectangle 
{
	private double length;
	private double width;
	
	//Declare length accessor (getter) method
	public double getLength()
	{
		return length;
	}
	
	//Declare length mutator (setter) method
	//It takes a parameter and sets the field
	//only if the parameter is non-negative
	public void setLength(double parameterLength)
	{
		if (parameterLength >= 0)
		{
			length = parameterLength;
		}
		
		else 
		{
			length = 0;
		}
	}
	
	//Declare width accessor (getter)
	public double getWidth()
	{
		return width;
	}
	
	//Declare width mutator (setter) method
	//It takes a parameter and sets the field
	//only if the parameter is non-negative
	public void setWidth(double parameterWidth)
	{
		if (parameterWidth >= 0)
		{
			width = parameterWidth;
		}
		
		else
		{
			width = 0;
		}
	}
	
	
	//Implement method computeArea() 
	public double computeArea()
	{
		//Calculate and return area
		return length * width;
	}
	
	
	
	//Implement method computePerimeter()
	public double computePerimeter() 
	{
		//Calculate and return perimeter
		return width + width + length + length;
	}

	
	
	//Implement method toString()
	public String toString()
	{	
		//Returns the string message
		return String.format("The length is: %.2f" + "\n" +
				 			 "The width is: %.2f" + "\n", getLength(), getWidth()); 
	}
	
	
	
	//Implement method displayRectangle()
	public void displayRectangle()
	{
		//Calls the toString() method and prints
		//the returned value to the console
		System.out.println(toString());

	}
	
	
	
	//Implement method equals()
	public boolean equals(Rectangle other)
	{
		//Compares the class fields to that of the parameter Rectangle
		//If they are equal, return true
		//If they are unequal, return false
		if (length == other.length && width == other.width)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	//Initializer constructor
	public Rectangle(double len, double w)
	{
		length = len;
		width = w;
	}
		
	
	//Default constructor
	public Rectangle() 
	{ 
		
	}
	
} //End Rectangle class








/*
* Montana Singer
* CS 160–04, Spring Semester 2018
* Lab 8
*
* The purpose of this class is to provide the main
* content of the program and display the relevant 
* information regarding 3 boxes
*
*/
public class Applications {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		double length, width;
		
		//Declare scanner to read console
		Scanner keyboard = new Scanner(System.in);
		
		//Print a message to input values for length
		System.out.println("Enter the length of the rectangle:");
		
		//Assign length the value that the user input
		length = keyboard.nextDouble();
		
		//Print a message to input values for the width
		System.out.println("Enter the width of the rectangle:");
		
		//Assign width the value that the user input
		width = keyboard.nextDouble();
		
		//Close scanner object
		keyboard.close();
		
		
		//Declare a Rectangle object named box
		//Instantiate with length and width parameters
		Rectangle box = new Rectangle(length, width);
		
		//Declare a Rectangle object named box2
		Rectangle box2 = new Rectangle();
		
		
		//Box 2 calls the setLength and setWidth methods
		//Numbers for parameters are chosen at will
		box2.setLength(50.1);
		box2.setWidth(51.5);
		
		
		//The displayRectangle() method is called with respect to box and box2
		box.displayRectangle();
		box2.displayRectangle();
		
		
		//Box calls the equals() method, uses box2 as parameter
		box.equals(box2);
		//Print the returned boolean to the console
		System.out.println("The boolean value for box = box2 is   " + box.equals(box2));
		
		
		//Declare and instantiate third Rectangle object, box3
		Rectangle box3 = new Rectangle();
		
		
		//Box calls the getter methods
		//Assign the local variables width and length 
		//the values returned by the getters
		length = box.getLength();
		width = box.getWidth();
		
		
		//Box3 calls the setters
		//Setters take the local variables for parameter
		box3.setLength(length);
		box3.setWidth(width);
		
	
		//Box calls the equals method and uses box3 for parameter
		box.equals(box3);
		//Print the returned boolean to the console
		System.out.println("The boolean value for box = box3 is   " + box.equals(box3));
		
		
		//Call the computeArea and computePerimeter methods for all boxes
		System.out.printf("\nThe area for box is: %.3f" + "\n" +
						  "The perimeter for box is: %.3f" + "\n\n",
						  box.computeArea(), box.computePerimeter());
		
		System.out.printf("The area for box2 is: %.3f" + "\n" +
				  		  "The perimeter for box2 is: %.3f" + "\n\n",
				  		  box2.computeArea(), box2.computePerimeter());
		
		System.out.printf("The area for box3 is: %.3f" + "\n" +
						  "The perimeter for box3 is: %.3f",
						  box3.computeArea(), box3.computePerimeter());
		
		
	} //End main method

} //End Applications class

//Comment Block
/*
 *  Enter the length of the rectangle:
 *	10
 *	Enter the width of the rectangle:
 *	15
 *	The length is: 10.00
 *	The width is: 15.00
 *
 *	The length is: 50.10
 *	The width is: 51.50
 *	
 *	The field values do correspond to the input
 *	
 *Comment on boolean value
 * The boolean value for box2 as parameter is false
 * 
 * The rectangles (box and box2) were not equal
 * 
 * 
 *Comment on second booolean value
 * The boolean value for box3 as parameter is true
 * 
 * The rectangles (box and box3) were equal
 *
 *
 *Comment on the areas and perimeters
 * The area for box is: 150.000
 * The perimeter for box is: 50.000
 *  box's values are obtained from console, they match.
 *
 * The area for box2 is: 2580.150
 * The perimeter for box2 is: 203.200
 *  box2's values are obtained from lines 190-191 as its parameters
 *  are directly set in the code itself, they match.
 *
 * The area for box3 is: 150.000
 * The perimeter for box3 is: 50.000
 *  box3's values come from passing box's values to box3's parameters
 *  and as such, it obtains the same values as whatever box is assigned
 *  from the console.
 *
*/


