import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;

public class UIWindow extends JFrame {
	private JPanel topBar;
	private Canvas canvas;

	public UIWindow(String title) {
		super(title);

		setLayout(new BorderLayout());

		topBar = new JPanel();
		topBar.add(new JButton("Button"));

		canvas = new Canvas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		add(topBar, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new UIWindow("CS 342 Project 3: Data Structure Visualization");
	}
}