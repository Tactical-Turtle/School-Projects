package singmm01_CS161_lab7;

/*
 * Montana Singer
 * CS161 - 01
 * Spring 2019
 * Lab 7
 *
 */ 

public class IllegalTriangleException extends Exception{
	
	static final String message = "Triangle inequality violated";
	
	
	//Getter for message
	public String getMessage()
	{
		return message;
	}
	
	
	//Default constructor
	public IllegalTriangleException()
	{
		super(message);
	}
	
	
	//Another constructor if 3rd message is needed
	public IllegalTriangleException(String pass)
	{
		super(pass);
	}
	
	

} //End IllegalTriangleException class
