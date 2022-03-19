package lab02_MontanaSinger;
import javax.swing.JOptionPane;

/*
 * Montana Singer
 * CS 160-06 Fall 2018
 * Lab 2
 * 
 */

/*
 *Solve the programming challenge #4 Star Pattern at the end of Chapter 2
 *on page 106 of the text book with added requirements listed in the pdf 
 */

public class StarPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pattern = new String("*" + "\n" +
				"* " + "*" + "\n" + 
				"* " + "* " + "*" + "\n" + 
				"* " + "* " + "* " + "*" + "\n" +
				"  *" + " *" + " *" + "\n" +
				"    *" + " *" + "\n" +
				"      *");
		System.out.println("The size of the String is " + pattern.length());
		System.out.println(pattern);
		
		JOptionPane.showMessageDialog(null, "The number of characters in the pattern is 43");
		//displays the character length in a java messagebox
		JOptionPane.showMessageDialog(null, pattern);
		//displays the star pattern in a messagebox
		
		
		int asterisks;
		int numberOfColumns = 4;
		asterisks = numberOfColumns * numberOfColumns;		
		
		
		System.out.println("\n" + "\n" + "The Number of * symbols in the lines of the pattern are" + "\n" +
		"1, 2, 3, 4, 3, 2, 1" + "\n" +
		"The total number of * symbols in the pattern is:" + asterisks);

		JOptionPane.showMessageDialog(null, "The number of characters in the pattern are:");
		JOptionPane.showMessageDialog(null, "1, 2, 3, 4, 3, 2, 1");
		JOptionPane.showMessageDialog(null, "The total number of * symbols in the pattern is:" + asterisks);
		
		JOptionPane.showMessageDialog(null, "Check the answer?");
		JOptionPane.showInputDialog(null, "Enter the number of Columns for a star pattern:");
		JOptionPane.showMessageDialog(null,  "There are 4 columns in a star pattern has 16 stars.");
		
		System.exit(0);
		
	}

}
