package diningPhilosophers;

/*
 * Montana Singer
 * CS 472
 * Spring 2021
 * Project 2 - Dining Philosophers solution
 * 
 */

/*
 * This class contains the main method and instantiates
 * the array of Philosophers in which the operations are performed.
 * Also calculates the min, max, and average meals eaten.
 * 
 * The amount of time the program runs for can be changed in
 * Philosopher class in the run() method
 */
public class DiningPhilosophers 
{
	
	public static void main(String[] args) 
	{		
		DiningServer server = new DiningServerControl();
		
		// Create array of 5 Philosopher objects
		Philosopher[] pArray = new Philosopher[DiningServerControl.NUM_OF_PHILS];
	     
	    // Assign a thread for each Philosopher
	    for (int i = 0; i < DiningServerControl.NUM_OF_PHILS; i++)
	    {
	    	pArray[i] = new Philosopher(server,i);
	    }
	     
	     // Start Philosopher threads
	     for (int i = 0; i < DiningServerControl.NUM_OF_PHILS; i++)
	     {
	    	 new Thread(pArray[i]).start();
	     }
	     
	     long minMeals = Long.MAX_VALUE;
	     long maxMeals = 0;
	     double avgMeals = 0;
	     
	     // Display min, max, and average meals eaten
	     for (int i = 0; i < DiningServerControl.NUM_OF_PHILS; i++)
	     {
	    	 System.out.println("Philosopher " + i + " meals eaten: " + pArray[i].numberOfMeals);
	    	 
	    	 if (pArray[i].numberOfMeals > maxMeals)
	    	 {
	    		 maxMeals = pArray[i].numberOfMeals;
	    	 }
	    	 
	    	 if (pArray[i].numberOfMeals < minMeals)
	    	 {
	    		 minMeals = pArray[i].numberOfMeals;
	    	 }
	    	 
	    	 avgMeals = avgMeals + pArray[i].numberOfMeals;
	     }
	     
	     avgMeals = avgMeals / DiningServerControl.NUM_OF_PHILS;
	     
	     System.out.println("Max meals: " + maxMeals);
	     System.out.println("Min meals: " + minMeals);
	     System.out.printf("Avg meals: %.3f", avgMeals);
	     
	} // End main method

} // End DiningPhilosophers class
