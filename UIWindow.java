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

//class for user interface
public class UIWindow extends JFrame {
	private JPanel topBar;				//panel for top toolbar of UI
	private OptionsPane insertPane;		//Pane for insert option
	private OptionsPane removePane;		//Pane for remove option
	private OptionsPane searchPane;		//Pane for search option
	private Canvas canvas;				//Canvas to draw on

	public UIWindow(String title) {
		super(title);						//call constructor of superclass 

		setLayout(new BorderLayout());		//set layout of frame to border layout

		topBar = new JPanel();				//construct topBar JPanel
		topBar.setLayout(new GridLayout(1, 3));	//set layout of topBar to 1x3 gridLayout

		//construct the 3 option panes
		insertPane = new OptionsPane(this, "Insert:", "Insert");
		removePane = new OptionsPane(this, "Remove:", "Remove");
		searchPane = new OptionsPane(this, "Search:", "Search");

		//add the 3 option panes
		topBar.add(insertPane);
		topBar.add(removePane);
		topBar.add(searchPane);

		//construct the canvas
		canvas = new Canvas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//set "close" functionality to close frame
		setSize(600, 400);								//set size of frame
		add(topBar, BorderLayout.NORTH);				//add the option bar to UI
		add(canvas, BorderLayout.CENTER);				//add the canvas to the UI
		pack();											//force resize elements of UI
		setVisible(true);								//make UI visible

		/***TESTING****/

		/*
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
		*/
	}

	public void actionPerformed(OptionsPane sender, int value) {
		canvas.displayMessage("the value we got was: " + value);

		if(sender == insertPane)	//Event handling for insertPane
		{
			canvas.displayInsert(value);
		}

		if(sender == removePane)	//Event handling for removePane
		{
			canvas.displayRemove(value);
		}

		if(sender == searchPane) 	//Event handling for searchPane
		{
			canvas.displaySearch(value);
		}
	}

	public static void main(String[] args) {
		new UIWindow("CS 342 Project 3: Data Structure Visualization");
	}
}