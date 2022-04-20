package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_15092 {
	
	static int M, N;
	static char[][] dish;
	
	static void init(BufferedReader br) throws IOException {
		// Petri dishes 의 크기를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// Petri dishes의 상태를 입력받는다.
		dish = new char[M][];
		for (int i = 0; i < M; i++) dish[i] = br.readLine().toCharArray();
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void BFS(int r, int c) {
		dish[r][c] = '.';
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(r, c));
		
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
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
		int answer = 0;
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (dish[i][j] == '#') {
					BFS(i, j);
					answer++;
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
