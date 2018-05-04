package main;

import java.net.*;
import java.io.*;

class TCPServer
{
	private ServerSocket serverSocket;

	public TCPServer(int serverPort)
	{
        System.out.println("Launching Server");

        try 
        {
            serverSocket = new ServerSocket(serverPort);
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

