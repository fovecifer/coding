package com.samsung.test;

/////////////////////////////////////////////////////////////////////////////////////////////
//You can edit all or part of the default code for your purpose
/////////////////////////////////////////////////////////////////////////////////////////////

import java.io.FileInputStream;
import java.util.Scanner;

//import java.io.FileInputStream;

/*
 As the name of the class should be Solution, using Solution.java as the filename is recommended.
 In any case, you can execute your program by running 'java Solution' command.
 */
public class Solution {
	public static void main(String args[]) throws Exception {
		long start = System.currentTimeMillis();
		/*
		 * The method below means that the program will read from
		 * sample_input.txt, instead of standard(keyboard) input. To test your
		 * program, you may save input data in sample_input.txt file, and call
		 * below method to read from the file when using nextInt() method. You
		 * may remove the comment symbols(//) in the below statement and use it.
		 * But before submission, you must remove the freopen function or
		 * rewrite comment symbols(//).
		 */
		System.setIn(new FileInputStream("sample_input.txt"));

		/*
		 * Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * Read each test case from standard input.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Please, implement your algorithm from this section.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////

			int N = sc.nextInt();
			int[][] data = new int[N + 1][N + 1];
			Line[] lines = new Line[N - 1];
			int[] results = new int[N - 1];

			for (int i = 0; i < (N - 1); i++) {
				int value = sc.nextInt();
				int l = sc.nextInt();
				int r = sc.nextInt();
				data[l][r] = value;
				data[r][l] = value;
				Line line = new Line(l, r, value);
				lines[i] = line;
			}

			for (int i = 0; i < (N - 1); i++) {
				Line line = lines[i];
				int l = line.getL();
				int r = line.getR();
				int value = line.getV();

				data[l][r] = 0;
				data[r][l] = 0;

				int res1 = getDiameter(data, l);
				int res2 = getDiameter(data, r);
				int res = res1 + res2 + value;
				results[i] = res;

				data[l][r] = value;
				data[r][l] = value;
			}

			int result = 0;
			for (int i = 0; i < (N - 1); i++) {
				if (results[i] > result) {
					result = results[i];
				}
			}

			// Print the answer to standard output(screen).
//			System.out.println("#" + test_case + " " + result);
			System.out.format("#%d %d\n", test_case, result);
		}
		long end = System.currentTimeMillis();
		
		System.out.println((end-start));
	}

	private static int getDiameter(int[][] data, int start) {
		int N = data[0].length - 1;
		int[] length = new int[N + 1];
		BFS(data, start, length);

		int res1 = 0;
		int v = 0;
		for (int i = 1; i <= N; i++) {
			if (length[i] > res1) {
				res1 = length[i];
				v = i;
			}
		}

		if (v == 0) {
			return 0;
		}

		length = new int[N + 1];
		BFS(data, v, length);

		int res2 = 0;
		int w = 0;
		for (int i = 1; i <= N; i++) {
			if (length[i] > res2) {
				res2 = length[i];
				w = i;
			}
		}

		return res2;
	}

	private static void BFS(int[][] data, int start, int[] length) {
		boolean[] visited = new boolean[data[0].length];
		length[start] = 0;

		Queue queue = new Queue(2000);
		queue.offer(start);
		visited[start] = true;

		while (queue.getSize() != 0) {
			int v = queue.poll();
			int[] nexts = getNexts(data, v);

			for (int next : nexts) {
				if (visited[next] == false) {
					visited[next] = true;
					queue.offer(next);
					length[next] = length[v] + data[v][next];
				}
			}
		}
	}

	private static int[] getNexts(int[][] data, int v) {
		int N = data[0].length - 1;
		int[] result = new int[N - 1];
		int pointer = 0;
		int size = 0;

		for (int i = 1; i <= N; i++) {
			if (data[v][i] != 0) {
				size++;
				result[pointer] = i;
				pointer++;
			}
		}

		int[] res = new int[size];
		for (int i = 0; i < size; i++) {
			res[i] = result[i];
		}

		return res;

	}

	static class Queue {
		private int[] datas;
		private int size = 0;
		private int bottom = 0;
		private int head = 0;

		public Queue(int size) {
			datas = new int[size];
		}

		public void offer(int node) {
			datas[++head] = node;
			size++;
		}

		public int poll() {
			head--;
			size--;
			return datas[head + 1];
		}

		public int getSize() {
			return size;
		}
	}

	static class Line {
		private int l;
		private int r;
		private int value;

		public Line(int l, int r, int value) {
			this.l = l;
			this.r = r;
			this.value = value;
		}

		public int getL() {
			return this.l;
		}

		public int getR() {
			return this.r;
		}

		public int getV() {
			return this.value;
		}
	}

}
