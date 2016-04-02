package maze;

import java.io.Serializable;

public class XY implements Serializable{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public XY(int X, int Y){
		x = X;
		y = Y;
	}
	public int getX(){		
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int s){
		x = s;
	}
	
	public void setY(int s){
		y = s;
	}
	
	@Override
	public String toString(){
		return x + "," + y;
	}
}
