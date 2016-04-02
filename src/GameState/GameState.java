package GameState;

import maze.Levels;
import Map.Background;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected Background p1;
	public abstract void init();
	public abstract void refresh();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int e);
	public abstract void keyReleased(int e);
	public abstract boolean getWon();
	
	
}
