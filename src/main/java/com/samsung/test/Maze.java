package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	private static final int SIZE = 100;
	private static final int ROAD = 0;
	private static final int WALL = 1;
	private static final int SOURCE = 2;
	private static final int TARGET = 3;
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./maze.input"));
		
		for(int i = 0; i < 10; i++) {
			int caseNum = scanner.nextInt();
			int[][] data = new int[SIZE][SIZE];
			int source = 0;
			int target = 0;
			for(int j = 0; j < 100; j++) {
				String line = scanner.next();
				char[] lines = line.toCharArray();
				for(int k = 0; k < lines.length; k++) {
					int d = Integer.valueOf(String.valueOf(lines[k]));
					if(d == SOURCE) source = convert(j, k);
					if(d == TARGET) target = convert(j, k);
					data[j][k] = d;
				}
			}
			if(dfsSearch(data, source, target)) {
				System.out.printf("%d# 1\n", caseNum);
			}else {
				System.out.printf("%d# 0\n", caseNum);
			}
		}
	}
	
	private static boolean dfsSearch(int[][] data, int source, int target) {
		int[][] color = new int[SIZE][SIZE];
		return dfsVisit(data, color, source, target);
	}
	
	private static boolean dfsVisit(int[][] data, int[][] color, int node, int target) {
		int[] xy = convert(node);
		if(color[xy[0]][xy[1]] == GRAY) return false;
		color[xy[0]][xy[1]] = GRAY;
		if(node == target) return true;
		
		int[] roads = findRoad(data, color, node);
		if(roads.length == 0) {
			color[xy[0]][xy[1]] = BLACK;
			return false;
		}else {
			for(int r : roads) {
				boolean res = dfsVisit(data, color, r, target);
				if(res) return true;
			}
		}
		color[xy[0]][xy[1]] = BLACK;
		return false;
	}
	
	private static int[] findRoad(int[][] data, int[][] color, int node) {
		List<Integer> result = new ArrayList<Integer>();
		int[] xy = convert(node);
		/* up */
		if((xy[0] - 1) >= 0 && color[xy[0] - 1][xy[1]] == WHITE && data[xy[0] - 1][xy[1]] != WALL) {
			result.add(convert(xy[0] - 1, xy[1]));
		}
		/* down */
		if((xy[0] + 1) <= SIZE && color[xy[0] + 1][xy[1]] == WHITE && data[xy[0] + 1][xy[1]] != WALL) {
			result.add(convert(xy[0] + 1, xy[1]));
		}
		
		/* left */
		if((xy[1] - 1) >= 0 && color[xy[0]][xy[1] - 1] == WHITE && data[xy[0]][xy[1] - 1] != WALL) {
			result.add(convert(xy[0], xy[1] - 1));
		}
		
		/* right */
		if((xy[1] + 1) <= SIZE && color[xy[0]][xy[1] + 1] == WHITE && data[xy[0]][xy[1] + 1] != WALL) {
			result.add(convert(xy[0], xy[1] + 1));
		}
		int[] res = new int[result.size()];
		for(int i = 0; i < result.size(); i++) {
			res[i] = result.get(i);
		}
		return res;
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
