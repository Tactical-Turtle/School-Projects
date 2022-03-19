package project01_CS260_MontanaSinger;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Montana Singer
 * CS 260 - Fall 2019
 * Project 1
 * 
 * This class contains the main method to test the program.
 * 
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		System.out.println("Loading GSData");
		GarageSet.loadGSData();
		
		CarDataNode car1 = new CarDataNode("ABCDEF",0,0);
		car1.setHead(car1);
		car1.addNode("123456", 0, 0);
		car1.addNodeAfter("YB72SW", 0, 0);
		car1.addNodeAfter("9KWLA2", 0, 0);
		car1.removeNode(CarDataNode.findNode(car1, "YB72SW"));
		System.out.println("Printing doubly linked list");
		car1.printList(car1);
		
		GarageSet garageSet = new GarageSet();
		garageSet.setGarageHead(car1);
		garageSet.checkIn(1);
		garageSet.checkIn(2);

		System.out.println("Car checking in, printing set");
		System.out.println(garageSet.toString());
		
		System.out.println("\nCar checking out, printing set again");
		garageSet.checkOut(0);
		System.out.println(garageSet.toString());
		
		GarageExitBag exitBag = new GarageExitBag();
		System.out.println("\nGarage exit bag: ");
		System.out.println(GarageSet.bag.bagToString());
		

		//When the Exit option is used, do these things
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() 
		    {
		        try 
		        {
		        	GarageSet.saveGSData();
		        	GarageSet.bag.dumpOutputFile();
				} 
		        catch (FileNotFoundException e) 
		        {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}));
		
		

	} //End main method

} //End Main class
