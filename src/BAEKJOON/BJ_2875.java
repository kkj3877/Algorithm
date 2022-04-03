package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2875 {
	
	static int N, M, K;
	
	static void init(BufferedReader br) throws IOException {
		// 여학생의 수 N, 남학생의 수 M, 인턴쉽 참여 인원 K 를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	static int solve() {
		int girlTeamNumber = N / 2;
		
		int remain = 0;
		if (N % 2 != 0) {
			remain = 1;
			N--;
		}
		
		int total = 0;
		if (girlTeamNumber >= M) {
			remain += N - (M * 2);
			total = 3 * M;
		}
		else {
			remain += M - (N / 2);
			total = 3 * (N / 2);
		}
//		System.out.println("remain = " + remain);
//		System.out.println("total  = " + total);
		
		if (remain >= K) return total / 3;
		else {
			total -= K - remain;
			return (total / 3) > 0 ? (total / 3) : 0;
		}
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
