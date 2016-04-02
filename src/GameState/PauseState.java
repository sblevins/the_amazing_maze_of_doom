package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Map.Background;
import java.awt.event.KeyEvent;

public class PauseState extends GameState {

	private int current = 0;
	private Background back;
	private String[] options = {
			"Resume",
			"Main Menu"
	};
	private Color color;
	private Font title;
	private Font list;
	
	public PauseState(GameStateManager gsm){
		this.gsm = gsm;
		try {
			back = new Background("/Backgrounds/background.png", 1);
			color = new Color(218, 0, 0);
			title = new Font("Century Gothic", Font.PLAIN, 60);
			list = new Font("Century Gothic", Font.PLAIN, 40);	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void init() {
		// leave empty
		
	}

	@Override
	public void refresh() {
		// refresh screen
		back.refresh();
	}

	@Override
	public void draw(Graphics2D g) {
		//display background
		//back.draw(g);
		//write out header
		g.setColor(color);
		g.setFont(title);
		g.drawString("Paused", 500, 250);
		
		//print out options
		g.setFont(list);
		for(int i = 0; i < options.length; i++) {
			if (i == current) {
				g.setColor(Color.CYAN);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 500, 350 + i*50);
		}
	}

	private void select() {
		//chose resume
		if(current == 0) {
			//resume game
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		//chose return to main menu
		if(current == 1){
			//change state to main menu
			gsm.setState(GameStateManager.MENUSTATE);
		}
		
	}
	
	@Override
	public void keyPressed(int e) {
		// enter key pressed
		if(e == KeyEvent.VK_ENTER){
			select();
		}
		//up key pressed
		if(e == KeyEvent.VK_UP){
			current--;
			//if was already at top of list, go to bottom of list
			if (current == -1){
				current = options.length-1;
			}
		}
		//down key pressed
		if(e == KeyEvent.VK_DOWN){
			current++;
			//if was already at bottom of list, go to first item on list
			if (current == options.length){
				current = 0;
			}
		}
	}

	@Override
	public void keyReleased(int e) {
		// No events occur when a key is released
		
	}
	
	public void reset() {
		//reset choice to top choice
		current = 0;
	}


	@Override
	public boolean getWon() {
		return false;
	}
	
}