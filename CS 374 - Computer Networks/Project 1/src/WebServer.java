/*
 *  Montana Singer
 *  CS 374
 *  Spring 2021
 *  24 February 2021
 *  
 */


import java.net.* ;

public final class WebServer 
{

	public static void main(String[] args) throws Exception
	{
		// Set the port number.
		int port = 6789;

		// Establish the listen socket.
		ServerSocket listen = new ServerSocket(port); 

		// Process HTTP service requests in an infinite loop.
		while (true) 
		{
			// Listen for a TCP connection request.
			// Connection received
			if (listen.isBound()) 
			{
				// Construct an object to process the HTTP request message.
				HttpRequest request = new HttpRequest(listen.accept());

				// Create a new thread to process the request.
				Thread thread = new Thread(request);

				// Start the thread.
				thread.start();
				listen.close();
				listen = new ServerSocket(port);
			}
			
		} // End while loop

	} // End main method

} // End WebServer class
