package project2_CS260_montana_singer;

import java.io.Serializable;

/**
 * This class represents a Dog object that stores int data.
 * These Dog objects are contained in BinaryTreeNodes.
 * @author Montana Singer
 */
public class Dog implements Comparable<Dog>, Serializable {

	private int data;
	
	/* Default constructor. */
	public Dog () {}
	
	
	/**
	 * Constructor to initialize a Dog object with int data.
	 * @param data Data in the form of an int to be stored.
	 */
	public Dog(int data)
	{
		this.data = data;
	}

	
	/** Returns the data in the Dog object.
	 *@Return The int in the Dog object.
	 */
	public int getData()
	{
		return data;
	}
	
	
	/**
	 * Compares Dog objects.
	 * @Override
	 * @param dog The object to be compared to.
	 * @return Returns 1 if the first Dog object is greater than the second.
	 * Returns 0 if the first Dog object is less than the second.
	 */
	public int compareTo(Dog dog)
	{
		if (data > dog.getData())
		{
			return 1; 
		}
		
		else if (data < dog.getData())
		{
			return -1;
		}
		
		else
			return 0;
		
	}
	
	/**
	 * Displays the int data as a string.
	 * @Override
	 * @return String representation of the int data.
	 */
	public String toString()
	{
		return Integer.toString(data);
	}
	
} //End Dog class.
