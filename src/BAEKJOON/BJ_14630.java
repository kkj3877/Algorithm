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
		
		// ���� ������ ���� N�� �Է¹޴´�.
		int N = Integer.parseInt(br.readLine());
		
		// ���� �Է¹޾� �ڸ������� �и��Ͽ� �����Ѵ�.
		nums = new int[N + 1][101];
		for (int i = 1; i < N + 1; i++) {
			char[] line = br.readLine().toCharArray();
			length = line.length;
			for (int j = 0; j < length; j++) {
				nums[i][j] = line[j] - '0';
			}
		}
		
		// ���� ���¹�ȣ�� �����ϰ��� �ϴ� ���¹�ȣ�� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		
		
		// i ��° ���Ż��¿��� j ��° ���Ż��·� �����ϴµ� ���
		// ����� ������ ����� �����ϰ�, �� ����� ����Ѵ�.
		int[][] costs = new int[N + 1][N + 1];
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				int price = calcPrice(i, j);
				costs[i][j] = costs[j][i] = price;
			}
		}
		
		// ���ͽ�Ʈ�� �˰����� ����Ͽ�
		// ���� ���·κ��� k ��° ���·� ���ϴµ� �ʿ��� �ּҺ���� ����Ѵ�.
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
