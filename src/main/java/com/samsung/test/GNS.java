package com.samsung.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GNS {

	public static void main(String[] args) throws FileNotFoundException {
		String[] data = new String[10];
		data[0] = "ZRO";
		data[1] = "ONE";
		data[2] = "TWO";
		data[3] = "THR";
		data[4] = "FOR";
		data[5] = "FIV";
		data[6] = "SIX";
		data[7] = "SVN";
		data[8] = "EGT";
		data[9] = "NIN";
		
		Scanner scanner = new Scanner(new File("./GNS.input"));
		int caseNum = scanner.nextInt();
		for(int i = 0; i < caseNum; i++) {
			scanner.next();
			int size = Integer.valueOf(scanner.next());
			
			int[] result = new int[10];
			for(int b = 0; b < size; b++) {
				String t = scanner.next();
				for(int a = 0; a < 10; a++) {
					if(data[a].equals(t)) {
						result[a]++;
						break;
					}
				}
			}
			
			System.out.println("#" + (i + 1));
			for(int j = 0; j < result.length; j++) {
				for(int k = 0; k < result[j]; k++) {
					System.out.print(data[j] + " ");
				}
			}
			System.out.println();
		}
	}

}
