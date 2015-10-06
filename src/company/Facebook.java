/**
 * 
 */
package company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import structure.Contact;
import structure.ListNode;
import structure.TreeNode;
import structure.UndirectedGraphNode;

/**
 * @author Thomas Xu
 *
 */
public class Facebook {
	
	/**
	 * leetcode 73 : set matrix zero
	 */
	public void setZeroes(int[][] matrix) {
		if(matrix == null || matrix.length == 0 ) return ;
		
		boolean col1Is0 = false;
		boolean row1Is0 = false;
		for(int i = 0 ; i< matrix.length ; i++){
			if(matrix[i][0] == 0) {
				col1Is0 = true;
				break;
			}
		}
	
		for(int i = 0 ; i < matrix[0].length ; i++){
			if(matrix[0][i] == 0){
				row1Is0 = true;
				break;
			}
		}
		
		for(int r = 1; r < matrix.length ; r++){
			for(int c = 1; c < matrix[r].length; c ++){
				if(matrix[r][c] == 0){
					matrix[r][0] = 0;
					matrix[0][c] = 0;
				}
			}
		}
		
		for(int r = 1; r < matrix.length ; r ++){
			for(int c = 1; c < matrix[r].length ; c ++){
				if(matrix[r][0] == 0 || matrix[0][c] == 0) matrix[r][c] = 0;
			}
		}
		
		if(row1Is0) for(int i = 0 ; i < matrix[0].length; i ++ ) matrix[0][i] = 0;
		if(col1Is0) for(int i = 0; i < matrix.length ; i ++) matrix[i][0] = 0;
	}
	
	/**
	 * leetcode 78: subset
	 */
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>>	result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        
        Arrays.sort(nums);
        result.add(new ArrayList<Integer>());
        
        for(int i = 0; i< nums.length ; i++){
        	int num = nums[i];
        	
        	int size = result.size();
        	for(int j = 0 ; j < size ; j++	){
        		List<Integer> newList = new ArrayList<Integer>(result.get(j));
        		newList.add(num);
        		result.add(newList);
        	}
        }
        
        return result;
        
    }
	
	/**
	 * leetcode 61: rotate list
	 */
	public ListNode rotateList(ListNode head, int k){
		if(head == null) throw new IllegalArgumentException("null head");
		
		ListNode next = head;
		int count = 0;
		ListNode split = head;
		while(next != null){
			count ++;
			next = head.next;
			if(count > k) split = split.next;
		}
		
		next = split.next;
		split.next = null;
		
		ListNode newHead = null;
		ListNode newTail = next;
		while(next != null){
			ListNode temp = next.next;
			
			next.next = newHead;
			newHead = next;
			
			next = temp;
		}
		
		newTail.next = head;
		return newHead;
	}
	
	/**
	 * http://www.careercup.com/question?id=15888677
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node){
		if(node == null) return null;
		
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Map<UndirectedGraphNode, UndirectedGraphNode> map= new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		
		UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
		map.put(node, newHead);
		queue.add(node);
		
		while(!queue.isEmpty()){
			UndirectedGraphNode cur = queue.poll();
			List<UndirectedGraphNode> children = cur.neighbors;
			
			UndirectedGraphNode curCopy = map.get(cur);
			for(UndirectedGraphNode child : children){
				if(! map.containsKey(child)){
					UndirectedGraphNode copy = new UndirectedGraphNode (child.label);
					map.put(child, copy	);
					queue.add(child);
					curCopy.neighbors.add(copy);
				}else{
					UndirectedGraphNode copy = map.get(child);
					curCopy.neighbors.add(copy);
				}
			}
		}
		
		return newHead;
	}
	
	/**
	 * http://www.careercup.com/question?id=5344154741637120
	 */
	public void sinkZero(TreeNode root){
		
		List<TreeNode> postorder = new ArrayList<TreeNode>();
		int insert = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		TreeNode lastVisitedNode = node;
		while(! stack.isEmpty() || node != null){
			if(node != null){
				stack.push(node);
				node = node.left;
			}else {
				TreeNode peeknode = stack.peek();
				if(peeknode.right != null && peeknode.right != lastVisitedNode) node = peeknode;
				else {
					//visit
					postorder.add(peeknode);
					if (peeknode.val == 0 && peeknode != postorder.get(insert)){
						int temp = peeknode.val;
						peeknode.val = postorder.get(insert).val;
						postorder.get(insert).val = 0;
						insert ++;
					}
					
					lastVisitedNode = stack.pop();
				}
			}
		}
	}
	
	/**
	 * http://www.careercup.com/question?id=5179916190482432
	 */
	public int[] multiplyOthers(int[] arr){
		if(arr == null || arr.length < 2) return arr;
		
		int [] result = new int[arr.length];
		result[0] = arr[0];
		for(int i = 1; i< arr.length ; i++){
			result[i] = result[i-1] * arr[i];
		}
		
		int multiplyAfter = arr[arr.length -1];
		result[result.length -1] = result[result.length -2];
		for(int i = result.length -2 ; i >= 0 ; i--	){
			result[i] = (i -1 >=0 ? result[i-1] : 1 ) * multiplyAfter;
			multiplyAfter *= arr[i];
		}
		
		return result;
	}
	
	/**
	 * http://www.careercup.com/question?id=5106757177180160
	 */
	public String reverseWords(String str){
		if(str == null || str.isEmpty()) return str;
		
		char[] chars = str.toCharArray();
		
		for(int i = 0 ;i < chars.length ; i++){
			int start = 0;
			while (start < chars.length && chars[start] == ' ') start ++;
			
			if(start == chars.length ) break;
			
			int end = start;
			while( end < chars.length && chars[end] != ' ') end ++;
			
			reverse(chars, start, end);
		}
		
		return new String(chars);
	}
	
	private void reverse(char[] chars, int start, int end){
		for(int i = start; start <= start + (end - start)/ 2; i ++){
			char temp = chars[i];
			chars[i] = chars[end -1 - i];
			chars[end-1-i] = temp;
		}
	}
	
	/**
	 * http://www.careercup.com/question?id=4849108141473792
	 */
	public String removeComments(String str){
		if(str == null || str.isEmpty()) return str;
		
		StringBuilder out = new StringBuilder();
		for(int i = 0; i< str.length() ; i++){
			if(str.charAt(i) == '/' && (i + 1 < str.length() && str.charAt(i+1) == '*')){
				i= i+2;
				
				while(i < str.length() - 1 && ! (str.charAt(i) == '*' && str.charAt(i+1) == '/')) i++;
			}
			
			if(i < str.length()) out.append(str.charAt(i));
			
		}
		
		return out.toString();
	}
	
	/**
	 * http://www.careercup.com/question?id=5090815091146752
	 */
	public int findCelebrity(int n){
		int candidate = -11;
		for (int i = 1; i <=n ; i++){
			if (candidate == -1){
				candidate = i;
				continue;
			}
			if( know(i, candidate) ){
				if( know(candidate, i )) candidate = -1;
				else ; //candidate = candidate;
			}else {
				if( know(candidate, i ))  candidate  = i;
				else candidate = -1;
			}
		}
		
		return candidate;
	}
	
	private boolean know(int i, int j){ return false;}
	
	/**
	 * http://www.careercup.com/question?id=5080405046722560
	 */
	public void sortPatients(int[] patients){
		if(patients == null || patients.length < 2) return ;
		
		int zero = -1;
		int one = 0;
		int two = patients.length;
	
		while (one < two){
			switch(patients[one]){
			case 0:
				zero++;
				one++;
				break;
			case 1:
				one++;
				break;
			case 2:
				two++;
				while (two >0 && patients[two] == 2) two++;
				if(two == -1) break;
				
				one++;
				if(patients[two] == 0) zero++;
			}
			
			if( zero > 0) patients[zero] = 0;
			
			if(one < patients.length) patients[one] = 1;
		}
	}
	
	
	
	/**
	 * http://www.careercup.com/question?id=5684404858912768
	 */
	public int paint(int n, int m, int[][] cost) {
		if(n == 0 ) return 0;
		if(m == 0  || cost == null || cost.length == 0) throw new IllegalArgumentException ("");
		
		int[][] minCost = new int[n][m];
		for(int i = 0 ; i< m ; i++) minCost[0][i] = cost[0][m];
		
		for(int h = 1; h < n ; h++)
			for(int c = 0 ; c < m; c++ ){
				int min = Integer.MAX_VALUE;
				for(int lc = 0 ; lc < m; lc ++){
					if(lc == c) continue;
					min = Math.min(min, minCost[h-1][lc] + cost[h][c]);
				}
				
				minCost[h][c] = min;
			}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0 ; i< m; i++){
			result = Math.min(result, minCost[n-1][i]);
		}
		
		return result;
	}
	
	
	/**
	 * http://www.careercup.com/question?id=5181387768332288
	 */
	public List<List<Integer>> mergeContact(Contact[] contacts){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		if(contacts == null || contacts.length == 0) return result;
		
		Map<String, Set<Integer>> phones = new HashMap<String, Set<Integer>>();
		for(int i = 0; i < contacts.length ; i++){
			if(phones.containsKey(contacts[i].phone)){
				phones.get(contacts[i].phone).add(i);
			}else{
				Set<Integer> set = new HashSet<Integer>();
				set.add(i);
				phones.put(contacts[i].phone, set);
			}
		}
		
		Map<String, Set<Integer>> emails = new HashMap<String, Set<Integer>>();
		for(int i = 0 ; i< contacts.length ; i++){
			if(emails.containsKey(contacts[i].email)){
				Set<Integer> set = emails.get(contacts[i].email);
				set.addAll(phones.get(contacts[i].phone));
			}else{
				emails.put(contacts[i].email, phones.get(contacts[i].phone));
			}
		}
		
		Iterator<Set<Integer>> iterator = emails.values().iterator();
		while(iterator.hasNext()) result.add(new ArrayList<Integer>(iterator.next()));
		
		return result;
	}
	
	/**
	 * http://www.careercup.com/question?id=5088794078347264
	 */
	public List<Integer> findSubset(int [] nums, int m, int sum){
		List<Integer> result = new ArrayList<Integer>();
		
		if(nums == null || nums.length == 0 || m == 0) return result;
		
		if (findSubset_(nums, 0, result, m, sum)) return result;
		else return new ArrayList<Integer>();
	}
	
	private boolean findSubset_(int[] nums, int start, List<Integer> result, int m, int sum){
		if(m == 0 || sum == 0) return true;
		if(sum < 0) return false;
		
		for(int i = start; i< nums.length ; i++){
			if(nums[i] < sum){
				result.add(nums[i]);
				if(findSubset_(nums, i + 1, result, m-1, sum - nums[i])) return true;
				
				result.remove(result.size() -1);
			}
		}
		
		return false;
	}
	
	/**
	 * http://www.careercup.com/question?id=5719182299627520
	 */
	public List<String> searchNumber(String[] nums, int m){
		List<String> result = new ArrayList<String>();
		
		return result;
	}

}
