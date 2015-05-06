package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RouteFinding {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./routefinding.input"));
		
		for(int i = 0; i < 10; i++) {
			int caseNum = scanner.nextInt();
			int roadsNum = scanner.nextInt();
			int[] data = new int[200];
			for(int j = 0; j < roadsNum; j++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				if(data[x] == 0) {
					data[x] = y;
				}else {
					data[x + 100] = y;
				}
				
			}
			
			if(find(0, 99, data)) {
				System.out.printf("#%d 1\n", caseNum);
			}else {
				System.out.printf("#%d 0\n", caseNum);
			}
		}
	}
	
	private static boolean find(int start, int end, int[] data) {
		if(data[start] == end || data[start + 100] == end) {
			return true;
		}
		int m1 = data[start];
		int m2 = data[start + 100];
		
		if(m1 != 0 && m2 != 0) {
			return (find(m1, end, data) || find(m2, end, data));
		}else if(m1 != 0) {
			return find(m1, end, data);
		}else if(m2 != 0) {
			return find(m2, end, data);
		}
		
		return false;
	}

}
