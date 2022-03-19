package singmm01_CS161_lab1;

import java.util.Arrays;

/*
* Montana Singer
* CS161 - 01
* Spring 2019
* Lab 1
*
*/

public class Applications {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] unsortedArray = {5, 9, 2, 6, 1, 3, 10, 8, 4, 7};
		
		IntegerArrays.printArray(unsortedArray);
		
		//Apply the sortArray() method
		IntegerArrays.sortArray(unsortedArray);
		
		//Print sorted array
		IntegerArrays.printArray(unsortedArray);
		
		
		//for loop for k = 10
		for (int k = 0; k < 10; k++)
		{
			int[] generatedArray = IntegerArrays.generateArray(k);
			
			long startNanos10 = IntegerArrays.getNanos();
			long startMilis10 = IntegerArrays.getMillis();
			
			IntegerArrays.sortArray(generatedArray);
			
			long endNanos10 = IntegerArrays.getNanos();
			long endMilis10 = IntegerArrays.getMillis();
			
			System.out.println()
			
		}
		
		
		
		
		
	} //End main method

} //End Applications class
