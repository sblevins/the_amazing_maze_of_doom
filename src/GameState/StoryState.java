package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Map.Background;

public class StoryState extends GameState {

	private Background story;
	
	public StoryState(GameStateManager gsm){
		this.gsm = gsm;
		try{
			story = new Background("/story.png",1);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		story.draw(g);
	}

	@Override
	public void keyPressed(int e) {
		// TODO Auto-generated method stub
		if(e == KeyEvent.VK_ENTER){
			gsm.setState(GameStateManager.LEVELSTATE);
		}
	}

	@Override
	public void keyReleased(int e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getWon() {
		// TODO Auto-generated method stub
		return false;
	}

}
