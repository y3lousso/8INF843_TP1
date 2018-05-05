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
    		case(1):
    			ObjectCollServer();
    			break;    			
    		default:
    			System.out.println("Invalid choice");
    			break;    		
    		}		
		}
    }
    
    public void ObjectCollServer() 
    {
    	Object obj = streamManager.receiveObject();
		String methodName = streamManager.receiveString(); 
		String arg1 = streamManager.receiveString(); 
		String arg2 = streamManager.receiveString(); 

        Class<?> c = obj.getClass();
        Class[] argTypes = new Class[] { String.class, String.class };
        
        Method method = null;
        try {
            method = c.getDeclaredMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            System.err.println("Method not found");
            e.printStackTrace();
        }
        try {
            assert method != null;
            streamManager.sendString(method.invoke(obj, (Object)arg1, (Object)arg2).toString());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
    	}
    }
}
