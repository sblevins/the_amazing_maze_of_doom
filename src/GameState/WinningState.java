package GameState;

import java.awt.Graphics2D;
import Map.Background;
import java.awt.event.KeyEvent;

public class WinningState extends GameState {

	private Background ending;
	
	public WinningState(GameStateManager gsm){
		this.gsm = gsm;
		try{
			ending = new Background("/ending.png",1);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() {


	}

	@Override
	public void refresh() {

		
	}

	@Override
	public void draw(Graphics2D g) {

		ending.draw(g);
	}

	@Override
	public void keyPressed(int e) {

		if(e == KeyEvent.VK_ENTER){
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(int e) {


	}

	@Override
	public boolean getWon() {
		return false;
	}

}
