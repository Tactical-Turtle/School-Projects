package singmm01_CS161_project2;

/*
 * Montana Singer
 * CS161 - 01
 * Spring 2019
 * Project 2
 *
 */

import java.util.*;

//This class is the logic of the game
public class Workshop {

	
	public String message;
	
	public boolean finished;
	
	public boolean[][] mirror;
	
	public final boolean[][] allFalse;
	
	public ArrayList<boolean[][]> history = new ArrayList<>();
	
	public int rows;
	public int columns;
	
	public final int minLifeToLife = 1;
	public final int maxLifeToLife = 4;
	
	public final int minDeadToLife = 1;
	public final int maxDeadToLife = 4;
	
	
	//Instantiates some of the fields
	public Workshop(int r, int col)
	{
		rows = r;
		columns = col;
		
		mirror = new boolean[r][col];
		allFalse = new boolean[r][col];
	}
	
	
	//Resets the mirror array
	public void resetMirror()
	{
		mirror = new boolean[rows][columns];
	}
	
	
	//Resets the history array
	public void resetHistory()
	{
		history.clear();
	}
	
	
	//Checks if a square is alive or dead and returns its value
	private boolean isAlive(int k, int j)
	{
		if (k > mirror.length || k < 0 ||
			j > mirror[k].length || j < 0)
		{
			return false;
		}
		
		else
		{
			return mirror[k][k];
		}
	}
	
	
	//Counts the number of neighboring entries a square has
	private int neighborCount(int k, int j)
	{
		int numberOfNeighbors = 0;
		
		for ( int i = k - 1; i <= k + 1 ; i++)
		{
		   for (int l = j - 1 ; l <= j + 1 ; l++)
		    {
		       try 
		       {
		         if (isAlive(i, l) == true && (i != k || l!=j))
		         {
		           numberOfNeighbors++;
		         }          
		       } 
		       
		       catch ( ArrayIndexOutOfBoundsException f)
		       {
		    	  continue;
		       }
		    }
		}
	
		
		return numberOfNeighbors;
	}
	
	
	//Checks if the 2d arrays have the same exact entries
	private boolean equals(boolean[][] arrA, boolean[][] arrB)
	{
		return Arrays.deepEquals(arrA, arrB);
	}
	
	
	//Returns the position of an index
	private int listPositionOf(boolean[][] target)
	{
		int counter = 1;
		
		for (int i = 0; i < history.size(); i++)
		{
			if (history.get(i) != target)
	        {
	        	counter++;
	        }
		}
		
		if (counter >= history.size())
		{
			return 0;
		}
		
		return counter;
		
	} //End listPositionOf method
	

	//Logic for creating the next generation of alive or dead squares
	public void nextGeneration()
	{
		history.add(mirror);
		
		finished = false;
		
		boolean[][] next = new boolean[rows][columns];
		
		for (int i = 0; i < mirror.length; i++)
		{
			for (int j = 0; j < mirror[i].length; j++)
				{
			      	//If the square is currently alive
					if (isAlive(i,j) == true)
					{
						//Remains alive if it has 2-3 living neighbors
						if (neighborCount(i, j) > minLifeToLife && neighborCount(i,j) < maxLifeToLife)
						{
							next[i][j] = true;
						}
						
						//Dies if it has fewer than 2 living neighbors
						if (neighborCount(i, j) <= minDeadToLife)
						{
							next[i][j] = false;
						}
						
						//Dies if it has 4 or more living neighbors
						if (neighborCount(i, j) >= maxDeadToLife)
						{
							next[i][j] = false;
						}
						
					} //End logic for if the square is alive
					
					//if the square is currently dead
					else
					{
						//Comes to life if it has exactly 2 or 3 living neighbors
						if (neighborCount(i, j) > minLifeToLife && neighborCount(i,j) < maxLifeToLife)
						{
							next[i][j] = true;
						}
						
						//Remains dead in all other cases
						else
						{
							next[i][j] = false;
						}
						
					}
			        
			    } //End inner for loop
			
		} //End for loop
		
		if (equals(next, allFalse) == true)
		{
			message = "After " + history.size() + " generations life is extinct in Island";
			
			mirror = next;
			finished = true;
			return;
		}
		
		else
		{
			int position;
			position = listPositionOf(next);
			
			if (position > 0)
			{
				message = "After " + (position-1) + " generations life is cyclic of length " + 
						  (history.size()-position+1);
				mirror = next;
				finished = true;
				return;
			}
			
			else
			{
				mirror = next;
			}
				
		}
		
	} //End nextGeneration method
	
} //End Workshop class
