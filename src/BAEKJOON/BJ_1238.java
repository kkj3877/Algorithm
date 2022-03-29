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
		// �л��� �� N, ������ ���� M, ��Ƽ�� �������� ������ ��ȣ X �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// �� ������ ���� ���¸� ������ List �� �����Ѵ�.
		connLists = new ArrayList<List<Path>>();
		reverseConnLists = new ArrayList<List<Path>>();
		for (int i = 0; i < N + 1; i++) {
			connLists.add(new ArrayList<Path>());
			reverseConnLists.add(new ArrayList<Path>());
		}
		
		// �� ������ ���� ���¸� �Է¹޾� �����Ѵ�.
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
		
		// Dijkstra �˰����� ����Ͽ� [�� ���� -> ��Ƽ����] ��
		// [��Ƽ���� -> �� ����] �� ���� �ִܰŸ��� ����Ѵ�.
		int[] fromXtoEach = dijkstra(connLists);
		int[] fromEachToX = dijkstra(reverseConnLists);
		
		// �պ��� �ʿ��� ���� ū �ð��� ����Ѵ�.
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
