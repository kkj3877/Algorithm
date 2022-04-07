package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_16486 {
	
	static int d1, d2;
	static double PI = 3.141592;
	
	static void init(BufferedReader br) throws IOException {
		d1 = Integer.parseInt(br.readLine());
		d2 = Integer.parseInt(br.readLine());
	}
	
	static double solve() {
		return 2 * d1 + 2 * PI * d2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		double answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
