import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FiveInARow {
	
	int clicked_x, clicked_y;
	public final int P_WIDTH = 700;
	public final int P_HEIGHT = 700;
	public final int BOARDERS = 2;
	public final int LINES = 1;
	public final int ROWS = 15;
	public int lineWidth = P_WIDTH/ROWS;
	public int mode = 1; //mode 1 is single player and mode 2 is multiplayer
	int[][] board = new int[ROWS][ROWS];
	HashSet<int[][]> possibleMoves = new HashSet<int[][]>();
	int computerNum = 2;
	int userNum = 1;
	int currentNum = 1;
	
	public FiveInARow(){
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(P_WIDTH, P_HEIGHT));
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(P_WIDTH, P_HEIGHT));
		panel.setBackground(Color.ORANGE);
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke(new BasicStroke(BOARDERS));
				g2.setStroke(new BasicStroke(LINES));
				for(int i = 0; i < ROWS; i++) {
					g2.drawLine(0, i*lineWidth, P_WIDTH, i*lineWidth);
					g2.drawLine(i*lineWidth, 0, i*lineWidth, P_HEIGHT);
				}
				g.drawLine(P_WIDTH, 0, P_WIDTH, P_HEIGHT);
				g.drawLine(0, P_HEIGHT, P_WIDTH, P_HEIGHT);
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board[i].length; j++) {
						if(board[i][j] == 1) {
							g.setColor(Color.BLACK);
							g.fillOval(i*lineWidth, j*lineWidth, lineWidth-1, lineWidth-1);
						}
						else if (board[i][j] == 2){
							g.setColor(Color.WHITE);
							g.fillOval(i*lineWidth, j*lineWidth, lineWidth-1, lineWidth-1);
						}
					}
				}
			}
		};
		
		canvas.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				clicked_x = e.getX();
				clicked_y = e.getY();
				int[] square = inSquare(clicked_x, clicked_y);
				board[square[0]][square[1]] = currentNum;
				if(currentNum ==1) {
					currentNum = 2;
				}
				else {
					currentNum = 1;
				}
				System.out.println(isWon());
				frame.getContentPane().repaint(); //frame will be redrawn
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		canvas.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'm') {
					if(mode == 1) {
						mode = 2;
					}
					else {
						mode = 1;
					}
				}

			}

			public void keyReleased(KeyEvent e) {}
			
		});
		
		canvas.setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));				
		frame.add(panel);
		panel.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public int[] inSquare(int x, int y) {
		int[] out = new int[2];
		out[0] = x/(lineWidth + BOARDERS);
		out[1] = y/(lineWidth + BOARDERS);
		return out;
	}
	
	public int isWon() {
		// check to see if there are five in a row horizontally and vertically
		int currhor1 = 0;
		int currhor2 = 0;
		int currver1 = 0;
		int currver2 = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 1) {
					currhor1++;
					currhor2 = 0;
				}
				else if(board[i][j] == 2) {
					currhor2++;
					currhor1 = 0;
				}
				if(board[j][i] == 1) {
					currver1++;
					currver2 = 0;
				}
				else if(board[j][i] == 2) {
					currver2++;
					currver1 =0;
				}
				if(currhor1 == 5 || currver1 ==5) {
					return 1;
				}
				else if(currhor2 == 5 || currver2 == 5) {
					return 2;
				}
			}
			currhor1 = 0;
			currhor2 = 0;
			currver1 = 0;
			currver2 = 0;
		}
		int currdia1 = 0;
		int currdia2 = 0;
		int currdia11 = 0;
		int currdia22 = 0;
		// check to see if there are five in a row diagonally downwards
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j+i <board[i].length; j++) {
				if(board[j+i][j] == 1) {
					currdia1++;
					currdia2 = 0;
				}
				else if(board[j+i][j] == 2) {
					currdia2++;
					currdia1 = 0;
				}
				if(board[j][j+i] == 1) {
					currdia11++;
					currdia22 = 0;
				}
				else if(board[j+i][j] == 2) {
					currdia22++;
					currdia11 = 0;
				}
				if(currdia1 == 5 || currdia11 == 5) {
					return 1;
				}
				else if(currdia2 == 5 || currdia22 == 5) {
					return 2;
				}
			}
		
		}
		/*currdia1 = 0;
		currdia2 = 0;
		currdia11 = 0;
		currdia22 = 0;*/
		// check to see if there are five in a row diagonally downwards
		/*for(int i = board.length-1; i >= 0; i--) {
			for(int j = board.length-1-i; i-j >= 0; j--) {
				if(board[i-j][j] == 1) {
					currdia1++;
					currdia2 = 0;
				}
				else if(board[i-j][j] == 2) {
					currdia2++;
					currdia1 = 0;
				}
				if(board[j][i-j] == 1) {
					currdia11++;
					currdia22 = 0;
				}
				else if(board[j][i-j] == 2) {
					currdia22++;
					currdia11 = 0;
				}
				if(currdia1 == 5 || currdia11 == 5) {
					return 1;
				}
				else if(currdia2 == 5 || currdia22 == 5) {
					return 2;
				}
			}
		}
	}*/
	return 0;

	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'm') {
			if(mode == 1) {
				mode = 2;
			}
			else {
				mode = 1;
			}
		}
	}
		
	public void nextMove() {
		possibleMoves = new HashSet<int[][]>();
		int[][] curr = new int[][]{};
		for(int i = 0; i< board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				curr[i][j] = board[i][j];
			}
		}
		
		for(int i = 1; i < board.length-1; i++) {
			for(int j = 1; j < board[i].length-1; j++) {
				for(int x = -1; x <=1; x++) {
					for(int y = -1; y <=1; y++) {
						if(board[x+i][y+j] != 0 && board[i][j] == 0) {
							curr[i][j] = computerNum;
							possibleMoves.add(curr);
							curr[i][j] = 0;
						}
					}
				}
			}
		}
	}
	
	public boolean isLegal(int[][] state) {
		for(int i = 0; i < state.length; i++) {
			for(int j = 0; j < state[i].length; j++) {
				if(state[i][j] == userNum) {
					for(int x = i; x < 4; x++) {
						for(int y = j; y < 4; y++) {
							if(state[i+x][j+y] != userNum) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		FiveInARow run = new FiveInARow();
	}
}
