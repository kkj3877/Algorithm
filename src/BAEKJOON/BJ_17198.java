package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_17198 {
	
	static char[][] farm;
	static int startR, startC;
	
	static void init(BufferedReader br) throws IOException {
		farm = new char[10][];
		boolean flag = false;
		
		for (int i = 0; i < 10; i++) {
			farm[i] = br.readLine().toCharArray();
			
			if (flag) continue;
			for (int j = 0; j < 10; j++) {
				if (farm[i][j] == 'B') {
					startR = i;
					startC = j;
					flag = true;
				}
			}
		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int solve() {
		int[][] distance = new int[10][10];
		Queue<Pair> queue = new LinkedList<Pair>();
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		distance[startR][startC] = 1;
		queue.add(new Pair(startR, startC));
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowDistance = distance[nowR][nowC];
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > 9) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > 9) continue;
				
				if (farm[nextR][nextC] == 'L') return nowDistance - 1;
				
				if (farm[nextR][nextC] == '.') {
					farm[nextR][nextC] = 'C';
					distance[nextR][nextC] = nowDistance + 1;
					queue.add(new Pair(nextR, nextC));
				}
			}
		}
		
		return -1;
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
