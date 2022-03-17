package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_1932 {
	
	static int N;
	static int[][] nums;
	static int[][] sums;
	
	static void init(BufferedReader br) throws IOException {
		// 삼각형의 크기 N과 삼각형의 상태를 입력받는다.
		N = Integer.parseInt(br.readLine());
		nums = new int[N][];
		sums = new int[N][];
		for (int i = 0; i < N; i++) {
			nums[i] = new int[i + 1];
			sums[i] = new int[i + 1];
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) nums[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(">> Input Complete");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N - i; j++) System.out.print(" ");
//			for (int j = 0; j < i + 1; j++) System.out.print(nums[i][j]+" ");
//			System.out.println();
//		}
//		System.out.println("----------------------------");
	}
	
	static void solve(BufferedWriter bw) throws IOException {
		// 위에서부터 아래로 가는 경로를 추적하며 합 배열을 업데이트해나간다.
		// 이 때, 최대합이 업데이트 된다면 그 이전 경로를 추적할 수 있도록 한다.
		sums[0][0] = nums[0][0];
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (sums[i + 1][j] < sums[i][j] + nums[i + 1][j]) {
					sums[i + 1][j] = sums[i][j] + nums[i + 1][j];
				}
				
				sums[i + 1][j + 1] = sums[i][j] + nums[i + 1][j + 1];
			}
		}
		
		int maxSum = sums[0][0];
		for (int j = 0; j < N; j++) if (maxSum < sums[N - 1][j]) maxSum = sums[N - 1][j];
		
//		System.out.println(">> Solve Complete");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N - i; j++) System.out.print(" ");
//			for (int j = 0; j < i + 1; j++) System.out.print(sums[i][j]+" ");
//			System.out.println();
//		}
//		System.out.println("----------------------------");
		
		bw.write(maxSum+"\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		solve(bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
