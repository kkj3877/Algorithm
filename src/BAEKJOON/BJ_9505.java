package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_9505 {
	
	static int K, W, H;
//	static int[] timeMap;
	static Map<Character, Integer> timeMap;
	static int[][] grid;
	static int startR, startC;
	
	static void init(BufferedReader br) throws IOException {
		// Ŭ���� �������� Ŭ���� ���� K, ����� �� W, ���� H �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
//		timeMap = new int[26];
//		for (int k = 0; k < K; k++) {
//			st = new StringTokenizer(br.readLine());
//			int className = st.nextToken().charAt(0) - 'A';
//			int time = Integer.parseInt(st.nextToken());
//			timeMap[className] = time;
//		}
		
		// Ŭ���� �������� Ŭ���� �̸��� ����ȭ �ð��� �Է¹޴´�.
		timeMap = new HashMap<Character, Integer>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			char className = st.nextToken().charAt(0);
			int time = Integer.parseInt(st.nextToken());
			timeMap.put(className, time);
		}
		
		// ��� ������ ����� ������ ���¸� �Է¹޴´�.
		grid = new int[H][W];
		for (int i = 0; i < H; i++) {
			String line = br.readLine();
			for (int j = 0; j < W; j++) {
				char ch = line.charAt(j);
				if (ch == 'E') {
					startR = i;
					startC = j;
					grid[i][j] = 0;
				}
				else grid[i][j] = timeMap.get(ch);
			}
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int r, c, cost;
		
		public Pair(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pair o) {
			return (this.cost < o.cost) ? -1 : 1;
		}
	}
	
	static int solve() {
		// Dijkstra �˰����� ����Ͽ� �ִܰŸ��� ����Ѵ�.(Priority Queue ���)
		// Ž�� �������� �����ڸ����� Ȯ�εǸ� PQ�� �ش� ��带 ���� �������μ�
		// �����ڸ��� �ν��ϵ��� �Ѵ�.
		int answer = Integer.MAX_VALUE;
		
		// �Է� Ȯ�ο� �����
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) System.out.print(grid[i][j]+" ");
//			System.out.println();
//		}
		
		// Dijkstra �˰����� ���� ����
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		int[][] costs = new int[H][W];
		for (int i = 0; i < H; i++) Arrays.fill(costs[i], Integer.MAX_VALUE);
		costs[startR][startC] = 0;
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		// ������������ȣ�� ��ġ�κ��� �ִܰŸ� ����� ������.
		pq.add(new Pair(startR, startC, 0));
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowCost = now.cost;
			
			if (nowCost < costs[nowR][nowC]) continue;
			
			if (nowR == 0 || nowR == H - 1 || nowC == 0 || nowC == W - 1) {
				if (answer > nowCost) answer = nowCost;
				continue;
			}
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > H - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > W - 1) continue;
				
				int nextCost = nowCost + grid[nextR][nextC];
				if (costs[nextR][nextC] > nextCost) {
					costs[nextR][nextC] = nextCost;
					pq.add(new Pair(nextR, nextC, nextCost));
				}
			}
		}
		
//		System.out.println("costs is>>");
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) System.out.print(costs[i][j]+" ");
//			System.out.println();
//		}
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// �׽�Ʈ���̽��� ������ �Է¹ް�, T ��ŭ�� ������ �ݺ��Ѵ�.
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init(br);
			int answer = solve();
			bw.write(answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
