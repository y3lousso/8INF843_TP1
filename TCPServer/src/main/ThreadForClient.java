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
    	int n = 0;
    	while(true) 
    	{
    		/*System.out.println("[SERVER] WAiting for new client input ...");
    		String s = streamManager.receiveString();
    		
    		streamManager.sendString(s + " OK");*/
    		
    		Object obj = streamManager.receiveObject();
    		String methodName = "add"; 
    		String arg1 = "5";
    		String arg2 = "6";

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
}
