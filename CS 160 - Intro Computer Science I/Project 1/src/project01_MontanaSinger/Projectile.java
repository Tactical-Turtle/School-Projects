package project01_MontanaSinger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Montana Singer
 * CS 160 – 06, Fall Semester 2018
 * Project 1: Compute the Projectile Motion
 *
 * Description. The purpose of this project is to become familiar with Java syntax and the various applications
 * of coding in Java. It also gives insight into the developmental process for creating a variety of things such as software or applications
 *
 */

/*
 	distance to target: 550 feet
	initial velocity: 150.0 feet/sec
	launch angle: 45.0 degrees
	flight time: 6.63 seconds
	maximum height: 175.78 feet
	distance traveled: 703.13 feet
	target missed by: 153.13 feet
 */

public class Projectile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//constant of gravitational acceleration
		final int GRAVITATION = 32;
		
		//user inputs values
		String inputDistanceToTarget = JOptionPane.showInputDialog(null, "Enter distance to target in feet:"); 											  
		final double distanceToTarget = Double.parseDouble(inputDistanceToTarget);
		
		String inputInitialVelocity = JOptionPane.showInputDialog(null, "Enter initial velocity in feet/sec:");											  
		double initialVelocity = Double.parseDouble(inputInitialVelocity);
		
		String inputLaunchAngle = JOptionPane.showInputDialog(null, "Enter the launch angle in degrees:");
		double launchAngle = Double.parseDouble(inputLaunchAngle);
		
		
		//declare and assign the double radian
		//converts launchAngle to radians
		//the double "radian" essentially replaces launchAngle when used in formulas
		double radian = launchAngle * Math.PI / 180;
		
		
		//declare the variables
		double flightTime;
		
		double highestPoint;
		
		double distanceTraveled;
		
		double error; 
		
		double  minError;
		
		
		//Assign the variables the formulas for the first input/attempt
		flightTime = (2 * initialVelocity * Math.sin(radian)) / GRAVITATION;
		
		highestPoint = (initialVelocity * Math.sin(radian)) * (flightTime / 2) - (0.5 * GRAVITATION * Math.pow((flightTime / 2), 2));
		
		distanceTraveled = (initialVelocity * Math.cos(radian)) * flightTime;
		
		error = distanceTraveled - distanceToTarget;
		
		minError = Math.min(Math.abs(error), error);
		
		//Print values obtained through user input and calculations in a Message Box
		String trajectory = "initial velocity: " + initialVelocity + " feet/sec" + "\n" +
									  "launch angle: " + launchAngle + " degrees" + "\n" +
									  "flight time: " + String.format("%.2f", flightTime) + " seconds" + "\n" +
									  "maximum height: " + String.format("%.2f", highestPoint) + " feet" + "\n" +
									  "distance traveled: " + String.format("%.2f", distanceTraveled) + " feet" + "\n" +
									  "target missed by: " + String.format("%.2f", error) + " feet" + "\n";
		
		//Prints the values in the string to a message box
		JOptionPane.showMessageDialog(null, trajectory);
		//Prints the values in the string to the console
		System.out.printf(trajectory);
		
		//If the "target missed by" is negative and not within the 1 foot error margin, then it will terminate the program
		//and ask the user start again with a higher initial velocity
		if ( error < -1) 
		{
		    JOptionPane.showMessageDialog(new JPanel(), 
		            "Target is too far! Restart the program with greater initial velocity",
		            "Modification needed",
		            JOptionPane.WARNING_MESSAGE);
		    return;
		}
	
		//If the user hits the target, message box is displayed
		//and program terminates
		//minError is also displayed in console
		if (Math.abs(error) <= 1) 
		{
			JOptionPane.showMessageDialog(null, "Target is hit within an error of 1 foot!" +
					"\n" + "The program terminates");
			System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
			return;
		}
		
		//Displays whether the user needs to increase or decrease the launch angle
		//based on the error amount
		if (error > 0) 
		{
			System.out.println("Shot went beyond the target. Decrease the launch angle!");
		} 
		else 
		{
			System.out.println("Shot fell short of the target. Increase the launch angle!");
		}
		
//Begins the 2nd attempt to hit the target
		
			//Reassigns the launch angle through user input
			inputLaunchAngle = JOptionPane.showInputDialog(null, "Adjust the launch angle in degrees:");
			launchAngle = Double.parseDouble(inputLaunchAngle);
			
			//2nd launch calculations
			//Updating the variables
			radian = launchAngle * Math.PI / 180;
			
			flightTime = (2 * initialVelocity * Math.sin(radian)) / GRAVITATION;
			
			highestPoint = (initialVelocity * Math.sin(radian)) * (flightTime / 2) - (0.5 * GRAVITATION * Math.pow((flightTime / 2), 2));
			
			distanceTraveled = (initialVelocity * Math.cos(radian)) * flightTime;
			
			error = distanceTraveled - distanceToTarget;
			
			minError = Math.min(Math.abs(error), minError);
			
			//Update the string for 2nd launch numbers
			trajectory = "";
			trajectory = "initial velocity: " + initialVelocity + " feet/sec" + "\n" +
					  "launch angle: " + launchAngle + " degrees" + "\n" +
					  "flight time: " + String.format("%.2f", flightTime) + " seconds" + "\n" +
					  "maximum height: " + String.format("%.2f", highestPoint) + " feet" + "\n" +
					  "distance traveled: " + String.format("%.2f", distanceTraveled) + " feet" + "\n" +
					  "target missed by: " + String.format("%.2f", error) + " feet" + "\n";
			
			//Prints the values of the 2nd launch to a message box
			JOptionPane.showMessageDialog(null, trajectory);
			//Prints the values of the 2nd launch to the console
			System.out.printf(trajectory);
			
			//If the user hits the target, message box is displayed
			//and program terminates
			//minError is also displayed in console
			if (Math.abs(error) <= 1) 
			{
					JOptionPane.showMessageDialog(null, "Target is hit within an error of 1 foot!" +
							"\n" + "The program terminates");
					System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
					return;
			}
				
			//Displays whether the user needs to increase or decrease the launch angle
			//based on the error amount
			if (error > 0) 
			{
					System.out.println("Shot went beyond the target. Decrease the launch angle!");
			} 
			
			else 
			{
					System.out.println("Shot fell short of the target. Increase the launch angle!");
			}
		
		
//Begins the 3rd attempt to hit the target
			
			//Reassigns the launch angle through user input
			inputLaunchAngle = JOptionPane.showInputDialog(null, "Adjust the launch angle in degrees:");
			launchAngle = Double.parseDouble(inputLaunchAngle);
			
			//3rd launch calculations
			//Updating the variables
			radian = launchAngle * Math.PI / 180;
			
			flightTime = (2 * initialVelocity * Math.sin(radian)) / GRAVITATION;
			
			highestPoint = (initialVelocity * Math.sin(radian)) * (flightTime / 2) - (0.5 * GRAVITATION * Math.pow((flightTime / 2), 2));
			
			distanceTraveled = (initialVelocity * Math.cos(radian)) * flightTime;
			
			error = distanceTraveled - distanceToTarget;
			
			minError = Math.min(Math.abs(error), minError);
			
			//Update the string for 3rd launch numbers
			trajectory = "";
			trajectory = "initial velocity: " + initialVelocity + " feet/sec" + "\n" +
					  "launch angle: " + launchAngle + " degrees" + "\n" +
					  "flight time: " + String.format("%.2f", flightTime) + " seconds" + "\n" +
					  "maximum height: " + String.format("%.2f", highestPoint) + " feet" + "\n" +
					  "distance traveled: " + String.format("%.2f", distanceTraveled) + " feet" + "\n" +
					  "target missed by: " + String.format("%.2f", error) + " feet" + "\n";
			
			//Prints the values of the 3rd launch to a message box
			JOptionPane.showMessageDialog(null, trajectory);
			//Prints the values of the 3rd launch to the console
			System.out.printf(trajectory);
			
			//If the user hits the target, message box is displayed
			//and program terminates
			//minError is also displayed in console
			if (Math.abs(error) <= 1) 
			{
					JOptionPane.showMessageDialog(null, "Target is hit within an error of 1 foot!" +
							"\n" + "The program terminates");
					System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
					return;
			}
				
			//Displays whether the user needs to increase or decrease the launch angle
			//based on the error amount
			if (error > 0) 
			{
					System.out.println("Shot went beyond the target. Decrease the launch angle!");
			} 
			
			else 
			{
					System.out.println("Shot fell short of the target. Increase the launch angle!");
			}
			
//Begins the 4th attempt to hit the target
			
			//Reassigns the launch angle through user input
			inputLaunchAngle = JOptionPane.showInputDialog(null, "Adjust the launch angle in degrees:");
			launchAngle = Double.parseDouble(inputLaunchAngle);
			
			//4th launch calculations
			//Updating the variables
			radian = launchAngle * Math.PI / 180;
			
			flightTime = (2 * initialVelocity * Math.sin(radian)) / GRAVITATION;
			
			highestPoint = (initialVelocity * Math.sin(radian)) * (flightTime / 2) - (0.5 * GRAVITATION * Math.pow((flightTime / 2), 2));
			
			distanceTraveled = (initialVelocity * Math.cos(radian)) * flightTime;
			
			error = distanceTraveled - distanceToTarget;
			
			minError = Math.min(Math.abs(error), minError);
			
			//Update the string for 4th launch numbers
			trajectory = "";
			trajectory = "initial velocity: " + initialVelocity + " feet/sec" + "\n" +
					  "launch angle: " + launchAngle + " degrees" + "\n" +
					  "flight time: " + String.format("%.2f", flightTime) + " seconds" + "\n" +
					  "maximum height: " + String.format("%.2f", highestPoint) + " feet" + "\n" +
					  "distance traveled: " + String.format("%.2f", distanceTraveled) + " feet" + "\n" +
					  "target missed by: " + String.format("%.2f", error) + " feet" + "\n";
			
			//Prints the values of the 4th launch to a message box
			JOptionPane.showMessageDialog(null, trajectory);
			//Prints the values of the 4th launch to the console
			System.out.printf(trajectory);
			
			//If the user hits the target, message box is displayed
			//and program terminates
			//minError is also displayed in console
			if (Math.abs(error) <= 1) 
			{
					JOptionPane.showMessageDialog(null, "Target is hit within an error of 1 foot!" +
							"\n" + "The program terminates");
					System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
					return;
			}
				
			//Displays whether the user needs to increase or decrease the launch angle
			//based on the error amount
			if (error > 0) 
			{
					System.out.println("Shot went beyond the target. Decrease the launch angle!");
			} 
			
			else 
			{
					System.out.println("Shot fell short of the target. Increase the launch angle!");
			}
			
//Begins the final attempt to hit the target
			
			//Reassigns the launch angle through user input
			inputLaunchAngle = JOptionPane.showInputDialog(null, "Adjust the launch angle in degrees:");
			launchAngle = Double.parseDouble(inputLaunchAngle);
			
			//final launch calculations
			//Updating the variables
			radian = launchAngle * Math.PI / 180;
			
			flightTime = (2 * initialVelocity * Math.sin(radian)) / GRAVITATION;
			
			highestPoint = (initialVelocity * Math.sin(radian)) * (flightTime / 2) - (0.5 * GRAVITATION * Math.pow((flightTime / 2), 2));
			
			distanceTraveled = (initialVelocity * Math.cos(radian)) * flightTime;
			
			error = distanceTraveled - distanceToTarget;
			
			minError = Math.min(Math.abs(error), minError);
			
			//Update the string for final launch numbers
			trajectory = "";
			trajectory = "initial velocity: " + initialVelocity + " feet/sec" + "\n" +
					  "launch angle: " + launchAngle + " degrees" + "\n" +
					  "flight time: " + String.format("%.2f", flightTime) + " seconds" + "\n" +
					  "maximum height: " + String.format("%.2f", highestPoint) + " feet" + "\n" +
					  "distance traveled: " + String.format("%.2f", distanceTraveled) + " feet" + "\n" +
					  "target missed by: " + String.format("%.2f", error) + " feet" + "\n";
			
			//Prints the values of the final launch to a message box
			JOptionPane.showMessageDialog(null, trajectory);
			//Prints the values of the 4th launch to the console
			System.out.printf(trajectory);
			
			//If the user hits the target, message box is displayed
			//and program terminates
			//minError is also displayed in console
			if (Math.abs(error) <= 1) 
			{
					JOptionPane.showMessageDialog(null, "Target is hit within an error of 1 foot!" +
							"\n" + "The program terminates");
					System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
					return;
	
			}
				
			//Displays whether the user needs to increase or decrease the launch angle
			//based on the error amount
			if (error > 0) 
			{
					System.out.println("Shot went beyond the target. Decrease the launch angle!");
			} 
			
			else 
			{
					System.out.println("Shot fell short of the target. Increase the launch angle!");
			}
			
			System.out.printf("Your best shot missed the target with " + String.format("%.2f", minError) + " feet.");
	
		System.exit(0);
	}

}
