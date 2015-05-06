package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Palindrome {
	private static final int SIZE = 100;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./palindrome.input"));
		for(int i = 0; i < 10; i++) {
			int caseNum = scanner.nextInt();
			char[][] data = new char[SIZE][SIZE];
			for(int j = 0; j < SIZE; j++) {
				String line = scanner.next();
				char[] chars = line.toCharArray();
				for(int k = 0; k < chars.length; k++) {
					data[j][k] = chars[k];
				}
			}
			System.out.printf("#%d %d\n", caseNum, getAnswer(data));
		}
	}
	
	private static int getAnswer(char[][] data) {
		int result = 1;
		
		for(int i = 0; i < SIZE; i++) {
			int resHor = getAnswerHorizontal(i, data);
			if(resHor > result) 
				result = resHor;
			
			int resVer = getAnswerVertical(i, data);
			if(resVer > result) 
				result = resVer;
		}
		return result;
	}
	
	private static int getAnswerHorizontal(int x, char[][] data) {
		int result = 1;
		for(int size = SIZE; size > 1; size--) {
			for(int j = 0; j < (SIZE - size + 1); j++) {
				int k = j + size - 1;
				if(check(x, j, x, k, data)) {
					return size;
				}
			}
		}
		return result;
	}
	
	private static int getAnswerVertical(int y, char[][] data) {
		int result = 1;
		for(int size = SIZE; size > 1; size--) {
			for(int j = 0; j < (SIZE -size + 1); j++) {
				int k = j + size - 1;
				if((check(j, y, k, y, data))) {
					return size;
				}
			}
		}
		return result;
	}
	
	private static boolean check(int x1, int y1, int x2, int y2, char[][]data) {
		if(x1 != x2 && y1 != y2) {
			return false;
		}
		if(x1 == x2) {
			if(y1 == y2) return true;
			int i = 0;
			boolean result = true;
			int t1 = y1, t2 = y2;
			while(t1 < t2) {
				if(t2 == 100) {
					System.out.println("");
				}
				if(data[x1][t1] != data[x2][t2]){
					result = false;
					break;
				}
				i++;
				t1 = y1 + i;
				t2 = y2 - i;
			}
			return result;
		}else {
			if(x1 == x2) return true;
			int i = 0;
			boolean result = true;
			int t1 = x1, t2 = x2;
			while(t1 < t2) {
				if(data[t1][y1] != data[t2][y2]){
					result = false;
					break;
				}
				i++;
				t1 = x1 + i;
				t2 = x2 - i;
			}
			return result;
		}
	}

}
