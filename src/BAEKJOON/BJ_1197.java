package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1197 {
	
	static int V, E;
	static List<List<Path>> connLists;
	
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
		// 정점의 개수 V, 간선의 개수 E 를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 각 정점별로 연결상태를 저장할 List 를 선언한다.
		connLists = new ArrayList<List<Path>>();
		for (int i = 0; i < V + 1; i++) connLists.add(new ArrayList<Path>());
		
		// 간선의 정보를 입력받아 List 에 저장한다.
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			connLists.get(start).add(new Path(to, cost));
			connLists.get(to).add(new Path(start, cost));
		}
	}
	
	static int primMST(int start) {
		int cost = 0;
		
		// 경로들을 저장할 우선순위 큐를 선언하고,
		// 정점들의 방문 여부를 확인할 boolean 배열을 선언한다.
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		boolean[] visited = new boolean[V + 1];
		
		// start 번호를 가진 정점을 시작 노드로 하여 Prim 알고리즘을 수행한다.
		pq.add(new Path(start, 0));
		while (!pq.isEmpty()) {
			Path now = pq.poll();
			int nowNum = now.dest;
			
			// 우선순위 큐에서 나온 경로가 아직 방문하지 않은 정점으로 가는 경로라면
			// 해당 경로를 채택하고 해당 경로를 통해 연결되는 정점에 방문 표시를 해준다.
			if (visited[nowNum]) continue;
			visited[nowNum] = true;
			cost += now.cost;
			
			// 현재 보는 정점에 연결된 경로 중 아직 방문하지 않은 정점으로 가는
			// 경로들에 대해서 우선순위 큐에 삽입해준다.
			List<Path> connList = connLists.get(nowNum);
			for (Path next : connList) {
				int nextNum = next.dest;
				if (!visited[nextNum]) {
					pq.add(new Path(nextNum, next.cost));
				}
			}
		}
		
		return cost;
	}
	
	static int solve(int start) {
		// Prim 알고리즘을 사용하여 MST 의 가중치를 계산한다.
		return primMST(start);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve(1);
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
