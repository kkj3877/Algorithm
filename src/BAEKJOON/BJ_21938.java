package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21938 {
	
	static int N, M;
	static int[][] screen;
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		screen = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int sum = 0;
				for (int k = 0; k < 3; k++) {
					sum += Integer.parseInt(st.nextToken());
				}
				screen[i][j] = sum / 3;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				screen[i][j] = (screen[i][j] >= T) ? 1 : 0;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(screen[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void BFS(int r, int c) {
		screen[r][c] = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
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
				if (nextC < 0 || nextC > M - 1) continue;
				
				if (screen[nextR][nextC] == 1) {
					screen[nextR][nextC] = 0;
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
	}
	
	static int solve() throws IOException {
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (screen[i][j] == 1) {
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
