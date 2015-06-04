package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinalTest {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./finaltest.input"));
		int caseNum = scanner.nextInt();
		
		for(int i = 0; i < caseNum; i++) {
			int R = scanner.nextInt(); // size of the grid
			int N = scanner.nextInt(); // number of robots
			int K = scanner.nextInt(); // constant
			
			int[] robots = new int[N];
			int[] shotedNum = new int[N];
			for(int j = 0; j < N; j++) {
				robots[j] = scanner.nextInt() * R + scanner.nextInt();
			}
			for(int j = 0; j < N; j++) {
				shotedNum[j] = getShotedNum(j, robots, R, N, K);
			}
		}

	}
	
	private static int getShotedNum(int index, int[] robots, int R, int N, int K) {
		for(int i = 0; i < N; i++) {
			if(index == i) continue;
			
			int robot = robots[index];
			int[] robotXY = conFrom(robot, R);
			
			int target = robots[i];
			int[] targetXY = conFrom(target, R);
			
			
		}
		return 0;
	}
	
	private static int[] getRobotsShoted(int robot1, int robot2, int[] robots, int R) {
		List<Integer> res = new ArrayList<Integer>();
		int[] robotXY1 = conFrom(robot1, R);
		int[] robotXY2 = conFrom(robot2, R);
		
		//  a * x + b = y 
		res.add(Integer.valueOf(robot2));
		
		if((robotXY2[1] - robotXY1[1]) == 0) {
			// y = robotXY2[1]
			for(int i = 0; i < robots.length; i++) {
				if(robots[i] == robot1 || robots[i] == robot2) continue;
				int[] xy = conFrom(robots[i], R);
				if(xy[1] == robotXY2[1]) {
					res.add(Integer.valueOf(robots[i]));
				}
			}
		}else if((robotXY2[0] - robotXY1[0]) == 0) {
			// x = robotXY2[0]
			for(int i = 0; i < robots.length; i++) {
				if(robots[i] == robot1 || robots[i] == robot2) continue;
				int[] xy = conFrom(robots[i], R);
				if(xy[0] == robotXY2[0]) {
					res.add(Integer.valueOf(robots[i]));
				}
			}
		}else {
			int a = (robotXY2[1] - robotXY1[1]) / (robotXY2[0] - robotXY1[0]);
			int b = robotXY1[1] - a * robotXY1[0];
			
			for(int i = 0; i < robots.length; i++) {
				if(robots[i] == robot1 || robots[i] == robot2) continue;
				int[] xy = conFrom(robots[i], R);
				if(a * xy[0] + b == xy[1]) {
					res.add(Integer.valueOf(robots[i]));
				}
			}
		}
		
		int point1 = 0;
		int point2 = 0;
		if(robotXY1[0] == robotXY2[0]) {
			// compare X
			int big = R;
			int small = -R;
			for(int i = 0; i < res.size(); i++) {
				int[] toCheck = conFrom(res.get(i), R);
				int t1 = toCheck[1] - robotXY1[1];
				if(t1 > 0 && t1 < big) {
					big = t1;
					point1 = res.get(i);
				}else if(t1 < 0 && t1 > small) {
					small = t1;
					point2 = res.get(i);
				}
			}
		}else {
			// compare Y
		}
		
		int[] result = new int[res.size()];
		for(int i = 0; i < res.size(); i++) {
			result[i] = res.get(i);
		}
		return result;
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
