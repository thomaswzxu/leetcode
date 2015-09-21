package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import leetcode.Leetcode;
import leetcode.Solutions;

import org.junit.Test;

import structure.Point;
import util.DataConverter;
import util.DataPrinter;
import util.Logger;

public class TestLeetcode {

	@Test
	public void test() {
		String methodName = "test";
		Logger.logStart(methodName);
		int[] A = {0,1,2,3,4,5,6,7,8,9};
		
		Logger.logData("A" , A);
		Solutions sol = new Solutions();
		int result = 0;
		Logger.logData("result" ,  result);
		
		
		Logger.logEnd(methodName);
	}
	
//---------------------------------------------------------------------------------------
	
	@Test
	public void testwordBreakII() {
		String methodName = "testwordBreakII";
		Logger.logStart(methodName);
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		String[] words = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		Set<String> wordDict = new HashSet<String>();
		for(String str : words) wordDict.add(str);
		
		Solutions sol = new Solutions();
		List<String> result = sol.wordBreakII(s, wordDict);
		Logger.logData("result" ,  DataPrinter.printListString(result));
		
		
		Logger.logEnd(methodName);
	}
	
	@Test
	public void testmaxPoints() {
		String methodName = "testmaxPoints";
		Logger.logStart(methodName);
		Point[] p = new Point[3];
		p[0]= new Point(2, 3);
		p[1]= new Point(3, 3);
		p[2]= new Point(-5, 3);
		
		Solutions s = new Solutions();
		int max = s.maxPoints(p);
		Logger.logData("result" ,  max);

		Logger.logEnd(methodName);
	}
	
	@Test
	public void testwordBreak() {
		String methodName = "testwordBreak";
		Logger.logStart(methodName);

		String s = "aaaaaaa";
		Set<String> wordDict = DataConverter.toSetString(new String[]{"aaaa","aaa"});
		
		Leetcode leetcode = new Leetcode();
		boolean result = leetcode.wordBreak(s, wordDict);
		Logger.logData("result" ,  result);
		
		
		Logger.logEnd(methodName);
	}
	
	@Test
	public void testfindMedianSortedArrays() {
		String methodName = "test";
		Logger.logStart(methodName);
		int[] A = {0,1,2,3,4,5,6,7,8,9};
		int[] B = {0,1,2,3,4,5,6,7,8};
		
		Logger.logData("A" , A);
		Logger.logData("B" , B);
		Leetcode leetcode = new Leetcode();
		double result = leetcode.findMedianSortedArrays(A, B);
		Logger.logData("result" ,  result);
		
		
		Logger.logEnd(methodName);
	}

}
