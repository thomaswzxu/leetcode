package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import structure.ListNode;
import structure.RandomListNode;
import structure.TreeNode;

public class Leetcode {
	
	public boolean exist(char[][] board, String word){
		if(board == null || board.length == 0) return false;
		
		int rlen = board.length;
		int clen = board[0].length;
		
		for(int r = 0 ; r < rlen; r ++)
			for( int c = 0; c < clen; c++)
				if(existDfs(board, word, r, c, 0)) return true;
		
		return false;
	}
	
	private boolean existDfs(char[][] board, String word, int r, int c, int i){
		int rlen = board.length;
		int clen = board[0].length;
		
		if(r >= rlen || c >= clen || r < 0 || c < 0) return false;
		
		if(board[r][c] == word.charAt(i)){
			if(i == word.length() -1 ) return true;
			
			char temp = board[r][c];
			board[r][c] = '#';
			
			if(existDfs(board, word, r-1, c, i+1) 
					|| existDfs(board, word, r, c+1, i+1)
					|| existDfs(board, word, r +1, c, i+1)
					|| existDfs(board, word, r, c-1, i+1)) return true;
			
			board[r][c] = temp;
			
		}
		
		return false;
	}
	
	public void recoverTree(TreeNode root) {
        if(root == null) return ;
        
        TreeNode current = root;
        TreeNode previous = null;
        TreeNode parent = null;
        TreeNode n1, n2;
        
        while ( current != null){
        	if(current.left == null){
        		if( parent != null ) ;
        	}else{
        		
        	}
        }
    }
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null) return null;
		
		RandomListNode rest = head;
		while(rest != null){
			RandomListNode copy = new RandomListNode(rest.label);
			RandomListNode previous = rest;
			rest = rest.next;
			previous.next = copy;
			copy.next = rest;
		}
		
		rest = head;
		while (rest != null ){
			RandomListNode r = rest.random;
			if(r != null){
				rest.next.random = r.next;
			}
			
			rest = rest.next.next;
		}
		
		RandomListNode result = head.next;
		RandomListNode tail = null;
		rest = head;
		while( rest != null){
			RandomListNode temp = rest;
			rest = rest.next.next;
			if( tail != null){
				tail.next = temp.next;
				tail = tail.next;
			}else{
				tail = temp.next;
			}
			
			temp.next = rest;
		}
		
        return result;
    }
	
	public boolean wordBreak_2(String s, Set<String> wordDict) {
		if(s == null) return false;
		if(s.length() == 0) return true;
		if(wordDict == null || wordDict.size() == 0) return false;
		
		boolean[] result = new boolean[s.length() + 1];
		result[0] = true;
		
		for(int i = 1; i<result.length; i++){
			for(int j = i - 1; j >= 0; j--){
				if(result[j]){
					String sub = s.substring(j, i);
					if(wordDict.contains(sub)){
						result[i] = true;
						break;
					}
				}
			}
		}
		
        return result[result.length -1];
    }
	
	public boolean wordBreak(String s, Set<String> wordDict) {
		if(s == null) return false;
		if(s.length() == 0) return true;
		if(wordDict == null || wordDict.size() == 0) return false;
		
		for(int i = 1 ; i <= s.length() ; i++){
			String sub = s.substring(0, i);
			if(wordDict.contains(sub)){
				if(i == s.length() || wordBreak(s.substring(i), wordDict)) return true; 
			}
		}
		
        return false;
    }
	
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		if(s == null || s.isEmpty() || words == null || words.length == 0) return result;
		
		int size = words.length;
		int wordlen = words[0].length();
		int totallen = wordlen* size;
		if(s.length() < totallen) return result;
		
		Map<String, Integer> wordcounts = new HashMap<String, Integer>(); 
		for(String w : words){
			if(wordcounts.containsKey(w)) wordcounts.put(w, wordcounts.get(w) + 1);
			else wordcounts.put(w, 1);
		}
		
		for(int i = 0 ; i<= s.length() - totallen; i++){
			String sub = s.substring(i, i + totallen);
			if(checkSubstring(sub, wordcounts, wordlen)) result.add(i);
		}
		
        return result;
    }
	
	private boolean checkSubstring(String sub, Map<String, Integer> wordcounts, int wordlen){
		Map<String, Integer> found = new HashMap<String, Integer>();
		for(int i = 0 ; i< sub.length(); i = i + wordlen){
			String word = sub.substring(i, i + wordlen);
			if(wordcounts.containsKey(word)){
				found.put(word, found.get(word) == null ? 1 : found.get(word) + 1);
				if(found.get(word) > wordcounts.get(word)) return false;
			}else return false;
		}
		
		return true;
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || k < 2) return head;
		
		ListNode fakehead = new ListNode(-1);
		ListNode tail = fakehead;
		
		ListNode grouphead = head;
		ListNode rest = head;
		int count = 0;
		
		while(rest != null){
			count ++;
			if(count < k){
				rest = rest.next;
				continue;
			}
			
			ListNode groupTail = rest;
			rest = rest.next;
			groupTail.next = null;
			ListNode nextTail = grouphead;
			
			tail.next = reverseGroup(grouphead);
			tail = nextTail;
			grouphead = rest;
			count = 0;
		}
		
		return fakehead.next;
	}
	
	private ListNode reverseGroup(ListNode head){
		ListNode resulthead = null;
		ListNode rest = head;
		
		while(rest != null){
			ListNode temp = rest;
			rest = rest.next;
			
			temp.next = resulthead;
			resulthead = temp;
		}
		
		return resulthead;
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if(o1.val > o2.val) return 1;
				else if(o1.val < o2.val) return -1;
				else return 0;
			}
        });
        
        for(ListNode list : lists) if(list != null) queue.add(list);
        
        ListNode fakeHead = new ListNode(-1);
        ListNode tail = fakeHead;
        while(queue.size() > 0)	{
        	ListNode node = queue.poll();
        	tail.next = node;
        	
        	if(node.next != null) queue.add(node.next);
        	
        	tail = tail.next;
        }
        
		return fakeHead.next;
    }
	
	public boolean isMatch(String s, String p) {
		if(s == null || p == null) return false;
		
		if(p.length() == 0){
			return s.length() == 0;
		}
		
		if(p.length() == 1){
			if(p.charAt(0) == '.'){
				return s.length() == 1;
			}else{
				return s.length() == 1 && s.charAt(0) == p.charAt(0);
			}
		}
		
		if(p.charAt(1) == '*'){
			if(p.charAt(0) == '.'){
				return isMatch(s, p.substring(2)) || (s.length() > 0 && isMatch(s.substring(1), p));
			}else{
				return isMatch(s, p.substring(2)) || (s.length() > 0 && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p));
			}
		}else{
			return s.length() > 0 && (p.charAt(0) == '.' || (s.charAt(0) == p.charAt(0))) && isMatch(s.substring(1), p.substring(1));
		}
    }

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		
		if(( len1 + len2 ) % 2 == 0){
			return (findKth(nums1, nums2, 0, len1-1, 0, len2-1, (len1 + len2)/2 -1) +
					findKth(nums1, nums2, 0, len1-1, 0, len2-1, (len1 + len2)/2)) * 0.5;
		}else{
			return (double)findKth(nums1, nums2, 0, len1-1, 0, len2-1, (len1 + len2)/2 );
		}
	}
	
	private double findKth(int[] A, int[] B, int as, int ae, int bs, int be, int k){
		int al = ae - as +1;
		int bl = be - bs + 1;
		
		//Logger.logInfo("----------------------");
		//Logger.logData("al, bl, k", new int[]{al, bl, k});
		if(al == 0) return B[bs + k];
		if(bl == 0) return A[as + k];
		if(k == 0) return Math.min(A[as], B[bs]);
		
		int am = al * k /(al + bl);
		int bm = bl - am -1;
		
		am = as + am;
		bm = bs + bm;
		
		//Logger.logData("as, ae, am, bs, be, bm", new int[]{as, ae, am, bs, be, bm});
		//Logger.logData("a.am, b.bm", new int[]{A[am], B[bm]});
		if(A[am] > B[bm]){
			k = k - (bm - bs + 1);
			bs = bm + 1;
			ae = am;
		}else if (A[am] < B[bm]){
			k = k - (am - as + 1);
			as = am + 1;
			be = bm;
		}else{
			return A[am];
		}
		
		
		return findKth(A, B, as, ae, bs, be, k);
	}
	
}
