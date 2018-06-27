package main;

public class AppClient 
{
	private static final String SERVER_HOST = "localhost";
	private static final int CLIENT_PORT = 10122; 
	
	public static void main(String[] args) throws Exception
	{
		Stub s = new Stub(SERVER_HOST, CLIENT_PORT);	
		
		s.streamManager.sendString("0");  // 0 select the String Handler
		int r1 = s.addition(3, 5);		
		System.out.println("Result 3 + 5 = " + r1);
		
		s.streamManager.sendString("0");  // 0 select the String Handler
		int r2 = s.multiplication(3, 3);		
		System.out.println("Result 3 * 3 = " + r2);
		
		s.streamManager.sendString("0");  // 0 select the String Handler
		int r3 = s.soustraction(5, 3);		
		System.out.println("Result 5 - 3 = " + r3);
		
		s.streamManager.sendString("1");  // 1 select the Object Handler
		Message m1 = new Message("ADD",3,5);
		int r4 = s.sendMessage(m1);		
		System.out.println("Result 3 + 5 = " + r4);
		
		s.streamManager.sendString("1");  // 1 select the Object Handler
		Message m2 = new Message("MUL",3,5);
		int r5 = s.sendMessage(m1);	
		System.out.println("Result 3 * 3 = " + r5);
		
		s.streamManager.sendString("1");  // 1 select the Object Handler
		Message m3 = new Message("SUB",3,5);
		int r6 = s.sendMessage(m1);	
		System.out.println("Result 5 - 3 = " + r6);
		
		System.out.println("All good !");
	}
}

