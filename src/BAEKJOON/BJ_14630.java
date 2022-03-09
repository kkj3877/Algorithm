package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_14630 {
	
	static int length;
	static int[][] nums;
	static int[] transformPrice;
	
	static int calcPrice(int from, int to) {
		int price = 0;
		
		for (int i = 0; i < length; i++) {
			price += (nums[from][i] - nums[to][i]) * (nums[from][i] - nums[to][i]); 
		}
		
		return price;
	}
	
	static class Pair implements Comparable<Pair> {
		int dest, cost;
		
		public Pair(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair o) {
			return (this.cost < o.cost) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 변신 상태의 개수 N을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		
		// 수를 입력받아 자릿수들을 분리하여 저장한다.
		nums = new int[N + 1][101];
		for (int i = 1; i < N + 1; i++) {
			char[] line = br.readLine().toCharArray();
			length = line.length;
			for (int j = 0; j < length; j++) {
				nums[i][j] = line[j] - '0';
			}
		}
		
		// 현재 상태번호와 변신하고자 하는 상태번호를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		
		
		// i 번째 변신상태에서 j 번째 변신상태로 변신하는데 드는
		// 비용을 저장할 행렬을 선언하고, 그 비용을 계산한다.
		int[][] costs = new int[N + 1][N + 1];
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				int price = calcPrice(i, j);
				costs[i][j] = costs[j][i] = price;
			}
		}
		
		// 다익스트라 알고리즘을 사용하여
		// 현재 상태로부터 k 번째 상태로 변하는데 필요한 최소비용을 계산한다.
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		int[] leastCost = new int[N + 1];
		Arrays.fill(leastCost, Integer.MAX_VALUE);
		pq.add(new Pair(from, 0));
		leastCost[from] = 0;
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowNum = now.dest;
			int nowCost = now.cost;
			
			if (leastCost[nowNum] < nowCost) continue;
			
			for (int i = 1; i < N + 1; i++) {
				if (nowNum == i) continue;
				
				int nextCost = nowCost + costs[nowNum][i];
				if (nextCost < leastCost[i]) {
					leastCost[i] = nextCost;
					pq.add(new Pair(i, nextCost));
				}
			}
		}
		
		bw.write(leastCost[to]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
