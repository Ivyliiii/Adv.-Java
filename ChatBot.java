package JavaGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ChatBot {
	
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	public final int width = 600, height = 800;
	private JTextArea displayArea, typeArea;
	private boolean entered = false;
	
	private String[] answers = {"No, that is not true", "Hello", "I don't understand what you are talking about"
			,"I mean, what can I say", "......", "I agree"};
 
	public ChatBot() {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				g.setColor(new Color(250, 246, 207));
				g.fillRect(0, height/30, width, 14*height/15);
			}
		};
		
		BoxLayout boxlayout = new BoxLayout(canvas, BoxLayout.Y_AXIS);
		canvas.setLayout(boxlayout);
		
		displayArea = new JTextArea();
		displayArea.setEditable(false);
		
		typeArea = new JTextArea();
		typeArea.setEditable(true);
		typeArea.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					write();
				}
			}
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
		
		JScrollPane scroll = new JScrollPane(displayArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scroll2 = new JScrollPane(typeArea);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scroll.setPreferredSize(new Dimension(width, 9*height/10));
		scroll2.setPreferredSize(new Dimension(width, height - height/10));
		canvas.add(scroll);
		canvas.add(scroll2);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				write();
			}
		});
		
		JPanel innerPanel = new JPanel();
		innerPanel.add(sendButton);
		canvas.add(innerPanel);
		
		frame.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		canvas.setFocusable(true);
		
		displayArea.setText("\n Welcome to AI ChatBot. Start chatting with the robot!");
		
		run();	
		
	}
	
	public void write() {
		if (!typeArea.getText().trim().equals("")) {
			displayArea.setText(displayArea.getText()+"\n\n    "+typeArea.getText().trim());
			entered = true;
		}
		typeArea.setText("");
	}
	
	public void run() {
		while(true) {
			if(entered) {
				try{Thread.sleep(500);}
				catch(InterruptedException e) {}
				int rand = (int)(Math.random()*answers.length);
				displayArea.setText(displayArea.getText() + "\n\n  ChatBot:  " + answers[rand]);
				entered = false;
			}
			try {Thread.sleep(50);}
			catch(InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		new ChatBot();
	}

}
