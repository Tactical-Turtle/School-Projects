package lab05_MontanaSinger;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Order3Strings {

/*
* Montana Singer
* CS 160-06 Fall 2018
* Lab 5
*
*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	int yesNo = 0;
	int count = 0;	
	int answer;
	String names = null;	
	String name1, name2, name3;
	
//While loop to continue comparing strings if
//the user wants to
while (yesNo == JOptionPane.YES_OPTION)

{	
	
	//Show confirm dialog box to compare strings.
	answer = JOptionPane.showConfirmDialog(null, "Do you want to compare strings?",
				"3 Strings Comparison", JOptionPane.YES_NO_OPTION);
	
	
	
	
	//If the user selects NO to compare strings 
	//a message is displayed and the program terminates.
	if (answer == JOptionPane.NO_OPTION)
		{
		JOptionPane.showMessageDialog(null, "The program terminates!" + "\n" + "End of this program.", 
				                      "3 Strings Comparison", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
		}
	
	
	
	
	//If the user selects YES, the program continues.
	if (answer == JOptionPane.YES_OPTION)
		{
		
		//Initiate a string to store the user input.
		names = JOptionPane.showInputDialog(null, "Enter 3 names with blank(s) in between:", 
											"Welcome to the name ordering process", 
											JOptionPane.QUESTION_MESSAGE);
		
		//Counts the words.
		//This is used to ensure the string is more than 2 words.
		for(int i=0; i<names.length()-1; i++)
        {
            if(names.charAt(i)==' ' && names.charAt(i+1)!=' ')
                count++;
        }	
		
		
			//These if statements check if the user input is null, empty, or less than 2 words
			//and returns an error message and terminates the program if one of the conditions is true.
			if (names == null || names.equals("") || count < 2)
			{
				JOptionPane.showMessageDialog(null, "This program terminates for invalid input.",
					 					      "3 Strings Comparison", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			 
		}
		
	
	
	
	//Declare and initiate a Scanner class.
	Scanner splitter = new Scanner (names);
			
	//Divides the names string into 3 smaller strings.
	//Count also counts how many times the splitter.next() method is called
	//for use in terminating the program if less than 3 words are input.
	name1 = splitter.next();
	name2 = splitter.next();
	name3 = splitter.next();
			
	
	
	
	//Determine lexicographic order of the names.
	
	//Declare temporary strings to hold values for clarity purposes.
	String first = null;
	String second = null;
	String third = null;
	
	//Case 1: A, B, C
	if (name1.compareToIgnoreCase(name2) < 0 && name2.compareToIgnoreCase(name3) < 0)
	{
		first = name1;
		second = name2;
		third = name3;
	}
	
	//Case 2: A, C, B
	else if (name1.compareToIgnoreCase(name3) < 0 && name3.compareToIgnoreCase(name2) < 0)
	{
		//B & C swap
		first = name1;
		second = name3;	
		third = name2;
	}
	
	//Case 3: B, A, C
	else if (name2.compareToIgnoreCase(name1) < 0 && name2.compareToIgnoreCase(name3) < 0)
	{
		first = name2;
		second = name1;
		third = name3;
	}
	
	//Case 4: B, C, A
	else if (name2.compareToIgnoreCase(name3) < 0 && name3.compareToIgnoreCase(name1) < 0)
	{
		first = name2;
		second = name3;
		third = name1;
	}
	
	//Case 5: C, B, A
	else if (name3.compareToIgnoreCase(name2) < 0 && name2.compareToIgnoreCase(name1) < 0)
	{
		first = name3;
		second = name2;
		third = name1;
	}
	
	//Case 6: C, A, B
	else if (name3.compareToIgnoreCase(name1) < 0 && name1.compareToIgnoreCase(name2) < 0)
	{
		first = name3;
		second = name1;
		third = name2;
	}
	
	
	//Store the ordered variables in a single string.	
	String namesOrdered = first + " " + second + " " + third;
		
	//Print the ordered names in a message box
	JOptionPane.showMessageDialog(null, "X3: The names in lexicographic order are " + 
								  "\n" + namesOrdered, "The names in order", JOptionPane.INFORMATION_MESSAGE);
	
	yesNo = JOptionPane.showConfirmDialog(null, "Do you want to continue string comparison", "3 Strings Comparison",
										  JOptionPane.YES_NO_OPTION);
		
			
} //End while loop.

	if (yesNo == JOptionPane.NO_OPTION)
	{
		JOptionPane.showMessageDialog(null, "This program terminates!" + "\n" + "End of this program",
			      "3 Strings Comparison", JOptionPane.WARNING_MESSAGE);
	}
		
		
		System.exit(0);
	}

}
