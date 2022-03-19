

package lab04_MontanaSinger;
import javax.swing.JOptionPane;

public class RomanNumerals {

/*
* Montana Singer
* CS 160-06 Fall 2018
* Lab 4
*
*/
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		int yesNo;
		int decimal = 0;
		String roman;
		
		String title = "Conversion of Roman numerals";
		String task = "Enter a Roman numeral between \"I\" and \"XV\" ";	
	

		
//This is simply used for the error loop
int error = 2;
	
//Marks the beginning of the looping sequence for the input
//of Roman numerals and also if the user wants to keep inputting more
//Roman numerals for conversion
do {
	
	//User input for the Roman numeral
	roman = JOptionPane.showInputDialog(null, task, title,
		JOptionPane.QUESTION_MESSAGE);
	
		//Begin loop for error message if input is not one 
		//of the 15 Roman numerals
		do {
		
			//Error message displays if the input is not one of the 
			//15 Roman numerals
			// (?i) refers to case insensitivity
			if (roman.matches("(?i)I|II|III|IV|V|VI|VII|VIII|IX|X|XI|XII|XIII|XIV|XV"))
			{
				error = 0;
			}
			else
				{
				error = 1;
				JOptionPane.showMessageDialog(null, "input " + roman + " is not an admissible Roman Numeral", title, 0);
				roman = JOptionPane.showInputDialog(null, task, title,
					JOptionPane.QUESTION_MESSAGE);
				}	

		}	while (error == 1);
		//End of loop for finding input error
	
	
	//Check if first input is I
	if(roman.charAt(0) == 'I' || roman.charAt(0) == 'i')	{	
		
		if(roman.equalsIgnoreCase("I"))
		{
			decimal = 1;
		}
			else if(roman.equalsIgnoreCase("II"))
		{
			decimal = 2;
		}
			else if(roman.equalsIgnoreCase("III"))
		{
			decimal = 3;
		}
			else if(roman.equalsIgnoreCase("IV"))
		{
			decimal = 4;
		}
			else if(roman.equalsIgnoreCase("IX"))
		{
			decimal = 9;	
		}
	
	}
	
	
	
	
	//Check if first input is V
		if(roman.charAt(0) == 'V' || roman.charAt(0) == 'v'){	
			
			if(roman.equalsIgnoreCase("V"))
			{
				decimal = 5;
			}
				else if(roman.equalsIgnoreCase("VI"))
			{
				decimal = 6;
			}
				else if(roman.equalsIgnoreCase("VII"))
			{
				decimal = 7;
			}
				else if(roman.equalsIgnoreCase("VIII"))
			{
				decimal = 8;
			}
			
		}
		
	
		
		
		//Check if first input is X
		if(roman.charAt(0) == 'X' || roman.charAt(0) == 'x')	{	
			
			if(roman.equalsIgnoreCase("X"))
			{
				decimal = 10;
			}
				else if(roman.equalsIgnoreCase("XI"))
			{
				decimal = 11;
			}
				else if(roman.equalsIgnoreCase("XII"))
			{
				decimal = 12;
			}
				else if(roman.equalsIgnoreCase("XIII"))
			{
				decimal = 13;
			}
				else if(roman.equalsIgnoreCase("XIV"))
			{
				decimal = 14;	
			}
				else if(roman.equalsIgnoreCase("XV"))
			{
					decimal = 15;
			}
			
		}
		
					
			
		//String containing the format for the display
		//of the Roman numeral and its decimal value
		String outTask = String.format("The decimal value " +
									   "for the Roman numeral " +
									   "\"" + roman.toUpperCase() + "\" is: " + "\n" +
									   ".......... " + decimal + " ..........");
		
		
		//Message box containing the value of the Roman numeral
		JOptionPane.showMessageDialog(null, outTask, title, JOptionPane.INFORMATION_MESSAGE);
		
		
		//Asks if the user wants to input more Roman numerals	
		yesNo = JOptionPane.showConfirmDialog(null, "Any more Roman Numerals? \nyes or no", title, 
				JOptionPane.YES_NO_OPTION);
		
		
} while (yesNo == 0);    

//Marks end of do-while loop



		//Displays the program ending if user says no more Roman numerals to input
		JOptionPane.showMessageDialog(null, "End of Program!", title, yesNo);


		System.exit(0);
		
	}
	
}
