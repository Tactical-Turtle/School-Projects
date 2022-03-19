package singmm01_lab9;


/*
* Montana Singer
* CS 160–07, Fall Semester 2018
* Lab 9
* 
* The purpose of this class is to have the methods
* to create rectangle objects with length and width
* and also be able to print them
* 
*/

public class Rectangle {
	
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
