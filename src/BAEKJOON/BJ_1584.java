package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1584 {
	
	static class Pair implements Comparable<Pair> {
		int x, y, lost;
		public Pair(int x, int y, int lost) {
			this.x = x;
			this.y = y;
			this.lost = lost;
		}
		
		@Override
		public int compareTo(Pair o) {
			return (this.lost < o.lost) ? -1 : 1; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ���� ���¸� ������ �迭�� �����Ѵ�.
		int[][] map = new int[501][501];
		int[][] losts = new int[501][501];
		for (int i = 0; i < 501; i++) Arrays.fill(losts[i], Integer.MAX_VALUE);
		
		// ������ ������ �� N �� �Է¹ް�, ������ �������� ǥ���Ѵ�.
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if (x2 < x1) {
				int temp = x2;
				x2 = x1;
				x1 = temp;
			}
			if (y2 < y1) {
				int temp = y2;
				y2 = y1;
				y1 = temp;
			}
			
			for (int x = x1; x <= x2; x++) {
				for (int y = y1; y <= y2; y++) map[x][y] = 1;
			}
		}
		
		// ������ ������ �� M �� �Է¹ް�, ������ �������� ǥ���Ѵ�.
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if (x2 < x1) {
				int temp = x2;
				x2 = x1;
				x1 = temp;
			}
			if (y2 < y1) {
				int temp = y2;
				y2 = y1;
				y1 = temp;
			}
			
			for (int x = x1; x <= x2; x++) {
				for (int y = y1; y <= y2; y++) map[x][y] = 2;
			}
		}
		
		// ���ͽ�Ʈ�� �˰����� ����Ͽ� (0, 0) ���� (500, 500) ���� �����ϱ� ����
		// �Ҿ�� �ϴ� ������ �ּڰ��� ����Ѵ�.
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(0, 0, 0));
		losts[0][0] = 0;
		
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowLost = now.lost;
			
			if (losts[nowX][nowY] < nowLost) continue;
			
			for (int k = 0; k < 4; k++) {
				int nextX = nowX + dx[k];
				if (nextX < 0 || nextX > 500) continue;
				
				int nextY = nowY + dy[k];
				if (nextY < 0 || nextY > 500) continue;
				
				if (map[nextX][nextY] == 2) {
					continue;
				}
				else if (map[nextX][nextY] == 1) {
					if (nowLost + 1 < losts[nextX][nextY]) {
						losts[nextX][nextY] = nowLost + 1;
						pq.add(new Pair(nextX, nextY, nowLost + 1));
					}
				}
				else if (nowLost < losts[nextX][nextY]) {
					losts[nextX][nextY] = nowLost;
					pq.add(new Pair(nextX, nextY, nowLost));
				}
			}
		}
		
		if (losts[500][500] == Integer.MAX_VALUE) bw.write("-1\n");
		else bw.write(losts[500][500]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
