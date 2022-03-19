package singmm01_lab9;

/*
* Montana Singer
* CS 160–07, Fall Semester 2018
* Lab 9
* 
* The purpose of this class is to display each 
* of the elements of each array
* 
*/
public class Applications {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//Instantiate an ArrayPractice object
	ArrayPractice arrayPracticeObject = new ArrayPractice();
		
	//Call the displayNumbers method
	arrayPracticeObject.displayNumbers();
	//Call the displayBoxes method
	arrayPracticeObject.displayBoxes();
	//Call the displayList method
	arrayPracticeObject.displayList();
	
	
	//Comment on the console output
	/*
	 * The entries for the numbers array are all 0
	 * and the entries for boxes and list are null
	 */
	
	
	//Instantiate a String array named list
	String[] list = {"Bob", "Jamie", "Gabe", "Caleb", "Lisa", "Pepe"};
	
	
	//Declare and instantiate an ArrayPractice object with the parameterized constructor
	ArrayPractice secondArrayPracticeObject;
	secondArrayPracticeObject = new ArrayPractice(50, 6, list);
	
	System.out.println("\n Second time calling methods: \n");
	
	//Call the displayNumbers method
	secondArrayPracticeObject.displayNumbers();
	//Call the displayBoxes method
	secondArrayPracticeObject.displayBoxes();
	//Call the displayList method
	secondArrayPracticeObject.displayList();
	
	//Comment on the console output
	/*
	 * The entries are properly displayed		 
	 */

	} //End main method

} //End Applications class
