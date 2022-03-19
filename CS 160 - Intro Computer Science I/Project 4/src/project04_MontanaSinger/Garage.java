package project04_MontanaSinger;

/*
 * Montana Singer
 * CS 160 – 07, Fall Semester 2018
 * Project 4: Parking Management
 * 
 * Description: 
 * The purpose of this class is to declare the cars array and
 * the time in of the index of the cars array
 *
 */
public class Garage {
	
	//An array to store Car objects
	//Instantiated in the Garage() constructor
	private Car[] cars; 
	
	//Accessor method
	public Car[] getCars()
	{
		return cars;
	
	} //End getCars method
	
	
	
	//Mutator method
	//Adds auto to the array at index
	public void setCars(Car auto, int index)
	{
		//Adds auto the array at index
		cars[index] = auto; 
	
	} //End setCars method
	
	
	
	public boolean isEmpty(int k)
	{
		
		return cars[k] == null;
	
	} //End isEmpty method
	
	
	
	public void displayState()
	{
		//Prints all instances of the cars array 
		//to the console in a single line
		for (int indexInitial = 0; indexInitial < cars.length; indexInitial++)
		{
			System.out.print(indexInitial + "   ");
		}
	
		System.out.println("\n");
		
		
		//Prints a letter E for empty (null)
		//Prints a letter C for non-empty (non-null)
		for (int indexEorC = 0; indexEorC < cars.length; indexEorC++)
			
			if (cars[indexEorC] == null)
			{
				System.out.print("E   ");
			}
			
			else
			{
				System.out.print("C   ");
			}
		
		System.out.println("\n");
	
	} //End displayState method
	
	
	
	public int park(Car auto)
	{
		
		int index = 0;
		int numberOfNonEmptyBays = 0;
		
			for ( ; index < getCars().length; index++)
			{
				//Calls isEmpty for each bay
				isEmpty(index);
			
					//Counts the number of non-empty bays 
					if (isEmpty(index) == true)
					{
						//Adds auto to the array at index
						cars[index] = auto;
						return index;
						
					}
			
					//First time an empty bay is found,
					else 
					{
						numberOfNonEmptyBays++;
					}

			} //End for loop
			
		
		//If the garage is full, returns -1
		if (numberOfNonEmptyBays == cars.length)
		{
			return -1;
		}
		
		return index;
	
	} //End park method
	
	
	
	public double remove(int index)
	{
		double elapsedTime;
		long departureTime;
		
		departureTime = System.currentTimeMillis();
		
		//Computes the elapsed parking time of the 
		//element of the cars array
		elapsedTime = departureTime - (cars[index].getTime());
		
		//Assigns null to the bay of index
		cars[index] = null;
		
		//returns elapsed time
		return elapsedTime;
		
	} //End remove method
	
	
	
	public int findBayOfCar(int carNumber)
	{	
		//Finds and returns the index of the bay where
		//a car with a randomly selected serial number is parked
		int index = -1;
		for ( ; index < carNumber;)
		{
			//update index
			index++;
			
			while (isEmpty(index))
			{
				//update index
				index++;
				
			} //End while loop
		
		} //End for loop
		
		return index;
	
	} //End findBayOfCar method
	
	
	
	public Garage(int capacity)
	{
		//Instantiates the cars array to length capacity
		cars = new Car[capacity];
	
	} //End Garage method
	
	
} //End Garage class
