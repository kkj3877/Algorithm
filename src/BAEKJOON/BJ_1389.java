package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1389 {
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 유저의 수 N 과 친구 관계의 수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 서로의 친밀도 거리를 저장할 행렬을 선언하고
		// 친구관계를 입력받아 행렬을 업데이트 한다.
		int[][] friendDistance = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(friendDistance[i], 500);
			friendDistance[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			friendDistance[from][to] = friendDistance[to][from] = 1;
		}
		
		// 플로이드-와샬 알고리즘을 사용하여 모든 노드별 최단거리를 계산한다.
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i ++) {
				for (int j = 1; j < N + 1; j++) {
					if (friendDistance[i][j] > friendDistance[i][k] + friendDistance[k][j]) {
						friendDistance[i][j] = friendDistance[i][k] + friendDistance[k][j];
					}
				}
			}
		}
		
		// 가장 케빈 베이컨 수가 가장 작은 사람을 찾고 정답으로 설정한다.
		int minSum = 500;
		int minIdx = 0;
		for (int i = 1; i < N + 1; i++) {
			int sum = 0;
			for (int j = 1; j < N + 1; j++) sum += friendDistance[i][j];
			if (sum < minSum) {
				minSum = sum;
				minIdx = i;
			}
		}
		
		bw.write(minIdx+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
