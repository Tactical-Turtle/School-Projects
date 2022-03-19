package singmm01_CS161_lab6;

import javax.swing.*;
import java.awt.*;

/*
* Montana Singer
* CS161 - 01
* Spring 2019
* Lab 6
*
*/

//Constructs a window to be displayed to the user
public class BaseWindow extends JFrame {
	
	JPanel west;
	JPanel east;
	JPanel center;
	JPanel middle;
	
	JButton button;
	
	JTextField text;
	
	JLabel label;
	
	Container pane = getContentPane();
	
	//This constructor initializes a blank window
	public BaseWindow(int textFieldLength) 
	{
		super(">>>>> BASE WINDOW with COLORED COMPONENETS <<<<<");
		
		text = new JTextField(textFieldLength);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		buildWindow();
	}
	
	//This method adds components to the window
	public void buildWindow()
	{
		west = new JPanel();
		east = new JPanel();
		center = new JPanel();
		middle = new JPanel();
		
		button = new JButton("button!");
		
		label = new JLabel("Middle panel!");
		
		west.setBackground(Color.GREEN);
		east.setBackground(Color.YELLOW);
		center.setBackground(Color.LIGHT_GRAY);
		middle.setBackground(Color.DARK_GRAY);
		button.setBackground(Color.RED);
		text.setBackground(Color.PINK);
		
		label.setForeground(Color.CYAN);
		
		center.setLayout(new GridLayout(5, 1));
		
		west.add(button);
		east.add(text);
		center.add(label);
		middle.add(label);
		
		center.add(new JLabel());
		center.add(new JLabel());
		center.add(middle);
		
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		
	}
	


} //End BaseWindow class
