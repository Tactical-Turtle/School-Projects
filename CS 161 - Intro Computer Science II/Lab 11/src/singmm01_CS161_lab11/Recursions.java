package singmm01_CS161_lab11;


/*
* Montana Singer
* CS 161
* Spring 2019
* Lab 11
*
*/


//This class has 2 recursive methods that print a number
//vertically and backwards
public class Recursions {

	public static void main(String[] args) {
			
		System.out.println("Printing 81384922 vertically");
		printVertical(81384922L);
		
		System.out.println("\nPrinting 24576218 backwards");
		printBackwards(24576218L);
		
		System.out.println("\n\nPrinting -38436 vertically");
		printVertical(-38436L);
		
		System.out.println("\n\nPrinting -78135 backwards");
		printBackwards(-78135L);
		
		System.out.println("\n\nPrinting -5 vertically");
		printVertical(-5L);
		
		System.out.println("\nPrinting -9 backwards");
		printBackwards(-9L);
		
		
	} //End main method
	
	
	//Prints a number vertically
	public static void printVertical(long parameterVertical)
	{
        
        boolean isSingleDigit = (parameterVertical > 0 && parameterVertical < 10) 
        					 || (parameterVertical < 0 && parameterVertical > -10);
        
        if (parameterVertical < 0)
        {
        	System.out.println("-");
        	printVertical(Math.abs(parameterVertical));
        }
        
        else if (isSingleDigit == true) 
        {
            System.out.println(parameterVertical);
            return;
        } 
       
        else 
        {
        	printVertical(parameterVertical / 10);
            System.out.println(parameterVertical % 10);
        }
    
	} //End printVertical method
	
	
	//Prints a number backwards
	public static void printBackwards(long parameterBackwards)
	{
		
        boolean isSingleDigit = (parameterBackwards > 0 && parameterBackwards < 10) 
        					 || (parameterBackwards < 0 && parameterBackwards > -10);
        
		
		if (parameterBackwards < 0)
		{
			printBackwards(Math.abs(parameterBackwards));
			System.out.print("-");
		}
		
		else if (isSingleDigit == true)
		{
			System.out.print(parameterBackwards);
			return;
		}
		
		else
		{
			System.out.print(parameterBackwards % 10);
			printBackwards(parameterBackwards/10);
		}
		
	} //End printBackwards method

} //End Recursions class
