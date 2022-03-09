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

public class BJ_5972 {
	
	static class Path implements Comparable<Path> {
		int dest;
		int cost;
		
		public Path(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path o) {
			return (this.cost < o.cost) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 헛간의 개수 N 과 길의 개수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 헛간의 개수만큼 길을 저장할 리스트를 만들어준다.
		List<List<Path>> pathLists = new ArrayList<List<Path>>();
		for (int i = 0; i < N + 1; i++) pathLists.add(new ArrayList<Path>());
		
		// 길을 입력받으며 헛간별로 연결된 길의 상태를 저장해준다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			pathLists.get(A).add(new Path(B, C));
			pathLists.get(B).add(new Path(A, C));
		}
		
		// 1번 헛간에서부터 시작하는 다익스트라 알고리즘을 사용하여
		// N 번 헛간까지 가는데 필요한 최소 비용을 계산한다.
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		pq.add(new Path(1, 0));
		
		int[] leastCost = new int[N + 1];
		Arrays.fill(leastCost, Integer.MAX_VALUE);
		leastCost[1] = 0;
		
		while (!pq.isEmpty()) {
			Path now = pq.poll();
			int nowNum = now.dest;
			int nowCost = now.cost;
			
			if (leastCost[nowNum] < nowCost) continue;
			
			List<Path> pathList = pathLists.get(nowNum);
			for (Path path : pathList) {
				int dest = path.dest;
				int nextCost = nowCost + path.cost;
				if (nextCost < leastCost[dest]) {
					leastCost[dest] = nextCost;
					pq.add(new Path(dest, nextCost));
				}
			}
		}
		
		bw.write(leastCost[N]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
