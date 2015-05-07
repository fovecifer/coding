package com.samsung.test;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Magnetic {
	private static int SIZE = 100;
	
	private static final int BLANK = 0;
	private static final int N = 1;
	private static final int S = 2;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(Paths.get("./magnetic.input").toFile());
		for(int i = 0; i < 11; i++) {
			int caseNum = scanner.nextInt();
			SIZE = caseNum;
			int[][] data = new int[SIZE][SIZE];
			for(int j = 0; j < SIZE; j++) {
				for(int k = 0; k < SIZE; k++) {
					data[j][k] = scanner.nextInt();
				}
			}
			moveAll(data);
//			print(data);
			int res = getBlockNum(data);
			System.out.format("%d# %d\n", (i + 1), res);
		}
	}
	
	private static int getBlockNum(int[][] data) {
		int result = 0;
		for(int i = 0; i < SIZE; i++) {
			result += getBlockNum(data, i);
		}
		return result;
	}
	
	private static int getBlockNum(int[][] data, int y) {
		boolean flag = true;
		int result = 0;
		for(int i = 0; i < SIZE; i++) {
			if(data[i][y] != BLANK && flag) {
				result++;
				flag = false;
			}else if(data[i][y] == BLANK) {
				flag = true;
			}
		}
		return result;
	}
	
	private static void moveAll(int[][] data) {
		for(int i = 0; i < SIZE; i++) {
			moveOneColumn(data, i);
//			print(data);
		}
	}
	
	private static void moveOneColumn(int[][] data, int y) {
		for(int i = 0; i < SIZE; i++) {
			/* move N */
			for(int j = (SIZE - 1); j >= 0; j--) {
				if(data[j][y] == N) move(data, convert(j, y));
			}
			/* move S */
			for(int j = 0; j < SIZE; j++) {
				if(data[j][y] == S) move(data, convert(j, y));
			}
		}
	}
	
	private static void move(int[][] data, int node) {
		int[] v = convert(node);
		int x = v[0];
		int y = v[1];
		
		if(data[x][y] == N) {
			/* down */
			if(x == (SIZE - 1)) {
				data[x][y] = BLANK;
			}else if(data[x + 1][y] == BLANK) {
				data[x + 1][y] = N;
				data[x][y] = BLANK;
			}
		}else if(data[x][y] == S) {
			/* up */
			if(x == 0) {
				data[x][y] = BLANK;
			}else if(data[x - 1][y] == BLANK) {
				data[x - 1][y] = S;
				data[x][y] = BLANK;
			}
		}
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
	
	private static void print(int[][] data) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.format("%d ", data[i][j]);
			}
			System.out.println("");
		}
	}

}
