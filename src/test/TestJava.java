package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import util.DataPrinter;

/**
 * 
 * class: test.TestJava
 * @author wxu
 * @since Jul 30, 2015
 */
public class TestJava {
	
	@Test
	public void testList(){
		List<Integer> list = new ArrayList<Integer>(20);
		list.add(10, 15);
	}
	
	@Test
	public void testNull(){
		String a = null;
		String b = null;
		System.out.println(a==b);
	}

	@Test
	public void testStringCompare(){
		String[] str = {"1", "9", "91", "92", "987"};
		Arrays.sort(str, new Comparator<String>(){
			public int compare(String s1, String s2){
				return (s1+ s2).compareTo(s2 + s1);
			}
		});
		
		System.out.println(DataPrinter.printArray(str));
	}
	
	@Test
	public void testBits(){
		int d = Integer.MAX_VALUE;
		System.out.println(d);
		System.out.println(Integer.toBinaryString(d));
		boolean t = (d&Integer.MAX_VALUE) != (d&Integer.MAX_VALUE);
		
		System.out.println(t);
		d = d<< 1;
		
		System.out.println("<<" + Integer.toBinaryString(d));
		
		
	}
}
