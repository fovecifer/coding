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
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 *
 */
public class OptimalPath {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("optimalpath.input"));

        int caseNum = scanner.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int N = scanner.nextInt();

            Node office = new Node(scanner.nextInt(), scanner.nextInt());
            Node home = new Node(scanner.nextInt(), scanner.nextInt());

            Node[] nodeArray = new Node[N];
            for (int j = 0; j < N; j++) {
                Node node = new Node(scanner.nextInt(), scanner.nextInt());
                nodeArray[j] = node;
            }
            int result = combString(nodeArray, office, home);

            System.out.format("#%d %d\n", (i + 1), result);
        }
    }

    public static int combString(Node[] nodes, Node office, Node home) {

        int res = Integer.MAX_VALUE;
        //char[] a = s.toCharArray();
        int n = nodes.length;
        int cases = factorial(n);
        Node[][] result = new Node[cases][n];
        int resPointer = 0;
        result[resPointer++] = nodes;
        int[] p = new int[n];  // Weight index control array initially all zeros. Of course, same size of the char array.
        int i = 1; //Upper bound index. i.e: if string is "abc" then index i could be at "c"
        while (i < n) {
            if (p[i] < i) { //if the weight index is bigger or the same it means that we have already switched between these i,j (one iteration before).
                int j = ((i % 2) == 0) ? 0 : p[i];//Lower bound index. i.e: if string is "abc" then j index will always be 0.
                swap(nodes, i, j);
                int r = getDistance(nodes, office, home);
                res = (r < res) ? r : res;
                //Node[] tmp = new Node[n];
                //for(int k = 0; k < n; k++) {
                //    tmp[k] = nodes[k];
                //}
                // Print current
                //result[resPointer++] = tmp;
                p[i]++; //Adding 1 to the specific weight that relates to the char array.
                i = 1; //if i was 2 (for example), after the swap we now need to swap for i=1
            } else {
                p[i] = 0;//Weight index will be zero because one iteration before, it was 1 (for example) to indicate that char array a[i] swapped.
                i++;//i index will have the option to go forward in the char array for "longer swaps"
            }
        }
        return res;
    }

    private static void printNodes(Node[] nodes) {
        for (Node node : nodes) {
            System.out.format("Nodes(%d, %d) ", node.getX(), node.getY());
        }
        System.out.println();
    }

    private static void swap(Node[] a, int i, int j) {
        Node temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int getShorest(List<List<Node>> input, Node office, Node home) {
        int result = Integer.MAX_VALUE;
        for (List<Node> c : input) {
            int t = getDistance(c, office, home);
            if (t < result) {
                result = t;
            }
        }
        return result;
    }

    private static int getShorest(Node[][] input, Node office, Node home) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < input.length; i++) {
            Node[] c = input[i];
            int t = getDistance(c, office, home);
            if (t < result) {
                result = t;
            }
        }
        return result;
    }

    private static int getDistance(Node[] list, Node office, Node home) {
        int result = 0;
        Node s = office;
        for (Node node : list) {
            result += s.distance(node);
            s = node;
        }
        result += s.distance(home);
        return result;
    }

    private static int getDistance(List<Node> list, Node office, Node home) {
        int result = 0;
        Node s = office;
        for (Node node : list) {
            result += s.distance(node);
            s = node;
        }
        result += s.distance(home);
        return result;
    }

    private static void printNode(List<List<Node>> res) {
        System.out.println("Total: " + res.size());
        for (List<Node> l : res) {
            for (Node node : l) {
                System.out.format("Node(%d,%d) ", node.getX(), node.getY());
            }
            System.out.println();
        }
    }

    private static List<List<Node>> getOrders(List<Node> input) {
        int size = input.size();

        List<List<Node>> result = new LinkedList<List<Node>>();
        if (size == 1) {
            result.add(input);
            return result;
        }
        for (int i = 0; i < size; i++) {
            Node node = input.get(i);
            List<Node> input2 = new ArrayList<Node>(input.size() - 1);
            for (int j = 0; j < input.size(); j++) {
                if (j == i) {
                    continue;
                }
                input2.add(input.get(j));
            }
            List<List<Node>> res = getOrders(input2);

            for (List<Node> n : res) {
                n.add(0, node);
                result.add(n);
            }
        }

        return result;
    }

    private static Node[][] getOrders(Node[] nodes, int size) {

        int length = nodes.length;
        int cases = factorial(size);

        Node[][] result = new Node[cases][length];
        int resPointer = 0;
        Node[] tmp = new Node[length];

        if (size == 1) {
            result[resPointer++] = nodes;
        } else {
            for (int i = (length - size); i < length; i++) {
                Node node = nodes[i];
                tmp = Arrays.copyOf(nodes, length);
                tmp[i] = null;
                for (int j = i; j > (length - size); j--) {
                    tmp[j] = tmp[j - 1];
                    tmp[j - 1] = null;
                }

                Node[][] tmpRes = getOrders(tmp, (size - 1));
                for (int j = 0; j < tmpRes.length; j++) {
                    Node[] ns = tmpRes[j];
                    //Node[] tmpNs = new Node[ns.length];
                    //tmpNs = Arrays.copyOf(ns, ns.length);
                    ns[length - size] = node;
                    result[resPointer++] = ns;
                }
            }
        }

        return result;
    }

    private static void move(Node[] nodes, int size) {
        for (int i = (nodes.length - 1); i > 0; i--) {
            if (nodes[i] == null) {
                nodes[i] = nodes[i - 1];
            }
        }
    }

    private static int factorial(int input) {
        int result = 1;
        for (int i = input; i > 1; i--) {
            result *= i;
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
