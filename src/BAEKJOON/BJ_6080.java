package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6080 {
	
	static int R, C;
	static int[][] farm;
	
	static void init(BufferedReader br) throws IOException {
		// row 의 수 R 과 col 의 수 C 를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 농장의 상태를 입력받는다.
		farm = new int[R][C];
		for (int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; ++j) farm[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void BFS(int r, int c) {
		farm[r][c] = 0;
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(r, c));
		
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 8; ++k) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > R - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > C - 1) continue;
				
				if (farm[nextR][nextC] == 0) continue;
				
				farm[nextR][nextC] = 0;
				queue.add(new Pair(nextR, nextC));
			}
				
		}
		
	}
	
	static int solve() {
		int numOfIslands = 0;
		
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (farm[i][j] != 0) {
					++numOfIslands;
					BFS(i, j);
				}
			}
		}
		
		return numOfIslands;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve();
		bw.write(answer + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
