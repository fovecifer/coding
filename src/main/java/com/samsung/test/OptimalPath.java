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
        for(int i = 0; i < 10; i++) {
            int N = scanner.nextInt();
            
            Node office = new Node(scanner.nextInt(), scanner.nextInt());
            Node home = new Node(scanner.nextInt(), scanner.nextInt());
            
            List<Node> nodeList = new ArrayList<Node>(N);
            for(int j = 0; j < N; j++) {
                nodeList.add(new Node(scanner.nextInt(), scanner.nextInt()));
            }
            
            
        }
    }
    
    private static LinkedList<List<Node>> getOrders(LinkedList<Node> input) {
        int size = input.size();
        LinkedList<List<Node>> result = new LinkedList<List<Node>>();
        for(int i = 0; i < size; i++) {
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
