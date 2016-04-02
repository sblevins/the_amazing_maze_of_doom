package Map;

import maze.GamePanel;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Background {
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;		
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void setPos(double x, double y) {
		this.x = (x*moveScale) % maze.GamePanel.WIDTH;
		this.y = (y*moveScale) % maze.GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void refresh() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		if(g == null) {
			System.out.println("G is null");
			System.exit(0);
		}
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < -720) {
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y,null);
		}
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}

}
