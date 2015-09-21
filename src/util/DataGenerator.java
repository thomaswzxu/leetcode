package util;

import java.util.Arrays;
import java.util.Random;

import structure.ListNode;

/**
 *
 *
 *@author: wxu on Jul 31, 2014
 *
 */
public class DataGenerator {
	
	private static Random _random = new Random();
	
	private final static String LETTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final static String DIGITS ="1234567890";
	private final static String SPECIALS ="~!@#$%^&*,./;'<>?:\"\\|+=_-";
	private final static String PARENTHESIS = "()[]{}";
	
	public static ListNode toList(String str){
		int[] arr = toArray(str);
		
		ListNode head = new ListNode(arr[0]);
		ListNode tail = head;
		for(int i = 1; i< arr.length;i++) {
			tail.next = new ListNode(arr[i]);
			tail = tail.next;
		}
		
		return head;
	}
	
	public static int[] toArray(String str){
		String[] numstr =	str.split(",");
		
		int[] result = new int[numstr.length];
		
		for(int i =0;i< result.length; i++){
			result[i] = Integer.valueOf(numstr[i].trim());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param min inclusive
	 * @param max exclusive
	 * @return
	 */
	public static int createInt(int min, int max){
		return _random.nextInt(max-min) + min;
	}
	
	/**
	 * 
	 * @param size
	 * @param density
	 * @return
	 */
	public static String createPalindromeString(int size, int density){
		String source = LETTERS;
		
		int max = density < 52? density : 52;
		StringBuilder result = new StringBuilder();
		for(int i=0;i<size;i++){
			result.append(source.charAt(_random.nextInt(max)));
		}
		
		return result.toString();
	}
	
	public static String createParenthesis(int size){
		String source = PARENTHESIS;
		
		int max = source.length();
		StringBuilder result = new StringBuilder();
		for(int i=0;i<size;i++){
			result.append(source.charAt(_random.nextInt(max)));
		}
		
		return result.toString();
	}
	
	/**
	 * 
	 * @param size
	 * @param type, 0: all; 1:only letters; 2:letters + digits; 3 digits;
	 * @return
	 */
	public static String createString(int size, int type){
		String source ="";
		switch(type){
		case 0: 
			source = LETTERS + DIGITS + SPECIALS + PARENTHESIS;
			break;
		case 1:
			source = LETTERS;
			break;
		case 2:
			source = LETTERS + DIGITS;
			break;
		case 3:
			source = DIGITS;
			break;
		default:
			source = LETTERS + DIGITS + SPECIALS+ PARENTHESIS;
		}
		
		int max = source.length();
		StringBuilder result = new StringBuilder();
		for(int i=0;i<size;i++){
			result.append(source.charAt(_random.nextInt(max)));
		}
		
		return result.toString();
	}
	

	
	public static int[] createNegativeIntArray(int size, int max, int order){
		int[] result = createIntArray(size, max, order);
		
		for(int i = 0; i< result.length;i ++){
			result[i] = _random.nextBoolean()? result[i] : -result[i];
		}
		
		return result;
	}
	
	public static int[] createIntArray(int size, int max, int order){
		int[] result = new int[size];
		for(int i = 0;i< size; i++){
			result[i] = _random.nextInt(max);
		}
		
		if(order > 0 ){
			Arrays.sort(result);
			return result;
		}else if(order < 0 ){
			Arrays.sort(result);
			for(int i = 0;i<size;i++){
				if(i >= size -1 - i){
					break;
				}
				
				int temp = result[i];
				result[i] = result[size-1-i];
				result[size-1-i] = temp;
			}
			
			return result;
		}else{
			return result;
		}
		
	}
	
	
	//* private supporting functions -------------------------------
	
	

}
