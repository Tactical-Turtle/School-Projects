package singmm01_CS161_lab7;

import java.util.*;

/*
 * Montana Singer
 * CS161 - 01
 * Spring 2019
 * Lab 7
 *
 */

public class Client {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter a double for side 'a'");
		double a = keyboard.nextDouble();
		
		System.out.println("Enter a double for side 'b'");
		double b = keyboard.nextDouble();
		
		System.out.println("Enter a double for side 'c'");
		double c = keyboard.nextDouble();
		
		keyboard.close();
		
		
		Triangle triangle;
		
		try
		{
			triangle = new Triangle(a, b, c);
			System.out.println("The area of the triangle is " + triangle.computeArea());
		}
		catch(IllegalTriangleException e)
		{
			System.out.println("First attempt failed with sides: " + a + " " + b + " " + c);
			System.out.println(IllegalTriangleException.message);
			
			try
			{
				triangle = new Triangle(a, a, b);
				System.out.println("The area of the triangle is " + triangle.computeArea());
			}
			catch(IllegalTriangleException e2)
			{
				System.out.println("Second attempt failed with sides: " + a + " " + a + " " + b);
				System.out.println(IllegalTriangleException.message);
			}
		}
		
		finally
		{
			try
			{
				triangle = new Triangle(a, a, a);
				System.out.println("The area of the triangle is " + triangle.computeArea());
			}
			catch(IllegalTriangleException e3)
			{
				
			}
				
				finally
				{
					System.out.println("Sucess!");
				}
		}

	} //End main method

} //End Client class
