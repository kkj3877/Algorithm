package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1238 {
	
	static int N, M, X;
	static List<List<Path>> connLists;
	static List<List<Path>> reverseConnLists;
	
	static class Path implements Comparable<Path>{
		int dest, cost;
		
		public Path(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Path o) {
			return (this.cost < o.cost) ? -1 : 1;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		// 학생의 수 N, 도로의 개수 M, 파티가 벌어지는 마을의 번호 X 를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 각 마을별 연결 상태를 저장할 List 를 선언한다.
		connLists = new ArrayList<List<Path>>();
		reverseConnLists = new ArrayList<List<Path>>();
		for (int i = 0; i < N + 1; i++) {
			connLists.add(new ArrayList<Path>());
			reverseConnLists.add(new ArrayList<Path>());
		}
		
		// 각 마을의 연결 상태를 입력받아 저장한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			connLists.get(start).add(new Path(to, cost));
			reverseConnLists.get(to).add(new Path(start, cost));
		}
	}
	
	static int[] dijkstra(List<List<Path>> pathLists) {
		int[] leastCost = new int[N + 1];
		Arrays.fill(leastCost, Integer.MAX_VALUE);
		
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		pq.add(new Path(X, 0));
		leastCost[X] = 0;
		
		while (!pq.isEmpty()) {
			Path now = pq.poll();
			int nowNum = now.dest;
			int nowCost = now.cost;
			
			if (leastCost[nowNum] < nowCost) continue;
			
			List<Path> connList = pathLists.get(nowNum);
			for (Path next : connList) {
				int nextNum = next.dest;
				int nextCost = nowCost + next.cost;
				if (leastCost[nextNum] > nextCost) {
					leastCost[nextNum] = nextCost;
					pq.add(new Path(nextNum, nextCost));
				}
			}
		}
		
		return leastCost;
	}
	
	static int solve() {
		int maxTime = -1;
		
		// Dijkstra 알고리즘을 사용하여 [각 마을 -> 파티마을] 과
		// [파티마을 -> 각 마을] 에 대한 최단거리를 계산한다.
		int[] fromXtoEach = dijkstra(connLists);
		int[] fromEachToX = dijkstra(reverseConnLists);
		
		// 왕복에 필요한 가장 큰 시간을 계산한다.
		for (int i = 1; i < N + 1; i++) {
			int time = fromXtoEach[i] + fromEachToX[i];
			if (maxTime < time) maxTime = time;
		}
		
		return maxTime;
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
