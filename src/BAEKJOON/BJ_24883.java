package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_24883 {
	
	static char ch;
	
	static void init(BufferedReader br) throws IOException {
		ch = br.readLine().charAt(0);
	}
	
	static boolean solve() {
		boolean isD = false;
		if (ch == 'N' || ch == 'n') {
			isD = true;
		}
		return isD;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		boolean answer = solve();
		if (answer) bw.write("Naver D2\n");
		else bw.write("Naver Whale\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
