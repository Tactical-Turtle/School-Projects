package project04_MontanaSinger;

/*
 * Montana Singer
 * CS 160 – 07, Fall Semester 2018
 * Project 4: Parking Management
 * 
 * Description: 
 * The purpose of this class is to assign the capacity and limit
 * of the garage. It also instantiates a manager.
 *
 */
public class Application {

	public static void main(String[] args) {
		
		//Stores garage capacity and the limit of iterations
		int garageCapacity = 15;
		int limitOfIterations = 15;
		
		//Declare and instantiate a Garage object with the given capacity
		Garage garage = new Garage(garageCapacity);
		
		//Set up a counter variable
		int counter = 0;
		
		//For loop to visit all parking bays 
		for ( ; counter < garage.getCars().length;)
		{
			//Boolean to enable the probability of 50%
			boolean coinFlip = Math.random() < 0.5;
			
			if (coinFlip == true)
			{
				//At each bay a new Car object is added to the bay with 50% probability
				garage.setCars(new Car(), counter);
				
			}
			
			else 
			{
				garage.setCars(null, counter);
			}

			counter++;
			
		} //End for loop
		
		//Instantiates a Manager object with garage and counter parameters
		Manager manager = new Manager(garage, counter);
		
		//Object calls processParking() with parameter limit
		manager.processParking(limitOfIterations);
		
	} //End main method

} //End Application class
