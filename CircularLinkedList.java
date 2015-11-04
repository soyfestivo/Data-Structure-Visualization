

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
		remove(start);
		return tmp;
	}

	private int getPositionInList(Node n) {
		Node<T> tmp = start;
		int count = 0;
		while(tmp != n) {
			count++;
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

	public class Node<T> {
		public T value;
		public Node<T> next;
		public boolean isCurrent;

		public Node(T value) {
			this.value = value;
			next = null;
			isCurrent = false;
		}

		public changePosition(int x, int y) {
			position[0] = x;
			position[1] = y;
		}

		public double[] computeXY() {
			int myPosition = getPositionInList(this);
			double angle = ((double) myPosition) * (Math.PI / (double) length());
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			
			return new double[] {x, y};
		}
	}
}