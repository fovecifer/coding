package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Expression {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./expression.input"));

		for (int i = 0; i < 10; i++) {
			int size = scanner.nextInt();
			Node[] datas = new Node[size];
			for (int j = 0; j < size; j++) {
				int nodeNum = scanner.nextInt();
				String d = scanner.next();
				if (d.equalsIgnoreCase("+") || d.equalsIgnoreCase("-")
						|| d.equalsIgnoreCase("*") || d.equalsIgnoreCase("/")) {
					datas[j] = new Node(scanner.nextInt(), scanner.nextInt(), d);
				}else {
					datas[j] = new Node(d);
				}
			}
			
			System.out.format("#%d %d\n", (i + 1), cal(datas, datas[0]));
		}
	}
	
	private static int cal(Node[] datas, Node node) {
		if (node.getData().equalsIgnoreCase("+")) {
			return cal(datas, datas[node.getLeft() - 1]) + cal(datas, datas[node.getRight() - 1]);
		}else if(node.getData().equalsIgnoreCase("-")) {
			return cal(datas, datas[node.getLeft() - 1]) - cal(datas, datas[node.getRight() - 1]);
		}else if(node.getData().equalsIgnoreCase("*")) {
			return cal(datas, datas[node.getLeft() - 1]) * cal(datas, datas[node.getRight() - 1]);
		}else if(node.getData().equalsIgnoreCase("/")) {
			return cal(datas, datas[node.getLeft() - 1]) / cal(datas, datas[node.getRight() - 1]);
		}else {
			return Integer.valueOf(node.getData());
		}
		
	}

	
	static class Node {
		private int left = -1;
		private int right = -1;
		
		private String data = null;
		
		public Node() {}
		
		public Node(int left, int right, String data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
		
		public Node(String data) {
			this.data = data;
		}

		public int getLeft() {
			return left;
		}

		public void setLeft(int left) {
			this.left = left;
		}

		public int getRight() {
			return right;
		}

		public void setRight(int right) {
			this.right = right;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
 	}
}
