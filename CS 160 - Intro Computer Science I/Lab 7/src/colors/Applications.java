package colors;

import java.awt.Color;
import javax.swing.*;
import java.util.Random;

/*
* Montana Singer
* CS 160-07 Fall 2018
* Lab 7
*
*/
public class Applications {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ESPGame game = new ESPGame();
		game.guessColor();
		
		System.exit(0);
	
	} //End main method
	
} //End Applications class



/*
* Montana Singer
* CS 160-07 Fall 2018
* Lab 7
*
*/
class ESPGame
{
	private String chosenColor;
	
	
	//This method returns one of the colors
	//Blue, Yellow, Red, Green, Orange, or Cyan
	//based on the input parameter
	public Color chooseColor(int x)
	{
		
		Color color;
		int input = x;
		
		//Begin switch statement for each case of color
		switch (input)
		{
			
			case 1: 
				color = Color.BLUE;
				chosenColor = "blue";
				break;
			
			case 2:
				color = Color.YELLOW;
				chosenColor = "yellow";
				break;
			
			case 3:
				color = Color.RED;
				chosenColor = "red";
				break;
				
			case 4:
				color = Color.GREEN;
				chosenColor = "green";
				break;
				
			case 5:
				color = Color.ORANGE;
				chosenColor = "orange";
				break;
				
			case 6:
				color = Color.CYAN;
				chosenColor = "cyan";
				break;
				
			default:
				color = Color.BLACK;
				chosenColor = "black";
				
				
		}
		
		return color;
		
	} //End chooseColor() method
	
	
	
	//This method creates a small window for displaying
	//the parameter color
	public void showColor(Color color)
	{
		
		JFrame frame = new JFrame("Guess this color");
		frame.setSize(200,200);
		frame.setLocation(300,300);
		JPanel panel = new JPanel();
		panel.setBackground(color);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	} //End showColor() method
	
	
	public void guessColor() 
	{
		
		//Declare boolean to control while loop
		boolean espGame = true;
		
		//Declare int for user option yes or no
		int yesNo;
		
		//Declare random variable for while loop
		Random random = new Random();
		
		//Declare int to hold random number value
		int randomNumber;
		
		//Declare string to hold user input of the color they see
		String chooseColorDisplayed = null;
		
		while (espGame == true)
		{
			yesNo = JOptionPane.showConfirmDialog(null, "Enter the ESP game?", "ESP Game", 
					  JOptionPane.YES_NO_OPTION);
			
			if (yesNo == JOptionPane.YES_OPTION)
			{
				randomNumber = random.nextInt(6) + 1;
				
				//call the chooseColor() method with the random
				//number as its parameter
				chooseColor(randomNumber);
				
				//Call the showColor() method with the color value 
				//returned by chooseColor()
				showColor(chooseColor(randomNumber));
				
				
				chooseColorDisplayed = JOptionPane.showInputDialog(null, "Choose the name of the color you see:" + "\n" +
																  "brown" + "\n" +
																  "red" + "\n" +
																  "green" + "\n" +
																  "blue" + "\n" +
																  "magenta" + "\n" +
																  "cyan" + "\n" +
																  "black" + "\n" +
																  "orange" + "\n" +
																  "yellow" + "\n" +
																  "beige", 
																  "Input", JOptionPane.QUESTION_MESSAGE);
				
				if (chooseColorDisplayed == null)
				{
					System.exit(0);;
				}
				
				//Compares the selection to the choseColor value
				
				//If they match, displays it is correct
				if (chooseColorDisplayed.matches(chosenColor))
				{
					JOptionPane.showMessageDialog(null, "You guessed correct", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
						
				else
				{
					JOptionPane.showMessageDialog(null, "You guessed wrong", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else 
			{
				break;
			} //End if else statement
		
		} //End while loop
	
	} //End guessColor() method

} //End ESPGame class












