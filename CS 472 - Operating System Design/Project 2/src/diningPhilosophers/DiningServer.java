package diningPhilosophers;

/*
 * This class contains the methods called by the  philosophers. 
 */

public interface DiningServer 
{  
   // When Philosopher wants to eat
   public void takeForks(int pNumber);
   
   // When Philosopher is done eating
   public void returnForks(int pNumber);
}
