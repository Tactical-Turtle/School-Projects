package lab03_MontanaSinger;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class IO_Practice1 {

/*
 * Montana Singer
 * CS 160-06 Fall 2018
 * 
 * Lab 3
 * 
*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numberOfBooks, titleLength, yearPublisher;
	
		double unitPrice, totalCost;
		
		String authorName, bookTitle, publisher, edition;
		
		String firstName, middleName, lastName;
		
		String cfirstName, cmiddleName, clastName;
		
		String streetAddress, cityName, stateName, zipCode;
		
		char middleInitial;
		
		Scanner keyboardInput = new Scanner(System.in);
		
		System.out.printf("Number of books ordered? " + "\n");
		numberOfBooks = keyboardInput.nextInt();
		
		
		System.out.printf("Unit price of the book? " + "\n");
		unitPrice = keyboardInput.nextDouble();
		
		totalCost = (numberOfBooks * unitPrice);
		
		
		System.out.printf("Enter your first name, middle name and last name: " + "\n");
			
			cfirstName = keyboardInput.next();
			
			cmiddleName = keyboardInput.next();
			
			clastName = keyboardInput.next();
			
			//Convert last name to upper case
			clastName = clastName.toUpperCase();
	
			
		System.out.printf("Enter your street address(i.e., nos and street): " + "\n");
		
		//Consume the line
		keyboardInput.next();
		
		streetAddress = keyboardInput.nextLine();
		
		System.out.printf("Enter name of the city: " + "\n");
		cityName = keyboardInput.nextLine();
		
		
		System.out.printf("Enter name of the state: " + "\n");
		stateName = keyboardInput.nextLine();
		
		
		System.out.printf("Enter the Zip Code: " + "\n");
		zipCode = keyboardInput.nextLine();
		
		
		System.out.printf("Enter your favorite author first name, middle name, and last name: " + "\n");
			
			firstName = keyboardInput.next();
			
			middleName = keyboardInput.next();
			
			lastName = keyboardInput.next();
			
			//Get the first initial from middle name
			middleInitial = middleName.charAt(0);
					
			//Convert last name to upper case
			lastName = lastName.toUpperCase();
			
			//Re-assign author's name to be in format
			authorName = lastName + ", " + firstName + " " + middleInitial;
		
		//Consume the line ( because of using next() )	
		keyboardInput.nextLine();
		
		
		System.out.println("Title of your favorite book: ");
		//User inputs book Title
		bookTitle = keyboardInput.nextLine();
		//convert input to upper case
		bookTitle = bookTitle.toUpperCase();
		
		
		System.out.println("Enter the name of the publisher for the book: ");
		publisher = keyboardInput.nextLine();
	
		
		System.out.println("Enter the edition for the book: ");
		edition = keyboardInput.nextLine();
		
		
		System.out.println("Enter the year for publishing the book: ");
		yearPublisher = keyboardInput.nextInt();
		

		
		//declare integers to be used in reorder process
		int reorderNumberOfBooks;
		double reorderUnitPrice;
		
		//begin the reorder process
		int reorder = JOptionPane.showConfirmDialog(null, "Do you want to reorder the book? yes or no", "Question?", +
									 JOptionPane.YES_NO_OPTION);
									
		if (reorder == JOptionPane.YES_OPTION) 
		{
			reorderNumberOfBooks = Integer.parseInt( JOptionPane.showInputDialog(null, "Enter the number of books to be reordered: "));
			reorderUnitPrice = Double.parseDouble( JOptionPane.showInputDialog(null, "Enter the unit price of the book: "));
		}
		
		else 
		{
			return; 
		}
		
		
		
		//get the book title length
		titleLength = bookTitle.length();
		
		//Information about the book
		System.out.printf("Information about your Favorite Book: " + "\n" +
						  "My favorite author is: " + "\n\t" + 
						  		authorName + "\n" +
						  "My favorite book from " + firstName + " is " + "\n\t" +
						  		bookTitle + "\n\t" +
						  		publisher + ", " + edition + " Edition, " + yearPublisher + "." + "\n" +
						  "The length of the title is: " + titleLength + ".");
		
		
		
		//how old the book is with proper year or years
		int bookAge = (2018 - yearPublisher);
		
		if ( bookAge == 1 ) 
			{
			System.out.println("\n" + "It is a " + bookAge + " year old book");
			}
		
		else 			  
			{
			System.out.println("\n" + "It is a " + bookAge + " years old book");
			}
		
		
		//total cost of books (number of books * price)
		totalCost = numberOfBooks * unitPrice;
		
		//cost of the book or books
		if (numberOfBooks > 1)
			{
			System.out.println("The total cost of " + numberOfBooks + " books of unit price $" + unitPrice + " is $" + 
								totalCost + " from the " + publisher + ".");
			}
		else
			{
			System.out.println("The total cost of " + numberOfBooks + " book of unit price $" + unitPrice + " is $" + 
					totalCost + " from the " + publisher + ".");
			}
		
		
		//information about the customer
		System.out.printf("\n" + "Information about the Customer: " + "\n" +
						  "Name: " + cfirstName + " " + cmiddleName + " " + clastName + "\n" +
						  "Street Address: " + streetAddress + "\n" +
						  "City: " + cityName + "\n" +
						  "State: " + stateName + "\n" +
						  "ZipCode: " + zipCode);
		
		
		
		//total cost of reorder with correct book or books
		if (reorderNumberOfBooks > 1)
		{
			System.out.printf("\n\n" + "The total cost for reordered " + reorderNumberOfBooks + " books of unit price $" + reorderUnitPrice + 
							  " is $ " + (reorderNumberOfBooks * reorderUnitPrice) + " from the " + publisher + ".");
		}
		else
		{
			System.out.printf("\n\n" + "The total cost for reordered " + reorderNumberOfBooks + " book of unit price $" + reorderUnitPrice + 
					  " is $ " + (reorderNumberOfBooks * reorderUnitPrice) + " from the " + publisher + ".");
		}
		
		//end of invoice
		System.out.println("\n\n" + "The end of the invoice");
		
		System.exit(0);
	}

}
