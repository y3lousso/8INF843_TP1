package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.Socket;

public class Stub implements CalculatriceItf{
		
	private Socket clientSocket;  
	public StreamManager streamManager;
    
	private BufferedReader br;
       
    public Stub(String hostName, int port) throws IOException
    {
		this.clientSocket = new Socket(Inet4Address.getByName(hostName),port);
		this.streamManager =  new StreamManager(clientSocket); 
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Client running");
	}   
    
	@Override
	public int addition(int arg1, int arg2) {
		streamManager.sendString("ADD:"+arg1+"/"+arg2);		
		return Integer.parseInt(streamManager.receiveString());	
	}

	@Override
	public int multiplication(int arg1, int arg2) {
		streamManager.sendString("MUL:"+arg1+"/"+arg2);	
		return Integer.parseInt(streamManager.receiveString());	
	}

	@Override
	public int soustraction(int arg1, int arg2) {
		streamManager.sendString("SUB:"+arg1+"/"+arg2);	
		return Integer.parseInt(streamManager.receiveString());	
	}
	
	public int sendMessage(Message msg) {
		streamManager.sendObject(msg);
		return Integer.parseInt(streamManager.receiveString());	
	}
	
}
