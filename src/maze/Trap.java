package maze;



import java.io.Serializable;
import javax.swing.JButton;

public class Trap extends JButton implements Serializable{
	private static final long serialVersionUID = 1L;
	private int order;//order in which the traps fire
	private XY position;	
	
	public Trap(int o, int x, int y){
		order = o;
		position = new XY(x,y);
		
	}
	
	public Trap(int x, int y){
		position = new XY(x,y);
	}
	
	public int getOrder(){
		return order;
	}
	
	public XY getPosition(){
		return position;
	}
	
	public void setOrder(int o){
		order = o;
	}

	public void setPosition(int x, int y){
		position.setX(x);
		position.setY(y);
	}
	
}
