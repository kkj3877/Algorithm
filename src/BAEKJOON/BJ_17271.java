package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_17271 {
	
	static int N, M;
	static long[] caseCount;
	
	static void init(BufferedReader br) throws IOException {
		// 싸움 시간 N 과 B 스킬의 시전 시간 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 각 시간별 경우의 수를 저장할 배열을 선언한다.
		caseCount = new long[N + 1];
	}
	
	static int solve() {
		if (N < M) return 1;
		
		for (int i = 0; i < M; i++) caseCount[i] = 1;
		for (int i = M; i <= N; i++) {
			caseCount[i] = (caseCount[i - 1] + caseCount[i - M]) % 1000000007;
		}
		
		return (int)caseCount[N];
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
