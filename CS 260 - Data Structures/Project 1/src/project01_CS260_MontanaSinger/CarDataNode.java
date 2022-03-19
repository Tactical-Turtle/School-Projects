package project01_CS260_MontanaSinger;

/*
 * Montana Singer
 * CS 260 - Fall 2019
 * Project 1
 * 
 * This class represents the Car data nodes, which are placed in a doubly linked list.
 * It provides the methods needed to perform operations on these nodes.
 * 
 * @param head Head node of the list
 * @param tail Tail node of the list
 * @param size Number of nodes in the list
 * @param checkIn Time when the car arrives at the garage
 * @param checkOut Time when the car leaves the garage
 * 
 */

public class CarDataNode {
	
	private CarDataNode head;
	private CarDataNode tail;
	private int size;

	private String licensePlate;
	private long checkIn;
	private long checkOut;
	
	private CarDataNode prev;
	private CarDataNode next;
	
	
	/* Constructor */
	public CarDataNode(String licensePlate, long checkIn, long checkOut)
	{
		this.licensePlate = licensePlate;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	/* Default constructor */
	public CarDataNode() {}
	
	
	/* Method to return the license plate of the node 
	 * @return A string representing the license plate
	 */
	public String getLicensePlate()
	{	return licensePlate;	}
	
	
	/* Method to set the license plate of the node */
	public void setLicensePlate(String setPlate)
	{	licensePlate = setPlate;	}
	
	
	/* Sets the checkIn field to the current time */
	public void checkIn()
	{	checkIn = System.currentTimeMillis();	} 
	
	
	/* Method to return the check in time of the node 
	 * @return A long that represents the current system time
	 */
	public long getCheckIn()
	{	return checkIn;		}
	
	
	/* Sets the checkOut field to the current time */
	public void checkOut()
	{	checkOut = System.currentTimeMillis();	} 
	
	
	/* Method to return the check out time of the node 
	 * @return A long that represents the current system time
	 */
	public long getCheckOut()
	{	return checkOut;	}
	
	/* Returns next
	 * @return A CarDataNode referring to the next node
	 */
	public CarDataNode getNext()
	{
		return next;
	}
	
	/* Returns prev 
	 * @return A CarDataNode referring to the previous node
	 */
	public CarDataNode getPrevious()
	{
		return prev;
	}
	
	/* Sets the field next */
	public void setNext(CarDataNode next)
	{
		this.next = next;
	}
	
	/* Sets the head node */
	public void setHead(CarDataNode head)
	{
		this.head = head;
	}
	
	/* Method to return the number of nodes in the list
	 * @return An int that holds the number of nodes in the list
	 */
	public int getSize()
	{	return size;	}
	
	
	/* This method adds a new node into the list at the end 
	*  @param newLicensePlate a license plate string
	*  @param newCheckIn check in time, going to be null as it is set by garage later on
	*  @param newCheckOut check out time, also going to be null
	*/
	public void addNode(String newLicensePlate, long newCheckIn, long newCheckOut)
	{
		CarDataNode newNode = new CarDataNode(newLicensePlate, newCheckIn, newCheckOut);
		
        if (head == null) 
        {  
           head = newNode;
        }  

        else 
        { 
        	tail = newNode;
        	next = tail;
        	tail.next = null;
        	tail.prev = this;
        }  

		size++;
	}
	
	
	/* This method adds a node after a specified node with the given license plate
	*  @param newLicensePlate a license plate string
	*  @param newCheckIn check in time, going to be null as it is set by garage later on
	*  @param newCheckOut check out time, also going to be null
	 */
	public void addNodeAfter(String plateData, long timeIn, long timeOut) 
	{ 
		CarDataNode newNode = new CarDataNode(plateData, timeIn, timeOut);
		newNode.next = next;
		next = newNode;
		newNode.prev = this;
		
		size++;
		
		if (newNode.next != null)
		{
			newNode.next.prev = newNode;
		}
		
	} //End addNodeAfter method
	
	
	/*
	 * Removes the target node
	 * @param target The node to be removed
	 */
    public void removeNode(CarDataNode target) 
    {
        //The list is empty or target is null
        if (head == null || target == null)
        {
        	return;
        }
        
        //Delete the head of the list
        if (head == target)
        {
        	head = target.next;
        }
        
        //Delete the tail of the list
        if (target.next == null)
        {
        	target.prev.next = null;
        }
        
        //Both of these contain logic for if the target
        //is in the middle of the list
        if (target.next != null || target.prev != null)
        {
        	target.next.prev = target.prev;
        	target.prev.next = target.next;
        }

    } //End removeNode method
    
    
    
    /* This method finds a node index with the given license plate
     * @param searchStart The node in the list where the search starts (head)
     * @param searchPlate The license plate that is being searched for
     * @return CarDataNode with the given license plate
     */
    public static CarDataNode findNode(CarDataNode searchStart, String searchPlate)
    {
    	CarDataNode temp = searchStart;  
          
        if(temp == null)
        {
        	throw new IllegalArgumentException("Starting search node is null");
        }
        
        else
        {     
            while (temp != null)  
            {  
                //If it is found return the node  
                if (temp.getLicensePlate().equals(searchPlate))  
                {   
                	return temp;
                }  
                //Increment temp pointer  
                temp = temp.next;  
            }  
        }
        
        return temp; 
        
    } //End findNodePlate method
    
    
    
    /*
     * Finds the node with the target index
     * @param searchStart The node in the list where the search will start (should be head)
     * @param targetIndex The index of the node being searched for
     * @return CarDataNode that corresponds to the given index
     */
    public static CarDataNode findNode(CarDataNode searchStart, int targetIndex)
    {
    	CarDataNode temp = searchStart;
    	int index = 0;
    	
    	if (temp == null)
    	{
    		throw new IllegalArgumentException("Starting search node is null");
    	}
    	
        while (temp != null)  
        {   
        	if (index == targetIndex)
        	{
        		break;
        	  //return temp;
        	}
        	temp = temp.next;
        	index++;
        }
        
        return temp;
    }
    
    
    /* Returns the CarDataNode in string format
     * @return A string containing the license plate, check in, and check out times
     */
    public String toString()
    {
        String result = "Plate: " + getLicensePlate() + "\n" +
        				"Check in: " + getCheckIn() + "\n" +
        				"Check out: " + getCheckOut();

        return result;  
        
    } //End toString method
    
    /* Prints the doubly linked list
     * @param head The head node of the list
     */
    public void printList(CarDataNode head) 
    { 
        CarDataNode temp = head; 
        while (temp != null) 
        { 
            System.out.println(temp.toString() + "\n"); 
            temp = temp.next; 
        }  
    } 

	
	
	/* Check if the doubly linked list is empty or not 
	 * @return True or false if the list is empty
	 */
	public boolean isEmpty() 
	{	return head == null;	} 
	
	
	/* Checks if 2 license plates are equal to each other 
	 * @param e Node to be compared
	 * @return True or false whether the two nodes are equal
	 */
	public boolean equals(CarDataNode e)
	{		
		if (getLicensePlate().equals(e.getLicensePlate()))
		{
			return true;
		}
		
		else
			return false;
	
	} //End equals method

} //End CarDataNode class
