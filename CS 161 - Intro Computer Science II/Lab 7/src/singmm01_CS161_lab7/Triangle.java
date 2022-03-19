package singmm01_CS161_lab7;

/*
 * Montana Singer
 * CS161 - 01
 * Spring 2019
 * Lab 7
 *
 */

//
public class Triangle {
	
	private double a;
	private double b;
	private double c;
	
	public double getA()
	{
		return a;
	}
	
	public double getB()
	{
		return b;
	}
	
	public double getC()
	{
		return c;
	}
	
	public Triangle(double passA, double passB, double passC)
	{
		a = Math.abs(passA);
		b = Math.abs(passB);
		c = Math.abs(passC);	
	}
	
	
	//
	public double computeArea() throws IllegalTriangleException
	{
		double area = 0;
		
		double s;
		s = (a+b+c)/2;
		
		area = Math.sqrt( s*(s-a)*(s-b)*(s-c) );
		
		if (s-a < 0)
		{
			throw new IllegalTriangleException();
		}
		
		if (s-b < 0)
		{
			throw new IllegalTriangleException();
		}
		
		if (s-c < 0)
		{
			throw new IllegalTriangleException();
		}
		
		return area;
	}
	
	

} //End Triangle class
