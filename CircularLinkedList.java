import java.awt.Graphics;
import java.awt.Color;

public class CircularLinkedList<T> {
	protected Node<T> start;
	protected int length;
	private boolean isFrontEnd;
	private Canvas parent;

	public CircularLinkedList() {
		start = null; // there is nothing in the list
		length = 0;   // the length is 0
		isFrontEnd = false;
		parent = null;
	}

	public CircularLinkedList(Canvas parent) {
		this();
		isFrontEnd = true;
		this.parent = parent;
	}

	public void add(T item) {
		Node<T> n = new Node<T>(item);
		newState(n);
		if(start == null) {
			start = n;
			n.next = start;
		}
		else { // find end
			Node tmp = start;
			while(tmp.next != start) { // scan until loop
				newState(tmp);
				tmp = tmp.next;
			}
			n.next = start;
			start = n;
			tmp.next = n; // complete the loop
			newState(n);
		}
		length++;
	}

	public void add(T item, boolean isCurrent) {
		Node<T> n = new Node<T>(item, isCurrent);
		if(start == null) {
			start = n;
			n.next = start;
		}
		else { // find end
			Node tmp = start;
			while(tmp.next != start) { // scan until loop
				tmp = tmp.next;
			}
			n.next = start;
			start = n;
			tmp.next = n; // complete the loop
		}
		length++;
	}

	private void newState(Node<T> n) {
		/*if(isFrontEnd) {
			n.isCurrent = true;
			parent.pushAnimationState((CircularLinkedList<Integer>) this); // this.clone()
			n.isCurrent = false;
		}*/
	}

	public int length() {
		return length;
	}

	public T getLast() {
		Node<T> tmp = start;
		while(tmp.next != start) {
			tmp = tmp.next;
		}
		return tmp.value;
	}

	public T pop() {
		T tmp = start.value;
		remove(start.value);
		return tmp;
	}

	private int getPositionInList(Node n) {
		Node<T> tmp = start;
		int count = 0;
		while(tmp != n) {
			if(count > length) {
				return -1;
			}
			count++;
			tmp = tmp.next;
		}
		return count;
	}

	public boolean remove(T item) {
		Node<T> tmp = start;
		int count = 0;
		while((tmp.next != start && count == 0) || count < 2) { // scan at most twice through loop
			if(tmp.next.value == item) { // we found it!
				if(tmp.next == start && tmp != start) { // removing first of list
					start = tmp.next.next;
				}
				tmp.next = tmp.next.next; // pass over current
				if(tmp.next == start && tmp == start && length == 1) { // removing first and only of list
					start = null;
				}
				length--;
				return true;
			}
			if(tmp == start) {
				count++;
			}
			tmp = tmp.next;
		}
		return false;
	}

	public boolean search(T item) {
		Node<T> tmp = start;
		int count = 0;
		while((tmp.next != start && count == 0) || count < 1) {
			if(tmp.value == item) {
				return true;
			}
			if(tmp.next == start) {
				count++;
			}
			tmp = tmp.next;
		}
		return false;
	}

	public void print() {
		Node<T> tmp = start;
		int count = 0;
		if(length == 0) {
			System.out.println("0: []");
			return;
		}
		System.out.println("start: " + tmp.value);
		System.out.print(length + ": [");
		while((tmp.next != start && count == 0) || count < 1) {
			System.out.print(tmp.value.toString() + ", ");
			if(tmp.next == start) {
				count++;
			}
			tmp = tmp.next;
		}
		System.out.print("]\n");
	}

	@Override
	public CircularLinkedList<T> clone() {
		CircularLinkedList<T> newList = new CircularLinkedList<T>(); // pretend not UI version
		newList.length = this.length;
		Node<T> tmp = start;
		if(tmp == null) {
			newList.parent = this.parent;
			return newList;
		}
		while(tmp.next != start) {
			newList.add(tmp.value, tmp.isCurrent);
			tmp = tmp.next;
		}
		newList.add(tmp.value, tmp.isCurrent); // for the last one
		newList.parent = this.parent;
		return newList;
	}

	public void drawGraph(Graphics g) {
		Node<T> n = start;
		int count = 0;
		while((n.next != start && count == 0) || count < 1) {
			int[] pos = n.computeXY();
			int[] nextPos = n.next.computeXY();
			//System.out.println("--\nval: " + n.value + " position: " + pos[0] + ", " + pos[1]);
			g.setColor(Color.BLACK);
			g.drawLine(pos[0], pos[1], nextPos[0], nextPos[1]);
			if(n.isCurrent) {
				g.setColor(new Color(100, 200, 100));
			}
			else {
				g.setColor(new Color(175, 175, 175));
			}
			g.fillOval(pos[0] - 25, pos[1] - 25, 50, 50);
			g.setColor(Color.BLACK);
			g.drawString(n.value.toString(), pos[0], pos[1]);
			
			if(n.next == start) {
				count++;
			}
			n = n.next;
		}
	}

	public class Node<T> {
		public T value;
		public Node<T> next;
		public boolean isCurrent;

		public Node(T value) {
			this.value = value;
			next = null;
			isCurrent = false;
		}

		public Node(T value, boolean isCurrent) {
			this(value);
			this.isCurrent = isCurrent;
		}

		public int[] computeXY() {
			int myPosition = getPositionInList(this);
			double angle = (((double) myPosition) * ((2*Math.PI) / (double) length));
			double x = Math.cos(angle) * 0.5;
			double y = Math.sin(angle) * 0.5;
			int[] tmp = ScaledPoint.getRealCoordinance(x, y);
			
			return ScaledPoint.getRealCoordinance(x, y);
		}
	}
}