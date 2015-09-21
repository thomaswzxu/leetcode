package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import structure.ListNode;
import structure.TreeNode;

public class DataConverter {
	
	private static Pattern pattern = Pattern.compile("\\[(\\s*\\-?\\d+\\s*(,\\s*\\-?\\d+\\s*)*)\\]");

	public static int[] toArray(String str){
		Matcher m = pattern.matcher(str);
		m.matches();
		String[] numstr =	m.group(1).split(",");
		
		int[] result = new int[numstr.length];
		
		for(int i =0;i< result.length; i++){
			result[i] = Integer.valueOf(numstr[i].trim());
		}
		
		return result;
	}
	
	public static Set<String> toSetString(String[] strs){
		Set<String> result = new HashSet<String>();
		for(String str: strs){
			result.add(str);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static TreeNode toTree(int[] arr){
		TreeNode[] nodes = new TreeNode[arr.length];
		nodes[0] = new TreeNode(Integer.valueOf(arr[0]));
		
		for(int i = 1 ; i < arr.length ;i ++){
			if(arr[i] == '#') continue;
			else nodes[i] = new TreeNode(Integer.valueOf(arr[i]));
			
			if(i%2 == 1){
				nodes[(i-1)/2].left = nodes[i]; 
			}else{
				nodes[(i-2)/2].right = nodes[i];
			}
		}
		
		return nodes[0];
	}
	
	public static ListNode toLinkedList(int[] arr){
		ListNode head = new ListNode(arr[0]);
		ListNode tail = head;
		for(int i = 1; i< arr.length; i++){
			tail.next = new ListNode(arr[i]);
			tail = tail.next;
		}
		
		return head;
	}
	
	public static ListNode toLinkedList(String str){
		int[] arr = toArray(str);
		return toLinkedList(arr);
	}
	
	public static int[][] toMatrix(String str){
		Matcher m = pattern.matcher(str);
		List<int[]> result = new ArrayList<int[]>();
		int start =0;
		while(m.find(start)){
			String g = m.group();
			int[] arr = toArray(g);
			result.add(arr);
			start = m.end();
		}
		
		int[][] matrix = new int[result.size()][];
		for(int i = 0 ; i < matrix.length; i ++) matrix[i] = result.get(i);
		
		return matrix;
	}
}
