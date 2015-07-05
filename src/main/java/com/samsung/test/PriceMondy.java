package com.samsung.test;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
/**
 *
 */
public class PriceMondy {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("prizemondy.input"));
        int caseNum = scanner.nextInt();
        
        for(int i = 0; i < caseNum; i++) {
            int input = scanner.nextInt();
            int switchNum = scanner.nextInt();
            
            char[] inputChars = String.valueOf(input).toCharArray();
            int[] data = new int[inputChars.length];
            
            for(int j = 0; j < data.length; j++) {
                data[j] = Integer.valueOf(String.valueOf(inputChars[j]));
            }
            
            move(data, switchNum);
            System.out.format("#%d %s\n", (i + 1), change(data));
        }
    }
    
    private static String change(int[] data) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        return sb.toString();
    }
    
    private static void move(int[] data, int times) {
        int moveTimes = data.length < times ? data.length : times;
        for(int i = 0; i < moveTimes; i++) {
            int[] tmp = new int[data.length];
            tmp = Arrays.copyOf(data, data.length);
            Arrays.sort(tmp);
            
            int pointer = tmp.length - 1 - i;
            int ithMax = tmp[pointer];
            for(int j = (data.length - 1); j >= 0; j--) {
                if(data[j] != ithMax) {
                    continue;
                }else {
                    if(data[j] == data[i]) {
                        break;
                    }else {
                        int t = data[j];
                        data[j] = data[i];
                        data[i] = t;
                        break;
                    }
                }
            }
        }
    }
}
