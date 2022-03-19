package singmm01_CS161_lab9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
* Montana Singer
* CS 161
* Spring 2019
* Lab 9
*/

public class Menus extends JFrame implements ActionListener{

	private JPanel east;
	private JPanel eastNorth;
	private JPanel center;
	private JPanel paintPanel = new PaintOnPanel();
	
	private JMenuBar bar;
	private JMenu shapeMenu;
	private JMenu colorMenu;
	
	private String selectedShape;
	private Color selectedColor;
	
	private boolean selectedFill = false;
	private boolean multipleShape = false;
	
	private JButton paintButton;
	private JButton disposeShape;
	private JButton fillOrDraw;
	private JButton multipleMode;
	
	private Container pane = getContentPane();
	
	
	private String[] shapeItems = {"rectangle", "oval", "circle", "triangle", 
								   "line", "segment", "arc"};	
	
	private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, 
							  Color.PINK, Color.MAGENTA, Color.ORANGE, Color.CYAN,
							  Color.BLACK, Color.WHITE, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY};
	
	
	private String[] colorNames = {"RED", "GREEN", "BLUE", "YELLOW", 
			  					   "PINK", "MAGENTA", "ORANGE", "CYAN",
			  					   "BLACK", "WHITE","GRAY", "LIGHT GRAY", "DARK GRAY"};
	
	
	
	public Menus()
	{
		super("We paint the Shapes!");
		
		pane.setLayout(new BorderLayout(10, 10));
		pane.setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		setSize(800, 650);
		setLocationRelativeTo(null);
		
		buildJMenuBar();
		buildCenterPanel();
		buildEastPanel();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	} //End Menus constructor
	
	
	
	//Builds the center panel in the JFrame
	public void buildCenterPanel()
	{
		
		center = new JPanel();
		center.setBackground(Color.GREEN);
		add(center, BorderLayout.CENTER);
		
	} //End buildCenterPanel method
	
	
	
	//Builds the east panel in the JFrame
	public void buildEastPanel()
	{
		east = new JPanel(new BorderLayout(10, 10));
		east.setBackground(Color.BLACK);
		
		eastNorth = new JPanel();
		
		paintButton = new JButton("PAINTBUTTON");
		paintButton.setBackground(Color.CYAN);
		paintButton.addActionListener(this);
		
		disposeShape = new JButton("DISPOSE_SHAPE");
		disposeShape.setBackground(Color.PINK);
		disposeShape.addActionListener(this);
		
		fillOrDraw = new JButton("FILL_DRAW_MODE");
		fillOrDraw.setBackground(Color.CYAN);
		fillOrDraw.addActionListener(this);
		
		multipleMode = new JButton("MULTIPLE_MODE");
		multipleMode.setBackground(Color.PINK);
		multipleMode.addActionListener(this);
		
		eastNorth.add(paintButton);
		eastNorth.add(disposeShape);
		eastNorth.add(fillOrDraw);
		eastNorth.add(multipleMode);
		
		paintPanel.setBackground(Color.WHITE);
		
		east.add(eastNorth, BorderLayout.NORTH);
		east.add(paintPanel, BorderLayout.CENTER);
		
		add(east, BorderLayout.EAST);
		
	} //End buildEastPanel method
	
	
	
	//Instantiates the shapes menu
	private void buildShapeMenu()
	{
		ShapeListener shapeListener = new ShapeListener();
		
		shapeMenu = new JMenu("Shapes");
		
		JMenuItem item;
		
		for (int i = 0; i < shapeItems.length; i++)
		{
			item = new JMenuItem(shapeItems[i]);
			item.addActionListener(shapeListener);
			shapeMenu.add(item);
		}
				
	} //End buildShapeMenu

	
	
	//Instantiates the color menu
	private void buildColorMenu()
	{
		ColorListener colorListener = new ColorListener();
		
		colorMenu = new JMenu("Colors");
		
		JMenuItem item;
		
		for (int i = 0; i < colors.length; i++)
		{
			item = new JMenuItem(colorNames[i]);
			item.setBackground(colors[i]);
			item.addActionListener(colorListener);
			colorMenu.add(item);
		}
				
	} //End buildColorMenu
	
	
	
	//Instantiate JMenuBar
	private void buildJMenuBar()
	{
		bar = new JMenuBar();
		
		buildShapeMenu();
		buildColorMenu();
		
		bar.add(shapeMenu);
		bar.add(colorMenu);
		
		setJMenuBar(bar);
		
	} //End buildJMenuBar
	
	
	
	//Logic for pressing menu items
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == paintButton)
		{
			
			paintPanel.repaint();

		}
		
		else if (e.getSource() == fillOrDraw)
		{
			
			if (selectedFill == true)
			{
				selectedFill = false;
				fillOrDraw.setText("FILL or DRAW: DRAW");
			}
			
			else if (selectedFill == false)
			{
				selectedFill = true;
				fillOrDraw.setText("FILL or DRAW: FILL");
			}

			
			
		}
		
		else if (e.getSource() == multipleMode)
		{
			
			if (multipleShape == true)
			{
				multipleShape = false;
				multipleMode.setText("MULTIPLE: FALSE");
				
			}
			
			else if (multipleShape == false)
			{
				multipleShape = true;
				multipleMode.setText("MULTIPLE: TRUE");
				
				selectedShape = "rectangle";
				
			}
			
		}
		
		else if (e.getSource() == disposeShape)
		{
			
			selectedColor = Color.WHITE;
			repaint();
			
		}
		
	} //End action performed for Menus class
	
	
	
	
	
		//Private inner class to paint the shapes
		private class PaintOnPanel extends JPanel 
		{
			
			public PaintOnPanel()
			{
				super();
			}
			
			
			public void paintComponent(Graphics g)
			{
				
				super.paintComponent(g);
				
				g.setColor(selectedColor);
				
				if (selectedShape == null)
				{
					selectedShape = "";
				}
				
				 switch (selectedShape) 
				 { 
			       
				 	//Rectangle
				 	case "rectangle": 
			            if (selectedFill)
			            	g.fillRect(50,10,340,140);
			            
			            else
			            	g.drawRect(180,230,100,240);
			            
			            if (!multipleShape)
			            break; 
			            
			         
			        //Oval    
			        case "oval": 
			        	if (selectedFill)
			        		g.fillOval(50,10,340,140);
			        	
			        	else
			        		g.drawOval(250,50,200,100);
			        	
			        	if (!multipleShape) 				
			            break; 
			        	
			        	
			        //Circle    
			        case "circle": 
			            if (selectedFill)
			            	g.fillOval(200,200,100,100);
			            
			            else
			            	g.drawOval(300,200,100,100);
			            
			            if (!multipleShape)
			            break; 
			            
			        
			        //Triangle    
			        case "triangle": 
			        	
			        	int[] xPoints = {100,50,150};
			        	int[] yPoints = {100,200,200};
			        	g.drawPolygon(xPoints, yPoints, 3);
			            
			            if (!multipleShape)
			            break; 
			            
			            
			        //Line    
			        case "line": 
			        
			        	g.drawLine(300, 300, 30, 30);
			        	
			            if (!multipleShape)
			            break; 
			        
			            
			        //Segment    
			        case "segment": 		       
			        	
			        	g.drawArc(300, 300, 5, 40, 45, 5);
			            
			            if (!multipleShape)
			            break; 
			            
			        case "arc": 
			            
			            g.drawArc(400, 400, 50, 50, 0, 45);
			            
			            if (!multipleShape)
			            break; 
			       
			        default: 
			            System.out.println("SHAPE SELECTION SKIPPED");
			            break; 
			      } 
				 
			}
			
		} //End private inner class PaintOnPanel
	
	
		
		
		//Private inner class to retrieve the selected shape
		private class ShapeListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) 
			{

				selectedShape = ((JMenuItem) e.getSource()).getText();
			
			} //End actionPerformed method
		
		} //End ShapeListener inner class
		
		
		
		
		//Private inner class to retrieve the selected color
		private class ColorListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) 
			{
					
				selectedColor = ((JMenuItem) e.getSource()).getBackground();
					
			} //End actionPerformed method
				
		} //End ColorListener inner class
	

	
	
	
	
	/*
	 * 
	 * Menu class implements ActionListener
	 * - contains actionPerformed() for the buttons ONLY
	 * - paint button should ONLY paint panel repaint()
	 * - in the data fields
	 * 		private JPanel paintPanel = new PaintOnPanel()
	 * 
	 * Private inner classes
	 * - one to be the ActionListener for the shape menu
	 * - one to be the ActionListener for the color menu
	 * 
	 * PaintOnPanel extends JPanel
	 * Implements
	 * paintComponent(Graphics g)
	 * 
	 */
	
	
	

} //End Menus class
