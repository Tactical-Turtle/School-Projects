package singmm01_CS161_project3;


/*
* Montana Singer
* CS 161
* Spring 2019
* Project 3
*
*/


//Instantiates class objects for testing
public class Test {

	public static void main(String[] args) {
		
		ClockWork cw = new ClockWork();
		
		ControlDevices control = new ControlDevices(cw);
		
		FaceGUI gui = new FaceGUI(cw);
		
		control.setVisible(true);
		gui.setVisible(true);

	} //End main method

} //End Test class
