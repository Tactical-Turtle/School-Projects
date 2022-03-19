package project04_MontanaSinger;

import javax.swing.JOptionPane;

import java.util.Random;

/*
 * Montana Singer
 * CS 160 – 07, Fall Semester 2018
 * Project 4: Parking Management
 * 
 * Description: 
 * The purpose of this class is to know the state of the garage and
 * can communicate with the garage class.
 *
 */
public class Manager {
	
	//A Garage type reference variable
	Garage garage;
	
	//constant for hourly parking
	final double FEE_PER_HOUR = 1.50;
	
	//Stores the running total of collected fees
	double feeTotal;
	
	//Stores the number of cars PARKED in the garage
	int manyCars;
	
	
	public void parkACar()
	{
		//Calls the park method of the garage class
		//Return value is stored in local variable index
		int index = garage.park(new Car());
		
		if (index != -1) 
		{
			//Displays figure 4
			JOptionPane.showMessageDialog(null, "A car is arriving at bay #" + index + ". \n" +
										        "Garage displayed on console.");
			//Update many cars
			manyCars++;
			
			//Call displayState() method
			garage.displayState();
			
		}
		
		
		else if (index == -1)
		{
			//Display figure 5
			JOptionPane.showMessageDialog(null, "The garge is full." + "\n" + "The parking process terminates", 
										  "Parking Management", JOptionPane.WARNING_MESSAGE);
			
			//Display figure 7
			System.out.println("Total parking fee collected is " + String.format("%.2f" , feeTotal));
			
			//Terminate program
			System.exit(0);

		}
		
	} //End parkACar method
	
	
	
	public void chooseACarToLeave()
	{
		//If garage is empty, display figure 6 and 8 and terminate program
		if (manyCars == 0)
		{
			//Figure 6
			JOptionPane.showMessageDialog(null, "The garge is empty." + "\n" + "The parking process terminates", 
					  					  "Parking Management", JOptionPane.WARNING_MESSAGE);
			
			//Figure 8
			System.out.println("After " + manyCars + " parking operations the process is terminated");
			
			//Terminate program
			System.exit(0);
		}
		
		else
		{
			Random random = new Random();
			
			//Select a random number for the amount of cars there are
			//ie if manyCars = 10, it generates from 1 to 10
			int randomNumber = random.nextInt(manyCars) + 1;
			
			//Call the method
			//Store index return value in index 
			int index = garage.findBayOfCar(randomNumber);
			
			//Call the method with index as parameter
			//It recieves the elapsed time
			//Store elapsed time in double elapsedTime
			double elapsedTime = garage.remove(index);
			
			//Computes the fee
			double fee = FEE_PER_HOUR * (elapsedTime) / 1000.00;
			
			//Updates feeTotal
			feeTotal = feeTotal + fee;
			
			//Displays figure 3
			JOptionPane.showMessageDialog(null, "The car from bay #" + index + " is leaving the garage." + "\n" +
										        "Parking fee paid: $" + String.format("%.2f", fee));
			
			//displayState is called
			garage.displayState();
			
			//manyCars is updated
			manyCars = manyCars - 1;
			
		}
		
	} //End chooseACarToLeave method
	
	
	
	public void processParking(int limit)
	{
		//Displays the welcoming message
		JOptionPane.showMessageDialog(null, "Welcome to the garage parking simulation!" + "\n" +
											"See the initial state of the garage on the console", 
											"Parking Management", JOptionPane.WARNING_MESSAGE);
		
		
		//Calls displayState()
		garage.displayState();
		
		//Runs a for loop to limit
		for (int index = 0; index < limit; index++) 
		{
			
			//At each iteration either park or leave a car 
			//methods are called, each with probability of 50%
			boolean coinFlip = Math.random() < 0.5;
			
			if (coinFlip == true)
			{
				parkACar();
			}
			
			else 
			{
				chooseACarToLeave();
			}
			
		} //End for loop
		
		
		//Display figure 7
		System.out.println("Total parking fee collected is $" + (String.format("%.2f", feeTotal)));
		
		//Display figure 8
		System.out.println("After " + limit + " parking operatiosn the process is terminated");
		
	} //End processParking method
	
	
	
	public Manager(Garage gar, int many)
	{
		//Initializes garage
		garage = gar;
		
		//Initializes manyCars
		manyCars = many;
	
	} //End Manager method
	
	
} //End Manager class
