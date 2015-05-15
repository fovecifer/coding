package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact {
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./contact.input"));
		
		for(int i = 0; i < 10; i++) {
			int size = scanner.nextInt();
			int start = scanner.nextInt();
			List<Integer>[] data = new ArrayList[100];
			for(List l : data) {
				l = null;
			}
			for(int j = 0; j < (size / 2); j++) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				
				if(data[from - 1] == null) {
					List<Integer> d = new ArrayList<Integer>();
					d.add(Integer.valueOf(to));
					data[from - 1] = d;
				}else {
					data[from - 1].add(Integer.valueOf(to));
				}
			}
			System.out.format("#%d %d\n", (i + 1), getRes(data, start));
		}
	}
	
	private static int getRes(List<Integer>[] data, int start) {
		int[] color = new int[100];
		
		visit_BFS(data, start, color);
		
//		System.out.println(color);
		
		for(int i = 99; i >=0; i--) {
			if(color[i] == GRAY || color[i] == BLACK) {
				if((i + 1) != start) {
					return (i + 1);
				}
			}
		}
		
		return -1;
	}
	
	private static void visit_BFS(List<Integer>[] data, int start, int[] color) {
		List<Integer> tmp = data[start - 1];
		while(tmp.size() > 0) {
			List<Integer> t = new ArrayList<Integer>();
			for(Integer i : tmp) {
				color[i - 1] = GRAY;
				if(data[i - 1] == null) {
					return;
				}else {
					t.addAll(data[i - 1]);
				}
			}
			tmp = t;
		}
	}
	
	private static void visit(List<Integer>[] data, int start, int[] color) {
		color[start - 1] = GRAY;
		
		List<Integer> d = data[start - 1];
		if(d != null) {
			for(Integer i : d) {
				if(color[i - 1] == WHITE) {
					visit(data, i, color);
				}
			}
		}
		color[start - 1] = BLACK;
		return;
	}

}
