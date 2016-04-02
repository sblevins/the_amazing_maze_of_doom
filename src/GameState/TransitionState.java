package GameState;

import java.awt.Graphics2D;

import Map.Background;

public class TransitionState extends GameState {
	private Background back;
	
	public TransitionState(GameStateManager gsm){
		this.gsm = gsm;
		try {
			back = new Background("/Backgrounds/background.png", 1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {


	}

	@Override
	public void refresh() {

		back.refresh();
	}

	@Override
	public void draw(Graphics2D g) {


	}

	@Override
	public void keyPressed(int e) {


	}

	@Override
	public void keyReleased(int e) {


	}

	@Override
	public boolean getWon() {

		return false;
	}

}
