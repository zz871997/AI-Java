package Search_Engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Tran Qauang Khai
 *	Test DepthDirstSearchEngine with UI (Swing)
 */

public class MazeDepthFirstSearch extends JFrame{
	JPanel panel1 = new JPanel();
	DepthFirstSearchEngine currentSearchEngine = null;
	
	public MazeDepthFirstSearch() {
		try{
			jbInit();
		}
		catch (Exception e){
			System.out.println("GUI initilization error " + e);
		}
		currentSearchEngine = new DepthFirstSearchEngine (10, 10);
		repaint();
	}
	
	public void paint (Graphics g_unused){
		if(currentSearchEngine == null) return;
		Maze maze = currentSearchEngine.getMaze();
		
		int width = maze.getWidth();
		int height = maze.getHeight();
		System.out.println("Size of current maze: " + width + " x " + height);
		
		Graphics g = panel1.getGraphics();
		BufferedImage image = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = image.getGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 320, 320);
		g2.setColor(Color.BLACK);
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				short value = maze.getValue(x, y);
				if(value == Maze.OBSTICLE){
					g2.setColor(Color.lightGray);
					g2.fillRect(6 + x*29, 3 + y*29, 29, 29);
					g2.setColor(Color.black);
					g2.drawRect(6 + x*29, 3 + y*29, 29, 29);
				}
				else if(value == Maze.START_LOC_VALUE || value == 1){
					g2.setColor(Color.blue);
					g2.drawString("S", 16 + x*29, 19 + y*29);
					g2.setColor(Color.black);
					g2.drawRect(6 + x*29, 3 + y*29, 29, 29);
				}
				else if(value == Maze.GOAL_LOC_VALUE){
					g2.setColor(Color.RED);
					g2.drawString("G", 16 + x*29, 19 + y*29);
					g2.setColor(Color.black);
					g2.drawRect(6 + x*29, 3 + y*29, 29, 29);
				}
				else{
					g2.setColor(Color.BLACK);
					g2.drawRect(6 + x*29, 3 + y*29, 29, 29);
				}
			}
		}
		
		//Redraw the path in black
		g2.setColor(Color.black);
		Location[] path = currentSearchEngine.getPath();
		for(int i = 1; i < path.length; i++){
			int x = path[i].x;
			int y = path[i].y;
			short value = maze.getValue(x, y);
			g2.drawString("" + value, 16 + x*28, 19 + y*29);
		}
		g.drawImage(image, 30, 40, 320, 320, null);
	}
	
	public static void main(String[] args) {
		new MazeDepthFirstSearch();
	}
	
	
	// jbInit
	private void jbInit() throws Exception {
		this.setContentPane(panel1);
		this.setCursor(null);	// set Cursor
		this.setDefaultCloseOperation(3);
		this.setTitle("Maze Depth First Search");
		this.getContentPane().setLayout(null);
			
		panel1.setBackground(Color.WHITE);
		panel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		panel1.setDoubleBuffered(false);
		panel1.setRequestFocusEnabled(false);
		panel1.setLayout(null);
		this.setSize(370, 420);
		this.setVisible(true);
	}
	
}
