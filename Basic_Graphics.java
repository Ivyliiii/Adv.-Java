package JavaGraphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Basic_Graphics extends JPanel {
	private final int width = 600, height = 800;
	
	public Basic_Graphics() {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	
	public void paint(Graphics g) {
		int strokeW = 10;
		int[] x = {200, 300, 400};
		int[] y = {500, 500-173, 500};
		g.setColor(new Color(0, 0,0));
		g.fillRect(0, 0, width, height);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.RED);
		/*g2.setStroke(new BasicStroke(strokeW));
		g2.drawLine(width/2, height/2, width, height/2+height/30);
		g.setColor(Color.ORANGE);
		g2.drawLine(width/2, height/2+strokeW, width, height/2+height/30+strokeW);
		g.setColor(Color.YELLOW);
		g2.drawLine(width/2, height/2+2*strokeW, width, height/2+height/30+2*strokeW);
		g.setColor(Color.GREEN);
		g2.drawLine(width/2, height/2+3*strokeW, width, height/2+height/30+3*strokeW);
		g.setColor(Color.BLUE);
		g2.drawLine(width/2, height/2+4*strokeW, width, height/2+height/30+4*strokeW);
		g.setColor(new Color(126, 14, 196));
		g2.drawLine(width/2, height/2+5*strokeW, width, height/2+height/30+5*strokeW);*/
		g2.setStroke(new BasicStroke(strokeW));
		g.setColor(Color.RED);
		g2.drawLine(width/2, height/2, width, height/2+height/30);
		g.setColor(Color.ORANGE);
		g2.drawLine(width/2, height/2, width, height/2+height/30+strokeW);
		g.setColor(Color.YELLOW);
		g2.drawLine(width/2, height/2, width, height/2+height/30+2*strokeW);
		g.setColor(Color.GREEN);
		g2.drawLine(width/2, height/2, width, height/2+height/30+3*strokeW);
		g.setColor(Color.BLUE);
		g2.drawLine(width/2, height/2, width, height/2+height/30+4*strokeW);
		g.setColor(new Color(126, 14, 196));
		g2.drawLine(width/2, height/2, width, height/2+height/30+5*strokeW);
		g.setColor(Color.BLACK);
		g2.fillPolygon(x, y, 3);
		g.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		g2.drawPolygon(x, y, 3);
		g.drawLine(0,height/2+6*strokeW, 255, height/2);
		
	}
	
	public static void main(String[] args) {
		new Basic_Graphics();
	}
	
}

