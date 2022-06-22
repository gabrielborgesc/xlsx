package xslx;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String args[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(10);
		list.add(2);
		list.add(1);
		System.out.println("sorted: " + sort(list));
	}
	
	public static List<Integer> sort(List<Integer> list) {
		if(list.isEmpty())
			return list;
		return insertSorted(head(list), sort(tail(list)));
		
	}
	
	public static List<Integer> insertSorted(Integer e, List<Integer> list){
		
		if(list.isEmpty())
			return cons(e, new ArrayList<Integer>());
		
		if(e <= head(list)) 
			return cons(e, list);
		
		return cons(head(list), insertSorted(e, tail(list)));
				
	}
	
	public static Integer max(List<Integer> list) {
		
		if(list.isEmpty())
			return 0;
		
		Integer head = list.get(0);
		List<Integer> tail = new ArrayList<Integer>(list);
		tail.remove(0);
		
		if(head > max(tail))
			return head;
		
		return max(tail);
	}
	
	public static Integer head(List<Integer> list) {
		return list.get(0);
	}
	
	public static List<Integer> tail(List<Integer> list){
		List<Integer> tail = new ArrayList<Integer>(list);
		tail.remove(0);
		return tail;
	}
	
	public static List<Integer> cons(Integer e, List<Integer> list){
		list.add(0, e);
		return list;
	}
	
}
