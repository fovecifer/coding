package com.samsung.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class FinalTest {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./finaltest.input"));
        int caseNum = scanner.nextInt();

        for (int i = 0; i < caseNum; i++) {
            int R = scanner.nextInt(); // size of the grid
            int N = scanner.nextInt(); // number of robots
            int K = scanner.nextInt(); // constant

            int[] robots = new int[N];
            int[] shotedNum = new int[N];
            for (int j = 0; j < N; j++) {
                robots[j] = (scanner.nextInt() - 1) * R + (scanner.nextInt() - 1);
            }
            for (int j = 0; j < N; j++) {
                shotedNum[j] = getShotedNum(j, robots, R, N, K);
            }
            
            int sum = 0;
            for(int j = 0; j < N; j++) {
                sum += shotedNum[j];
            }
            
            int[] A = new int[2 * shotedNum.length];
            
            for(int a= 0; a < N; a++) {
            	A[a] = shotedNum[a];
            }
            
            int[] B = new int[2 * N + 1];
            int[] C = new int[N];
            
            for(int a = 0; a < N; a++) {
            	for(int b = 0; b < N; b++) {
            		A[b] = ((A[b] * K + (b + 1)) % N) + 1;
            		A[N + b] = ((A[b] * (b + 1) + K) % N) + 1;
            	}
            	
            	A = sort(A);
            	B[0] = 1;
                for(int b = 1; b <= (2*N); b++) {
                	B[b] = ((B[b - 1] * A[b - 1] + b) % N) + 1; 
                }
                C[a] = B[2 * N];
            }
            
            int sum2 = 0;
            for(int j = 0; j < N; j++) {
                sum2 += C[j];
            }
            
            System.out.format("#%d %d %d\n", (i + 1), sum, sum2);
        }

    }
    
    private static int[] sort(int[] input) {
    	List<Integer> tmp = new ArrayList<Integer>(input.length);
    	int[] result = new int[input.length];
    	
    	for(int i = 0; i < input.length; i++) {
    		tmp.add(Integer.valueOf(input[i]));
    	}
    	Collections.sort(tmp);
    	for(int i = 0; i < input.length; i++) {
    		result[i] = tmp.get(i);
    	}
    	return result;
    }

    private static int getShotedNum(int index, int[] robots, int R, int N, int K) {
        Set<Integer> shotedSet = new HashSet<Integer>();

        for (int i = 0; i < N; i++) {
            if (index == i) {
                continue;
            }

            int robot = robots[index];
            int[] robotXY = conFrom(robot, R);

            int target = robots[i];
            int[] targetXY = conFrom(target, R);

            int[] shotedRobots = getRobotsShoted(robot, target, robots, R);
            for(int t : shotedRobots) {
                shotedSet.add(Integer.valueOf(t));
            }
        }
        return shotedSet.size();
    }

    private static int[] getRobotsShoted(int robot1, int robot2, int[] robots, int R) {
        List<Integer> res = new ArrayList<Integer>();
        int[] robotXY1 = conFrom(robot1, R);
        int[] robotXY2 = conFrom(robot2, R);

        //  a * x + b = y 
        res.add(Integer.valueOf(robot2));

        if ((robotXY2[1] - robotXY1[1]) == 0) {
            // y = robotXY2[1]
            for (int i = 0; i < robots.length; i++) {
                if (robots[i] == robot1 || robots[i] == robot2) {
                    continue;
                }
                int[] xy = conFrom(robots[i], R);
                if (xy[1] == robotXY2[1]) {
                    res.add(Integer.valueOf(robots[i]));
                }
            }
        } else if ((robotXY2[0] - robotXY1[0]) == 0) {
            // x = robotXY2[0]
            for (int i = 0; i < robots.length; i++) {
                if (robots[i] == robot1 || robots[i] == robot2) {
                    continue;
                }
                int[] xy = conFrom(robots[i], R);
                if (xy[0] == robotXY2[0]) {
                    res.add(Integer.valueOf(robots[i]));
                }
            }
        } else {
            double a = (double)(robotXY2[1] - robotXY1[1]) / (double)(robotXY2[0] - robotXY1[0]);
            double b = (robotXY1[1] - a * robotXY1[0]);

            for (int i = 0; i < robots.length; i++) {
                if (robots[i] == robot1 || robots[i] == robot2) {
                    continue;
                }
                int[] xy = conFrom(robots[i], R);
                if (a * xy[0] + b == xy[1]) {
                    res.add(Integer.valueOf(robots[i]));
                }
            }
        }

        int point1 = -1;
        int point2 = -1;
        if (robotXY1[0] == robotXY2[0]) {
            // compare Y
            int big = R;
            int small = -R;
            for (int i = 0; i < res.size(); i++) {
                int[] toCheck = conFrom(res.get(i), R);
                int t1 = toCheck[1] - robotXY1[1];
                if (t1 > 0 && t1 < big) {
                    big = t1;
                    point1 = res.get(i);
                } else if (t1 < 0 && t1 > small) {
                    small = t1;
                    point2 = res.get(i);
                }
            }
        } else {
            // compare X
            int big = R;
            int small = -R;
            for (int i = 0; i < res.size(); i++) {
                int[] toCheck = conFrom(res.get(i), R);
                int t1 = toCheck[0] - robotXY1[0];
                if (t1 > 0 && t1 < big) {
                    big = t1;
                    point1 = res.get(i);
                } else if (t1 < 0 && t1 > small) {
                    small = t1;
                    point2 = res.get(i);
                }
            }
        }
        int[] result;
        if (point1 != -1 && point2 != -1) {
            result = new int[2];
            result[0] = point1;
            result[1] = point2;
            return result;
        } else {
            result = new int[1];
            if (point1 != -1) {
                result[0] = point1;
            } else {
                result[0] = point2;
            }
            return result;
        }
    }

    private static int conTo(int x, int y, int R) {
        return x * R + y;
    }

    private static int[] conFrom(int z, int R) {
        int[] res = new int[2];
        res[0] = z / R;
        res[1] = z % R;
        return res;
    }

}
