import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class maze {
	private final int height = 600, width = 800;
	boolean[][] maze = new boolean[20][20];
	int startBlock;
	boolean run = true;
	double random = 0;
	JPanel canvas; 
	JFrame frame;
	int rect = 10;
	boolean legs = false;
	
	public maze() {
		System.out.println("maze");
		extendFromFront();
	}
	
	public void extendFromFront() {
		maze = new boolean[20][20];
		int startBlock = (int)(Math.random()*(maze.length-2)) + 1;
		int w = 1; 
		int h = startBlock;
		int endBlock = (int)(Math.random()*(maze.length-2)) + 1;
		int w_e = maze.length;
		int h_e = endBlock;
		maze[h][w-1] = true;
		maze[h][w] = true;
		while(run) {
			random = Math.random() * 10;
			for(int i = 0; i < maze.length; i++) {
				for(int j = 0; j < maze[i].length; j++) {
					if(maze[i][j]) {
						map(h,w);
						System.out.println("mapped");
					}
				}
			}
			for(int i = 1; i < maze.length; i++) {
				for(int j = 1; j < maze[i].length; j++) {
					if(maze[i][j]) {
						legs = true;
						map(h, w);
					}
				}
			}
			drawMaze();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void map(int h, int w) {
		boolean happened = false;
		while (run()) {
			try {
				if(toFront()) {
					if(run() | legs == true) {
						random = Math.random() * 10;
						w += 1;
						if(maze[h][w]) {
							w+=1;
						}
					}
					happened = true;
				}
				maze[h][w] = true;
				if(toLeft() && h > 0) {
					System.out.println("hii");
					random = Math.random() * 10;
					h -= 1;
					happened = true;
				}
				maze[h][w] = true;
				if(toRight() && maze.length-h > 0) {
					h += 1;
					happened = true;
				}
				maze[h][w] = true;
				if(!happened) {
					maze[h][w+1] = true;
					random = Math.random() * 10;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				extendFromFront();
			}
		}
	}
	
	public boolean run() {
		for(int i = 0; i < maze.length; i++) {
			if(maze[i][maze.length-1]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean toFront() {
		//(int)random%2 == 0
		if(random < 6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean toRight() {
		if(random > 4 && random < 9) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean toLeft() {
		if(random > 8) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean surroundingTrue(int w, int h) {
		if(maze[w+1][h] && maze[w][h+1] && maze[w-1][h] && maze[w-1][h]) {
			return true;
		}
		return false;
	}
	
	public void drawMaze() {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				if(maze[i][j]) {
					System.out.print("0 ");
				}
				else {
					System.out.print("1 ");
				}
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("maze printed");
	}
	
	public static void main(String[] args) {
		new maze();
	}

}
