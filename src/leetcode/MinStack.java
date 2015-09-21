package leetcode;

public class MinStack {
	private Node head = null;
	
	public void push(int x) {
        if(head == null) head = new Node(x, x);
        else {
        	Node newnode = new Node(x, Math.min(x, head.min));
        	newnode.next = head;
        	head = newnode;
        }
    }

    public void pop() {
        if(head != null) head = head.next;
    }

    public int top() {
        if( head != null) return head.val;
        
        throw new IndexOutOfBoundsException("");
    }

    public int getMin() {
        if(head != null) return head.min;
        
        throw new IndexOutOfBoundsException("");
    }
    
    private class Node{
    	int val;
    	int min;
    	Node next;
    	
    	Node (int val, int min){
    		this.val = val;
    		this.min = min;
    	}
    }
}
