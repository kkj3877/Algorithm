package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_5986 {
	
	static int N;
	static char[][][] space;
	
	static void init(BufferedReader br) throws IOException {
		// space 의 한 변의 길이 N 을 입력받는다.
		N = Integer.parseInt(br.readLine());
		
		// space 의 상태를 입력받는다.
		space = new char[N][N][];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				space[i][j] = br.readLine().toCharArray();
			}
		}
	}
	
	static class Pair {
		int h, r, c;
		public Pair(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	
	static void BFS(int h, int r, int c) {
		space[h][r][c] = '.';
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(h, r, c));
		
		int[] dh = { -1, 0, 0, 0, 0, 1 };
		int[] dr = { 0, -1, 0, 1, 0, 0 };
		int[] dc = { 0, 0, 1, 0, -1, 0 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowH = now.h;
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 6; k++) {
				int nextH = nowH + dh[k];
				if (nextH < 0 || nextH > N - 1) continue;
				
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > N - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > N - 1) continue;
				
				if (space[nextH][nextR][nextC] == '*') {
					space[nextH][nextR][nextC] = '.';
					queue.add(new Pair(nextH, nextR, nextC));
				}
			}
		}
	}
	
	static int solve() {
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (space[i][j][k] == '*') {
						BFS(i, j, k);
						answer++;
					}
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
