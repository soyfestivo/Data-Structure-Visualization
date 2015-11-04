import java.lang.Thread;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.FileInputStream;
import java.lang.Thread;
import java.lang.Runnable;

public class Canvas extends JPanel implements Runnable {
	private boolean meassageToDisplay;
	private String message;
	private Thread animationThread;
	private CircularLinkedList<CircularLinkedList<Integer>> animationQueue;
	private CircularLinkedList<Integer> list;

	public Canvas() {
		setPreferredSize(new Dimension(800,420));
		meassageToDisplay = false;
		animationThread = new Thread(this);
		animationQueue = new CircularLinkedList<CircularLinkedList<Integer>>(this);
		list = new CircularLinkedList<Integer>();
	}

	public void run() {
		while(1) {
			if(animationQueue.length() > 0) {
				handleDraw(animationQueue.pop());
			}
			else {
				try {
					Thread.sleep(250);
				}
				catch(Exception e) {}
			}
		}
	}

	public void start() {
		run();
	}

	public void stop() {
		// do nothing
	}

	private handleDraw(CircularLinkedList<Integer> state) {
		
	}

	public void displayMessage(String str) {
		message = str;
		meassageToDisplay = true;
		repaint(0, 0, getWidth(), getHeight());
	}

	private void drawMessage(Graphics g) {
		int width = g.getFontMetrics().stringWidth(message);
		int height = g.getFontMetrics().getHeight();
		g.setColor(new Color(0, 204, 255));
		g.fillRect(0, 0, width + 24, height + 24);
		g.setColor(Color.BLACK);
		g.drawString(message, 12, 12);
		meassageToDisplay = false;
		//Thread.sleep(3000);
		repaint(0, 0, getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());

		if(meassageToDisplay) {
			drawMessage(g);
		}
	}
}