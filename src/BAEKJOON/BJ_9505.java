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
		// 클링온 전투선의 클래스 개수 K, 평면의 폭 W, 높이 H 를 입력받는다.
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
		
		// 클링온 전투선의 클래스 이름별 무력화 시간을 입력받는다.
		timeMap = new HashMap<Character, Integer>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			char className = st.nextToken().charAt(0);
			int time = Integer.parseInt(st.nextToken());
			timeMap.put(className, time);
		}
		
		// 평면 지도를 만들고 지도의 상태를 입력받는다.
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
		// Dijkstra 알고리즘을 사용하여 최단거리를 계산한다.(Priority Queue 사용)
		// 탐색 과정에서 가장자리임이 확인되면 PQ에 해당 노드를 넣지 않음으로서
		// 가장자리를 인식하도록 한다.
		int answer = Integer.MAX_VALUE;
		
		// 입력 확인용 디버깅
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) System.out.print(grid[i][j]+" ");
//			System.out.println();
//		}
		
		// Dijkstra 알고리즘을 위한 세팅
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		int[][] costs = new int[H][W];
		for (int i = 0; i < H; i++) Arrays.fill(costs[i], Integer.MAX_VALUE);
		costs[startR][startC] = 0;
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		// 엔터프라이즈호의 위치로부터 최단거리 행렬을 만들어간다.
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
		
		// 테스트케이스의 개수를 입력받고, T 만큼의 시행을 반복한다.
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
