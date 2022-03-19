package singmm01_project3;

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Random;


/**
 * Montana Singer
 * CS 160 - 07, Fall Semester 2018
 * Project 3: At the ATM Machine
 * 
 *Description. This class represents the ATM machine. It communicates with the client
 *when a transaction is carried out
 *
 */

class ATM {
	
	//acc is an Account reference
	private Account acc;
	
	
	//Declare a scanner reference
	private Scanner keyboard = new Scanner(System.in);
	
	
	//Takes an Account type parameter to initialize the acc field
	public ATM(Account account)
	{
		acc = account;
	}
	
	
	
	public void transaction()
	{
		//Declare string to store value of input of W, w, D, or d
		String depositOrWithdraw = null;
		
		//Display input box to either Deposit or Withdraw
		//Save input in depositOrWithdraw string
		depositOrWithdraw = JOptionPane.showInputDialog(null, "Please enter W or w to withdraw" + "\n" +
														"Enter D or d for deposit", "Input", 
														JOptionPane.QUESTION_MESSAGE);
	
		
		//Declare int to hold user input PIN
		int inputPin;
		
		if (depositOrWithdraw == null)
		{
			System.out.println("Ends the withdraw and deposit process.");
			return;
		}
		
		//If the user wants to withdraw money
		//Solicits and reads the PIN code from console
		if(depositOrWithdraw.equals("W") || depositOrWithdraw.equals("w"))
		{
			System.out.println("Please enter your PIN code here");
			
			//User input is stored in inputPin
			inputPin = keyboard.nextInt();
			
			
				//If the input PIN matches the one previously given
				if(inputPin == (acc.getPin()))
				{
					//Ask for an amount to withdraw
					//Store input in withdrawAmount string
					String withdrawString;
					
						//Checks for valid input (ie can't be blank "")
						do
						{
						
							withdrawString = JOptionPane.showInputDialog(null, "Please enter the amount" + "\n" +
																		"you want to withdraw", "Input", JOptionPane.QUESTION_MESSAGE);
					
						} while (withdrawString.equals(""));
					
					
					//Convert string to integer
					//Store in withdrawAmount
					int withdrawAmount = Integer.parseInt(withdrawString);
					
					//call the withdraw() method of the Account class
					//input is used for its parameter
					acc.withdraw(withdrawAmount);
					
					//calls the showBalance() method of the Account class
					acc.showBalance();
					
				} //End if PIN matches
				
				
				
				//If the input PIN does NOT match the one previously given
				else
				{
					System.out.println("Wrong PIN, transaction aborted");
					return;
			
				} //End while loop for wrong PIN input
			
		} //End withdraw
		
		
		
		
		
		
		//If the user wants to deposit money
		if(depositOrWithdraw.equals("D") || depositOrWithdraw.equals("d"))
		{
			//Lists the accepted dollar bills
			JOptionPane.showMessageDialog(null, "We accept the following dollar bills:" + "\n" +
										  "1, 5, 10, 20, 50, 100" + "\n" +
										  "Please insert the bill on the console" + "\n" +
										  "Enter any other number to stop depositing", 
										  "Message", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Enter the bills here");
			
			
			//Declare int for while loop
			int inputDollarBill;
			int dollarBillsSum = 0;
			
			boolean validAmount = true;
			
			//Loop for input of dollar bills
			while (validAmount == true)
			{	
				inputDollarBill = keyboard.nextInt();
				
				if (inputDollarBill == 1 || inputDollarBill == 5 || inputDollarBill == 10 || 
					inputDollarBill == 20 || inputDollarBill == 50 || inputDollarBill == 100)
				{
					//sums up the valid bills
					dollarBillsSum = dollarBillsSum + inputDollarBill;
				}
				
				//If an invalid bill is input
				//the loop is terminated
				else
				{
					validAmount = false;
				}
				
			}  //End while loop for valid deposit amount
			
			
			//Call the deposit() method of the Account class
			//The parameter is the sum of the bill values
			acc.deposit(dollarBillsSum);
			
			//Call the showBalance() method of the Account class
			acc.showBalance();
	
		} //End logic for deposit option
		
	} //End transaction() method
	

}  //end ATM class








/**
 * Montana Singer
 * CS 160 - 07, Fall Semester 2018
 * Project 3: At the ATM Machine
 * 
 *Description. This class represents the bank account and contains
 *all the data and operations necessary for transactions
 *
 */

class Account {
	
	
	double balance;
	int pin;
	
	
	public int getPin()
	{
		return pin;
	}
	
	
	public void createPin()
	{
		//Declare an instance of the random class
		Random randomNumber = new Random();
		
		//Assign pin to hold a random number between 1000-9999
		pin = randomNumber.nextInt(9000) + 1000;
	}
	
	
	//Takes a parameter (must be a positive number)
	public void setBalance(double x)
	{
		if (x >= 0)
		{
			//Assigns balance
			balance = x;
		}
	}
	
	
	public void showBalance()
	{
		JOptionPane.showMessageDialog(null, String.format("The current balance is" + "\n" +
									                     "$" + "%.2f", balance));
	}
	
	
	//Takes a parameter for the deposit value
	public void deposit(double y)
	{
		//Updates the balance
		balance = balance + y;
	}
	
	
	//Takes a parameter for the withdrawn amount
	public void withdraw(double z)
	{
		
		//Updates balance
		balance = balance - z;
		
		//If parameter is greater than the balance, print a message
		if(z > balance)
		{
			System.out.println("Required amount exceeds balance" + "\n" +
							   "You recieve your balance.");
			
			//Balance is assigned 0 as the user
			//took out their entire balance
			balance = 0;
		}
	
	}
	
	
	//Account() constructor takes the deposit value for parameter
	public Account(double a) 
	{
		//Takes parameter to initialize balance
		balance = a;
		
		//Call createPin()
		createPin();
		
		//Show the user their PIN
		JOptionPane.showMessageDialog(null, "Take note of your PIN:" + "\n" + 
									  getPin(), "Client window", JOptionPane.WARNING_MESSAGE);
	}
	
	
	
}  //end Account class







/**
 * Montana Singer
 * CS 160 - 07, Fall Semester 2018
 * Project 3: At the ATM Machine
 * 
 *Description. This class controls the logic of the program
 *by communicating with the user regarding choices to be made
 *
 */

public class Applications {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		//Create Account and ATM references
		//Assign them to null
		ATM atm = null;
		Account acc = null;

		
		//Declare boolean values
		boolean client = true;
		boolean transactionRequired;
		
		//Used for the initial option menu of opening an account
		int yesNoCancel_OpenAccount;
		
		//Used for when the user is asked if they want to
		//make a transaction
		int yesNo_Transaction;
		
		String inputDeposit;
		int initialDeposit;
		
		
		//Begin outer/main while loop
		while (client == true)
		{
			transactionRequired = true;
			
			//This do while loop is used to be able to restart the process
			//if the user clicked Cancel when they did not have an account already
			do
			{	
			
				//Asks if user wants to open an account, if they already have an account,
				//or if they want to cancel the program
				yesNoCancel_OpenAccount = JOptionPane.showConfirmDialog(null, "Would you like to open an account?" + "\n" +
																		"The minimum deposit is 100 dollars." + "\n" +
																		"If you have an account please click Cancel", "Client window", 
																		JOptionPane.YES_NO_CANCEL_OPTION);
			
			
				//Logic for the 3 options regarding opening an account
				//Begin YES to deposit option
				if (yesNoCancel_OpenAccount == JOptionPane.YES_OPTION)
				{ 	
					//Checks if the user inputs a blank for the withdraw amount
					do
					{
						//Assign the string for inputting the deposit amount
						inputDeposit = JOptionPane.showInputDialog(null, "Please enter the amount for deposit" + "\n" +
																  "Please take note a PIN code will be issued for you", "Client window",
																  JOptionPane.OK_CANCEL_OPTION);
				
					} while (inputDeposit.equals("")); //End do while loop
				
	
				
				//Convert the input deposit string to an integer
				initialDeposit = Integer.parseInt(inputDeposit);
				
						//Checks if the deposit was greater than 100
						//If not, display a message
						if (initialDeposit >= 100)
						{
							//Instantiate the acc reference
							//Account() constructor takes the deposit value for parameter
							acc = new Account(initialDeposit);
				
							//atm reference instantiates, ATM() constructor takes the acc reference for parameter
							//the ATM() constructor takes the acc reference for parameter
							atm = new ATM(acc);
						}
				
						else
						{
							//Error message displaying that the minimum deposit is $100
							JOptionPane.showMessageDialog(null, "The minimum deposit is 100 dollars", 
														 "Client window", JOptionPane.ERROR_MESSAGE);
						}
				
				} //End YES to deposit option
			
				
			
			
			
				//Begin NO to deposit Option
				else if (yesNoCancel_OpenAccount == JOptionPane.NO_OPTION)
					{
						System.out.println("The process terminates" + "\n" +
									       "Good Bye!");
						System.exit(0);
				
					} //End NO to deposit option
			
			
			
			
			
				//Begin CANCEL (ie if the user has an account) logic
				
				//User does NOT have an account
				else if (acc == null)
					{
						//Displays that the client does not have an account
						JOptionPane.showMessageDialog(null, "Sorry, you do not have an account", 
												 "Client Window", JOptionPane.ERROR_MESSAGE);
						client = false;
					
					}
				
				//User DOES have an account
				else
				{
					//atm reference is instantiated, constructor takes acc for parameter
					atm = new ATM(acc);	
				}
					
		
			} while(acc == null); //End do while loop
				
			
			
			
				//Begin inner while loop
				while (transactionRequired == true)
				{
					yesNo_Transaction = JOptionPane.showConfirmDialog(null, "Please click Yes for a transaction" + "\n" +
												  					 "Click No to exit current process", "Client window", 
												  					 JOptionPane.YES_NO_OPTION);
					
					if (yesNo_Transaction == JOptionPane.NO_OPTION)
					{
						transactionRequired = false;
					}
					
					else
					{
						//atm calls the transaction() method of the ATM class
						atm.transaction();
					}
					
				}  //End inner while loop
			
			
		}   //End outer/main while loop

	}	//end main method

}  //end Applications class



