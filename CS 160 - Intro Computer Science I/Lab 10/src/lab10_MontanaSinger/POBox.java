package lab10_MontanaSinger;

import java.util.Arrays;

/*
* Montana Singer
* CS160–07, Fall 2018
* Lab 10
* 
* The purpose of this class is to create the boxNumber and
* letters data fields. It also creates a copy constructor for POBox objects
* 
*/

public class POBox { 
	
	//Declare field values
	private int boxNumber;
	private Letter[] letters;
		
		
		//Accessor for the boxNumber fields
		public int getBoxNumber()
		{
			return boxNumber;
		}
		
		
		//Accessor for the letters field
		public Letter[] getLetters()
		{
			//Returns a copy of the letters array
			return Arrays.copyOf(letters, letters.length);
		}
		
		
		//Creates and returns a String message containing field values
		public String toString()
		{

			String messagePOBox = "boxNumber: " + boxNumber + "\n" + 
								  "sender" + "\t\t" + "printedMatter" + "\n\n";
			
			for (int index = 0; index < letters.length; index++)
			{
				 messagePOBox += (letters[index].toString()) + "\n";
			}
			
			return messagePOBox;

		}
		
		
		//Compares two POBox objects for equality
		//Uses the field values to check equality
		public boolean equals(POBox other)
		{
			return (other.boxNumber == this.boxNumber && other.letters == this.letters); 
		}
		
		
		//Default constructor 
		public POBox()
		{
			
		}
		
		
		//Initializer constructor
		public POBox(Letter[] lt, int bn)
		{
			letters = lt;
			boxNumber = bn;
		}
		
		
		//Copy constructor
		public POBox(POBox box)
		{
			this.letters = box.letters;
			this.boxNumber = box.boxNumber;
		}

} //End POBox class
