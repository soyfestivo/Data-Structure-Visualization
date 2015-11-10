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
	private CircularLinkedList<Integer> lastDrawState;
	private CircularLinkedList<Integer> list;

	public Canvas() {
		setPreferredSize(new Dimension(800,420));
		meassageToDisplay = false;
		animationThread = new Thread(this);
		animationQueue = new CircularLinkedList<CircularLinkedList<Integer>>(this);
		list = new CircularLinkedList<Integer>();
		lastDrawState = null;
		animationThread.start();
	}

	public void run() {
		while(true) {
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

	public void pushAnimationState(CircularLinkedList<Integer> state) {
		animationQueue.add(state);
	}

	private void handleDraw(CircularLinkedList<Integer> state) {
		if(lastDrawState == null) {
			lastDrawState = list;
		}
		else {
			lastDrawState = state;
		}
		repaint(0, 0, getWidth(), getHeight());
		//state.drawGraph(getGraphics());	
	}

	public void displayMessage(String str) {
		message = str;
		meassageToDisplay = true;
		repaint(0, 0, getWidth(), getHeight());
	}

	public void displayInsert(int value) {
		animationThread.interrupt();
		list.add(value);
		animationQueue.add(list);
	}

	public void displayRemove(int value) {
		animationThread.interrupt();
		list.remove(value);
		animationQueue.add(list);
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
		ScaledPoint.setSize(getWidth(), getHeight());

		if(lastDrawState != null) {
			try {
				lastDrawState.drawGraph(g);
			}
			catch(Exception e) {}
		}

		if(meassageToDisplay) {
			drawMessage(g);
		}
	}
}