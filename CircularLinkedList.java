//
//Stephen Selke, Denis Radinski, Oliver Panasewicz, Jonathan Galvan
//Project 3, Circular Linked List Visualization
//CS 342
//

import java.awt.Graphics;
import java.awt.Color;

//class to implement circular linked list data structure
public class CircularLinkedList<T> {
	protected Node<T> start;	//Head of the list
	protected int length;		//length of the list
	private boolean isFrontEnd;	//stores if the circular linked list is internal or linked with gui
	private Canvas parent;		//stores canvas to be painted to 

	//constructor for regular circular linked list
	public CircularLinkedList() 
	//POST: a circular linked list is constructed with all member variables set to 0, false, or null
	{
		start = null; // there is nothing in the list
		length = 0;   // the length is 0
		isFrontEnd = false;	//is not a front end list
		parent = null;		//no parent
	}

	//constructor for circular linked list that is referenced by the gui
	public CircularLinkedList(Canvas parent)
	//PRE: Canvas object is initialized
	//POST: Circular linked list is initialized with the parent canvas set to imput parameter parent
			and front end boolean set to true 
	{
		this();
		isFrontEnd = true;	//this list will be used by the gui class
		this.parent = parent; //set parent to Canvas object 
	}

	//function to add a node to Circular linked list
	public void add(T item) 
	//PRE: The input parameter item can be any type but must be initialized
	//POST: The input parameter item is added to the circular linked list
	{
		Node<T> n = new Node<T>(item);	//initialize new node for list 
		newState(n);					
		if(start == null) //if list is empty
		{ 
			start = n;
			n.next = start;
		}
		else 			//if the list is not empty
		{ // find end
			Node tmp = start;
			while(tmp.next != start) 
			{ // scan until loop
				newState(tmp);
				tmp = tmp.next;
			}
			// tmp.next == start at this point
			n.next = start;
			tmp.next = n; // complete the loop
		}
		length++;	//increment length of list
		newState(null);
	}

	//function to add node to the gui-connected circular linked list
	public void add(T item, boolean isCurrent) 
	//PRE: item and isCurrent are initialized
	//POST: item is added to the gui-related circular linked list 
	{
		Node<T> n = new Node<T>(item, isCurrent);
		if(start == null) //if list is empty
		{
			start = n;
			n.next = start;
		}
		else //if not empty
		{ // find end
			Node tmp = start;
			while(tmp.next != start) 
			{ // scan until loop
				tmp = tmp.next;
			}
			n.next = start;
			tmp.next = n; // complete the loop
		}
		length++;	//increment length of list
	}

	//TODO: IDK WHAT THIS DOES
	private void newState(Node<T> n)
	//PRE: 
	//POST:  
	{
		if(isFrontEnd) {
			if(n != null) 
			{
				n.isCurrent = true;
				parent.pushAnimationState((CircularLinkedList<Integer>) this.clone()); // this.clone()
				n.isCurrent = false;
			}
			else 
			{
				parent.pushAnimationState((CircularLinkedList<Integer>) this.clone()); // this.clone()
			}
		}
	}

	//return the length of the list
	public int length() 
	//POST: FCTVAL == length of the circular linked list
	{
		return length;
	}

	//function to get last element in the list
	public T getLast()
	//POST: FCTVAL == last element of the list
	{
		Node<T> tmp = start;
		while(tmp.next != start) //traverse the list to end
		{
			tmp = tmp.next;
		}
		return tmp.value;	//return end element
	}

	//function to pop first element off the list and return it
	public T pop() 
	//POST: First element of list removed and FCTVAL == first element in list
	{
		T tmp = start.value;
		remove(start.value);
		return tmp;
	}

	//function to return the position of a node in the list
	private int getPositionInList(Node n) 
	//PRE: Node n is initialized
	//POST: FCTVAL == the position of the Node n in the list
	{
		Node<T> tmp = start;
		int count = 0;	//set counter variable to keep track of position
		while(tmp != n) //while current node != to node being searched for
		{
			if(count > length) //make sure position is not greater than max size of list
			{
				return -1;
			}
			count++;	//increment position
			tmp = tmp.next;	//move to next element
		}
		return count;	//return count which is the position of the node n in the list
	}


	//TODO: fill in some blanks, not sure what newState does
	public boolean remove(T item) 
	//PRE: item is initialized
	//POST: FCTVAL == true if item was removed or false if not removed
	{
		Node<T> tmp = start;
		int count = 0;
		if(length == 0) {
			return false;
		}
		while((tmp.next != start && count == 0) || count < 2) 
		{ // scan at most twice through loop
			newState(tmp);
			if(tmp.next.value.equals(item)) 
			{ // we found it!
				if(tmp.next == start && tmp != start) 
				{ // removing first of list
					start = tmp.next.next;
				}
				tmp.next = tmp.next.next; // pass over current
				if(tmp.next == start && tmp == start && length == 1) 
				{ // removing first and only of list
					start = null;
				}
				length--;
				newState(null);
				return true;
			}
			if(tmp == start) 
			{
				count++;
			}
			tmp = tmp.next;
		}
		newState(null);
		return false;
	}
	//TODO: not sure what newState does
	public boolean search(T item) 
	//PRE: 
    //POST:

	{
		Node<T> tmp = start;
		int count = 0;
		if(length == 0) {
			return false;
		}
		while((tmp.next != start && count == 0) || count < 1) {
			newState(tmp);
			if(tmp.value.equals(item)) {
				newState(tmp);
				newState(tmp);
				newState(tmp);
				newState(tmp);
				newState(null);
				return true;
			}
			if(tmp.next == start) {
				count++;
			}
			tmp = tmp.next;
		}
		newState(null);
		return false;
	}

	//function to print out the tree
	public void print() 
    //POST:	The contents of the linked list are printed
	{
		Node<T> tmp = start;
		int count = 0; //counter to keep track of how many loops occur
		if(length == 0) //if empty list 
		{
			System.out.println("0: []");
			return;
		}
		System.out.println("start: " + tmp.value);
		System.out.print(length + ": [");

		//while the end of the list is not reached and it has not already looped once
		while((tmp.next != start && count == 0) || count < 1) 
		{
			System.out.print(tmp.value.toString() + ", ");
			if(tmp.next == start)  //if we have reached the end of the list
			{
				count++;		//increment loop counter
			}
			tmp = tmp.next;		//traverse list
		}
		System.out.print("]\n");
	}

	//TODO: Double check
	@Override
	public CircularLinkedList<T> clone() 
	//POST: FCTVAL == a copy of this circular linked list
	{
		CircularLinkedList<T> newList = new CircularLinkedList<T>(); // pretend not UI version
		Node<T> tmp = start;
		if(tmp == null) //if list is empty
		{
			newList.isFrontEnd = true;		//set isFrontEnd flag to true
			newList.parent = this.parent;	//clone over parent 
			return newList;					
		}
		while(tmp.next != start)  			//traverse list
		{
			newList.add(tmp.value, tmp.isCurrent);	//copy elements over
			tmp = tmp.next;
		}
		newList.add(tmp.value, tmp.isCurrent); // for the last one
		newList.isFrontEnd = true;
		newList.parent = this.parent;
		return newList;
	}

	//function to draw the list nodes to the gui
	public void drawGraph(Graphics g) 
	{
		Node<T> n = start;
		int count = 0;	//loop counter variable
		while((n.next != start && count == 0) || count < 1) //while not at end of list and hasnt looped
		{
			int[] pos = n.computeXY();						//compute coordinates of the current node
			int[] nextPos = n.next.computeXY();				//compute coordinates of the next node

			//System.out.println("--\nval: " + n.value + " position: " + pos[0] + ", " + pos[1]);

			g.setColor(Color.BLACK); //set draw color to black
			g.drawLine(pos[0], pos[1], nextPos[0], nextPos[1]); //draw line between current 
																// and next node
			if(n.isCurrent) //if the node n is the current node in the list
			{
				g.setColor(new Color(100, 200, 100));	
			}
			else 
			{
				g.setColor(new Color(130, 225, 235));	
			}

			g.fillOval(pos[0] - 25, pos[1] - 25, 50, 50);	//fill the oval
			g.setColor(Color.BLACK);						//set the color of the oval
			if(n == start) 									//if n is the starting node
			{
				g.drawOval(pos[0] - 20, pos[1] - 20, 40, 40);
			}
			g.drawOval(pos[0] - 25, pos[1] - 25, 50, 50);
			g.drawString(n.value.toString(), pos[0], pos[1] + 5);
			
			if(n.next == start) //if we are at the end
			{
				count++;		//increment loop counter
			}
			n = n.next;
		}
	}

	//class for node structure in the circular linked list
	public class Node<T> 
	{
		public T value;					//value of any type
		public Node<T> next;			//next node
		public boolean isCurrent;		//flag to control if the node is the current node

		//constructor for node	
		public Node(T value) 
		//PRE: T is initialized
		//POST: A node is created with value T 
		{
			this.value = value;	
			next = null;
			isCurrent = false;
		}

		//constructor for newest node
		public Node(T value, boolean isCurrent) 
		//PRE: T and isCurrent are initialized
		//POST: A node is created with value t and current flag set to true
		{
			this(value);
			this.isCurrent = isCurrent;
		}

		//function to compute coordinates of the node on the GUI
		public int[] computeXY() 
		//POST: FCTVAL == coordinates of the node in form [x, y]
		{
			int myPosition = getPositionInList(this);	//get the position of the node

			//calculate angle in the circle based on position
			double angle = (((double) myPosition) * ((2*Math.PI) / (double) length)); 

			//convert from polar to cartesian coordinates with radius 0.7; x = rcos(theta)
			double x = Math.cos(angle) * 0.7;		//x = rcos(theta)
			double y = Math.sin(angle) * 0.7;		//y = rsin(theta)
			int[] tmp = ScaledPoint.getRealCoordinance(x, y);
			
			return ScaledPoint.getRealCoordinance(x, y);
		}
	}
}