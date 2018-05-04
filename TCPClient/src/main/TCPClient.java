package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class TCPClient
{
	private Socket clientSocket;  
	private StreamManager streamManager;
    
	private BufferedReader br;
       
    public TCPClient(String hostName, int port) throws IOException
    {
		this.clientSocket = new Socket(Inet4Address.getByName(hostName),port);
		this.streamManager =  new StreamManager(clientSocket);  
		System.out.println("Client running");
	}    
    
    public void display() throws Exception
    {
    	int choice;
        while(true)
        {      
        	/*streamManager.sendString("Hello ! ");
    		
        	streamManager.receiveString();
        	System.out.println("[CLIENT] Received !");*/
        	
            Object calc = new Calc();
            streamManager.sendObject(calc);
            
            // Wait for result
            String result = streamManager.receiveString();
            System.out.println("Result : " + result);
        }
    }
}
