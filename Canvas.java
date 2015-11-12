//
//Stephen Selke, Denis Radinski, Oliver Panasewicz, Jonathan Galvan
//Project 3, Circular Linked List Visualization
//CS 342
//

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

//class to draw and handle gui 
public class Canvas extends JPanel implements Runnable {
	private boolean messageToDisplay;           //boolean controlling if a messagfe displays or not
	private String message;                     //variable to store display message
	private Thread animationThread;             //thread to take care of animation 
	private CircularLinkedList<CircularLinkedList<Integer>> animationQueue; //queue to store animations
	private CircularLinkedList<Integer> lastDrawState;     //stores last drawn state
	private CircularLinkedList<Integer> list;              //stores the circular linkes list itself
	private Color messageColor;                 //stores color of display message

	public Canvas() 
    {
        //initialize frame, canvas, and circular linked list
		setPreferredSize(new Dimension(800,420)); //set initial size of frame
		messageToDisplay = false;                 //initialize message boolean to false
		animationThread = new Thread(this);       //initialize animation thread
		animationQueue = new CircularLinkedList<CircularLinkedList<Integer>>();   //initialize queue
		list = new CircularLinkedList<Integer>(this);     //initialize circular linked list
		lastDrawState = null;                             //initialize lastDrawState to null
		message = "";                                     //initialize blank message
		messageColor = new Color(0, 204, 255);            //initialize color 

		animationThread.start();                          //start animation thread

        //add some values to data structure for initial state
		list.add(1, false);
		list.add(2, false);
		list.add(3, false);
		list.add(4, false);
		list.add(5, false);
		list.add(6, false);
		list.add(7, false);
		list.add(8, false);
		animationQueue.add(list.clone()); //clone the list and add it to queue for animation
	}

    //function to be called by thread
	public void run() 
    //POST: Hands linked list off to animation thread and draws the list
    {
		while(true) 
        {
			if(animationQueue.length() > 0) //if anything exists in the animation queue
            {
				handleDraw(animationQueue.pop());   
			}
			try //check for errors 
            {
				Thread.sleep(250); //sleep the thread
			}
			catch(Exception e) {} //handle exception
		}
	}

    //function to begin thread 
	public void start() 
    //POST: Thread calls run 
    {
		run(); //call run
	}

    //function to stop thread
	public void stop()
    //POST: Thread stops  
    {
		// do nothing
	}

    //function to push state to queue
	public void pushAnimationState(CircularLinkedList<Integer> state) 
    //PRE: A circular linked list state exists and is initialized
    //POST: The state of the list is added to the queue 
    {
		animationQueue.add(state); //add state to queue
	}

    //function to handle states of list to be drawn
	private void handleDraw(CircularLinkedList<Integer> state) 
    //PRE: A circular linked list state exists and is initialized
    //POST: The state to be animated is updated  
    {
		if(lastDrawState == null) //if first drawing
        {
			lastDrawState = list;    //set last state to current list
		}
		else                     //if not first drawing
        {
			lastDrawState = state; //set the last state to this state
		}
		repaint(0, 0, getWidth(), getHeight()); //repaint gui 
		//state.drawGraph(getGraphics());	
	}

    //function to display messages to user 
	public void displayMessage(String str) 
    //PRE: String str exists and is initialized
    //POST: display state is set to true, the display message is set to str
    {
		message = str;                            //set display message to input parameter str 
		messageToDisplay = true;                  //set display state to true
		repaint(0, 0, getWidth(), getHeight());   //repaint gui
	}

    //function to change display message after inserting a value into the list
	public void displayInsert(int value) 
    //PRE:  value is an int and is initialized
    //POST: the input parameter value is inserted into the circular linked list and the display
    //      message is changed to reflect that
    {
		list.add(value);          //add input parameter value to circular linked list
		messageColor = new Color(0, 204, 255);    //set message color
		message = "The value '"+value+"' was added to the list";
	}

    //function to change display message after removing a value from the list
	public void displayRemove(int value) 
    //PRE:  value is an int and is initialized
    //POST: the input parameter value is removed from the circular linked list and the display
    //      message is changed to reflect that
    {
		if(list.remove(value))    //if removal successful, display corresponding message to user
        {  
			messageColor = new Color(0, 204, 255);
			message = "The value '"+value+"' was removed in the list";
		}
		else                      //if removal failed, display corresponding message to the user
        {
			messageColor = new Color(195, 0, 0);
			message = "The value '"+value+"' was not removed from the list because it does not exist";
		}
	}

    //function to change display message after searching a value from the list
	public void displaySearch(int value) 
    //PRE:  value is an int and is initialized
    //POST: the input parameter value is searched from the circular linked list and the display
    //      message is changed to reflect that
    {
		if(list.search(value)) //if the value to be searched is found, display the corresponding message to the user
        {
			messageColor = new Color(0, 204, 255);
			message = "The value '"+value+"' was found in the list";
		}
		else                  //if the value to be searched is not found, display the corresponding message to the user
        {
			messageColor = new Color(195, 0, 0);
			message = "The value '"+value+"' was not found in the list";
		}
	}

    //function to draw the display message on the canvas 
	private void drawMessage(Graphics g, Color background) 
    //PRE:  The Color background is initialized, Graphics object g is initialized
    //POST: The current message is drawn to the gui
    {  
		int width = g.getFontMetrics().stringWidth(message);  //get width of message string in pixels
		int height = g.getFontMetrics().getHeight();          //get height of message string in pixels
		g.setColor(background);                               //set background color of display 
                                                              // message rectangle

		g.fillRect(0, 0, width + 24, height + 24);            //fill rectangle with the background color   
		g.setColor(Color.BLACK);                              //set color back to black
		g.drawString(message, 12, 24);                        //draw the display message to the gui to 
                                                              // the specified location
	}

	@Override
	public void paint(Graphics g) 
    {
		super.paint(g);
		g.setColor(Color.RED);                        //set color to red
		ScaledPoint.setSize(getWidth(), getHeight()); //update scaledPoint size for scaling of gui

		if(messageToDisplay) { //if the display message state is set to true, draw the display message
			drawMessage(g, messageColor);
		}

		if(lastDrawState != null) {   //if the lastDrawState exists, draw it onto the canvas
			try { //error handling
				lastDrawState.drawGraph(g);
			}
			catch(Exception e) {
			}
		}
	}
}