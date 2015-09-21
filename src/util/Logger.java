package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import structure.ListNode;
import structure.TreeNode;

/**
 *
 *@author: wxu on Jul 31, 2014
 *
 */
public class Logger {

	private static final String LOG_FILE = System.getProperty("user.home") + File.separator + "algorithm-log.txt";
	private static final String SEPARATOR = "-------------------------------------------------------------------------------------------------------";
	private static BufferedWriter _log;
	private static long _startTime = 0;
	
	static{
		try {
			_log = new BufferedWriter(new FileWriter(LOG_FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void logStart(String str){
		println(SEPARATOR);
		_startTime = System.currentTimeMillis();
		println("START ' "  + str + "' at : " + _startTime);
	}
	
	public static void logEnd(String str){
		long endTime = System.currentTimeMillis();
		println("END '" + str + "' at : " + endTime +". Elapsed time : " + (endTime - _startTime));
	}
	
	public static void logInfo(String str){
		println(str);
	}
	
	private static void logInfo(String msg, String data){
		logInfo(String.format("%s = %s", msg, data));
	}
	
	public static void logData(String msg, boolean result){
		logInfo(msg, result + "");
	}
	
	public static void logData(String msg, double result){
		logInfo(msg, result + "");
	}
	
	public static void logData(String msg, String result){
		logInfo(msg, result);
	}
	
	public static void logData(String msg, int result){
		logInfo(msg, result+"");
	}
	
	public static void logData(String msg, char[][] data){
		logInfo(msg, DataPrinter.printArray(data));
	}
	
	public static void logData(String msg, int[] data){
		logInfo(msg, DataPrinter.printArray(data));
	}
	
	public static void logData(String msg, TreeNode data){
		logInfo(msg, DataPrinter.printTree(data));
	}
	
	public static void logData(String msg, int[][] data){
		logInfo(msg, DataPrinter.printArray(data));
	}
	
	public static void logData(String msg, String[] arr){
		logInfo(msg ,DataPrinter.printArray(arr));
	}
	
	public static void logData(String msg, ListNode head){
		logInfo(msg ,DataPrinter.printLinkedList(head));
	}
	
	public static void logData(String msg, List<List<Integer>> list){
		logInfo(msg , DataPrinter.printListList(list));
	}
	
	private static void println(String str){
		try {
			str = str + "\n";
			_log.write(str);
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
