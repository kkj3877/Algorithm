package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15657 {
	
	static int N, M;
	static int[] nums;
	static int[] answer;
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		
		answer = new int[N];
	}
	
	static void solve(BufferedWriter bw, int idx, int start) throws IOException {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				if (i != M - 1) bw.write(answer[i]+" ");
				else bw.write(answer[i]+"\n");
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			answer[idx] = nums[i];
			solve(bw, idx + 1, i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		solve(bw, 0, 0);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
