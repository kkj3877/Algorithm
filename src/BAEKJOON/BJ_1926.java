package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1926 {
	
	static int n;
	static int m;
	static int[][] paper;
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int BFS(int i, int j) {
		paper[i][j] = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(i, j));
		int size = 1;
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > n - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > m - 1) continue;
				
				if (paper[nextR][nextC] == 1) {
					paper[nextR][nextC] = 0;
					size++;
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
		
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		paper = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		int maxSize = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (paper[i][j] == 1) {
					count++;
					int size = BFS(i, j);
					if (size > maxSize) maxSize = size;
				}
			}
		}
		
		bw.write(count+"\n");
		bw.write(maxSize+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
