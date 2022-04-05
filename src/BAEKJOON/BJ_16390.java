package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16390 {
	
	static int M, N;
	static char[][] dish;
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		// ���� ���� M �� ���� ���� N �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		
		// dish �� ���¸� ������ ������ �����ϰ� ���¸� �Է¹޴´�.
		dish = new char[M][];
		for (int i = 0; i < M; i++) dish[i] = br.readLine().toCharArray();
	}
	
	// dish[i][j] �� ���� BFS �� �����ϸ� ����� ����� üũ�Ѵ�.
	static void BFS(int i, int j) {
		Queue<Pair> queue = new LinkedList<Pair>();
		
		// 8 ���⿡ ���� ����
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
		
		// ���������� (i, j) �� �ϴ� BFS �� �����ϱ� ���� ù ��ǥ ó��
		dish[i][j] = '.';
		queue.add(new Pair(i, j));
		
		// ť�� �� �� ���� Ž���� �����Ѵ�.
		while (!queue.isEmpty()) {
			// ť�� �������� ���� ��ǥ�� Ȯ���Ѵ�.
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			// ���� ��ǥ�κ��� 8������ ���� �Ƹ޹ٰ� �ִ��� Ȯ���Ѵ�.
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
		int count = 0;
		
		// dish �� �ѷ����� BFS �� ����� closed loops �� ������ count �Ѵ�.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (dish[i][j] == '#') {
					BFS(i, j);
					count++;
				}
			}
		}
		
		return count;
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
