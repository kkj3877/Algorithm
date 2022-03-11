package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_2579 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 계단의 개수와 점수들을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N];
		int[] maxScore = new int[N];
		for (int i = 0; i < N; i++) stairs[i] = Integer.parseInt(br.readLine());
		
		int score = 0;
		if (N == 1) score = stairs[0];
		else if (N == 2) score = stairs[0] + stairs[1];
		else if (N == 3) score = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
		else {
			maxScore[0] = stairs[0];
			maxScore[1] = stairs[0] + stairs[1];
			maxScore[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
			
			for (int i = 3; i < N; i++) {
				maxScore[i] = stairs[i];
				maxScore[i] += Math.max(maxScore[i - 2], stairs[i - 1] + maxScore[i - 3]);
			}
			
			score = maxScore[N - 1];
		}
		
		bw.write(score+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
