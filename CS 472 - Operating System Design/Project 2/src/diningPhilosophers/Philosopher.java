package diningPhilosophers;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable 
{
	
	DiningServer server;
	int number;
	long numberOfMeals = 0;
	
	// Constructor to initialize data
	public Philosopher(DiningServer server, int number) 
	{
		this.server = server;
		this.number = number;
	}

	@Override
	// Sets timer for how long the program should run
	public void run() 
	{
		long secondsToRun = 60;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + secondsToRun * 1000;
		
		while(System.currentTimeMillis() < endTime) 
		{
			think();
			eat();
		}
	}
	
	// Indicate that Philosopher is thinking
	public void think() 
	{
		System.out.println("Philosopher " + number + " is thinking.");
		
		// Generate random number from 1000 to 3000
		long pauseTimeThink = ThreadLocalRandom.current().nextLong(1000, 4000);
		
		try 
		{
			// Think for anywhere between 1 and 3 seconds
			Thread.sleep(pauseTimeThink);
		} 
		
		catch (InterruptedException e)
		{
			System.out.println("Exception: Interruption while executing think()");
		}
	}
	
	// Pick up fork, eat, then return fork
	public void eat() 
	{
		server.takeForks(number);
		System.out.println("Philosopher " + number + " is eating.");
		
		// Generate random number from 1000 to 3000
		long pauseTimeEat = ThreadLocalRandom.current().nextLong(1000, 4000);
		
		try 
		{
			// Eat for between 1 and 3 seconds
			Thread.sleep(pauseTimeEat);
		} 
		
		catch (InterruptedException e)
		{
			System.out.println("Exception: Interruption while executing eat()");
		}

		server.returnForks(number);
		numberOfMeals++;
	}

} // End Philosopher class