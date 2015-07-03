package com.samsung.test;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *
 */
import java.io.FileNotFoundException;/**
 *
 */
public class OptimalPath {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("optimalpath.input"));
        int caseNum = scanner.nextInt();
        for(int i = 0; i < caseNum; i++) {
            int N = scanner.nextInt();
            
            Node office = new Node(scanner.nextInt(), scanner.nextInt());
            Node home = new Node(scanner.nextInt(), scanner.nextInt());
            
            List<Node> nodeList = new ArrayList<Node>(N);
            for(int j = 0; j < N; j++) {
                nodeList.add(new Node(scanner.nextInt(), scanner.nextInt()));
            }
            
            List<List<Node>> res = getOrders(nodeList);
            
            printNode(res);
        }
    }
    
    private static void printNode(List<List<Node>> res) {
    	System.out.println("Total: " + res.size());
    	for(List<Node> l : res) {
    		for(Node node : l) {
    			System.out.format("Node(%d,%d) ", node.getX(), node.getY());
    		}
    		System.out.println();
    	}
    }
    
    private static List<List<Node>> getOrders(List<Node> input) {
        int size = input.size();
        
        List<List<Node>> result = new LinkedList<List<Node>>();
        for(int i = 0; i < size; i++) {
        	Node node = input.get(i);
        	List<Node> input2 = new ArrayList<Node>(input.size() - 1);
        	for(int j = 0; j < input.size(); j++) {
        		if(j == i) continue;
        		input2.add(input.get(j));
        	}
        	List<List<Node>> res = getOrders(input2);

        	if(res.size() == 0) {
        		List<Node> r = new ArrayList<Node>(1);
        		r.add(node);
        		result.add(r);
        	}else {
        		for(List<Node> n : res) {
            		n.add(0, node);
            		result.add(n);
            	}
        	}
        }
        
        return result;
    }
    
    static public class Node {
        private final int x;
        private final int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
        
        public int distance(Node other) {
            int result = 0;
            
            int a = this.x - other.getX();
            result += a > 0 ? a : -a;
            
            int b = this.y - other.getY();
            result += b > 0 ? b : -b;
            
            return result;
        }
    }
}
