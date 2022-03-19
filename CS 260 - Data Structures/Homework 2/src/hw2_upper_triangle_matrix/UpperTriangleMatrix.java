package hw2_upper_triangle_matrix;

import java.util.Arrays;

/*
 * HW 2 - CS260
 * Montana Singer
 * Fall 2019
 * 
 */

public class UpperTriangleMatrix implements Cloneable{
	
	private final int rows;
	private final int columns;
	private final String[][] dataArray;
	private final String[] elementArray;
	
	
	//Constructor
	public UpperTriangleMatrix(String[][] data)
	{
		rows = data.length;
		columns = data[0].length;
		dataArray = data;
		
		elementArray = new String[(rows * (rows + 1)) / 2];
	}
	
	
	//Sets the element with the corresponding string
	public void setElement(int row, int col, String data)
	{
		if (row <= col)
		{
			elementArray[row * (row + 1) / 2 + col] = data;
		}
		
		else if (data != null)
		{
			throw new IllegalArgumentException("Element is not equal to zero");
		}
		
	}
	
	
	//Returns the string at the location
	public String getElement(int row, int col)
	{
		if (row > col)
		{
			return null;
		}
		
		else
		{	
			return elementArray[row * (row + 1) / 2 + col];
		}	
	}
	
	
	//Displays a UTM in string format
	public String toString()
	{
		return Arrays.deepToString(dataArray).replace("], ", "]\n");    
	}
	
	
	//Clones a UTM and returns it
	public UpperTriangleMatrix clone() 
	{
		UpperTriangleMatrix copy;
		
		try 
		{
			copy = (UpperTriangleMatrix) super.clone();
			
		    for(int i = 0; i < this.dataArray.length; i++) 
		    {
		        for(int j = 0; j < this.dataArray[i].length; j++) 
		        {
		        	copy.dataArray[i][j] = this.dataArray[i][j];
		        }
		    }
		    
		} 
		
		catch (CloneNotSupportedException e) 
		{
			throw new RuntimeException ("Unable to clone");
		}
		
		return copy;
	}
	
	
	public static UpperTriangleMatrix concatenate(UpperTriangleMatrix a, UpperTriangleMatrix b)
	{
		UpperTriangleMatrix combined = new UpperTriangleMatrix(a.dataArray);
		
	    for(int i = 0; i < a.dataArray.length; i++) 
	    {
	        for(int j = 0; j < a.dataArray[i].length; j++) 
	        {
	        	if (a.dataArray[i][j] != null || b.dataArray[i][j] != null)
	        	{
	        		combined.dataArray[i][j] = (a.dataArray[i][j] + " " + b.dataArray[i][j]);
	        	}
	        }
	    }
	    
		return combined;	
	}
	
	
	public static UpperTriangleMatrix largestCombo(UpperTriangleMatrix a, UpperTriangleMatrix b)
	{
		UpperTriangleMatrix largestStringMatrix = new UpperTriangleMatrix(a.dataArray);
		
	    for(int i = 0; i < a.dataArray.length; i++) 
	    {
	        for(int j = 0; j < a.dataArray[i].length; j++) 
	        {
	        	
	        		if (a.dataArray[i][j] != null || b.dataArray[i][j] != null)
	        		{
	        			if (a.dataArray[i][j].length() > b.dataArray[i][j].length())
	        			{
	        				largestStringMatrix.dataArray[i][j] = a.dataArray[i][j];
	        			}
	        		
	        			else 
	        			{
	        				largestStringMatrix.dataArray[i][j] = b.dataArray[i][j];
	        			}

	        		}	
	        }
	    }
	    
	    return largestStringMatrix;
	}
	
	
	public boolean equals(Object obj)
	{
		if (obj instanceof UpperTriangleMatrix)
		{
//			return obj.equals(this.dataArray);
			
			UpperTriangleMatrix a = this;
			UpperTriangleMatrix b = (UpperTriangleMatrix) obj;
			
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < columns; j++)
				{
					if (a.dataArray[i][j] != b.dataArray[i][j])
					{
						return false;
					} 
					
				}
			}
			
			return true;
		}
		
		else
			return false;
		
	} //End equals method
	
	
	
	
	
	public static void main(String[] args) throws CloneNotSupportedException 
	{
		String[][] matrix1 = new String[][] 
				   {{"Hello", "Hello", "Hello"},
				    {null, "Hello", "Hello"},
				    {null, null, "Hello"}, };
				
		String[][] matrix2 = new String[][] 
				   {{"Exotic", "Mug", "Adaptable"},
				    {null, "Yard", "Huge"},
				    {null, null, "End"}, };
				    
		String[][] matrix11 = new String[][] 
					{{"Hello", "Hello", "Hello"},
					{null, "Hello", "Hello"},
					{null, null, "Hello"}, };	
					
		String[][] matrix22 = new String[][] 
					{{"Exotic", "Mug", "Adaptable"},
					{null, "Yard", "Huge"},
					{null, null, "End"}, };					    
		
		
	    UpperTriangleMatrix test1 = new UpperTriangleMatrix(matrix1);
		UpperTriangleMatrix test2 = new UpperTriangleMatrix(matrix2);
		
	    UpperTriangleMatrix test11 = new UpperTriangleMatrix(matrix11);
		UpperTriangleMatrix test22 = new UpperTriangleMatrix(matrix22);
		
	    for(int i = 0; i < test1.dataArray.length; i++) 
	    {
	        for(int j = 0; j < test1.dataArray[i].length; j++) 
	        {
	            test1.setElement(i, j, test1.dataArray[i][j]);
	        }
	    }
	    
	    for(int i = 0; i < test2.dataArray.length; i++) 
	    {
	        for(int j = 0; j < test2.dataArray[i].length; j++) 
	        {
	            test2.setElement(i, j, test2.dataArray[i][j]);
	        }
	    }
		
		System.out.println("Printing test1 1D array " + Arrays.toString(test1.elementArray));
		System.out.println("\nPrinting test1 1D array " + Arrays.toString(test2.elementArray));
	    
	    System.out.println("\nPrinting test1");
		System.out.println(test1.toString());
		System.out.println("\nPrinting test2");
		System.out.println(test2.toString());
		
		
		UpperTriangleMatrix copyTest = test2.clone();
		System.out.println("\nCopying test2 and printing copy");
		System.out.println(copyTest.toString());

		
		UpperTriangleMatrix concatTest;
		System.out.println("\nTesting concatenation");
		concatTest = concatenate(test1, test2);
		System.out.println(concatTest.toString());
	
		
		System.out.println("\nTesting largestCombo");
		System.out.println(largestCombo(test11, test22).toString());
		
		
		System.out.println("\nTesting equals method");
		System.out.println(test1.equals(test2));
		System.out.println(test1.equals(test1));
		System.out.println(test2.equals(test22));
		
		
		//Changing matrix2 and updating it to test2 to check for a deep copy
		matrix2 = new String[][] 
				   {{"Exotic", "Mug", "Adaptable"},
				    {null, "ranomasdmasd", "Huge"},
				    {null, null, "End"}, };
				    
		test2 = new UpperTriangleMatrix(matrix2);		    
				    
		System.out.println("\nTesting if copy method is a deep copy. Printing copyTest");
		System.out.println(copyTest.toString());


	} //End main method

} //End UpperTriangleMatrix class


