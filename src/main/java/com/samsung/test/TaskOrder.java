package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskOrder {
	private static final int EXIST = 1;
	private static final int NOT_EXIST = 0;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./taskorder.input"));
		for(int i = 0; i < 10; i++) {
			int V = scanner.nextInt();
			int E = scanner.nextInt();
			
			int[][]graph = new int[V][V];
			for(int j = 0; j < E; j++) {
				int b = scanner.nextInt();
				int t = scanner.nextInt();
				
				graph[b - 1][t - 1] = EXIST;
			}
			
			System.out.format("%d# ", (i + 1));
			printTasks(graph, E);
			System.out.println();
		}

	}
	
	private static void printTasks(int[][] graph, int E) {
		int V = graph[0].length;
		
//		int[] result = new int[V];
		int pointer = 0;
		
		int[] printed = new int[V];
		
		while(E > 0) {
			int[] tmp = new int[V];
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
					if(graph[i][j] == EXIST) {
						tmp[j] = EXIST;
					}
				}
			}
			for(int i = 0; i < V; i++) {
				if(tmp[i] == NOT_EXIST && printed[i] == NOT_EXIST) {
//					result[i] = EXIST;
					for(int j = 0; j < V; j++) {
						graph[i][j] = NOT_EXIST;
					}
					E--;
					System.out.format("%d ", (i + 1));
					printed[i] = EXIST;
				}
			}
		}
		
		for(int i = 0; i < V; i++) {
			if(printed[i] == NOT_EXIST) {
				System.out.format("%d ", (i + 1));
			}
		}
	}

}
