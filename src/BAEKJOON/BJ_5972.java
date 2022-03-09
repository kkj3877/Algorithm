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
		
		// �갣�� ���� N �� ���� ���� M �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// �갣�� ������ŭ ���� ������ ����Ʈ�� ������ش�.
		List<List<Path>> pathLists = new ArrayList<List<Path>>();
		for (int i = 0; i < N + 1; i++) pathLists.add(new ArrayList<Path>());
		
		// ���� �Է¹����� �갣���� ����� ���� ���¸� �������ش�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			pathLists.get(A).add(new Path(B, C));
			pathLists.get(B).add(new Path(A, C));
		}
		
		// 1�� �갣�������� �����ϴ� ���ͽ�Ʈ�� �˰����� ����Ͽ�
		// N �� �갣���� ���µ� �ʿ��� �ּ� ����� ����Ѵ�.
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
