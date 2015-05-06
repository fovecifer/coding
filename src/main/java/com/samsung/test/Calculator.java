package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("calculator.input"));
		int caseNum = 10;
		
		for(int i = 0; i < caseNum; i++) {
			int length = scanner.nextInt();
			String line = scanner.next();
			System.out.println(i + 1 + "# " + calculator(line));
		}
	}
	
	private static int calculator(String input) {
		LinkedList<Character> in = new LinkedList<Character>(); 
		for(int i = 0; i < input.length(); i++) {
			in.add(input.charAt(i));
		}
		return calculator(in);
	}
	
	private static int calculator(LinkedList<Character> input) {
		LinkedList<Character> tmp = new LinkedList<Character>();
		
		while(!input.isEmpty()) {
			Character t = input.pollLast();
			if(t == '(') {
				Character tt;
				LinkedList<Character> toCal = new LinkedList<Character>();
				do {
					tt = tmp.pollFirst();
					toCal.add(tt);
				}while(tt != ')');
				toCal.removeLast();
				input.addAll(calculator2(toCal));
			}else {
				tmp.addFirst(t);
			}
		}
//		System.out.println(tmp);
		
		return ListToIn(tmp);
	}
	
	private static LinkedList<Character> calculator2(LinkedList<Character> in) {
		LinkedList<Character> result = new LinkedList<Character>();
		LinkedList<Character> left = new LinkedList<Character>();
		
		/* deal with * */
		while(!in.isEmpty()) {
			Character t = in.pollFirst();
			if(t != '*') {
				left.add(t);
			}else {
				LinkedList<Character> l = new LinkedList<Character>();
				LinkedList<Character> r = new LinkedList<Character>();
				while(left.peekLast() != null && left.peekLast() != '+') {
					l.addFirst(left.pollLast());
				}
				while(in.peekLast() != null && in.peekFirst() != '+' && in.peekFirst() != '*') {
					r.add(in.pollFirst());
				}
				LinkedList<Character> res = calculator3(l, r, '*');
				left.addAll(res);
			}
		}
		in = left;
		left = new LinkedList<Character>();
		/* deal with + */
		while(!in.isEmpty()) {
			Character t = in.pollFirst();
			if(t != '+') {
				left.add(t);
			}else {
				LinkedList<Character> l = new LinkedList<Character>();
				LinkedList<Character> r = new LinkedList<Character>();
				while(left.peekLast() != null && left.peekLast() != '+') {
					l.addFirst(left.pollLast());
				}
				while(in.peekLast() != null && in.peekFirst() != '+') {
					r.add(in.pollFirst());
				}
				LinkedList<Character> res = calculator3(l, r, '+');
				left.addAll(res);
			}
		}
		result = left;
		return result;
	}
	
	private static LinkedList<Character> calculator3(LinkedList<Character> p1, LinkedList<Character>p2, char f) {
		int left = ListToIn(p1);
		int right = ListToIn(p2);
		int res = 0;
		if(f == '*') {
			res = left * right;
		}else if(f == '+') {
			res = left + right;
		}
		String r = String.valueOf(res);
		
		LinkedList<Character> result = new LinkedList<Character>();
		for(int i = 0; i < r.length(); i++) {
			result.add(r.charAt(i));
		}
		return result;
	}
	
	private static int ListToIn(LinkedList<Character> in) {
		char[] charArray = new char[in.size()];
		for(int i = 0; i < in.size(); i++) {
			charArray[i] = in.get(i);
		}
		return Integer.valueOf(String.valueOf(charArray));
	}

}
