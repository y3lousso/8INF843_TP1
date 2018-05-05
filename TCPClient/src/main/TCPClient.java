package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Client running");
	}    
    
    public void display() throws Exception
    {
    	while(true)
        { 
    		System.out.println("Select your mode :");
    		System.out.println("1. OBJECTColl");
    		String choice = br.readLine();
    	
    		switch(Integer.parseInt(choice)) 
    		{
	    		case(1):
	    			System.out.println("OBJECTColl Selected");
	    			streamManager.sendString("1");
	    			String functionName, firstArg, secondArg;
	    			System.out.println("Enter function name :");
	    			functionName = br.readLine();
	    			System.out.println("Enter first arg :");
	    			firstArg = br.readLine();
	    			System.out.println("Enter second arg :");
	    			secondArg = br.readLine();
	    			ObjectCollClient(functionName,firstArg,secondArg);
	    			break;    			
	    		default:
	    			System.out.println("Invalid choice");
	    			break;    		
    		}            
        }
    }
    
    public void ObjectCollClient(String functionName, String firstArg, String secondArg) 
    {
		Object calc = new Calc();
        streamManager.sendObject(calc); 
        streamManager.sendString(functionName);
        streamManager.sendString(firstArg);
        streamManager.sendString(secondArg);
        
        String result = streamManager.receiveString(); 
        System.out.println("Result : " + result);
    }    
    
}
