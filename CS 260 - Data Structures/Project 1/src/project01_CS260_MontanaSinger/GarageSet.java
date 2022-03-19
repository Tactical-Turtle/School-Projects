package project01_CS260_MontanaSinger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


/*
 * Montana Singer
 * CS 260 - Fall 2019
 * Project 1
 * 
 * This class represents the garage, which is a linked set of CarDataNode nodes.
 * Cars can be added to the set or removed from the set using a license plate (string) or their index.
 * As cars are checked in and checked out, their corresponding fields are updated.
 * The set is saved in a file and saved upon exiting, and loaded upon starting the program.
 * 
 * @param garage The linked hash set to store CarDataNodes
 * @param bag A reference to the GarageExitBag to store nodes that are removed from the set.
 * @param setHead The head of the linked list
 * @param manyNodes The number of nodes in the set
 * 
 */

public class GarageSet {

	
	private static Set<CarDataNode> garage = new LinkedHashSet<CarDataNode>();
	
	public static GarageExitBag bag = new GarageExitBag();
	
	private CarDataNode setHead;
	private int manyNodes;	
	
	/* Constructor */
	public GarageSet()
	{
		manyNodes = 0;
	}
	
	/* Sets the head of the set
	 * @param e A CarDataNode to initialize setHead
	 */
	public void setGarageHead(CarDataNode e)
	{
		setHead = e;
	}
	
	
	/* Gets the size of the set
	 * @return The number of nodes in the set
	 */
	public int setSize()
	{
		return manyNodes;
	}


	/*
	 * Adds a car to the garage set
	 * Updates the car's check in data field
	 * @param plate The license plate of the car to be added
	 */
	public void checkIn(String plate)
	{
		CarDataNode carNode = CarDataNode.findNode(setHead, plate);
		
		if (garage.isEmpty())
		{
			carNode = setHead;
		}
		
		carNode.checkIn();
		garage.add(carNode);
		manyNodes++;
	}
	
	
	/*
	 * Adds a car to the garage set
	 * Updates the car's check in data field
	 * @param index The index of the car in the linked list to be added
	 */
	public void checkIn(int index)
	{
		CarDataNode carNode = CarDataNode.findNode(setHead, index);
		
		if (garage.isEmpty())
		{
			carNode = setHead;
		}
		
		carNode.checkIn();
		garage.add(carNode);
		manyNodes++;
	}
	
	
	/*
	 * Removes a car from the garage set and adds it into the garage exit bag
	 * Updates the car's check out data field
	 * @param plate The license plate of the car to be removed
	 */
	public void checkOut(String plate)
	{
		CarDataNode carNode = CarDataNode.findNode(setHead, plate);
		carNode.checkOut();
		garage.remove(carNode);
		manyNodes--;
		
		if (bag.isEmpty())
		{
			bag.setBagHead(carNode);
		}
		
		bag.add(carNode);
		
	}
	
	/*
	 * Removes a car from the garage set and adds it into the garage exit bag
	 * Updates the car's check out data field
	 * @param index The index of the car to be removed
	 */
	public void checkOut(int index)
	{
		CarDataNode carNode = CarDataNode.findNode(setHead, index);
		carNode.checkOut();
		garage.remove(carNode);
		manyNodes--;

		bag.add(carNode);
	}
	
	
	/* Returns the set of elements in garage in string format 
	 * @return A string of the nodes in the set
	 */
	public String toString()
	{
		String result = garage.toString().replace(", ", "\n\n");
		return result;
	}
	
	
	/* Loads the garage set data on startup */
	public static void loadGSData() throws ClassNotFoundException, IOException
	{
/*		FileInputStream fis = new FileInputStream("GSData.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		garage = (Set<CarDataNode>) ois.readObject();
		ois.close();
*/		
		Scanner scanner = new Scanner(new File("GSData.txt"));
		scanner.useDelimiter(",");
		
		while (scanner.hasNext())
		{
			System.out.println(scanner.next() + "\n");
		}
		
		scanner.close();

	}
	
	/* Saves the garage set data into a file on exit */
	public static void saveGSData() throws IOException
	{		
		PrintWriter out = new PrintWriter("GSData.txt");
		out.println(garage.toString());
		out.close();
	}

	
} //End GarageSet class
