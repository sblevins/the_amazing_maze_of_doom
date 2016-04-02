package GameState;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import maze.Board;



public class LevelEditor {
	private  JFrame window = new JFrame("Level Editor");
	private Board b = new Board();//data to be exported
	JLabel back;//the background
	private JLabel[][] labels;//array of tiles
	int xSelected = 0;//location of selected tile
	int ySelected = 0;
	JTextField interval;//text field for editing timer interval
	JButton key;//adds key to selected tile
	JTextField order;//inputs order in which the traps fire
	JLabel location;//displays selected trap in edit section
	Handler theHandler = new Handler();//handles user input
	JPanel p = new JPanel();
	JButton Null = new JButton("NULL");//button to set a trap to null
	JButton ok = new JButton("OK");//button to push input to interface
	JButton save = new JButton("Save");//button saves map to binary file
	JTextField name = new JTextField("");
	private int maxOrder = 0;
	
	LevelEditor(){
		p.setLayout(null);
		labels = new JLabel[7][9];
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setSize(700, 654);
		back = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/editboard.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.setSize(500,615);
		createLabels();		
		p.add(back);
		createInput();
		window.add(p);
		window.validate();
		window.setVisible(true);
		
	}
	
	public void createLabels(){//adds labels to screen, multiple for loops help keep the labels in line.
		for(int y=0;y<3;y++){
			for(int x=0;x<7;x++){
				labels[x][y] = new JLabel("NULL");
				labels[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				labels[x][y].setForeground(Color.RED);
				labels[x][y].setSize(57,57);
				labels[x][y].setBackground(Color.WHITE);
				labels[x][y].setOpaque(true);
				labels[x][y].setLocation(29+x*64,43+y*64);
				labels[x][y].setVisible(true);
				back.add(labels[x][y]);	
			}
		}
		for(int y=3;y<7;y++){
			for(int x=0;x<7;x++){
				labels[x][y] = new JLabel("NULL");
				labels[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				labels[x][y].setForeground(Color.RED);
				labels[x][y].setBackground(Color.WHITE);
				labels[x][y].setSize(57,57);
				labels[x][y].setOpaque(true);
				labels[x][y].setLocation(29+x*64,44+y*64);
				labels[x][y].setVisible(true);
				back.add(labels[x][y]);	
			}
		}
		for(int y=7;y<9;y++){
			for(int x=0;x<7;x++){
				labels[x][y] = new JLabel("NULL");
				labels[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				labels[x][y].setForeground(Color.RED);
				labels[x][y].setBackground(Color.WHITE);
				labels[x][y].setSize(57,57);
				labels[x][y].setOpaque(true);
				labels[x][y].setLocation(29+x*64,45+y*64);
				labels[x][y].setVisible(true);
				back.add(labels[x][y]);	
			}
		}
		
		
	}
	
	public void createInput(){		
		Null.addActionListener(theHandler);
		Null.setSize(100,20);
		Null.setLocation(530,140);
		ok.addActionListener(theHandler);
		ok.setSize(75,20);
		ok.setLocation(542,170);
		JLabel intervalLabel = new JLabel("Interval");
		intervalLabel.setSize(50,20);
		intervalLabel.setLocation(530,50);
		JLabel orderLabel = new JLabel("Order");
		orderLabel.setSize(50,20);
		orderLabel.setLocation(540,75);
		window.addMouseListener(theHandler);
		order = new JTextField("1");
		order.addActionListener(theHandler);
		order.setSize(50,20);
		order.setLocation(580,75);
		key = new JButton("Add Key");
		key.addActionListener(theHandler);
		key.setSize(100,20);
		key.setLocation(530,110);
		interval = new JTextField("1000");
		interval.addActionListener(theHandler);
		interval.setSize(50,20);
		interval.setLocation(580,50);
		location = new JLabel("No Location Selected");
		location.setSize(300,100);
		location.setLocation(530,-25);
		name.setSize(100,20);
		name.setLocation(570,530);
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setSize(50,20);
		nameLabel.setLocation(520,529);
		save.addActionListener(theHandler);
		save.setSize(100,20);
		save.setLocation(530, 565);
		p.add(save);
		p.add(nameLabel);
		p.add(name);
		p.add(Null);
		p.add(ok);
		p.add(orderLabel);
		p.add(intervalLabel);
		p.add(interval);
		p.add(key);
		p.add(order);
		p.add(location);
		p.setVisible(true);
		window.add(p);
	}
	
	public boolean export() throws IOException{
		int key = 0;//used to detect if there is more than 1 key on the editor
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 9; y++){
				if(labels[x][y].getText()=="KEY")
					key++;
			}
		}
		if(key>1)
			return false;
		for(int x = 0; x <7; x++){
			for(int y = 0; y < 9; y++){
				if(labels[x][y].getText()=="KEY"){
					b.setKey(x, y);
					b.setTrapNull(x, y);
				}
				else if(labels[x][y].getText()=="NULL"){
					b.setTrapNull(x,y);
				}
				else{
					
					try {
						b.setTrapOrder(x, y, Integer.parseInt(labels[x][y].getText()));
					} 
					catch(NumberFormatException ex){
						System.out.println("Trap"+x+","+y+"has been set to null since there wasn no valid input");
					}
				}
			}
			try {
				int inter = Integer.parseInt(interval.getText());
				b.setInterval(inter);
			} catch(NumberFormatException ex){
				System.out.println("Must enter a number in interval field");
			}	
			b.setMaxOrder(maxOrder);
		}
		System.out.println("attempting to write file");
		Board.writeToFile(b, name.getText());
		System.out.println("file successfully written");
		return true;
	}
	
	public static void main(String[] args){
		new LevelEditor();
	}
	
	
	private class Handler implements ActionListener, MouseListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==Null){
				labels[xSelected][ySelected].setText("NULL");
			}
			else if(e.getSource()==ok&&labels[xSelected][ySelected]!=null){
					
				
				try {
					int ord = Integer.parseInt(order.getText());
					labels[xSelected][ySelected].setText(""+ord);
					if(ord>=maxOrder)
						maxOrder = ord;

				} catch(NumberFormatException ex){
					System.out.println("Must enter a number in order field");
				}
			}
			else if(e.getSource()==key&&labels[xSelected][ySelected]!=null){
				labels[xSelected][ySelected].setText("KEY");
			}
			else if(e.getSource()==save){
				try {
					if(!export())
						System.out.print("export failed");
				} catch (IOException e1) {
					System.out.println("fml");
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//leave empty
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// leave empty
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// leave empty
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int tempX = 0;
			int tempY = 0;
			if((e.getX()<481&&e.getY()<644)&&e.getX()>29&&e.getY()>68){
				if(labels[xSelected][ySelected]!=null){
					tempX = xSelected;
					tempY = ySelected;
				}
					xSelected = (e.getX()-33)/64;
					ySelected = (e.getY()-68)/64;
					if(labels[xSelected][ySelected]!=null)
						labels[tempX][tempY].setBackground(Color.WHITE);
					labels[xSelected][ySelected] = labels[(e.getX()-33)/64][(e.getY()-68)/64];
					labels[xSelected][ySelected].setBackground(Color.BLUE);
				}
			}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
	}
}
