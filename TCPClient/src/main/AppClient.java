package main;

public class AppClient 
{
	private static final String SERVER_HOST = "localhost";
	private static final int CLIENT_PORT = 10122; 
	
	public static void main(String[] args) throws Exception
	{
		TCPClient client = new TCPClient(SERVER_HOST, CLIENT_PORT);	
		client.display();
	}
}

