package singmm01_CS161_project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*
* Montana Singer
* CS 161
* Spring 2019
* Project 3
*
*/

//This class builds the GUI
public class FaceGUI extends JFrame implements ActionListener{

	private ClockWork work;
	
	private Face face;
	
	private int radius;
	private int centerX;
	private int centerY;
	
	private Container pane = getContentPane();
	
	
	//Constructor initializes work
	public FaceGUI (ClockWork cw)
	{
		super("Grandpa's Clock");
		
		work = cw;
		work.addActionListener(this);
		buildClock();
		
		setVisible(true);
	}
	
	
	//Builds the clock component
	private void buildClock()
	{
		pane.setLayout(new BorderLayout(10, 10));
		setSize(800, 800);
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		radius = 300;
		centerX = 400;
		centerY = 400;
		
		work.setCircle(radius, centerX, centerY);
		work.loadTacks();
		face = new Face();
		face.setBackground(Color.YELLOW);
		add(face);
		
	} //End buildClock
	
	
	
	//Repaints the clock when an action is performed
	public void actionPerformed(ActionEvent event)
	{	
		face.repaint();
		
	} //End actionPerformed
	
	
			//Draws the clock
			class Face extends JPanel
			{
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					
					int longArmIndex = work.getLongArmIndex();
					int secondsArmIndex = work.getSecondsArmIndex();
					int shortArmIndex = work.getShortArmIndex();
					
					
					int[] x = work.getX();
					int[] y = work.getY();
					int[] xx = work.getXX();		
					int[] yy = work.getYY();
					
					
					g.setColor(Color.WHITE);
					g.fillOval(100, 100, radius*2, radius*2);
					
					g.setColor(Color.CYAN);
					g.fillOval(125, 125, radius*2-50, radius*2-50);
					
					g.setColor(Color.BLACK);
					g.fillOval(387, 385, 25, 25);
					
					//Perimeter circle outline
					g.drawOval(100, 100, radius*2, radius*2);
					
					
					//Draw tick marks
					for (int i = 0; i < x.length; i++)
					{
						g.drawLine(x[i], y[i], xx[i], yy[i]);
					}
					
					
					//Draw numbers
					Font font = new Font("Verdana", Font.PLAIN, 12);
					g.setFont(font);
					
					g.drawString("12", 390, 65);
					g.drawString("1", 565, 110);
					g.drawString("2", 690, 235);
					g.drawString("3", 740, 405);
					g.drawString("4", 700, 575);
					g.drawString("5", 565, 700);
					g.drawString("6", 395, 740);
					g.drawString("7", 225, 700);
					g.drawString("8", 100, 575);
					g.drawString("9", 50, 405);
					g.drawString("10", 80, 225);
					g.drawString("11", 225, 110);
				
					
					//Draw hour hand
					g.setColor(Color.BLUE);
					g.drawLine(centerX, centerY, x[shortArmIndex], y[shortArmIndex]);

					//Draw minutes hand
					g.setColor(Color.GREEN);
					g.drawLine(centerX, centerY, x[longArmIndex], y[longArmIndex]);
					
					//Draw seconds hand
					g.setColor(Color.RED);
					g.drawLine(centerX, centerY, x[secondsArmIndex], y[secondsArmIndex]);
					
				}
				
			} //End Face inner class

} //End FaceGUI class
