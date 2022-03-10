package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_11657 {
	
	static class Path {
		int from, to, cost;
		
		public Path(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static boolean bellmanFord(int start) {
		leastCosts = new long[N + 1];
		Arrays.fill(leastCosts, Long.MAX_VALUE);
		leastCosts[start] = 0;
		
		for (int i = 0; i < N; i++) {
			boolean flag = false;
			for (Path path : pathList) {
				int from = path.from;
				if (leastCosts[from] == Long.MAX_VALUE) continue;
				
				int to = path.to;
				int cost = path.cost;
				if (leastCosts[from] + cost < leastCosts[to]) {
					leastCosts[to] = leastCosts[from] + cost;
					if (i == N - 1) return true;
					
					flag = true;
				}
			}
			if (!flag) break;
		}
		
		return false;
	}
	
	static int N, M;
	static long[] leastCosts;
	static List<Path> pathList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 도시의 개수 N과 버스 노선의 개수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 버스 노선들의 정보를 입력받아 저장한다.
		pathList = new ArrayList<Path>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pathList.add(new Path(from, to, cost));
		}
		
		// 벨만-포드 알고리즘을 사용하여 1번 도시로부터 각 도시까지의 최단거리를 계산한다.
		boolean hasNagativeCycle = bellmanFord(1);
		
		if (hasNagativeCycle) bw.write("-1\n");
		else {
			for (int i = 2; i < N + 1; i++) {
				if (leastCosts[i] == Long.MAX_VALUE) bw.write("-1\n");
				else bw.write(leastCosts[i]+"\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
