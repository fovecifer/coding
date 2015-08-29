package com.samsung.test;

import java.util.Scanner;
import java.io.FileInputStream;

/*
As the name of the class should be Solution, using Solution.java as the filename is recommended.
In any case, you can execute your program by running 'java Solution' command.
*/
class SimpleBinaryCryptCode
{
	
	private static int[][][][][][][] table = new int[2][2][2][2][2][2][2];
	
	public static void main(String args[]) throws Exception
	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */
		System.setIn(new FileInputStream("simplebinarycryptcode.input"));

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
     			Read each test case from standard input.
     		*/
	
		table[0][0][0][1][1][0][1] = 0;
		table[0][0][1][1][0][0][1] = 1;
		table[0][0][1][0][0][1][1] = 2;
		table[0][1][1][1][1][0][1] = 3;
		table[0][1][0][0][0][1][1] = 4;
		table[0][1][1][0][0][0][1] = 5;
		table[0][1][0][1][1][1][1] = 6;
		table[0][1][1][1][0][1][1] = 7;
		table[0][1][1][0][1][1][1] = 8;
		table[0][0][0][1][0][1][1] = 9;

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				Please, implement your algorithm from this section.
			*/
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			/* height or row */
			int N = sc.nextInt();
			
			/*width or column*/
			int M = sc.nextInt();
			
			int[][] data =  new int[N][M];
			
			for(int i = 0; i < N; i++) {
				String line = sc.next();
				for(int j = 0; j < M; j++) {
					data[i][j] = Integer.valueOf(line.substring(j, j + 1));
				}
			}
			
			int answer = getResult(data, N, M);
			
			// Print the answer to standard output(screen).
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static int getResult(int[][] data, int N, int M) {
		int result = 0;
		int[] code = new int[8];
		
		for(int i = 0; i < N; i++) {
			for(int j = (M - 1); j >= 0; j--) {
				if(data[i][j] == 1) {
					int left = j - 55;
					int right = j;
					for(int a = 0; a < 8; a++) {
						code[a] = table[data[i][left]][data[i][left + 1]][data[i][left + 2]][data[i][left + 3]][data[i][left + 4]][data[i][left + 5]][data[i][left + 6]];
						left += 7;
					}
					int check = (code[0] + code[2] + code[4] + code[6]) * 3 + (code[1] + code[3] + code[5] + code[7]);
					if(check % 10 == 0) {
						result = code[0] + code[1] + code[2] + code[3] + code[4] + code[5] + code[6] + code[7];
					}
					return result;
				}
			}
		}
		
		return result;
		
	}
}


