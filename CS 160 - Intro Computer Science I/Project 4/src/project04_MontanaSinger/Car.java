package project04_MontanaSinger;

/*
 * Montana Singer
 * CS 160 – 07, Fall Semester 2018
 * Project 4: Parking Management
 * 
 * Description: 
 * The purpose of this class is to control the logic of the garage
 * and thus what the cars will do.
 *
 */

public class Car {
	
	//Stores the arrival time
	//Value assigned in the Car() constructor
	public long timeIn;
	
	//Returns the time in
	public long getTime()
	{
		return timeIn;
	}
	
	//Calls currentTimeMillis() to initialize the field timeIn
	public Car()
	{
		timeIn = System.currentTimeMillis();
	}
	
} //End Car class
