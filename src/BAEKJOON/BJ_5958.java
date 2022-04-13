package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_5958 {
	
	static int N;
	static char[][] map;
	
	static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void BFS(int r, int c) {
		Queue<Pair> queue = new LinkedList<Pair>();
		map[r][c] = '.';
		queue.add(new Pair(r, c));
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > N - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > N - 1) continue;
				
				if (map[nextR][nextC] == '*') {
					map[nextR][nextC] = '.';
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
	}
	
	static int solve() {
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '*') {
					answer++;
					BFS(i, j);
				}
			}
		}
		
		return answer;
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
