package singmm01_CS161_lab4;

/*
* Montana Singer
* CS161
* Spring 2019
* Lab 4
*
*/ 

public class Person {

	String name;
	int yearOfBirth;
	
	//Setter method for name String
	public void setName(String passName)
	{
		name = passName;
	}
	
	//Getter method for name String
	public String getName()
	{
		return name;
	}
	
	//Setter method for year of birth int
	public void setYearOfBirth (int passYear)
	{
		yearOfBirth = passYear;
	}
	
	//Getter method for year of birth int
	public int getYearOfBirth()
	{
		return yearOfBirth;
	}
	
	//Initializer constructor
	public Person(String passName, int passYear)
	{
		name = passName;
		yearOfBirth = passYear;
	}
	
	//Default constructor
	public Person()
	{
		
	}
	
	//Displays content as string
	public String toString()
	{
		return "name: " + getName() + ";" + " year " + getYearOfBirth(); 
	}
	
	//Prints a message to console
	public void displayAccess()
	{
		System.out.println("displayAccess called from Person");
	}
	
	//Verifies if objects are equal or not
	public boolean equals(Object other)
	{
		if (other instanceof Person)
		{
			boolean compare = ((Person) other).name.equals(name) && ((Person) other).yearOfBirth == yearOfBirth;
			return compare;
		} 
		 
		else
		{
			return false;
		}
	}
	
	
	
} //End Person Class
