

public class CircularLinkedList<T> {
	protected Node<T> start;
	protected int length;

	public CircularLinkedList() {
		start = null; // there is nothing in the list
		length = 0;   // the length is 0
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

	protected class Node<T> {
		public T value;
		public Node<T> next;

		public Node(T value) {
			this.value = value;
			next = null;
		}
	}
}