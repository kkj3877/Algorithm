package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16390 {
	
	static int M, N;
	static char[][] dish;
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		// 행의 개수 M 과 열의 개수 N 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		
		// dish 의 상태를 저장할 공간을 선언하고 상태를 입력받는다.
		dish = new char[M][];
		for (int i = 0; i < M; i++) dish[i] = br.readLine().toCharArray();
	}
	
	// dish[i][j] 로 부터 BFS 를 수행하며 연결된 덩어리를 체크한다.
	static void BFS(int i, int j) {
		Queue<Pair> queue = new LinkedList<Pair>();
		
		// 8 방향에 대한 벡터
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
		
		// 시작지점을 (i, j) 로 하는 BFS 를 수행하기 위한 첫 좌표 처리
		dish[i][j] = '.';
		queue.add(new Pair(i, j));
		
		// 큐가 빌 때 까지 탐색을 수행한다.
		while (!queue.isEmpty()) {
			// 큐를 꺼내보며 다음 좌표를 확인한다.
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			// 현재 좌표로부터 8방향을 보며 아메바가 있는지 확인한다.
			for (int k = 0; k < 8; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > M - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > N - 1) continue;
				
				if (dish[nextR][nextC] == '#') {
					dish[nextR][nextC] = '.';
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
	}
	
	static int solve() {
		int count = 0;
		
		// dish 를 둘러보며 BFS 를 사용해 closed loops 의 개수를 count 한다.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (dish[i][j] == '#') {
					BFS(i, j);
					count++;
				}
			}
		}
		
		return count;
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
