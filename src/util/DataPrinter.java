package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import structure.ListNode;
import structure.TreeNode;

/**
 *
 *@author: wxu on Jul 31, 2014
 *
 */
public class DataPrinter {
	
	public static String printArray(boolean[] arr){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(int i = 0;i< arr.length;i++){
			out.append(arr[i]?"T":"F" + ",");
		}
		
		out.deleteCharAt(out.length()-1);
		out.append("]");
		
		return out.toString();
	}
	
	public static String printSetString(Set<String> set){
		String[] arr = set.toArray(new String[0]);
		return printArray(arr);
	}
	
	public static String printArray(String[] arr){
		return printArray(arr, true);
	}
	
	public static String printArray(String[] arr, boolean singleLine){
		StringBuilder out = new StringBuilder();
		out.append("[").append(singleLine?"":"\n");
		for(int i = 0;i< arr.length;i++){
			out.append(arr[i] + ",").append(singleLine?"":"\n");
		}
		
		out.deleteCharAt(out.length()-1);
		out.append("]");
		
		return out.toString();
	}
	
	public static String printLinkedList(ListNode head){
		StringBuilder out = new StringBuilder();
		out.append("[");
		
		while(head != null){
			out.append(head.val + ",");
			head = head.next;
		}
		
		out.deleteCharAt(out.length()-1);
		out.append("]");
		return out.toString();
	}
	
	public static String printArray(char[][] arr){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(int i = 0 ;i < arr.length; i++){
			out.append("\""+(new String(arr[i]))+ "\",\n");
		}
		
		out.delete(out.length()-2, out.length());
		out.append("]");
		return out.toString();
	}
	
	public static String printArray(int[][] arr){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(int i = 0 ;i < arr.length; i++){
			out.append(printArray(arr[i]) + ",\n");
		}
		
		out.delete(out.length()-2, out.length());
		out.append("]");
		return out.toString();
	}

	public static String printArray(int[] arr){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(int i = 0;i< arr.length;i++){
			out.append(arr[i] + ",");
		}
		
		out.deleteCharAt(out.length()-1);
		out.append("]");
		
		return out.toString();
	}
	
	public static String printTree(TreeNode root){
		StringBuilder out = new StringBuilder();
		List<TreeNode> queue = new ArrayList<TreeNode>();
		queue.add(root);
		out.append("[("+root.val+") ");
		
		while(!queue.isEmpty()){
			List<TreeNode> temp = new ArrayList<TreeNode>();
			out.append("(");
			for(TreeNode node : queue){
				if(node.left != null){
					out.append(node.left.val + "^");
					temp.add(node.left);
				}else{
					out.append( "#^");
				}
				
				if(node.right != null){
					out.append(node.right.val + ",");
					temp.add(node.right);
				}else{
					out.append( "#,");
				}
			}
			out.append(") ");
			
			queue = temp;
		}
		
		out.append("]");
		
		return out.toString();
	}
	
	public static String printListList(List<List<Integer>> data){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(int i =0;i < data.size(); i++){
			List<Integer> list = data.get(i);
			out.append("\n(");
			for(Integer num : list) out.append(num + ", ");
			out.append(")");
		}
		out.append("]");
		
		return out.toString();
	}
	
	public static String printListString(List<String> data){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(String str: data){
			out.append("'").append(str).append("',");
		}
		
		out.append("]");
		return out.toString();
	}
	
	public static String printListInt(List<Integer> data){
		StringBuilder out = new StringBuilder();
		out.append("[");
		for(Integer str: data){
			out.append("'").append(str).append("',");
		}
		
		out.append("]");
		return out.toString();
	}
}
