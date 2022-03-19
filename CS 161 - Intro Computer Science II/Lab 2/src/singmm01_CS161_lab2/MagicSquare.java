package singmm01_CS161_lab2;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
* Montana Singer
* CS161
* Spring 2019
* Lab 2
*
*/

public class MagicSquare {
	
	
	//Verifies if a 2d int array is a magic square or not
	public static String isMagic(int[][] square)
	{
		int sum = 0;
		
		//Calculate the sum of diagonal
        int diagonalSum = 0; 
        for (int i = 0; i < square.length; i++) 
        {
        	diagonalSum = diagonalSum + square[i][i];
        }
        
        //Calculate sum of rows
        for (int i = 0; i < square.length; i++) 
        { 
            sum = 0; 
            for (int j = 0; j < square[i].length; j++) 
            {	
            	sum += square[i][j]; 
            }
            
            //Check if every row sum is equal to diagonal sum 
            if (sum != diagonalSum) 
            {	
                return "The array is not a magic square"; 
            }
       
        } //End for loop for row calculation
        
        //Calculate sum of Columns 
        for (int i = 0; i < square.length; i++) 
        { 
            int columnSum = 0; 
            for (int j = 0; j < square[i].length; j++) 
                columnSum += square[j][i]; 
  
            //Check if every column sum is equal to diagonal sum 
            if (diagonalSum != columnSum)
            {	
                return "The array is not a magic square"; 
            }   
        } //End for loop for column calculation
        
        return "The array is a magic square; the magic number it has is " + sum;
	
	} //End isMagic method
	
	
	
	//This method counts and returns the occurrences of a value for the array
	public static int countOccurrences(int[] arr, int target)
	{
		 int counter = 0;
		
		 int arraySize = arr.length;
		 for(int i = 0; i < arraySize; i++) 
		 {
			 if(arr[i] == target) 
			 {
				 counter++;
			 }
		 }
		  
		 return counter;
		
	} //End countOccurrences method
	
	
	//Returns the index of the greatest element in the array
	public static int findMaxIndex(int[] arr)
	{
		int maxIndex = 0;
		
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] > arr[maxIndex])
			{
				maxIndex = i;
			}
		}

		return maxIndex;
	}
		
		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] exampleSquare = { {16, 3, 2, 13}, 
								  {5, 10, 11, 8},
								  {9, 6, 7, 12}, 
								  {4, 15, 14, 1} };
		
		int[][] testSquare = { {1, 3, 5, 14}, 
							   {5, 12, 11, 8},
							   {8, 5, 7, 3}, 
							   {3, 15, 16, 10} };
		
		System.out.println(isMagic(exampleSquare));
		System.out.println(isMagic(testSquare));
		
		
		//Stores all quadruples
		int[][] table = new int[1820][4];
		
		int rowIndex = 0;
		
		//Load table array
		for (int k1 = 1; k1 < 14; k1++)
		{
			
			for (int k2 = 1 + k1; k2 < 15; k2++)
			{
				
				for (int k3 = 1 + k2; k3 < 16; k3++)
				{
					
					for (int k4 = 1 + k3; k4 < 17; k4++)
					{
						table[rowIndex][0] = k1;
						table[rowIndex][1] = k2;
						table[rowIndex][2] = k3;
						table[rowIndex][3] = k4;
						rowIndex++;
						
					} //End k4 for loop
					
				} //End k3 for loop
				
			} //End k2 for loop
			
		} //End k1 for loop
		
		System.out.println(Arrays.deepToString(table));
		System.out.println("This is the array loaded with all of the quadruples selected from 1-16\n");
		
		
		
		//Stores the sum of each row in the table array
		int[] sums = new int[table.length];
		
        for (int i = 0; i < table.length; i++) 
        { 
            sums[i] = 0;
            for (int j = 0; j < table[i].length; j++) 
            {	
            	sums[i] += table[i][j];
            }
        }
		
        
        
        
        int[][] occurrences = new int[2][49];
        
        for (int i = 0; i < occurrences.length; i++)
        {
        	for (int j = 0; j < occurrences[i].length; j++)
        	{
        		occurrences[0] = IntStream.rangeClosed(10, 58).toArray();
        		occurrences[1][j] = countOccurrences(sums, occurrences[0][j]);
        	}
        }
        
        System.out.println(Arrays.toString(occurrences[0]));
        System.out.println("This is all of the possible sum values of the table array\n");
        
        System.out.println(Arrays.toString(occurrences[1]));
        System.out.println("This is the number of times each of those possible sum values occurs");
        
        
        System.out.println("\nThe max element's index is " + findMaxIndex(occurrences[1]) + 
        				   ", its sum is " + occurrences[0][findMaxIndex(occurrences[1])] + 
        				   ", and it occurs " + occurrences[1][findMaxIndex(occurrences[1])] + " times");
        
        
        System.out.println("\nRows in the table where the sum is 34");
        System.out.println("\nindex\t row elements\n");
        
        for (int i = 0; i < sums.length; i++)
        {
        	if (sums[i] == 34)
        	{
        		System.out.println(i + "       " + Arrays.toString(table[i]));
        	}
        }
        
        
	} //End main method
		

} //End MagicSquare class
