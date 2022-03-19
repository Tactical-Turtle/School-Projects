package singmm01_CS161_lab8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
* Montana Singer
* CS161
* Spring 2019
* Lab 8
*/

public class Sliders extends JFrame {

	private JPanel north;
	private JPanel west;
	private JPanel eastOuter;
	private JPanel eastInner;
	private JPanel center;
	private JPanel south;
	
	private JButton thisIsIt;
	private JButton restart;
	
	private JLabel northLabel;
	
	private JLabel westLabel;
	
	private Container pane = getContentPane();
	
	private JSlider red, green, blue;
	private int r;
	private int g;
	private int b;
	private Color color;
	
	
	//This constructor initializes a blank window
	public Sliders()
	{
		super("Color the Panel!");
		
		pane.setLayout(new BorderLayout(10, 10));
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		buildNorthPanel();
		buildEastPanel();
		buildCenterPanel();
		buildWestPanel();
		buildSouthPanel();
	}
	
	
	
	//This method adds components to the window
	public void buildNorthPanel()
	{
		north = new JPanel();
	
		//Build north section
		northLabel = new JLabel("Blend your color here!");
		northLabel.setBackground(Color.BLUE);
		northLabel.setOpaque(true);
		
		north.add(northLabel);
			
		
		add(north, BorderLayout.NORTH);
		
	} //End buildNorthPanel
	
	
	
	//Builds east panel
	public void buildEastPanel()
	{
		eastOuter = new JPanel();
		eastInner = new JPanel();
		
		eastOuter.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		eastInner.setBorder(BorderFactory.createTitledBorder("RGB blend!"));
		
		red = makeSlider();
		green = makeSlider();
		blue = makeSlider();
		
		eastInner.add(red);
		eastInner.add(green);
		eastInner.add(blue);
		
		eastOuter.add(eastInner);
		
		add(eastOuter, BorderLayout.EAST);
		
	} //End buildEastPanel
	
	
	
	//Builds center panel
	public void buildCenterPanel()
	{
		center = new JPanel();
		
		//Build square with border
		center.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		center.setBackground(color);
		
		add(center, BorderLayout.CENTER);
		
	} //End buildCenterPanel
	
	
	
	//Builds west panel
	public void buildWestPanel()
	{
		west = new JPanel();
		
		westLabel = new JLabel("Checking your choice");
		
		west.add(westLabel);
		
		add(west, BorderLayout.WEST);
		
	} //End buildWestPanel
	
	
	
	//Builds south panel
	public void buildSouthPanel()
	{
		south = new JPanel();
		
		thisIsIt = new JButton("This is it!");
		restart = new JButton("Re-start");
		
		ButtonListener buttonListener = new ButtonListener();
		thisIsIt.addActionListener(buttonListener);
		restart.addActionListener(buttonListener);
		
		south.add(thisIsIt);
		south.add(restart);
		
		add(south, BorderLayout.SOUTH);
		
	} //End buildSouthPanel
	
	
	//Method to create and return a slider
	public JSlider makeSlider()
	{
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 255, 255);
		
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(5);
		slider.addChangeListener(new SliderListener());
		
		return slider;
	}
	
	
	 //Adds a listener to the sliders
	 private class SliderListener implements ChangeListener 
	 {
		 public void stateChanged(ChangeEvent e) 
		 {
			 JSlider slider = (JSlider) e.getSource();

		     if (slider == red) 
		     {
		    	 r = slider.getValue();
		     } 
		     if (slider == green) 
		     {
		    	 g = slider.getValue();
		     }
		     if (slider == blue) 
	         {
		    	 b = slider.getValue();
		     } 
		     
		     color = new Color(r, g, b);
		     center.setBackground(color);
		     
		 }
	 }
	 
	 
	 //Class to add a button listener to the buttons
	 class ButtonListener implements ActionListener
	 {
	 	//Adds a listener for the buttons
	 	public void actionPerformed(ActionEvent e) 
	 	{ 
	 		if (e.getSource() == thisIsIt)
	 		{
	 			west.setBackground(color);
	 			System.out.println(color);
	 			
	 			red.setEnabled(false);
	 			green.setEnabled(false);
	 			blue.setEnabled(false);

	 		}
		  
	 		else if (e.getSource() == restart)
	 		{
	 			red.setEnabled(true);
	 			green.setEnabled(true);
	 			blue.setEnabled(true);
	 		}
	 	} 
	 }
	
	
	
} //End Sliders class
