package com.samsung.test;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Magnetic {
	private static final int SIZE = 100;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(Paths.get("./magnetic.input").toFile());
		for(int i = 0; i < 10; i++) {
			int caseNum = scanner.nextInt();
			int[][] data = new int[SIZE][SIZE];
			for(int j = 0; j < SIZE; j++) {
				char[] lines = scanner.next().toCharArray();
				for(int k = 0; k < lines.length; k++) {
					data[j][k] = lines[k];
				}
			}
			
		}
	}
	
	private static void move(int[][] data, int node) {
		int[] v = convert(node);
		int x = v[0];
		int y = v[1];
		
		
	}
	
	private static int[] convert(int in) {
		int[] result = new int[2];
		result[0] = in / SIZE;
		result[1] = in % SIZE;
		return result;
	}
	
	private static int convert(int x, int y) {
		return x * SIZE + y;
	}

}
