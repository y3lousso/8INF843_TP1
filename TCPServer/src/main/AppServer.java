package main;

public class AppServer {

	private static final int SERVER_PORT = 10122; 
	
	public static void main(String args[])
    {
		new TCPServer(SERVER_PORT);
    }
	
}

