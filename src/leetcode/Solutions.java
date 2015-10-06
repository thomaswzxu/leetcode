package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import structure.Interval;
import structure.ListNode;
import structure.Point;
import structure.RandomListNode;
import structure.TreeNode;

public class Solutions {
	
	/**
	 * leet code 238: product of array except self
	 */
	public int[] productExceptSelf(int[] nums) {
		return null;
    }
	
	/**
	 * leetcode 223: rectangle area
	 */
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int xlen = intersect(A, C, E, G);
		int ylen = intersect(B, D, F, H);
		
		int result = Math.abs((A-C) *(B-D)) + Math.abs((E-G) * (F- H));
		result -= Math.abs(xlen * ylen);
		
		return result;
    }
	
	private int intersect(int a1, int a2, int b1, int b2){
		if(a1 < b1){
			if(a2 < b1) return 0;
			else if(a2 > b1  && a2 < b2) return a2 - b1;
			else return b2 - b1;
		}else {
			if(b2 < a1) return 0;
			else if (b2 > a1 && b2 < a2) return b2 - a1;
			else return a2 - a1;
		}
	}
	
	/**
	 * leetcode 215 : find kth largest number;
	 */
	public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) throw new IllegalArgumentException("");
        
        
        return getKth_(nums, 0, nums[nums.length - 1], k);
    }
	
	private int getKth_(int[] nums, int start, int end, int k){
		int pivot = nums[end];
		
		int left = start;
		int right = end;
		while(true){
			
			while(left < right && nums[left] < pivot) left ++;
			
			while(left < right && pivot <= nums[right]) right --;
			
			if(left == right) break;
			
			swap(nums, left, right);
		}
		
		swap(nums, left, end);
		
		if(k == left + 1) return pivot;
		else if ( k < left + 1) return getKth_(nums, start, left -1, k);
		else return getKth_(nums, left + 1, end, k);
	}
	
	private void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	/**
	 * leetcode 209: Minimum Size Subarray Sum
	 */
	public int minSubArrayLen(int s, int[] nums) {
		if(nums == null ||nums.length == 0) return 0;
		
		int from = 0;
		int sum = nums[0];
		int min = Integer.MAX_VALUE;
		for(int i = 1 ; i< nums.length ; i ++){
			if(sum < s) sum += nums[i];
			
			while(sum > s){
				if(sum - nums[from] >= s){
					sum -= nums[from];
					from ++;
				}
			}
			
			if(sum >= s) min = Math.min(min, i- from + 1);
		}
		
		return min;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 207: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null) {
			throw new IllegalArgumentException("illegal prerequisites array");
		}

		int len = prerequisites.length;

		if (numCourses == 0 || len == 0) {
			return true;
		}

		// counter for number of prerequisites
		int[] pCounter = new int[numCourses];
		for (int i = 0; i < len; i++) {
			pCounter[prerequisites[i][0]]++;
		}

		// store courses that have no prerequisites
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (pCounter[i] == 0) {
				queue.add(i);
			}
		}

		// number of courses that have no prerequisites
		int numNoPre = queue.size();

		while (!queue.isEmpty()) {
			int top = queue.remove();
			for (int i = 0; i < len; i++) {
				// if a course's prerequisite can be satisfied by a course in
				// queue
				if (prerequisites[i][1] == top) {
					pCounter[prerequisites[i][0]]--;
					if (pCounter[prerequisites[i][0]] == 0) {
						numNoPre++;
						queue.add(prerequisites[i][0]);
					}
				}
			}
		}

		return numNoPre == numCourses;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 206: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public ListNode reverseList(ListNode head) {
        ListNode result = null;
        while(head != null){
        	ListNode next = head.next;
        	
        	head.next = result;
        	result = head;
        	
        	head = next;
        }
        
        return result;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 205: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public boolean isIsomorphic(String s, String t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.length() != t.length()) return false;
        
        Map<Character, Character> smap = new HashMap<Character, Character>();
        Map<Character, Character> tmap = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i ++){
        	char ss = s.charAt(i);
        	char tt = t.charAt(i);
        	
        	if(smap.containsKey(ss)){
        		if(smap.get(ss) != tt) return false;
        	}else smap.put(ss, tt);
        	
        	if(tmap.containsKey(tt)){
        		if(tmap.get(tt) != ss) return false;
        	}else tmap.put(tt, ss);
        }
        
        return true;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 204: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int countPrimes(int n) {
        if(n <= 2) return 0;
        
        boolean[] isPrime = new boolean[n];
        for(int i = 2 ; i< isPrime.length ; i++) isPrime[i] = true;
        
        int sqrt = (int)Math.sqrt(n);
        for( int i = 2; i <= sqrt; i++){
        	if(isPrime[i]){
        		int sum = i + i;
        		while (sum < n){
        			isPrime[sum] = false;
        			sum += i;
        		}
        	}
        }
        
        int result = 0;
        for(boolean prime : isPrime) if(prime) result ++;
        
        return result;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 203: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while(head != null){
        	ListNode next = head.next;
        	if(head.val != val) {
        		tail.next = head;
        		tail = head;
        		tail.next = null;
        	}
        	
        	head = next;
        }
        
        return dummy.next;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 202: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<Integer>();
		while(!set.contains(n)){
			if (n == 1) return true;
			
			set.add(n);
			
			int sum = 0;
			while (n >= 10){
				int d = n%10;
				sum += d * d;
				n = n/10;
			}
			sum += n * n;
			
			n = sum;
		}
		
		return false;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 201: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int rangeBitwiseAnd(int m, int n) {
        int d = Integer.MAX_VALUE;
        
        while ((d &m) != (d &n)){
        	d = d<<1;
        }
        
        return m & d;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 200: Number of Islands</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0) return 0;
		
		int total = 0;
		for(int r = 0 ; r < grid.length; r++)
			for(int c = 0 ; c <grid[r].length; c++){
				if(grid[r][c] == '1') {
					total ++;
					flip(grid, r, c);
				}
			}
		
		return total;
	}
	
	private void flip (char[][] grid, int row, int col){
		if(row < 0 || row >= grid.length) return;
		
		if(col < 0 || col >= grid[0].length) return;
		
		grid[row][col] = '0';
		
		flip(grid, row -1, col);
		flip(grid, row +1, col);
		flip(grid, row, col +1);
		flip(grid, row, col -1);
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 199: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) return result;
		
		List<TreeNode> row = new ArrayList<TreeNode>();
		row.add(root);
		
		while(! row.isEmpty()){
			result.add(row.get(row.size() -1).val);
			
			List<TreeNode> nextRow = new ArrayList<TreeNode>();
			for(TreeNode node : row){
				if(node.left != null) nextRow.add(node.left);
				if(node.right != null) nextRow.add(node.right);
			}
			
			row = nextRow;
		}
		
		return result;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 198: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0 ) return 0;
		
		return rob_(nums, nums.length -1);
	}
	
	private int rob_(int[] nums, int index){
		if(index < 0) return 0;
		if(index == 0) return nums[0];
		
		int val1 = nums[index] + rob_ (nums, index -2);
		int val2 = rob_(nums, index -1);
		
		return Math.max(val1, val2);
		
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 191: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int hammingWeight(int n) {
        int total = 0;
        for(int i = 0 ; i < 32; i ++ ){
            int number = 1 << i;
            if((n ^ number) == number) total ++;
        }
        
        return total;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 190: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int reverseBits(int n) {
        int result = 0;
        
        for(int shift = 0 ; shift < 32; shift ++){
        	int number = 1 <<shift;
        	if((n & number) == number){
        		int val = 1 << (32 -1 - shift);
        		result += val;
        	}
        }
		
        return result;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 187: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
		if(s == null || s.length() < 10) return result;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 10; i <= s.length() ; i++){
			String mol = s.substring(i-10, i);
			int count = 0;
			if(map.containsKey(mol)) count = map.get(mol) +1;
			else count = 1;
			
			map.put(mol, count);
			if(count == 2) result.add(mol);
		}
		
		return result;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 179: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        
        String[] str = new String[nums.length];
        for(int i = 0 ; i< str.length ; i++) str[i] = (nums[i])+"";
        
        Arrays.sort(str, new Comparator<String>(){
        	public int compare(String s1, String s2){
        		return -(s1 + s2).compareTo(s2 + s1);
        	}
        });
        
        StringBuilder buf = new StringBuilder();
        for(String s : str ) buf.append(s);
        
        int i = 0;
        for(; i< buf.length(); i++ ) if(buf.charAt(i) != 0) break;
        
        return i == buf.length() ? "0" : buf.substring(i);
    }
	
	/**
	 * <a href="https://leetcode.com/problems/min-stack/" target="_blank">
	 *Leetcode 155: Min Stack</a><p>
	 * Notes:
	 * <ul>
	 * <li>answer: http://www.programcreek.com/2014/02/leetcode-min-stack-java/
	 * <li>{@link MinStack}
	 * </ul>
	 * @deprecated
	 */
	public void MinStack(){}
	
	/**
	 * <a href="https://leetcode.com/problems/max-points-on-a-line/" target="_blank">
	 *Leetcode 149: Max Points on a Line </a><p>
	 * Notes:
	 * <ul>
	 * <li>answer:http://fisherlei.blogspot.com/2013/12/leetcode-max-points-on-line-solution.html
	 * <li>answer: http://www.programcreek.com/2014/04/leetcode-max-points-on-a-line-java/
	 * </ul>
	 */
	public int maxPoints(Point[] points) {
		if(points == null || points.length == 0) return 0;
		
		int max = 0;
		for(int i = 0 ; i< points.length ; i++){
			int duplicate = 0;
			int vertical = 0;
			Map<Double, Integer> slopes = new HashMap<Double, Integer>();
			for(int j = i + 1; j< points.length; j++){
				if(points[i].x == points[j].x ){
					if(points[i].y == points[j].y) duplicate ++;
					else vertical ++;
				}else{
					double s = points[i].y == points[j].y ? 0 :
						(double)(points[i].y - points[j].y) /(double)(points[i].x - points[j].x);
					if(slopes.containsKey(s)) slopes.put(s, slopes.get(s) + 1);
					else slopes.put(s, 1);
				}
			}
			
			Integer[] counts = slopes.values().toArray(new Integer[0]);
			for(Integer c : counts) max = Math.max(c + duplicate, max);
			
			max = Math.max(vertical + duplicate ,  max);
		}
		
        return max +1;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/lru-cache/" target="_blank">
	 *Leetcode 146: LRU Cache </a><p>
	 * Notes:
	 * <ul>
	 * <li> {@link LRUCache}
	 * <li>answer:http://www.programcreek.com/2013/03/leetcode-lru-cache-java/
	 * </ul>
	 * @deprecated
	 */
	public void LRUCache(){}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 140: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<String> wordBreakII(String s, Set<String> wordDict) {
		List<String> result = new ArrayList<String>();
        List<String> item = new ArrayList<String>();
        
        if(s == null || s.length() == 0) return result;
        if(wordDict == null || wordDict.size() == 0 ) return result;
        
        wordBreakII_(s, 0, item, result, wordDict);
        
        return result;
    }
	
	private void wordBreakII_(String s, int start, List<String> item, List<String> result, Set<String> wordDict){
		if(start == s.length()){
			StringBuilder buf = new StringBuilder();
			for(String str : item) buf.append(str).append(" ");
			result.add(buf.toString().trim());
			return;
		}
		
		for(String word : wordDict){
			int len = word.length();
			if(start + len <= s.length()){
				String sub = s.substring(start, start + len);
				if(word.equals(sub)){
					item.add(sub);
					wordBreakII_(s, start + len, item, result, wordDict);
					item.remove(item.size() -1);
				}
			}
		}
	}
	
	/**
	 * <a href="https://leetcode.com/problems/word-break/" target="_blank">
	 *Leetcode 139: Word Break</a><p>
	 * Notes:
	 * <ul>
	 * <li>{@link Leetcode#wordBreak}
	 * </ul>
	 */
	public boolean wordBreak(String s, Set<String> wordDict) {
		boolean[] cans = new boolean[s.length() + 1];
		cans[0] = true;
		
		for(int i = 1; i <= s.length(); i++){
			cans[i] = false;
			for(String w : wordDict){
				int len = w.length();
				int start = i - len;
				
				if(start >=0 && cans[start]){
					String sub = s.substring(start, i);
					if(w.equals(sub)) cans[i] = true;
				}
			}
		}
		
		return cans[s.length()];
	}
	
	public boolean wordBreak1(String s, Set<String> wordDict) {
		if(s == null || s.length() == 0 ) return true;
		if(wordDict.size() == 0) return false;
		
        return wordBreak_(wordDict, s, 0);
    }
	
	private boolean wordBreak_(Set<String> wordDict, String s, int index){
		if(index >= s.length()) return true;
		
		for(int i = index+1; i <= s.length(); i ++){
			String sub = s.substring(index, i);
			if(wordDict.contains(sub)){
				wordDict.remove(sub);
				if(wordBreak_(wordDict, s, i)) return true;
				
				wordDict.add(sub);
			}
		}
		
		return false;
	}
	
	/**
	 * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/" target="_blank">
	 *Leetcode 138: Copy List with Random Pointer </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 * @deprecated
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
        return null;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 137: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int singleNumberII(int[] nums) {
        int ones = nums[0];
        int twos = 0;
        int threes = 0;
        
        for(int i = 1; i < nums.length ; i++){
        	ones = ones ^ nums[i];
        	twos |= ones & nums[i];
        	threes = ones & twos;
        	ones &= ~threes;
        	twos &= ~threes;
        }
        
        return ones;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 131: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<List<String>> partition(String s) {
		List<List<String>>  result = new ArrayList<List<String>>();
        if(s == null || s.length() == 0 ) return result;
        
        partition_(s, 0,new ArrayList<String>(), result);
        return result;
    }
	
	private void partition_(String s, int start, List<String> item, List<List<String>> result){
		if(start == s.length()){
			List<String> temp = new ArrayList<String>(item);
			result.add(temp);
			return;
		}
		
		for(int i = start +1; i <= s.length() ; i ++){
			String sub = s.substring(start, i);
			if(isPalindrone(sub)){
				item.add(sub);
				partition_(s, i, item, result);
				item.remove(item.size() - 1);
			}
		}
	}
	
	private boolean isPalindrone(String s){
		int left = 0 ; int right = s.length() -1;
		
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) return false;
			
			left ++;
			right --;
		}
		
		return true;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 130: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public void solve(char[][] board) {
        if(board == null || board.length <=2 || board[0].length <=2) return;
        
        int rlen = board.length; int clen = board[0].length;
        for(int i =0; i< rlen; i++){
        	solve_(board, i, 0);
        	solve_(board, i, clen-1);
        }
        
        for(int i = 0 ; i<clen; i++){
        	solve_(board, 0, i);
        	solve_(board, rlen-1, i);
        }
        
        for(int r = 0 ; r< rlen; r++)
        	for(int c = 0 ; c < clen; c++){
        		if(board[r][c] == '0') board[r][c] = 'X';
        	}
        
        for(int r = 0 ; r< rlen; r++)
        	for(int c = 0 ; c < clen; c++){
        		if(board[r][c] == '#') board[r][c] = '0';
        	}
    }
	
	private void solve_(char[][] board, int row, int col){
		if(row < 0 || row >= board.length) return;
		if(col < 0 || col >= board[0].length) return;
		if(board[row][col] == 'X' || board[row][col] == '#') return;
		
		board[row][col] = '#';
		solve_(board, row +1, col);
		solve_(board, row-1, col);
		solve_(board, row, col+1);
		solve_(board, row, col-1);
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 128: Longest Consecutive Sequence</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int longestConsecutive(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		
		Map<Integer, int[]> map = new HashMap<Integer, int[]>();
		for(int i = 0; i< nums.length ; i++){
			int key = nums[i];
			if(map.containsKey(key)) continue;
			else if (map.containsKey(key -1 ) || map.containsKey(key + 1)){
				int[] count = map.containsKey(key-1)? map.get(key -1) : map.get(key +1);
				count[0] ++;
				map.put(key, count);
			}else{
				int[] count = new int[1];
				count[0] = 1;
				map.put(key, count);
			}
		}
		
		Iterator<int[]> values = map.values().iterator();
		int max = 0;
		while(values.hasNext()){
			int[] count = values.next();
			max = Math.max(count[0], max);
		}
		
		return max;
	}
	
	/**
	 * <a href="https://leetcode.com/problems/recover-binary-search-tree/" target="_blank">
	 *Leetcode 99: Recover Binary Search Tree</a><p>
	 * Notes:
	 * <ul>
	 * <li>answer: http://fisherlei.blogspot.com/2012/12/leetcode-recover-binary-search-tree.html
	 * <li> {@link Leetcode#recoverTree}
	 * </ul>
	 * @deprecated
	 */
	public void recoverTree(TreeNode root) {
        
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 79: word search</a><p>
	 * Notes:
	 * <ul>
	 * <li>{@link Leetcode#exist}
	 * </ul>
	 * @deprecated
	 */
	public boolean exist(char[][] board, String word){return false;}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 76: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public String minWindow(String s, String t) {
        if(s ==  null || s.length() == 0 || t == null || t.length() == 0) return "";
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0 ; i< t.length() ; i++){
        	char ch = t.charAt(i);
        	if(map.containsKey(ch)) map.put(ch, map.get(ch) + 1);
        	else map.put(ch, 1);
        }
        
        int total = t.length();
        int left = 0 ; int right = 0 ;
        for(int i = 0 ; i< s.length() ; i++){
        	char ch = s.charAt(i);
        	if(map.containsKey(ch)){
        		int count = map.get(ch);
        		if(count > 0){
        			map.put(ch, count -1);
        			total --;
        		}else{
        		}
        	}
        }
        
        return null;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode  72: Edit Distance </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int minDistance(String word1, String word2) {
		if(word1 == null | word2 == null ) throw new IllegalArgumentException ("");
		
		int l1 = word1.length(), l2= word2.length();
		
		int[][] distance = new int[l1+1][l2+1];
		
		for(int i = 0; i <= l1 ; i++) distance[i][0] = i;
		for(int j = 0 ; j <= l2; j++) distance[0][j] = j;
		
		for(int i = 0 ; i< l1; i++){
			char c1 = word1.charAt(i);
			for(int j = 0 ; j < l2; j++){
				char c2 = word2.charAt(j);
				
				if(c1 == c2){
					distance[i+1][j+1] = distance[i][j];
				}else{
					int replace = distance[i][j] + 1;
					int delete = distance[i+1][j] + 1;
					int insert = distance[i][j+1] + 1;
					
					int min = Math.min(replace, Math.min(delete, insert));
					distance[i+1][j+1] = min;
				}
			}
		}
		
		return distance[l1][l2];   
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 69 : sqrt </a><p>
	 * Notes:
	 * <ul>
	 * <li>http://www.programcreek.com/2012/02/java-calculate-square-root-without-using-library-method/
	 * </ul>
	 */
	public int mySqrt(int x) {
		if( x < 0) throw new IllegalArgumentException("");
		if(x == 0 || x == 1) return x;
		
		double last;
		double next = x /2.0;
		do{
			last = next;
			next = (last + (x /last)) /2.0;
		}while ((last - next) != 0);
		
		return (int)next;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 68: Text Justification </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<String>();

		
		return result;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 62: Unique Paths</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int uniquePaths(int m, int n) {
     
	return -1;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 61: </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;
        
        ListNode tail = head;
        int len = 1;
        while(tail.next != null){
        	tail = tail.next;
        	len ++;
        }
        
        int step = 0;
        while(step < len-k ){
        	tail = tail.next;
        	step ++;
        }
        
        head = tail.next;
        tail.next = null;
        return head;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 60: Permutation Sequence</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<Integer>();
        
        int total = 1;
        for(int i = 1; i<= n; i++){
        	total *= i;
        	numbers.add(i);
        }
        
        k--;
        
        String result = "";
        for(int i = 0 ; i < n ; i++	){
        	total = total / (n - i);
        	
        	int index = k /total;
        	k = k% total;
        	
        	result += numbers.get(index);
        	numbers.remove(index);
        }
        
        return result;
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 56: Merge Intervals </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if(intervals == null || intervals.size() < 2) return intervals;
		
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		List<Interval> result = new ArrayList<Interval>();
		result.add(intervals.get(0));
		for(int i = 1; i< intervals.size() ; i++){
			Interval next = intervals.get(i);
			Interval last = result.get(result.size() -1);
			if(last.end < next.start) result.add(next);
			else if( last.end == next.start) last.end = next.end;
			else last.end = Math.max(last.end, next.end);
		}
		
		return result;   
    }
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 51: N Queens</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(n == 0) return result;
        if(n == 1) {
        	List<String> item = new ArrayList<String>();
        	item.add("Q");
        	result.add(item);
        	return result;
        }
        
        char[] rowString = new char[n];
        for(int i = 0 ; i< n; i++) rowString[i]= '.';
        
        solveNQueens(new int[n], 0, rowString, n, result);
        
        return result;
    }
	
	private void solveNQueens(int[] colIndexes, int row, char[] rowStr, int n, List<List<String>> result){
		if(row == n){
			List<String> item = new ArrayList<String>();
			for(int i = 0 ; i < colIndexes.length ; i++){
				rowStr[colIndexes[i]] = 'Q';
				item.add(rowStr.toString());
				rowStr[colIndexes[i]] = '.';
			}
			
			result.add(item);
		}
		
		for(int i = 0 ; i< n ; i++){
			colIndexes[row] = i;
			if(!isValidNQueen(colIndexes, row)) continue;
			
			solveNQueens(colIndexes, row+1, rowStr, n, result);
		}
	}
	
	private boolean isValidNQueen(int[] colIndexes, int row){
		for(int i = 0 ; i < row ; i ++){
			if(colIndexes[i] == colIndexes[row] 
					|| Math.abs(row - i) == Math.abs(colIndexes[row] - colIndexes[i])) return false;
		}
		
		return true;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 42: Trapping Rain Water</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int trap(int[] height) {
		
		return 0;
	}
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 39: Combination Sum </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if( candidates == null || candidates.length == 0) return result;
		
		Arrays.sort(candidates);
		
		combinationSumDfs(candidates, result, new ArrayList<Integer>(), target, 0);
		return result;   
    }
	
	private void combinationSumDfs(int[] candidates, List<List<Integer>> result, List<Integer> current, int target, int index){
		if(target == 0){
			List<Integer> temp = new ArrayList<Integer>(current);
			result.add(temp);
			return;
		}
		
		for(int i = index; i < candidates.length ; i++	){
			if(target < candidates[i]) return;
			
			current.add(candidates[i]);
			combinationSumDfs(candidates, result, current, target - candidates[i], i);
			current.remove(current.size() - 1);
		}
	}
	
	
	/**
	 * <a href="" target="_blank">
	 *Leetcode 33: Search in Rotated Sorted Array  </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int search(int[] nums, int target) {
		if(nums == null || nums.length == 0	)	return -1;
		
		int l = 0; 
		int r = nums.length;
		while (l < r){
			int m = l + (r - l)/ 2;
			if(nums[m] == target)return m;
			
			if(nums[l] <= nums[m]){
				if(target >= nums[l] && target < nums[m] ) r = m -1;
				else l = m + 1;
			}else {
				if(target <= nums[r] && target > nums[m]) l = m + 1;
				else r = m -1;
			}
		}
		
		return -1;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/" target="_blank">
	 *Leetcode 30: Substring with Concatenation of All Words </a><p>
	 * Notes:
	 * <ul>
	 * <li>answer: http://fisherlei.blogspot.com/2013/01/leetcode-substring-with-concatenation.html
	 * </ul>
	 * @deprecated
	 */
	public List<Integer> findSubstring(String s, String[] words) {
        return null;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/" target="_blank">
	 *Leetcode 25: Reverse Nodes in k-Group</a><p>
	 * Notes:
	 * <ul>
	 * <li>answer:http://fisherlei.blogspot.com/2012/12/leetcode-reverse-nodes-in-k-group.html
	 * </ul>
	 * @deprecated
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
     return null;   
    }
	
	/**
	 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/" target="_blank">
	 *Leetcode 23: Merge k Sorted Lists </a><p>
	 * Notes:
	 * <ul>
	 * <li>answer: http://www.programcreek.com/2013/02/leetcode-merge-k-sorted-lists-java/
	 * </ul>
	 * @deprecated
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/roman-to-integer/" target="_blank">
	 *Leetcode 13: Roman to Integer</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int romanToInt(String s) {
        int result = 0;
		
		for(int i=0;i< s.length();i++){
			char ch = s.charAt(i);
			switch(ch){
			case 'M':
				result +=1000; break;
			case 'D':
				result +=500; break;
			case 'C':
				if(i + 1 < s.length()){
					if(s.charAt(i+1) == 'M'){
						result +=900;
						i++;
					}else if(s.charAt(i+1) == 'D') {
						result +=400;
						i++;
					}else result +=100;
				}else{
					result += 100;
				}
				break;
			case 'L':
				result +=50; break;
			case 'X':
				if(i + 1 < s.length()){
					if(s.charAt(i+1) == 'C'){
						result +=90;
						i++;
					}else if(s.charAt(i+1) == 'L') {
						result +=40;
						i++;
					}else result +=10;
				}else{
					result += 10;
				}
				break;
			case 'V':
				result +=5; break;
			case 'I':
				if(i + 1 < s.length()){
					if(s.charAt(i+1) == 'X'){
						result +=9;
						i++;
					}else if(s.charAt(i+1) == 'V') {
						result +=4;
						i++;
					}else result +=1;
				}else{
					result += 1;
				}
				break;
			default: throw new IllegalArgumentException("wrong format");
			}
		}
		
		return result;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/integer-to-roman/" target="_blank">
	 *Leetcode 12: Integer to Roman</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public String intToRoman(int num) {
		if (num > 3999)
			throw new IllegalArgumentException("num > 3999");

		StringBuilder result = new StringBuilder();
		int rest = num;

		result.append(getRoman(rest / 1000, "", "", "M"));
		rest = rest % 1000;
		result.append(getRoman(rest / 100, "M", "D", "C"));
		rest = rest % 100;
		result.append(getRoman(rest / 10, "C", "L", "X"));
		rest = rest % 10;
		result.append(getRoman(rest / 1, "X", "V", "I"));

		return result.toString();
	}

	private String getRoman(int d, String hig, String med, String cur) {
		switch (d) {
		case 0:
			return "";
		case 1:
			return cur;
		case 2:
			return cur + cur;
		case 3:
			return cur + cur + cur;
		case 4:
			return cur + med;
		case 5:
			return med;
		case 6:
			return med + cur;
		case 7:
			return med + cur + cur;
		case 8:
			return med + cur + cur + cur;
		case 9:
			return cur + hig;
		default:
			throw new IllegalArgumentException("too big number");
		}
	}
	
	/**
	 * <a href="https://leetcode.com/problems/container-with-most-water/" target="_blank">
	 *Leetcode 11: Container With Most Water</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int[] result ={0, 0};
        while(left < right){
        	if(Math.min(height[left], height[right]) * (right -left) > 
        		Math.min(height[result[0]], height[result[1]]) * (result[1] - result[0])){
        		result[0] = left;
        		result[1] = right;
        	}
        	
        	if(height[left] < height[right]){
        		left ++;
        	}else{
        		right --;
        	}
        }
        
		return Math.min(height[result[0]], height[result[1]]) * (result[1] - result[0]);
    }
	
	/**
	 * <a href="https://leetcode.com/problems/regular-expression-matching/" target="_blank">
	 *Leetcode 10: Regular Expression Matching</a><p>
	 * Notes:
	 * <ul>
	 * <li>answer: http://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
	 * </ul>
	 * @deprecated
	 */
	public boolean isMatch(String s, String p) {
        return false;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/palindrome-number/" target="_blank">
	 *Leetcode 9: Palindrome Number</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 */
	public boolean isPalindrome(int x) {
        try{
			int result = reverseInteger(x);
			if(result == Math.abs(x)) return true;
			else return false;
		}catch(Exception e){
			return false;
		}
    }
    
    private int reverseInteger(int x) {
		int rest = Math.abs(x);
		int result = 0;
		while(rest > 0 ){
			int dig = rest % 10;
			result = result * 10 + dig;
			
			rest = rest /10;
		}
		
		
		return x > 0? result : 0- result;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/string-to-integer-atoi/" target="_blank">
	 *Leetcode 8: String to Integer (atoi) </a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 * @deprecated
	 */
	public int myAtoi(String str) {
        return 0;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/reverse-integer/" target="_blank">
	 * Leetcode 7: Reverse Integer</a><p>
	 * Notes:
	 * <ul>
	 * <li> result out of max_value
	 * <li> input is negative, zero
	 * </ul>
	 */
	public int reverse(int x) {
		int rest = Math.abs(x);
		int result = 0;
		while(rest > 0 ){
			int dig = rest % 10;
			result = result * 10 + dig;
			
			rest = rest /10;
		}
		
		
		return x > 0? result : 0- result;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/zigzag-conversion/" target="_blank">
	 * Leetcode 6: ZigZag Conversion</a><p>
	 * Notes:
	 * <ul>
	 * <li>
	 * </ul>
	 * @deprecated
	 */
	public String convert(String s, int numRows) {
		return null;
    }
	
	/**
	 * <a href="https://leetcode.com/problems/longest-palindromic-substring/" target="_blank">
	 * Leetcode 5: Longest Palindromic Substring</a><p>
	 * Notes:
	 * <ul>
	 * <li>odd and even palindrome
	 * <li>forward check even case
	 * </ul>
	 */
    public String longestPalindrome(String s) {
        int[] result = {0, 0};
		int forward= 0, backward=0;
		
		for(int i = 0;i< s.length();i++){
			forward = i+1;
			backward = i-1;
			
			while(forward < s.length() && s.charAt(forward)==s.charAt(i)){
				forward ++;
			}
			
			while(forward < s.length() && backward >=0 && s.charAt(backward) == s.charAt(forward)){
				forward ++;
				backward --;
			}
			
			if(forward-backward > result[1]-result[0]){
				result[1]=forward;
				result[0]=backward;
			}
		}
		
		return s.substring(result[0]+1, result[1]);
    }
	
	/**
	 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/" target="_blank">
	 * Leetcode 4: Median of Two Sorted Arrays</a><p>
	 * Notes:
	 * <ul>
	 * <li>answer 1:http://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/
	 * </ul>
	 * @deprecated
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
     return 0;   
    }

	/**
	 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/" target="_blank">
	 * Leetcode 3: Longest Substring Without Repeating Characters</a><p>
	 * Notes:
	 * <ul>
	 * <li> string is null
	 * </ul>
	 */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) return 0;
        
        int result = 0;
        int start = -1;
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        
        for(int i = 0;i< s.length();i++){
        	char ch = s.charAt(i);
        	if(cache.containsKey((int) ch)){
        		int index = cache.get((int)ch);
        		if(index > start){
        			start = index;
        		}	
        	}
        	
        	result = Math.max(result, i -start);
    		cache.put((int)ch, i);
        }
        
        return result;
	}
	
    /**
     * <a href="https://leetcode.com/problems/add-two-numbers/" target="_blank">Leetcode 2: Add Two Numbers</a><p>
     * Notes:
     * <ul>
     * <li>two lists might have same length
     * <li>last carry over might not be 0
     * </ul>
     */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(Integer.MIN_VALUE);

		int carry = 0;
		ListNode tail = result;
		while (l1 != null || l2 != null) {
			int val = carry;
			if (l1 != null) {
				val += l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				val += l2.val;
				l2 = l2.next;
			}

			carry = val / 10;
			tail.next = new ListNode(val % 10);
			tail = tail.next;
		}

		if (carry > 0)
			tail.next = new ListNode(carry);

		return result.next;
	}

	/**
	 * <a href="https://leetcode.com/problems/two-sum/" target="_blank">Leetcode 1: Two Sum</a>
	 * Notes:
	 * <ul>
	 * <li>array might be empty
	 * </ul>
	 */
	public int[] twoSum(int[] numbers, int target) {
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

		for (int i = 0; i < numbers.length; i++) {
			int dif = target - numbers[i];
			if (cache.containsKey(dif)) {
				return new int[] { cache.get(dif) + 1, i + 1 };
			} else {
				cache.put(numbers[i], i);
			}
		}

		throw new IllegalArgumentException("no matching two numbers");
	}
	
}
