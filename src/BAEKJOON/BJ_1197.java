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
		// ������ ���� V, ������ ���� E �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// �� �������� ������¸� ������ List �� �����Ѵ�.
		connLists = new ArrayList<List<Path>>();
		for (int i = 0; i < V + 1; i++) connLists.add(new ArrayList<Path>());
		
		// ������ ������ �Է¹޾� List �� �����Ѵ�.
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
		
		// ��ε��� ������ �켱���� ť�� �����ϰ�,
		// �������� �湮 ���θ� Ȯ���� boolean �迭�� �����Ѵ�.
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		boolean[] visited = new boolean[V + 1];
		
		// start ��ȣ�� ���� ������ ���� ���� �Ͽ� Prim �˰����� �����Ѵ�.
		pq.add(new Path(start, 0));
		while (!pq.isEmpty()) {
			Path now = pq.poll();
			int nowNum = now.dest;
			
			// �켱���� ť���� ���� ��ΰ� ���� �湮���� ���� �������� ���� ��ζ��
			// �ش� ��θ� ä���ϰ� �ش� ��θ� ���� ����Ǵ� ������ �湮 ǥ�ø� ���ش�.
			if (visited[nowNum]) continue;
			visited[nowNum] = true;
			cost += now.cost;
			
			// ���� ���� ������ ����� ��� �� ���� �湮���� ���� �������� ����
			// ��ε鿡 ���ؼ� �켱���� ť�� �������ش�.
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
		// Prim �˰����� ����Ͽ� MST �� ����ġ�� ����Ѵ�.
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
