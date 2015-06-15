package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HumanNetwork {
	private static final int UNDEFINE = -1;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("humannetwork.input"));
		int caseNum = scanner.nextInt();
		
		for(int i = 0; i < caseNum; i++) {
			int N = scanner.nextInt();
			int[][] data = new int[N][N];
			/* init input */
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					data[k][j] = scanner.nextInt();
				}
			}
			int[] ccArray = new int[N];
			for(int j = 0; j < N; j++) {
				ccArray[j] = CC(data, j);
			}
			System.out.format("#%d %d\n", (i + 1), min(ccArray));
		}
	}
	
	private static int CC(int[][] data, int node) {
		int result = 0;
		for(int i = 0; i < data[0].length; i++) {
			result += closest2(data, node, i);
		}
		return result;
	}
	
	private static int closest2(int[][]data, int source, int target) {
		if(source == target) return 0;
		int[] previous = new int[data[0].length];
		int[] distance = new int[data[0].length];
		int[] S = new int[0];
		for(int i = 0; i < previous.length; i++) {
			previous[i] = UNDEFINE;
			distance[i] = Integer.MAX_VALUE;
		}
		distance[source] = 0;
		previous[source] = source;
		int[] nexts = getNexts(data, source, S);
		int path = 0;
		while(nexts.length > 0) {
			path++;
			for(int n : nexts) {
				int cursor = S.length - 1;
				if(distance[n] > (distance[S[cursor]] + 1)) {
					distance[n] = distance[S[cursor]] + 1;
					previous[n] = S[cursor];
					S = insert(S, n);
				}
				if(n == target) {
					return distance[n];
				}
			}
		}
		return UNDEFINE;
	}
	
	private static int closest(int[][]data, int source, int target) {
		int path = 0;
		if(source == target) return path;
		int[] passed = new int[0];
		
		int[] nexts = getNexts(data, source, passed);
		for(int i = 0; i < data[0].length; i++) {
			int[] tmp = new int[0];
			path++;
			for(int j = 0; j < nexts.length; j++) {
				if(nexts[j] == target) return path;
				passed = insert(passed, nexts[j]);
			}
			for(int j = 0; j < nexts.length; j++) {
				int[] next = getNexts(data, nexts[j], passed);
				for(int k = 0; k < next.length; k++) {
					tmp = insert(tmp, next[k]);
				}
			}
			nexts = null;
			nexts = tmp;
		}
		return data[0].length;
	}
	
	private static int[] getNexts(int[][] data, int source, int[] passed) {
		int[] result = new int[0];
		for(int i = 0; i < data[0].length; i++) {
			if(data[source][i] == 1 && !isIn(i, passed)) {
				result = insert(result, i);
			}
		}
		return result;
	}
	
	private static boolean isIn(int toCheck, int[] passed) {
		for(int i = 0; i < passed.length; i++) {
			if(toCheck == passed[i]) return true;
		}
		return false;
	}
	
	private static int[] insert(int[] array, int t) {
		int[] result = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[array.length] = t;
		array = null;
		array = result;
		return result;
	}
	
	private static int min(int[] input) {
		int result = 1 << 8;
		for(int i = 0; i < input.length; i++) {
			if(input[i] < result) result = input[i];
		}
		return result;
	}

}
