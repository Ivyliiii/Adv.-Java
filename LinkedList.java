package LinkedList;

public class LinkedList<E> { //<E> means a generic type
	
	private class Node { //private because the user doesn't need to know its existence
		
		Node next;
		E data;
		
		public Node(E data) {
			this.data = data;
		}
		
		
	}
	
	private Node first;
	
	public void add(E info, int i) {
		
		if (first == null) {
			first = new Node(info);
			return;
		}
		
		Node newNode = new Node(info);
		if (i == 0) {
			newNode.next = first;
			first = newNode;
			return;
		}
		
		try {
			Node curr = first;
			for(int j = 0; j < i; j++) {
				curr = curr.next;
			}
			
			newNode.next = curr.next;
			curr.next = newNode;
		}
		
		catch(NullPointerException e) {
			
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	public void add(E info) {
		Node curr = first;
		
		while(curr.next != null) {
			curr = curr.next;
		}
		
		curr.next = new Node(info);
		
		return;
	}
	
	public int sizes() {
		Node curr = first;
		int size = 0;
		while (curr != null) {
			size++;
			curr = curr.next;
		}
		return size;
	}
	
	public E get(int i) {

		try {
			Node curr = first;
			for(int j = 0; j < i; j++) {
				curr = curr.next;
			}
			return curr.data; // turn it into a data
		}
		
		catch(NullPointerException e) {
			
			throw new IndexOutOfBoundsException();
		}
	}
	
	public E remove(int i) {
		if(i == 0) {
			Node temp = first;
			first = first.next;
			return temp.data;
		}
		try {
			Node curr = first;
			
			for(int j = 0; j < i-1; j++) {
				curr = curr.next;
			}
			
			E temp = curr.next.data;
			
			curr.next = curr.next.next;
			
			return temp;
		}
		
		catch(NullPointerException e) {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("Hi",0);
		list.add("Bye",0);
		list.add("asd",1);
		list.add("asd",1);
		list.add("asd",0);
		list.add("yes");
		System.out.println(list.get(1));
		System.out.println(list.sizes());
		String s = (String)list.remove(0);
		System.out.println(s);
		System.out.println(list.get(1));
		
	}

	public void Compare(Card card, Card card2) {
		// TODO Auto-generated method stub
		
	}
	
}
