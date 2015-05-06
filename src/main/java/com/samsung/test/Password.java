package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Password {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./password.input"));
		for(int i = 0; i < 10; i++) {
			int size = scanner.nextInt();
			String caseStr = scanner.next();
			LinkedList<Character> data = new LinkedList<Character>();
			LinkedList<Character> left = new LinkedList<Character>();
			for(Character c : caseStr.toCharArray()) {
				data.add(c);
			}
			
			while(!data.isEmpty()) {
				Character tmp = data.pollFirst();
				Character l = left.pollLast();
				if(l != null && tmp.equals(l)) {
					continue;
				}else {
					if(l != null) {
						left.add(l);
					}
					left.add(tmp);
				}
			}
			System.out.printf("#%d ", i + 1);
			while(!left.isEmpty()) {
				System.out.print(left.pollFirst());
			}
			System.out.println("");
		}

	}

}
