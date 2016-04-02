package GameState;

import Map.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MenuState extends GameState {
	private Background bg;
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Help",
			"Editor",
			"Quit"	
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	/*
	 * Set up the background.png to bg
	 * set the font and color
	 */
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/background.png", 1);
			titleColor = new Color(218 ,0 , 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 60);
			font = new Font("Arial", Font.PLAIN, 40);	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		//leave empty
	}
	
	/*
	 * Draws the background 
	 * Writes out the title and the options.
	 * Highlights whatever option you are on.
	 */
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("The Amazing Maze of Doom", 250, 150);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice) {
				g.setColor(Color.GREEN);
			}else{
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 250, 250+ i * 50);
		}
		if(currentChoice==1){
			g.drawString("Find the golden key",400,250);
			g.drawString("Avoid the Traps!",400,300);
			g.drawString("Get to the Exit", 400, 350);
			g.drawString("WASD or Arrows to move and P to pause", 400, 400);	
		}
	}
	
	/*Selection
	 * The menu selection method.
	 * Start the game and exit the game from here.
	 */
	//test
	
	public void select(){
		if(currentChoice == 0){
			//start
			gsm.setState(GameStateManager.STORYSTATE);
			try {
				new PlayAudio(0);
			} catch (UnsupportedAudioFileException | IOException
					| LineUnavailableException e) {
				
				e.printStackTrace();
			}
		}
		if(currentChoice == 1){
			//help
		}
		if(currentChoice == 2){
			new LevelEditor();
			
			
		}
		if(currentChoice == 3){
			System.exit(0);
			//exit
		}
	}
	
	/*
	 * Refreshes screen in case background has movement.
	 * 
	 */
	
	public void refresh() {	
		bg.refresh();
	}
	
	/*
	 * KeyListener functions
	 * Lets you navigate the main menu
	 * 
	 */
	public void keyPressed(int e) {
		if(e == KeyEvent.VK_ENTER){
			select();
		}
		if(e == KeyEvent.VK_UP || e == KeyEvent.VK_W){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if(e == KeyEvent.VK_DOWN || e == KeyEvent.VK_S){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int e) {
	}

	@Override
	public boolean getWon() {
		return false;
	}
}
