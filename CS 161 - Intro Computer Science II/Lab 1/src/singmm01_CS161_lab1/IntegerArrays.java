package singmm01_CS161_lab1;

import java.util.Arrays;
import java.util.Random;

/*
* Montana Singer
* CS161 - 01
* Spring 2019
* Lab 1
*
*/

public class IntegerArrays {
	
	public static long getNanos()
	{
		return System.nanoTime();
	}
	
	
	public static long getMillis()
	{
		return System.currentTimeMillis();
	}
	
	
	public static int[] generateArray (int length)
	{
		//Declare array of int types values to given length parameter
		int[] numbers = new int[length];
		
		//Declare random variable
		Random random = new Random();
		
		//Fill array with random variables
		for (int index = 0; index < numbers.length; index++)
		{
			numbers[index] = random.nextInt();
		}
		
		//Return array
		return numbers;
		
	}
	
	private static int minIndex (int[] nums, int start)
	{
		int minIndex = start;
		
		for (int k = 0; start < nums.length; k++)
		{
			if (nums[k] < nums[minIndex])
			{
				minIndex = k;
			}
			
		} //End for loop
		
		return minIndex;
	}
	
	
	private static void swap (int[] arr, int k, int j)
	{
		//Swap the index k and index j elements of the arr array
		arr[k] = arr[j];
		arr[j] = arr[k];
	}
	
	
	public static void sortArray (int[] nums)
	{
		for (int z = 0; z < nums.length; z++)
		{
			int minIndexReturn = minIndex(nums, z);
			swap(nums, z, minIndexReturn);
		}
	}
	
	
	public static void printArray (int[] nums)
	{
	//	for (int x = 0; x < nums.length; x++)
	//	{
	//		System.out.println(nums[x]);
	//	}
		System.out.println(Arrays.toString(nums));
	}
	
	

} //End IntegerArrays class
