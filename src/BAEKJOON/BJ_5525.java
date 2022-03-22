package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_5525 {
	
	static int N;
	
	static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
	}
	
	static int solve(BufferedReader br) throws IOException {
		int count = 0;
		
		int M = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		
		int idx = 0;
		while (idx < M - 2) {
//			System.out.println("idx = " + idx);
			if (input[idx] == 'I') {
				int n = 0;
				while (idx < M - 2) {
					if (input[++idx] != 'O') break;
					if (input[++idx] != 'I') {
						idx++;
						break;
					}
					n++;
				}
				if (n >= N) count += n - N + 1;
			}
			else idx++;
		}
				
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve(br);
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
