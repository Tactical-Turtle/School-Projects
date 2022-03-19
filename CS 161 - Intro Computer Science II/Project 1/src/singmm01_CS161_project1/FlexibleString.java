package singmm01_CS161_project1;

import java.util.ArrayList;
import java.util.Collection;

/*
* Montana Singer
* CS161 - 01
* Spring 2019
* Project 1
*
*/

//This class acts a custom StringBuilder class that can edit strings
public class FlexibleString {
	
	ArrayList<Character> list;
	
	//Initializes list
	public FlexibleString(ArrayList<Character> list)
	{
		this.list = list;
	}
	
	//Instantiates the list field to the length of the parameter
	//Adds the characters of text to the ArrayList
	public FlexibleString(String text)
	{
		int length = text.length();
		list = new ArrayList<Character>(length);
		
		for (int i = 0; i < length; i++)
		{
			list.add(text.charAt(i));
		}
	}
	
	
	//Instantiates the field list with an enhanced for loop
	public FlexibleString(char[] text)
	{
		int length = text.length;
		
		list = new ArrayList<Character>(length);
		
		for (int i = 0; i < length; i++)
		{
			list.add(i, text[i]);
		}
	}
	
	
	//Copy constructor
	public FlexibleString(FlexibleString other)
	{
		list = other.list;
		int length = other.list.size();
		for (int i = 0; i < length; i++) 
		{
			list.add(new Character(other.list.get(i)));
		}
	}
	
	//Instantiates the field to the length size
	public FlexibleString(int size)
	{
		list = new ArrayList<Character>(size);
	}
	
	
	//Default constructor
	public FlexibleString()
	{
		
	}
	
	
	//Loads field elements into a char array and returns result
	public char[] toArray()
	{
		char[] charArray = new char[list.size()];
		
		for (int i = 0; i < charArray.length; i++)
		{
			charArray[i] = list.get(i);
		}
		
		return charArray;
	}
	
	
	//Converts character sequence into a string
	public String toString()
	{
		return String.valueOf(toArray());
	}
	
	
	//Equals method compares to FleixbleString objects
	public boolean equals(FlexibleString other)
	{
		return list.equals(other.list);
	} 
	
	
	//Adds the character parameter to list
	public void append(char item) 
	{
		list.add(item);
	}
	
	
	//Adds the FlexibleString parameter to list
	public void append(FlexibleString item)
	{
		list.addAll(item.list);
	}
	
	
	//Adds the String parameter to list
	public void append(String item)
	{
		FlexibleString test = new FlexibleString(item);
		list.addAll(test.list);
	}
	
	
	//Adds the char array parameter to list
	public void append(char[] item)
	{	
		FlexibleString test = new FlexibleString(item);
		list.addAll(test.list);
	}
	
	
	//Inserts the character parameter to list at the position
	public void insert(int position, char item) 
	{
		if (position >= 0 && position < list.size())
		{
			list.add(position, item);
		}
		else
		{
			list.add(item);
		}
	}
	
	
	//Inserts the FlexibleString parameter to list at the position
	public void insert(int position, FlexibleString item)
	{
		if (position >= 0 && position < list.size())
		{
			FlexibleString test = new FlexibleString(item);
			list.addAll(position, test.list);
		}
		else
		{
			
		}
	}
	
	
	//Inserts the String parameter to list at the position
	public void insert(int position, String item)
	{
		if (position >= 0 && position < list.size())
		{
			FlexibleString test = new FlexibleString(item);
			list.addAll(position, test.list);
		}
		
		else
		{
			
		}
	}
	
	
	//Inserts the char array parameter to list at the position
	public void insert(int position, char[] item)
	{
		if (position >= 0 && position < list.size())
		{
			FlexibleString test = new FlexibleString(item);
			list.addAll(position, test.list);
		}
		else
		{
			
		}
	}
	
	
	//Inserts the Character ArrayList parameter to list at the position
	public void insert(int position, ArrayList<Character> item)
	{
		if (position >= 0 && position < list.size())
		{
			list.addAll(position, item);
		}
		else
		{
			
		}
	}
	
	
	//Deletes the elements from start to end index from list
	public void delete(int start, int end)
	{
		int listSize = list.size();
		
		if (start >= 0 && end < listSize)
		{
			for (int i = start; i <= end; i++)
			{
				list.remove(start);
			}
		}

		if (start < 0)
		{
			return;
		}
		
		if (end >= listSize)
		{
			for (int i = start; i < listSize; i++)
			{
				list.remove(start);
			}
		}
		
	}
	
	
	//Deletes the character at parameter position from list
	public void deleteCharAt(int position)
	{
		list.remove(position);
		
		if (position < 0)
		{
			return;
		}
	}
	
	
	//Deletes all the parameter character elements from start to end index from list
	public void deleteAll(int start, int end, char item)
	{	
		if (start >= 0 && end < list.size())
		{
			for (int i = start; i <= end; i++)
			{
				if (list.get(i) == (item))
				{
					list.remove(i);
					i = i-1;
					end = end-1;
				}
			}
		}

		if (start < 0)
		{
			return;
		}
		
		if (end >= list.size())
		{
			for (int i = start; i < list.size(); i++)
			{
				if (list.get(i) == (item))
				{
					list.remove(i);
					i = i-1;
					end = end-1;
				}
			}
		}
	}
	
	
	//Replaces the element at position with a character
	public void replace(int position, char item)
	{
		if (position >= 0 && position < list.size())
		{
			list.set(position, item);
		}
		
		else
		{
			return;
		}
	}
	
	
	//Replaces the elements from start to end with a FlexibleString item
	public void replace(int start, int end, FlexibleString item)
	{
		if (start >= 0 && end < list.size())
		{
			for (int i = start; i <= end; i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}

				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		}
		
		if (start < 0)
		{
			return;
		}
		
		if (end > list.size())
		{
			for (int i = start; i < list.size(); i++)
			{
				list.remove(i);
			}
			
				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		
		}
	}
	
	
	//Replaces the elements from start to end with a String item
	public void replace(int start, int end, String item)
	{
		if (start >= 0 && end < list.size())
		{
			for (int i = start; i <= end; i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}

				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		}
		
		if (start < 0)
		{
			return;
		}
		
		if (end > list.size())
		{
			for (int i = start; i < list.size(); i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}
			
				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		
		}
	}
	
	
	//Replaces the elements from start to end with a char array item
	public void replace(int start, int end, char[] item)
	{
		if (start >= 0 && end < list.size())
		{
			for (int i = start; i <= end; i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}

				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		}
		
		if (start < 0)
		{
			return;
		}
		
		if (end > list.size())
		{
			for (int i = start; i < list.size(); i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}
			
				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					this.append(item);
				}
		
		}
	}
	
	
	//Replaces the elements from start to end with a Character ArrayList item
	public void replace(int start, int end, ArrayList<Character> item)
	{
		if (start >= 0 && end < list.size())
		{
			for (int i = start; i <= end; i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}

				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					list.addAll(item);
				}
		}
		
		if (start < 0)
		{
			return;
		}
		
		if (end > list.size())
		{
			for (int i = start; i < list.size(); i++)
			{
				list.remove(i);
				i = i-1;
				end = end-1;
			}
			
				if (start > 0 && end < list.size())
				{
					FlexibleString test = new FlexibleString(item);
					list.addAll(start, test.list);
				}
				
				else
				{
					list.addAll(item);
				}
		
		}
	}
	
	
	//Returns the character at index: position
	public char charAt(int position)
	{
		if (position >= 0 && position < list.size())
		{
			return list.get(position);
		}
		
		else
		{
			return '0';
		}
		
	}
	
	
	//Returns the index of the char: target
	public int indexOf(char target)
	{
		return list.toString().indexOf(target);
	}
	
	
	//Returns the index of the String: target
	public int indexOf(String target)
	{
		return list.toString().indexOf(target);
	}
	

} //End FlexibleString class

