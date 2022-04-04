package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_11549 {
	
	static int T;
	static int[] guessAnswer;
	
	static void init(BufferedReader br) throws IOException {
		// 티의 타입(정답) T 를 입력받는다.
		T = Integer.parseInt(br.readLine());
		
		guessAnswer = new int[5];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 5; i++) guessAnswer[i] = Integer.parseInt(st.nextToken());
	}
	
	static int solve() {
		int correct = 0;
		for (int i = 0; i < 5; i++) {
			if (guessAnswer[i] == T) correct++;
		}
		
		return correct;
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
