package singmm01_CS161_lab4;


/*
* Montana Singer
* CS161
* Spring 2019
* Lab 4
*
*/

//Extends the Person class
public class Employee extends Person {
	
	int hours;
	double wage;
	
	//Default constructor
	public Employee()
	{
		
	}
	
	//Initializes the hours and wage fields
	public Employee(int initializeHours, double initializeWage)
	{
		hours = initializeHours;
		wage = initializeWage;
	}
	
	//Overrides the toString method of the superclass
	@Override
	public String toString()
	{
		return super.toString() + "\nhours is " + hours + "\nwage is " + wage;
	}
	
	//Calculates and returns employees' earnings
	public double earnings()
	{
		return hours * wage; 
	}
	
	//Overrides the displayAccess() method of the Person class
	@Override
	public void displayAccess()
	{
		System.out.println("displayAccess called from Employee");
	}
	
	//Overrides the equals() method of the Person class
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Employee)
		{ 
			boolean hoursWageAndSuper = ((Employee) other).hours == hours 
									 && ((Employee) other).wage == wage 
									 && super.equals(other);
			return hoursWageAndSuper;
		}
		 
		else
		{
			return false;
		}
	}
	
	
	
	

} //End Employee Class
