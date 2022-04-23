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

public class BJ_6054 {
	
	static int N;
	static int[] lapTimes;
	static List<List<Integer>> signLists;
	
	static void init(BufferedReader br) throws IOException {
		// 소들의 마릿수인 N 을 입력받는다.
		N = Integer.parseInt(br.readLine());
		
		// 소들의 lap time 과 신호를 전달할 소들의 리스트를 입력받는다.
		lapTimes = new int[N + 1];
		signLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) signLists.add(new ArrayList<Integer>());
		
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lapTimes[i] = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			List<Integer> signList = signLists.get(i);
			for (int j = 0; j < M; j++) signList.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int dest;
		int cost;
		
		public Pair(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pair o) {
			return (this.cost < o.cost) ? -1 : 1;
		}
	}
	
	static int solve() {
		int answer = 0;
		boolean[] visit = new boolean[N + 1];
		int[] costs = new int[N + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		visit[1] = true;
		costs[1] = lapTimes[1];
		pq.add(new Pair(1, lapTimes[1]));
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowNum = now.dest;
			int nowCost = now.cost;
			
			if (costs[nowNum] < nowCost) continue;
			costs[nowNum] = nowCost;
			if (answer < nowCost) answer = nowCost;
			
			for (int next : signLists.get(nowNum)) {
				int nextCost = nowCost + lapTimes[next];
				
				if (costs[next] > nextCost) {
					costs[next] = nextCost;
					pq.add(new Pair(next, nextCost));
				}
			}
			
		}
		
		return answer;
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
