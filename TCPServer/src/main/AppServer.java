package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

	private static final int SERVER_PORT = 10122; 
	
	@SuppressWarnings("resource")
	public static void main(String args[])
    {
		ServerSocket serverSocket = null;
		
        System.out.println("Launching Server");

        try 
        {
            serverSocket = new ServerSocket(SERVER_PORT);
        } 
        catch (IOException e) 
        {
            System.err.println("Error launching Server");
            e.printStackTrace();
        }

        System.out.println("Server running");

        while (true) 
        {
			try {
				Socket clientSocket = serverSocket.accept();
				ThreadForClient threadForClient = new ThreadForClient(clientSocket);
	            threadForClient.run();
			} catch (IOException e) {
				System.err.println("Error launching Server");
				e.printStackTrace();
			}            
        }
	} 
}

