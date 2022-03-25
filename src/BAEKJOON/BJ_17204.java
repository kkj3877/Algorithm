package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_17204 {
	
	static int N, K;
	static int[] next;
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		next = new int[N];
		for (int i = 0; i < N; i++) next[i] = Integer.parseInt(br.readLine());
	}
	
	static int solve() throws IOException {
//		for (int i = 0; i < N; i++) {
//			System.out.println(i+ "->"+ next[i]);
//		}
		int answer = 1;
		int idx = 0;
		
		while (idx != K) {
//			System.out.println("idx::" + idx);
			if (next[idx] == -1) return -1;
			
			int temp = next[idx];
			next[idx] = -1;
			idx = temp;
			answer++;
		}
		
		return answer - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
