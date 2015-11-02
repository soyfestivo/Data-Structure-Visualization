import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class UIWindow extends JFrame {
	private JPanel topBar;
	private OptionsPane insertPane;
	private OptionsPane removePane;
	private OptionsPane searchPane;
	private Canvas canvas;

	public UIWindow(String title) {
		super(title);

		setLayout(new BorderLayout());

		topBar = new JPanel();
		topBar.setLayout(new GridLayout(1, 3));
		insertPane = new OptionsPane(this, "Insert:", "Insert");
		removePane = new OptionsPane(this, "Remove:", "Remove");
		searchPane = new OptionsPane(this, "Search:", "Search");
		topBar.add(insertPane);
		topBar.add(removePane);
		topBar.add(searchPane);

		canvas = new Canvas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		add(topBar, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		pack();
		setVisible(true);

		CircularLinkedList<Integer> clist = new CircularLinkedList<Integer>();
		clist.print();
		clist.add(10);
		clist.print();
		clist.add(12);
		clist.print();
		clist.add(44);
		clist.add(44);
		clist.add(-8);
		clist.add(11);
		clist.add(48);
		clist.print();
		System.out.println("----");
		clist.remove(44); // FAIL
		clist.remove(12);
		clist.remove(10);
		clist.print();
	}

	public void actionPerformed(OptionsPane sender, int value) {
		canvas.displayMessage("the value we got was: " + value);
		/*if(sender == insertPane) {
			JOptionPane.showInternalMessageDialog(this, "information", "Insert Performed", JOptionPane.INFORMATION_MESSAGE);
		}
		if(sender == removePane) {
			JOptionPane.showInternalMessageDialog(this, "information", "Remove Performed", JOptionPane.INFORMATION_MESSAGE);
		}
		if(sender == searchPane) {
			JOptionPane.showInternalMessageDialog(this, "information", "Search Performed", JOptionPane.INFORMATION_MESSAGE);
		}*/
	}

	public static void main(String[] args) {
		new UIWindow("CS 342 Project 3: Data Structure Visualization");
	}
}