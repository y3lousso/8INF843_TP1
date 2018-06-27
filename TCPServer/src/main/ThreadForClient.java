package main;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ThreadForClient extends Thread 
{
    private StreamManager streamManager;
    
    public ThreadForClient(Socket socket) throws IOException
    {
        this.streamManager = new StreamManager(socket);
    }

    @Override
    public void run() 
    {
    	while(true) 
    	{   	
	    	System.out.println("Waiting for action ...");
    		String choice = streamManager.receiveString();	    	
	    	System.out.println("Received action : " + choice );
	    	
	    	switch(Integer.parseInt(choice)) 
    		{
		    	case(0):
		    		String_Handler();
		    	break;
	    		case(1):
	    			Message_Handler();
	    			break;    			
	    		default:
	    			System.out.println("Invalid choice");
	    			break;    		
    		}		
		}
    }
    
    public void String_Handler() 
    {
    	String rawString = streamManager.receiveString(); 
    	String methodName = rawString.substring(0, 3);
    	System.out.println("method : " + methodName);
    	int arg1 = Integer.parseInt(rawString.substring(4,5));
    	System.out.println("arg1 : "+ arg1);
    	int arg2 = Integer.parseInt(rawString.substring(6,7));  	   	
    	System.out.println("arg2 : "+ arg2);
    	
    	if(methodName.equals("ADD") ) 
    	{
    		streamManager.sendString( Integer.toString(arg1 + arg2));
    	}
    	else if(methodName.equals("MUL") ) 
    	{
    		streamManager.sendString( Integer.toString(arg1 * arg2));
    	}
    	else if(methodName.equals("SUB") ) 
    	{
    		streamManager.sendString( Integer.toString(arg1 - arg2));
    	}else 
    	{
    		streamManager.sendString( "999");
    	}  
    }
    
    public void Message_Handler() 
    {
    	Object obj = streamManager.receiveObject();
    	Class<?> c = obj.getClass();
    	
    	try {   		
			String methodName = (String) c.getField("methodName").get(obj);
			System.out.println("method : " + methodName);
			int arg1 = c.getField("arg1").getInt(obj);
			System.out.println("arg1 : "+ arg1);	
			int arg2 = c.getField("arg2").getInt(obj);    	   	
	    	System.out.println("arg2 : "+ arg2);
				    	
	    	if(methodName.equals("ADD") ) 
	    	{
	    		streamManager.sendString( Integer.toString(arg1 + arg2));
	    	}
	    	else if(methodName.equals("MUL") ) 
	    	{
	    		streamManager.sendString( Integer.toString(arg1 * arg2));
	    	}
	    	else if(methodName.equals("SUB") ) 
	    	{
	    		streamManager.sendString( Integer.toString(arg1 - arg2));
	    	}else 
	    	{
	    		streamManager.sendString( "999");
	    	}  
    	}catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) 
    	{     	
    		e.printStackTrace();
    	}
      
    }
}
