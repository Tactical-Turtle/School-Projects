package diningPhilosophers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This class implements the logic for the DiningServer interface
 */
public class DiningServerControl implements DiningServer 
{

	public static final int NUM_OF_PHILS = 5;
	enum State {Thinking, Eating, Hungry};
	
	Lock lock;
	State[] state;
	Condition[] forks;
	
	DiningServerControl() 
	{
		lock = new ReentrantLock();				// Lock object for critical sections
		state = new State[NUM_OF_PHILS];		// State variables for philosophers
		forks = new Condition[NUM_OF_PHILS];	// Monitors for forks
		
		for (int i = 0; i < NUM_OF_PHILS; i++) 
		{
			state[i] = State.Thinking;			// Initialize state to thinking
			forks[i] = lock.newCondition();		// Initialize forks to new condition variable
		}
	}

	
  // Take fork and eat if available
  public void takeForks(int pNumber)
  {
	  lock.lock();
	  
	  try 
	  {
		  state[pNumber] = State.Hungry;
		  System.out.println("Philosopher " + pNumber + " is hungry.");
		  
		  // No surrounding philosophers are eating
		  if (!(state[(pNumber - 1 + NUM_OF_PHILS) % NUM_OF_PHILS] == State.Eating) && 
				  !(state[(pNumber + 1) % NUM_OF_PHILS] == State.Eating)) 
		  {
			  // Eat
			  state[pNumber] = State.Eating;
		  } 
		  
		  // One or more surrounding philosophers are eating
		  else 
		  {
			  // Wait until forks are released, then eat
			  forks[pNumber].await();
			  state[pNumber] = State.Eating;
		  }
	  } 
	  
	  catch (InterruptedException e) 
	  {
		  System.out.println("Interruption while takeForks() was executing");
	  } 
	  
	  finally 
	  {
		  lock.unlock();
	  }
	  
  } // End takeForks method

  
  // After eating, return fork
  public void returnForks(int pNumber)
  {
	 lock.lock();
	 
	 try 
	 {
		 System.out.println("Philosopher " + pNumber + " is done eating.");
		 forks[pNumber].signal();
		 
		 // Philosopher 4 condition back to start
		 if (pNumber == 4) 
		 {
			 forks[0].signal();
		 }  
		 
		 else 
		 {
			 forks[pNumber + 1].signal();
		 }
	 } 
	 
	 
	 finally 
	 {
		  lock.unlock();
	 }
	 
  } // End returnForks method
  
} // End DiningServerControl class
