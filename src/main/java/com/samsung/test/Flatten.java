package com.samsung.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Flatten {

	public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader(new File("./flatten.input"));
		BufferedReader br = Files.newBufferedReader(Paths.get("./flatten.input"), Charset.forName("UTF-8"));
		for(int i = 0; i < 10; i++) {
			int dumpNum = Integer.valueOf(br.readLine());
			Scanner scanner = new Scanner(br.readLine());
			List<Integer> data = new ArrayList<Integer>();
			while(scanner.hasNext()) {
				data.add(scanner.nextInt());
			}
			System.out.format("%d# %d\n", (i + 1), getResult(data, dumpNum));
		}
	}
	
	private static int getResult(List<Integer> data, int dumpNum) {
		for(int i = 0; i < dumpNum; i++) {
			Collections.sort(data);
			int s = data.get(0);
			int e = data.get(data.size() - 1);
			if(s == e) {
				return 0;
			}else {
				data.set(0, s + 1);
				data.set(data.size() - 1, e - 1);
			}
		}
		Collections.sort(data);
		return data.get(data.size() - 1) - data.get(0);
	}

}
