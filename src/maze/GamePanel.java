package maze;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import GameState.GameStateManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	public static final long serialVersionUID = 1L;
	
	//Dimensions
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int SCALE = 1;
	
	//Game thread
	private Thread thread;
	private boolean running;
	
	//Images YEEEE
	private BufferedImage image;
	private Graphics2D g;
	
	//Game State Manager
	private GameStateManager gsm;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void init() throws ClassNotFoundException, IOException {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}
	public void addNotify(){
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	public void refresh() {
		gsm.refresh();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	public void run() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//long start;
		
		//loop for game
		while(running){
			//start = System.nanoTime();
			refresh();
			draw();
			drawToScreen();
		}	
	}
	
	public void keyTyped(KeyEvent e){
		//empty
	}
	
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e){
		gsm.keyReleased(e.getKeyCode());
	}
}

