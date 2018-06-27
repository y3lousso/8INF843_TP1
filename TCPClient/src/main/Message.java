package main;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {

	public String methodName;
	public int arg1;
	public int arg2;
	
	public Message(String methodName, int arg1, int arg2) 
	{
		this.methodName = methodName;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	@Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
