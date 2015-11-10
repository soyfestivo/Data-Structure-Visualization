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
	private Color messageColor;

	public Canvas() {
		setPreferredSize(new Dimension(800,420));
		meassageToDisplay = false;
		animationThread = new Thread(this);
		animationQueue = new CircularLinkedList<CircularLinkedList<Integer>>();
		list = new CircularLinkedList<Integer>(this);
		lastDrawState = null;
		messageColor = new Color(0, 204, 255);

		animationThread.start();

		list.add(1, false);
		list.add(2, false);
		list.add(3, false);
		list.add(4, false);
		list.add(5, false);
		list.add(6, false);
		list.add(7, false);
		list.add(8, false);
		animationQueue.add(list.clone());
	}

	public void run() {
		while(true) {
			if(animationQueue.length() > 0) {
				handleDraw(animationQueue.pop());
			}
			try {
				Thread.sleep(250);
			}
			catch(Exception e) {}
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
		list.add(value);
		messageColor = new Color(0, 204, 255);
		message = "The value '"+value+"' was added to the list";
	}

	public void displayRemove(int value) {
		if(list.remove(value)) {
			messageColor = new Color(0, 204, 255);
			message = "The value '"+value+"' was removed in the list";
		}
		else {
			messageColor = new Color(195, 0, 0);
			message = "The value '"+value+"' was not removed from the list because it does not exist";
		}
	}

	public void displaySearch(int value) {
		if(list.search(value)) {
			messageColor = new Color(0, 204, 255);
			message = "The value '"+value+"' was found in the list";
		}
		else {
			messageColor = new Color(195, 0, 0);
			message = "The value '"+value+"' was not found in the list";
		}
	}

	private void drawMessage(Graphics g, Color background) {
		int width = g.getFontMetrics().stringWidth(message);
		int height = g.getFontMetrics().getHeight();
		g.setColor(background);
		g.fillRect(0, 0, width + 24, height + 24);
		g.setColor(Color.BLACK);
		g.drawString(message, 12, 24);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		ScaledPoint.setSize(getWidth(), getHeight());

		if(meassageToDisplay) {
			drawMessage(g, messageColor);
		}

		if(lastDrawState != null) {
			try {
				lastDrawState.drawGraph(g);
			}
			catch(Exception e) {
			}
		}
	}
}