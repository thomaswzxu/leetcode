package structure;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	if(word == null || word.isEmpty()) return ;
    	
        Map<Character, TrieNode> children = root.children;
        TrieNode nextNode = null;
        for(int i = 0 ;i < word.length() ; i++){
        	char ch = word.charAt( i);
        	if(children.containsKey(ch)) nextNode = children.get(ch);
        	else{
        		nextNode = new TrieNode();
        		nextNode.ch = ch;
        		children.put(ch, nextNode);
        	}
        	
        	children = nextNode.children;
        }
        
        if(nextNode != null) nextNode.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	if(word == null || word.isEmpty()) return false;
    	
        Map<Character, TrieNode> children = root.children;
        TrieNode nextNode = null;
        for(int i = 0; i< word.length() ; i++){
        	if(children.containsKey(word.charAt(i))){
        		nextNode = children.get(word.charAt(i));
        		children = nextNode.children;
        	}else return false;
        }
        
        if(nextNode != null && nextNode.isLeaf) return true;
        else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.isEmpty()) return false;
        
    	Map<Character, TrieNode> children = root.children;
        for(int i = 0; i< prefix.length() ; i++){
        	if(children.containsKey(prefix.charAt(i))){
        		children = children.get(prefix.charAt(i)).children;
        	}else return false;
        }
        
        return true;
    }
}

class TrieNode {
	public char ch;
    public final Map<Character, TrieNode> children;
    public boolean isLeaf = false;
    
    public TrieNode() {    
    	children = new HashMap<Character, TrieNode>();
    }
}