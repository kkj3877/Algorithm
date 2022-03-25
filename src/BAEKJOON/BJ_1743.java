package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1743 {
	
	static int N, M, K;
	static boolean[][] map;
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N + 1][M + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = true;
		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int BFS(int r, int c) {
		int size = 1;
		Queue<Pair> queue = new LinkedList<Pair>();
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		map[r][c] = false;
		queue.add(new Pair(r, c));		
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 1 || nextR > N) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 1 || nextC > M) continue;
				
				if (map[nextR][nextC]) {
					map[nextR][nextC] = false;
					queue.add(new Pair(nextR, nextC));
					size++;
				}
			}
		}
		
		return size;
	}
	
	static int solve() throws IOException {
		int maxSize = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j]) {
					int size = BFS(i, j);
					if (maxSize < size) maxSize = size;
				}
			}
		}
		
		return maxSize;
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
