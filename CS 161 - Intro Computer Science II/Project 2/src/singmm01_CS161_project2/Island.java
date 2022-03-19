package singmm01_CS161_project2;

/*
 * Montana Singer
 * CS161 - 01
 * Spring 2019
 * Project 2
 *
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.*;

//This class is for the constructing of the GUI representation of the game
public class Island extends JFrame implements ActionListener{
	
	public static Workshop shop;
	
	public JButton[][] population;
	
	public JPanel north;
	public JPanel center;
	public JPanel south;
	
	public JButton mark;
	public JButton next;
	public JButton reset;
	
	public JTextField northField;
	public JTextField markText;
	public JTextField nextText;
	public JTextField resetText;
	
	
	//Island constructor
	public Island (int rows, int columns, Workshop shop)
	{
		super(">>>>>>LIFE GAME BOARD<<<<<<");
		
		shop = new Workshop(rows, columns);
		
		setSize(1280, 720);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		population = new JButton[rows][columns];
		
	
		for (int i = 0; i < population.length; i++)
		{
			for (int j = 0; j < population[i].length; j++)
				{
					population[i][j] = new JButton(Integer.valueOf(i) + ":" + Integer.valueOf(j));
					
					//This registered to the array entries
					population[i][j].addActionListener(this);
					population[i][j].setBackground(Color.LIGHT_GRAY);
				}
		}
		
		buildWindow();
		
	} //End Island constructor
	
	
	//Enables board to be edited by user input
	private void enableBoard (boolean flag)
	{
		for (int i = 0; i < population.length; i++)
		{
			for (int j = 0; j < population[i].length; j++)
				{
					population[i][j].setEnabled(flag);
				}
		}		
		
	} //End enableBoard method
	
	
	//Resets board to all false
	private void resetBoard ()
	{
		for (int i = 0; i < population.length; i++)
		{
			for (int j = 0; j < population[i].length; j++)
				{
					population[i][j].setBackground(Color.LIGHT_GRAY);
				}
		}
		
	} //End resetBoard method
	
	
	//Builds GUI
	public void buildWindow()
	{ 
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		
		//Set the north area
		northField = new JTextField(25);
		northField.setBackground(Color.PINK);
		
		//Add north panel to JFrame
		north.add(northField);
		add(north, BorderLayout.NORTH);	


		
		//Set the center area
		center.setLayout(new GridLayout(4, 5));
		
		for (int i = 0; i < population.length; i++)
		{
			for (int j = 0; j < population[i].length; j++)
				{
					center.add(population[i][j]);
				}
		}
		
		add(center);
		
	
		//Set the south area
		markText = new JTextField("click for marking intial life >>>>>>>>", 15);
		markText.setBackground(Color.PINK);
			
		nextText = new JTextField("see the next generation >>>>>>>>>>>", 16);
		nextText.setBackground(Color.CYAN);
			
		resetText = new JTextField("reset the game >>>>>>>>>>>>>>.", 16);
		resetText.setBackground(Color.GREEN);
			
		//Create buttons
		mark = new JButton("mark");
		next = new JButton("next");
		reset = new JButton("reset");
		
		
		south.setLayout(new GridLayout(4, 2));
		
		south.add(markText);
		south.add(mark);
		
		south.add(nextText);
		south.add(next);
		
		south.add(resetText);
		south.add(reset);
		
		add(south, BorderLayout.SOUTH);

		
		//Instantiate a ButtonListener
		ButtonListener buttonListener = new ButtonListener();
		mark.addActionListener(buttonListener);
		next.addActionListener(buttonListener);
		reset.addActionListener(buttonListener);
		
		
	} //End buildWindow method
	
	
	//Displays the next generation and their corresponding yellow or light gray backgrounds
	private void displayGeneration(boolean[][] mirror)
	{
		
		for (int i = 0; i < mirror.length; i++)
		{
			for (int j = 0; j < population[i].length; j++)
				{
					if (mirror[i][j])
					{
						population[i][j].setBackground(Color.YELLOW);
					}
					
					else
					{
						population[i][j].setBackground(Color.LIGHT_GRAY);
					}
				}
		}
		
	} //End displayGeneration method
	
	
	//Flips background when button is clicked
	public void actionPerformed(ActionEvent event)
	{
		
		JButton temp = ((JButton) event.getSource());
		
		if (temp.getBackground() == Color.YELLOW)
		{
			temp.setBackground(Color.LIGHT_GRAY);
		}
		
		else if (temp.getBackground() == Color.LIGHT_GRAY)
		{
			temp.setBackground(Color.YELLOW);
		}
		
	} //End actionPerformed method
	
	
	//Provides a listener for the three control buttons
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			//Mark is source
			if (event.getSource() == mark) 
			{
				enableBoard(true);
				
				next.setEnabled(true);
				
				shop.resetHistory();
				
			} //End if source is mark button
			
			//Next is source
			else if (event.getSource() == next)
			{
				enableBoard(false);
				
				mark.setEnabled(false);
				
				shop.resetMirror();
				
				for (int i = 0; i < population.length; i++)
				{
					for (int j = 0; j < population[i].length; j++)
						{
							if (population[i][j].getBackground() != Color.LIGHT_GRAY)
							{
								shop.mirror[i][j] = true;
							}	
							
						} //End inner for loop
					
				} //End for loop
				
				
				shop.nextGeneration();
				
				northField = new JTextField(shop.message);
				
				displayGeneration(shop.mirror);
				
				if (shop.finished == true)
				{
					next.setEnabled(false);
				}
		
			} //End if source is Next button
			
			
			//Reset is source
			else if(event.getSource() == reset)
			{
				mark.setEnabled(true);
				
				shop.resetHistory();
				
				northField = new JTextField("");
				
				resetBoard();
			}
			
		} //End actionPerformed method
		
	} //End ButtonListener inner class
	
	
	//Main method
	public static void main(String[] args) {
		
		shop = new Workshop(4, 5);
		Island test = new Island(4, 5, shop);
		test.setVisible(true);
		test.enableBoard(false);
		
	} //End main method
	
	
	
} //End Island class
