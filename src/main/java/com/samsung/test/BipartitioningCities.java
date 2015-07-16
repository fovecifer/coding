package com.samsung.test;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BipartitioningCities {

	private static final int EXIST = 1;
	private static final int NOT_EXIST = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./bipartitioningcities.input"));
		int caseNum = 10;
		for(int i = 0; i < caseNum; i++) {
			int nodeNum = scanner.nextInt();
			int edgeNum = scanner.nextInt();
			
			int[][] array = new int[nodeNum][nodeNum];
			
			for(int j = 0; j < edgeNum; j++) {
				int l = scanner.nextInt() - 1;
				int r = scanner.nextInt() - 1;
				
				array[l][r] = EXIST;
				array[r][l] = EXIST;
			}
			
			int[] result = getResult(array, nodeNum);
			System.out.format("#%d ", (i + 1));
			for(int n : result) {
				System.out.format("%d ", n);
			}
			System.out.println();
		}
	}
	
	private static int[] getResult(int[][] array, int nodeNum) {
		int[] result = new int[0];
		int[] passed = new int[nodeNum];
		
		for(int i = 0; i < nodeNum; i++) {
			if(passed[i] == 1) {
				continue;
			}
			
			result = append(result, (i + 1));
			int[] previous = new int[1];
			previous[0] = i;
			passed[i] = 1;
			for(int j = 0; ; j++) {
				int[] next = getNext(array, previous, nodeNum, passed);
				if(next.length == 0) {
					break;
				}
				for(int n : next) {
					passed[n] = 1;
				}
				if(j % 2 == 1) {
					for(int n : next) {
						result = append(result, n + 1);
					}
				}
				previous = next;
			}
		}
		
		
		return result;
	}
	
	private static int[] getNext(int[][] array, int[] current, int nodeNum, int[] passed) {
		int[] result = new int[0];
		
		for(int i : current) {
			for(int j = 0; j < nodeNum; j++) {
				if(array[i][j] == EXIST && passed[j]  == 0) {
					if(!contain(result, j)) {
						result = append(result, j);
					}
				}
			}
		}
		return result;
	}
	
	private static boolean contain(int[] data, int n) {
		for(int i = 0; i < data.length; i++) {
			if(data[i] == n) {
				return true;
			}
		}
		
		return false;
	}
	
	private static int[] append(int[] input, int append) {
		int[] result = new int[input.length + 1];
		for(int i = 0; i < input.length; i++) {
			result[i] = input[i];
		}
		result[input.length] = append;
		return result;
	}

}
