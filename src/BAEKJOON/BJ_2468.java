package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468 {
	
	// ��� ���� ���� N, N * N �������� ���� ������ ������ �迭
	static int N;
	static int[][] map;
	
	// BFS�� ���� dr, dc �迭
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 }; 
	
	// ť�� �� ���
	static class Pair {
		int r, c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// Ư�� ���̸�ŭ ���� ��� ��� ���� ������ ������ ���Ͽ� ��ȯ�ϴ� �Լ�
	static int countSafePlace(int height) {
		int safe = 0;
		
		boolean[][] check = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check[i][j]) continue;
				
				if (map[i][j] > height) {
					safe++;
					
					Queue<Pair> queue = new LinkedList<Pair>();
					
					check[i][j] = true;
					queue.add(new Pair(i, j));
					while (!queue.isEmpty()) {
						Pair now = queue.poll();
						int nowR = now.r;
						int nowC = now.c;
						
						for (int k = 0; k < 4; k++) {
							int nextR = nowR + dr[k];
							if (nextR < 0 || nextR > N - 1) continue;
							
							int nextC = nowC + dc[k];
							if (nextC < 0 || nextC > N - 1) continue;
							
							if (check[nextR][nextC]) continue;
							
							check[nextR][nextC] = true;
							if (map[nextR][nextC] > height) queue.add(new Pair(nextR, nextC));
						}
					}
				}
			}
		}
		
		return safe;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ��� ���� ������ N�� �Է¹޴´�.
		N = Integer.parseInt(br.readLine());
		
		// N * N �������� ���̸� ������ �迭�� �����Ѵ�.
		map = new int[N][N];
		
		// Ư�� ������ ���� �ִ����� üũ�� �迭�� �����Ѵ�.
		boolean[] check = new boolean[101];
		
		// ������ ���� ���¸� �Է¹޴´�.
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int height = Integer.parseInt(st.nextToken());
				check[height] = true;
				map[i][j] = height;
			}
		}
		
		// �����ϴ� ���̵��� ������� ���� ���̸� �����ϸ� ������ ������ ������ ����Ѵ�.
		int maxSafe = 1;
		for (int h = 1; h < 101; h++) {
			if (check[h]) {
				int safe = countSafePlace(h);
				if (safe > maxSafe) maxSafe = safe;
			}
		}
		
		bw.write(maxSafe+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
