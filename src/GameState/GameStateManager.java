package GameState;

import java.io.IOException;
import java.util.ArrayList;

import maze.Levels;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int TRANSITIONSTATE = 2;
	public static final int STORYSTATE = 3;
	public static final int LEVELSTATE = 5;
	public static final int WINNINGSTATE = 4;
	private Levels l;
	
	public GameStateManager() throws ClassNotFoundException, IOException {
		l = new Levels();
		gameStates = new ArrayList<GameState>();
		currentState = 0;
		gameStates.add(new MenuState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new TransitionState(this));
		gameStates.add(new StoryState(this));
		gameStates.add(new WinningState(this));
		gameStates.add(new LevelState(this, l));
		
	}
	
	public void setState(int state) {
		currentState = state;
		if (currentState == 2){
			
		}
		gameStates.get(currentState).init();
	}
	
	public void refresh() {
		gameStates.get(currentState).refresh();
		if(gameStates.get(LEVELSTATE).getWon()){
			if(l.getLevelNum()==l.getTotal()){
				currentState = WINNINGSTATE;
			}
			else{
				currentState = TRANSITIONSTATE;
				gameStates.remove(LEVELSTATE);
				gameStates.add(new LevelState(this, l));
				currentState = LEVELSTATE;
				gameStates.get(LEVELSTATE).init();
			}
			
		}
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int e) {
		gameStates.get(currentState).keyPressed(e);
	}
	
	public void keyReleased(int e) {
		gameStates.get(currentState).keyReleased(e);
	}	
}
