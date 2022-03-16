package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21736 {
	
	static int N;
	static int M;
	static char[][] campus;
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		campus = new char[N][];
		for (int i = 0; i < N; i++) campus[i] = br.readLine().toCharArray();
	}
	
	static int solve() {
		// 도연이의 위치를 찾아 도연이로부터 탐색을 수행한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (campus[i][j] == 'I') {
					return BFS(i, j);
				}
			}
		}
		
		return -1;
	}
	
	static int BFS(int i, int j) {
		int meet = 0;
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		Queue<Pair> queue = new LinkedList<Pair>();
		campus[i][j] = 'X';
		queue.add(new Pair(i, j));
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > N - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > M - 1) continue;
				
				if (campus[nextR][nextC] != 'X') {
					if (campus[nextR][nextC] == 'P') meet++;
					campus[nextR][nextC] = 'X';
					queue.add(new Pair(nextR, nextC));			
				}
			}
		}
		
		return meet;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int meet = solve();
		if (meet == 0) bw.write("TT\n");
		else bw.write(meet+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
