package project01_CS260_MontanaSinger;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Montana Singer
 * CS 260 - Fall 2019
 * Project 1
 * 
 * This class represents a bag that contains CarDataNodes that were removed from the garage set.
 * The string representation of the car nodes is output into a text file upon program exit.
 * 
 * @param bagHead This is the head of the bag of CarDataNodes
 * @param next The next CarDataNode in the bag
 * @param bagElements Stores the number of CarDataNodes in the bag
 * 
 */

public class GarageExitBag {
	
	private CarDataNode bagHead;
	private CarDataNode next;
	private int bagElements;
	
	/* Constructor */
	public GarageExitBag()
	{
		bagHead = null;
		bagElements = 0;
	}
	
	/* Sets the head of the bag
	 * @param e A CarDataNode to initialize bagHead
	 */
	public void setBagHead(CarDataNode e)
	{
		bagHead = e;
	}
	
	
	/* Returns if the bag is empty or not
	 * @return boolean true or false if the bag is empty or not
	 */
	public boolean isEmpty()
	{	return bagHead == null;	}
	
	
	/* Adds a CarDataNode to the bag
	 * @param car The CarDataNode to be added
	 */
	public void add(CarDataNode car)
	{
		CarDataNode newNode = car;
		newNode.setNext(bagHead);
		bagHead = newNode;
		bagElements++;
	}
	
	
	/* Creates a file that shows the contents of the bag */
	public void dumpOutputFile() throws IOException
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = dateFormat.format(date) + ".txt";
		
		PrintWriter outputFile = new PrintWriter(fileName, "UTF-8");
		
		//The output message is printed to the output file.
		outputFile.print(bagToString());
		//The file is closed.
		outputFile.close();
	}
	
	
	/* Returns the elements in the bag in string format
	 * @return A string representation of the elements in the bag
	 */
	public String bagToString()
	{
		String result = "";
       
	    CarDataNode current = bagHead;

	    while (current != null)
	    {
	       result += current.toString() + "\n";
	       current = current.getNext();
	    }
		
		return result;
	}
	
	/* Returns the number of elements in the bag
	 * @return An int that holds the number of elements in the bag
	 */
	public int bagSize()
	{
		return bagElements;
	}
	
	

} //End GarageExitBag class
