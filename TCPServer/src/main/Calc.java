package main;

import java.io.Serializable;

public class Calc implements Serializable {
	
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
    
	public int add(String a, String b){
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return x + y;
	}
	
	public int mul(String a, String b){
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		return x * y;
	}
	
}
