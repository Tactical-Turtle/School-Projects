package singmm01_CS161_project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
* Montana Singer
* CS 161
* Spring 2019
* Project 3
*
*/


//This class builds the GUI for the control buttons for the clock
public class ControlDevices extends JFrame implements ActionListener, ChangeListener {
	
	
	ClockWork work;
	
	private JTextField textField = new JTextField(10);
	
	private JLabel enterTimeLabel = new JLabel("Enter time, use format 15:42");
	private JLabel tickTimeLabel = new JLabel("Select tick time");
	private JLabel startClockLabel = new JLabel("Click to start the clock");
	private JLabel stopClockLabel = new JLabel("Click to stop the clock");
	private JLabel resetClockLabel = new JLabel("Click to reset the clock");
	
	private JButton runClock = new JButton("Run the Clock");
	private JButton stopClock = new JButton("Stop the Clock");
	private JButton resetClock = new JButton("Reset the Clock");
	private JButton setClock = new JButton("Set the Clock");
	
	private JSlider tickTime = new JSlider(JSlider.VERTICAL, 0, 1000, 200);
	
	private Container pane = getContentPane();
	
	
	
	//ControlDevices constructor
	public ControlDevices(ClockWork cw)
	{
		super("Control Of Clockwork");
		
		work = cw;
		
		pane.setLayout(new BorderLayout(10, 10));
		pane.setBackground(Color.GRAY);
		setSize(800, 600);
		setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		makeSlider();
		buildControlGUI();
		
	} //End ControlDevices constructor
	
	
	
	//Bulds the slider in the GUI
	private void makeSlider()
	{
		tickTime.setMajorTickSpacing(100);
		tickTime.setMinorTickSpacing(10);
		tickTime.setPaintTicks(true);
		tickTime.setPaintLabels(true);
		tickTime.setBackground(Color.CYAN);
		tickTime.addChangeListener(this);
		tickTime.setVisible(true);
	}
	
	
	
	//Builds main GUI
	private void buildControlGUI()
	{
		JPanel center = new JPanel(new GridLayout(4, 1));
		JPanel run = new JPanel();
		JPanel stop = new JPanel();
		JPanel reset = new JPanel();
		JPanel set = new JPanel();
		
		JPanel east = new JPanel();
		
		
		runClock.addActionListener(this);
		stopClock.addActionListener(this);
		resetClock.addActionListener(this);
		setClock.addActionListener(this);
		
		run.add(runClock);
		run.add(startClockLabel);
		
		stop.add(stopClock);
		stop.add(stopClockLabel);
		
		reset.add(resetClock);
		reset.add(resetClockLabel);
	
		set.add(enterTimeLabel);
		set.add(textField);
		set.add(setClock);
		set.setBackground(Color.RED);

		center.add(run);
		center.add(stop);
		center.add(reset);
		center.add(set);
		
		east.add(tickTime);
		east.add(tickTimeLabel);
		
		add(center, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		
	} //End buildControlGUI method
	
	
	//Listener for the slider
	public void stateChanged(ChangeEvent event)
	{
		JSlider slider = (JSlider) event.getSource();
		work.setTickTime(slider.getValue());
		
	} //End stateChanged method
	
	
	//Logic for when the buttons are clicked
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == stopClock)
		{
			runClock.setEnabled(true);
			work.stop();
		}
		
		
		else if (e.getSource() == runClock)
		{
			runClock.setEnabled(false);
			work.makeTimer();
		}
		
		
		else if (e.getSource() == resetClock)
		{
			runClock.setEnabled(true);
			work.reset();
		}
		
		
		else if (e.getSource() == setClock)
		{
			work.setClock(textField.getText());
			textField.setText("");
		}
		
	} //End actionPerformed method	
	

} //End ControlDevices class
