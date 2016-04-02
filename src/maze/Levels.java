
package maze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Levels {
	private ArrayList<Board> levels;
	int level = 0;
	int total = 0;
	public Levels() throws ClassNotFoundException, IOException{
		Board b = new Board();
		b.writeDefault();
		levels = new ArrayList<Board>();
		readLevels();
	}
	
	public int getTotal(){
		return total;
	}
	
	public int getLevelNum(){
		return level;
	}
	public Board getLevel(){
		Board temp = levels.get(level);
		level++;
		return temp;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		Levels l = new Levels();
		System.out.print(l.toString());
	}
	
	@Override
	public String toString(){
		int cur = 0;
		String re = new String();//string to be returned
		while(levels.get(cur) != null){
			re = re + levels.get(cur).toString();
			re = re + "Level: " + cur + "\n\n";
			re = re + "~~~~~~~~~~~~~~~~~~~~~~~\n\n";
			cur++;
		}
		re = re + "Levels: " + cur + "\n";
		return re;
	}
	
	private void readLevels() throws IOException, ClassNotFoundException{
		boolean cont = true;
		while(cont){
			try{
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("Levels\\level" + total + ".bin"));
				levels.add((Board)oIS.readObject());
				oIS.close();
				total++;
			}
			catch (FileNotFoundException e){
				levels.add(null);
				cont = false;
			}
			
		}
		
	}
}
