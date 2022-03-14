package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14938 {
	
	static class Pair implements Comparable<Pair> {
		int dest;
		int distance;
		
		public Pair(int dest, int distance) {
			this.dest = dest;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Pair o) {
			return (this.distance < o.distance) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 지역의 개수 n 과 수색 범위 m, 그리고 길의 개수 r 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		// 각 지역의 아이템 개수를 입력받는다.
		int[] itemNum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) itemNum[i] = Integer.parseInt(st.nextToken());
		
		// 각 지역의 연결상태를 입력받는다.
		// 이 때, 예은이의 수색범위를 넘는 길은 입력받지 않는다.
		int[][] distance = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(distance[i], 10000);
			distance[i][i] = 0;
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (d > M) continue;
			distance[a][b] = d;
			distance[b][a] = d;
		}

		// 플로이드-와샬 알고리즘을 사용하여 각 지역별 최단거리를 계산한다.
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) System.out.print(distance[i][j]+" ");
//			System.out.println();
//		}
		
		int maxItem = 0;
		for (int i = 1; i < N + 1; i++) {
			int totalItem = 0;
			for (int j = 1; j < N + 1; j++) {
				if (distance[i][j] <= M) totalItem += itemNum[j];
			}
			if (maxItem < totalItem) maxItem = totalItem;
		}
		
		bw.write(maxItem+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}

