package lab10_MontanaSinger;


/*
* Montana Singer
* CS160–07, Fall 2018
* Lab 10
* 
* The purpose of this class is to create the sender and printedMatter
* variables and compare Letter objects for equality
* 
*/

public class Letter {
	
	private String sender;
	private boolean printedMatter;
	
	
	
	//Initializer constructor
	public Letter(String send, boolean print)
	{
		sender = send;
		printedMatter = print;
	}
	
	
	
	//Copy constructor
	public Letter(Letter other)
	{
		this.sender = other.sender;
		this.printedMatter = other.printedMatter;
	}
	
	
	
	//Creates and returns a string message containing the field values 
	public String toString()
	{
		//Create string to hold the field values
		String messageLetter = sender + "\t\t" + printedMatter;
		
		//Return the string message
		return messageLetter;
	}
	
	
	
	//Compares 2 Letter objects for equality
	//other and this fields
	public boolean equals(Letter other)
	{
		return (other.sender.equals(this.sender) && other.printedMatter == this.printedMatter); 
	}

}
