package maze;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Board implements Serializable{
	private static final long serialVersionUID = 1l;
	private Trap[][] board;//array of spaces on the board
	private int interval = 1000;//interval on which the board updates all obstacle intervals MUST be multiples of this number
	private XY player = new XY(0,0);//the player's location
	private XY key = new XY(3,4);//the key's location
	private int maxOrder = 0;

	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Board b = new Board();
		b = Board.readFile(0);
		System.out.print(b.toString());
		
	}
	
	public int getMaxOrder(){
		return maxOrder;
	}
	
	public void setMaxOrder(int o){
		maxOrder = o;
	}

	public int getInterval(){
		return interval;
	}
	
	public void setPlayer(int x, int y){
		player.setX(x);
		player.setY(y);
	}
	
	public int getKeyX(){
		return key.getX();	
	}
	
	public int getKeyY(){
		return key.getY();
	}
	
	public void setKey(int x, int y){
		key = new XY(x,y);
	}
	

	public void setInterval(int i){
		interval = i;
	}

	public Board(){
		board = new Trap[7][9];
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 7; x++){
				board[x][y] = new Trap(x,y);
			}
		}
	}
	



	public void writeDefault() throws IOException{
		Board d = new Board();
		d.maxOrder = 9;
		d.player = new XY(3,8);
		d.key = new XY(3,4);
		d.interval = 250;
		d.board = new Trap[7][9];
		int order = 9;
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 7; x++){
				d.board[x][y] = new Trap(order, x, y);
			}
			order--;
			d.board[3][8] = null;
		}
		writeToFile(d,"0");
	}


	public Trap getTrap(int x, int y){
		return board[x][y];
	}
	
	public void setTrapNull(int x, int y){
			board[x][y] = null;
	}
	
	public void setTrapOrder(int x, int y, int o){
		board[x][y].setOrder(o);
	}
	
	@Override
	public String toString(){
		String re = new String();//string to be returned
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 7; x++){
				if(board[x][y] != null)
					re = re + board[x][y].getPosition().toString() + " " + board[x][y].getOrder() + "\n";
				else
					re = re + "null" + "\n";
			}
		}
		re = re + "Player Location: " + player + "\n";
		re = re + "Clock Interval: " + interval + "\n";
		re = re + "Key Location: " + key + "\n";
		return re;
	}
	
	
	
	public static void writeToFile(Board b,String lv) throws IOException{
		try {
			ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("Levels\\level" + lv + ".bin"));
			oOS.writeObject(b);
			oOS.close();
		}
		catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}
	
	
	public static Board readFile(int lv) throws FileNotFoundException, IOException, ClassNotFoundException{
		@SuppressWarnings("resource")
		ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("Levels\\level" + lv + ".bin"));
		Board b = (Board)oIS.readObject();
		oIS.close();
		return b;
	}
	
}
