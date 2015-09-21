package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private Node head;
	private Node tail;
	private final Map<Integer, Node> map;
	private final int capacity;
	
	public LRUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        return node == null? -1 : node.val;
    }
    
    public void set(int key, int value) {
    	if(map.containsKey(key)){
    		Node node = map.get(key);
    		
    		Node prev = node.prev;
    		Node next = node.next;
    		if(prev != null) prev.next = next;
    		if(next != null) next.prev = prev;
    		
    		node.next = null; 
    		node.prev = null;
    	}else{
    		if(map.size() == this.capacity){
    			map.remove(head.key);
    			
    			Node next = head.next;
    			if(next != null){
    				next.prev = null;
    				head = next;
    			}
            }
    	}
    	
    	Node newNode = new Node(key, value);
		if (tail != null) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		} else {
			head = newNode;
			tail = newNode;
		}

    }
	
	private class Node {
		Node prev;
		Node next;
		int key;
		int val;
		
		Node(int key, int val){this.key = key; this.val = val;}
	}
}
