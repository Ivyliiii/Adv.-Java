package JavaGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Listeners {
	private final int height = 800, width = 600;
	private int x = width/2, y = height/2;
	private int len = 40;
	private JFrame frame;
	private JPanel canvas;
	
	public Listeners() throws IOException {
		frame = new JFrame();
		canvas = new JPanel(){
			public void paint(Graphics g) {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, len, len);
			}
		};
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(canvas);
		frame.setVisible(true);
		canvas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("up");
					y-=50;
					frame.repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("down");
					y+=50;
					frame.repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("left");
					x-=50;
					frame.repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("right");
					x+=50;
					frame.repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		
		canvas.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				frame.repaint();
			}
			public void mouseClicked(MouseEvent e) {
			};
			public void mouseEntered(MouseEvent e) {};
			public void mouseExited(MouseEvent e) {};
		});
		
		
		canvas.setFocusable(true);
	
	}
	
	public static void main(String[] args) {
		try {
			new Listeners();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
