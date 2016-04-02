package GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import Map.Background;
import maze.Board;
import maze.Levels;


public class LevelState extends GameState implements ActionListener{
	private Background back;
	private Background stage;
	private Background key;
	private Timer t;
	private int spd = 2;
	private boolean hasKey = false;
	private Board b;
	private boolean won = false;
	int step = 1;//variable holds the step in which the spikes go off
	/*for example a first row of spikes goes off when the game starts because step is equal to 1 so the spikes
	 * with an order of 1 will fire, and step will increase by 1, then traps with ID 2 will fire and traps with ID 1 will dissapear
	 * 
	 * */
	int maxStep;//the last traps to go off
	Background spikes [][];//hold all of the spike pictures
	
	public LevelState(GameStateManager gsm, Levels l) {
		this.gsm = gsm;
		try{
			spikes = new Background[7][9];
			b = l.getLevel();
			t = new Timer(b.getInterval(), this);//initializing timer	
			//t.start();
			key = new Background("/key.png", 1);
			back = new Background("/Backgrounds/background.png", 1);
			stage = new Background("/board.png", 1);
			p1 = new Background("/jimmy.png",1);
			p1.setPos(595, 656);
			new Color(255, 255, 255);
			new Font("Helvetica", Font.PLAIN, 60);
			for(int x = 0; x < 7; x++){
				for(int y = 0; y < 9; y++){
					if(b.getTrap(x, y) != null)
						spikes[x][y] = new Background("/spikes.png", 1);	
				}		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
}
	

	@Override
	public void actionPerformed(ActionEvent arg0){//function set off by timer going off
		if(step >= b.getMaxOrder())
			step = 1;
		else
			step++;
	}
	
	public boolean getWon(){
		return won;
	}
	public void draw(Graphics2D g) {
		back.draw(g);
		stage.draw(g);
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 9; y++){
				if(b.getTrap(x, y)!=null){
					if(b.getTrap(x, y).getOrder() == step){//it is this traps turn to go off
						spikes[x][y].setPos(385 + x*64, 79+y*64);
						spikes[x][y].draw(g);
						
					}
				}				
			}

		}
		key.draw(g);
		key.setPos(385 + b.getKeyX() * 64, 79 + b.getKeyY() * 64);
		p1.draw(g);
		
	}
	
	public void init() {
		t.start();
	}
	
	public void refresh() {
		p1.refresh();
		int midX = (int) (p1.getX()+16);
		int midY = (int) (p1.getY()+20);
		if(up.pressed() && p1.getY() <= 714 && p1.getY() >= -5){
			p1.setPos(p1.getX(), p1.getY() - spd);			
		}
		if(down.pressed() && p1.getY() <= 656 && p1.getY() >= -20){
			p1.setPos(p1.getX(), p1.getY() + spd);			
		}
		if(left.pressed() && p1.getX() <= 945 && p1.getX() >= 388){
			p1.setPos(p1.getX() - spd, p1.getY());
		}
		if(right.pressed() && p1.getX() <= 801 && p1.getX() >= 380){
			p1.setPos(p1.getX() + spd, p1.getY());
		}
		b.setPlayer(playerX(midX), playerY(midY));
		if(midY < 626 && midY > 79)
			death(midX, midY);
		pickUpKey(midX, midY);
		if(hasKey){
			key.setPos(1060, 250);
		}
		winCondition(midX, midY);
		
	}
	
	public void keyPressed(int e) {
		// TODO Auto-generated method stub
		if(e == KeyEvent.VK_UP || e == KeyEvent.VK_W){
			up.toggle(true);
		}
		if(e == KeyEvent.VK_DOWN || e == KeyEvent.VK_S){
			down.toggle(true);
		}
		if(e == KeyEvent.VK_LEFT || e == KeyEvent.VK_A){
			left.toggle(true);
		}
		if(e == KeyEvent.VK_RIGHT || e == KeyEvent.VK_D){
			right.toggle(true);
		}
		//Pause Button
		if(e == KeyEvent.VK_P){
			t.stop();
			gsm.setState(GameStateManager.PAUSESTATE);
		}
		
	}
	
	public void keyReleased(int e) {
		// TODO Auto-generated method stub
		if(e == KeyEvent.VK_UP || e == KeyEvent.VK_W){
			up.toggle(false);
		}
		if(e == KeyEvent.VK_DOWN || e == KeyEvent.VK_S){
			down.toggle(false);
		}
		if(e == KeyEvent.VK_LEFT || e == KeyEvent.VK_A){
			left.toggle(false);
		}
		if(e == KeyEvent.VK_RIGHT || e == KeyEvent.VK_D){
			right.toggle(false);
		}
		
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public class Key {
		public boolean isPressed = false;
		public void toggle(boolean pressed){
			isPressed = pressed;
		}
		public boolean pressed(){
			return isPressed;
		}
	}
	
	//Returns the x coordinate of the player
	public int playerX(int midX){
		if(midX < 450){
			return 0;
		}
		if(midX >= 450 && midX < 514){
			return 1;
		}
		if(midX >= 514 && midX < 578){
			return 2;
		}
		if(midX >= 578 && midX < 642){
			return 3;
		}
		if(midX >= 642 && midX < 706){
			return 4;
		}
		if(midX >= 706 && midX < 770){
			return 5;
		}
		if(midX >= 770 && midX < 834){
			return 6;
		}
		else
			return 9; 
	}
	
	//returns Jimmy's Y Location.
	public int playerY(int midY){
		if(midY >= 562 && midY < 626 ){
			return 8;
		}
		if(midY >= 498 && midY < 562 ){
			return 7;
		}
		if(midY >= 434 && midY < 498 ){
			return 6;
		}
		if(midY >= 370 && midY < 434 ){
			return 5;
		}
		if(midY >= 306 && midY < 370 ){
			return 4;
		}
		if(midY >= 242 && midY < 306 ){
			return 3;
		}
		if(midY >= 178 && midY < 242 ){
			return 2;
		}
		if(midY >= 114 && midY < 178 ){
			return 1;
		}
		if(midY >= 50 && midY < 114 ){
			return 0;
		}
		else
			return 9;
	}
	
	public void death(int x, int y){
		if(b.getTrap(playerX(x), playerY(y)) != null){
			if(playerX(x) == b.getTrap(playerX(x), playerY(y)).getPosition().getX() && 
				playerY(y) == b.getTrap(playerX(x), playerY(y)).getPosition().getY() && 
				step == b.getTrap(playerX(x), playerY(y)).getOrder()) {
					p1.setPos(595, 656);
					hasKey = false;
					key.setPos(385 + b.getKeyX() * 64, 79 + b.getKeyY() * 64);
					
					
					try {
						new PlayAudio(1);
					} catch (UnsupportedAudioFileException | IOException
							| LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	public void pickUpKey(int x, int y){
		if(playerX(x) == b.getKeyX() &&  playerY(y) == b.getKeyY()){
			hasKey = true;
		}
	}
	
	public void winCondition(int x, int y){
		if(hasKey == true && x > 589 && x < 641 && y < 62 ){
			won = true;
		}
	}
}
