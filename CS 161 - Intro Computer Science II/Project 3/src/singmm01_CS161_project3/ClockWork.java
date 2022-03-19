package singmm01_CS161_project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/*
* Montana Singer
* CS 161
* Spring 2019
* Project 3
*
*/


//This class handles all the logic for the clock
public class ClockWork {

	
	private int longArmIndex = 0;
	private int shortArmIndex = 0;
	private int secondsArmIndex = 0;
	
	private int radius;
	private int centerX;
	private int centerY;
	
	private int[] x = new int[60];
	private int[] y = new int[60];
	private int[] xx = new int[60];
	private int[] yy = new int[60];
	
	private int tickTime;
	
	private Timer timer;
	private ActionListener listener;
	private ActionEvent event;
	
	
	//Constructor
	public ClockWork()
	{
		event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
	}
	
	
	//Getters
	public int getLongArmIndex()
	{
		return longArmIndex;
	}
	
	
	public int getShortArmIndex()
	{
		return shortArmIndex;
	}
	
	
	public int getSecondsArmIndex()
	{
		return secondsArmIndex;
	}
	
	
	public int[] getX()
	{
		return x;
	}
	
	public int[] getY()
	{
		return y;
	}
	
	public int[] getXX()
	{
		return xx;
	}
	
	public int[] getYY()
	{
		return yy;
	}
	
	
	
	//Setters
	public void setTickTime (int tick)
	{
		tickTime = tick;
	}
	
	public void setCircle(int radiusValue, int centerXValue, int centerYValue)
	{
		radius = radiusValue;
		centerX = centerXValue;
		centerY = centerYValue;
	}
	
	
	public void addActionListener(ActionListener watch)
	{
		listener = watch;
	}
	
	
	//Stores the coordinates of the tacks around the clock
	public void loadTacks()
	{
		for (int i = 0; i < x.length; i++)
		{
			x[i] = (int) (centerX + radius * Math.sin(2 * i * Math.PI/60));
			y[i] = (int) (centerY - radius * Math.cos(2 * i * Math.PI/60));
			
			if (i % 5 == 0)
			{
				xx[i] = (int) (centerX + (radius + 20) * Math.sin(2 * i * Math.PI/60));
				yy[i] = (int) (centerY - (radius + 20) * Math.cos(2 * i * Math.PI/60));
			}
			
			else
			{
				xx[i] = (int) (centerX + (radius + 10) * Math.sin(2 * i * Math.PI/60));
				yy[i] = (int) (centerY - (radius + 10) * Math.cos(2 * i * Math.PI/60));
			}	
		}
		
	} //End loadTacks method
	
	
	
	//Makes the timer and starts it
	public void makeTimer()
	{
		timer = new Timer(tickTime, new TimerListener());
		
		timer.start();
	}
	
	
	//Stops the timer
	public void stop()
	{
		timer.stop();
	}
	
	
	//Resets the clock so all hands are on 12
	public void reset()
	{
		stop();
		
		longArmIndex = 0;
		shortArmIndex = 0;
		secondsArmIndex = 0;
		
		connect();
	}
	
	
	//Validates time input
	private boolean timeFormat(String inp)
	{
		boolean validString;
		
		if (! inp.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
		{
			validString = false;
		}
		
		else
		{
			validString = true;
		}
		
		return validString;
		
	} //End timeFormat
	
	
	
	//Sets the clock based on user input
	public void setClock(String timeToSet)
	{
		if (timeFormat(timeToSet) == true)
		{
			
			String substringHours;
			String substringMinutes;
			
			int hoursInput;
			
			substringHours = timeToSet.substring(0, 2);
			hoursInput = Integer.parseInt(substringHours);
			if (hoursInput < 12)
			{
				shortArmIndex = hoursInput * 5;
			}
			
			else if (hoursInput >= 12 && hoursInput < 24)
			{
				shortArmIndex = (hoursInput - 12) * 5;
			}
			
					
			substringMinutes = timeToSet.substring(3, 5);
			longArmIndex = Integer.parseInt(substringMinutes);		
			
			secondsArmIndex = 0;
			
			connect();
		}
		
		else
		{
			return;
		}
		
	} //End setClock method
	
	
	private void connect()
	{
		listener.actionPerformed(event);
	}
	
	
			//This inner class implements ActionListener for user interaction on the GUI
			class TimerListener implements ActionListener
			{
				
				public void actionPerformed(ActionEvent event)
				{
					secondsArmIndex = (++secondsArmIndex)%60;
							
					if (secondsArmIndex == 0)
					{
						longArmIndex = ++longArmIndex%60;
					}
					
					if (longArmIndex % 12 == 0)
					{
						shortArmIndex = ++shortArmIndex%60;
					}
					
					connect();
					
				}
				
			} //End private inner class TimerListener
	
	
} //End ClockWork class
