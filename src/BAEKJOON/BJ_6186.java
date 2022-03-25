package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6186 {
	
	static int R, C;
	static char[][] grid;
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new char[R][];
		for (int i = 0; i < R; i++) {
			grid[i] = br.readLine().toCharArray();
		}
	}
	
	static void BFS(int r, int c) {
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		grid[r][c] = '.';
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(r, c));
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > R - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > C - 1) continue;
				
				if (grid[nextR][nextC] == '#') {
					grid[nextR][nextC] = '.';
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
	}
	
	static int solve() throws IOException {
		int numOfClumps = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j= 0; j < C; j++) {
				if (grid[i][j] == '#') {
					numOfClumps++;
					BFS(i, j);
				}
			}
		}
		
		return numOfClumps;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int numOfClumps = solve();
		bw.write(numOfClumps+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
