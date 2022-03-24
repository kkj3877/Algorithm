package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_1371 {
	
	static void init(BufferedReader br) throws IOException {
		
	}
	
	static String solve(BufferedReader br) throws IOException {
		int[] count = new int[26];
		
		String line = null;
		while ((line = br.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch >= 'a' && ch <= 'z') count[line.charAt(i) - 'a']++;;
			}
		}
		
		int maxCount = 0;
		for (int i = 0; i < 26; i++) {
			if (maxCount < count[i]) {
				maxCount = count[i];
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 26; i++) {
			if (count[i] == maxCount) sb.append((char)('a' + i));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Temp\\input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		String answer = solve(br);
		bw.write(answer);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
