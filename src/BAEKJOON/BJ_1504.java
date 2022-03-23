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

public class BJ_1504 {
	
	static class Pair implements Comparable<Pair> {
		int dest;
		int distance;
		
		public Pair(int dest, int distance) {
			this.dest = dest;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Pair o) {
			return (this.distance < o.distance) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ������ ���� N �� ������ ���� E �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// ��ε��� ���¸� �Է¹޾� �����Ѵ�.
		List<List<Pair>> pathLists = new ArrayList<List<Pair>>();
		for (int i = 0; i < N + 1; i++) pathLists.add(new ArrayList<Pair>());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			pathLists.get(A).add(new Pair(B, distance));
			pathLists.get(B).add(new Pair(A, distance));
		}
		
		// �ݵ�� ���ľ��ϴ� ������ ��ȣ v1 �� v2 �� �Է¹޴´�.
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		// ���ͽ�Ʈ�� �˰����� ����Ͽ� v1�� v2�κ��� 1���� N�� ���������� �ִܰŸ��� ���Ѵ�.
		int[] leastDistanceFromV1 = new int[N + 1];
		Arrays.fill(leastDistanceFromV1, Integer.MAX_VALUE);
		leastDistanceFromV1[v1] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(v1, 0));
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowNum = now.dest;
			int nowDistance = now.distance;
			
			if (leastDistanceFromV1[nowNum] < nowDistance) continue;
			
			for (Pair next : pathLists.get(nowNum)) {
				int nextNum = next.dest;
				int nextDistance = nowDistance + next.distance;
				
				if (leastDistanceFromV1[nextNum] > nextDistance) { 
					leastDistanceFromV1[nextNum] = nextDistance;
					pq.add(new Pair(nextNum, nextDistance));
				}
			}
		}
		
		int[] leastDistanceFromV2 = new int[N + 1];
		Arrays.fill(leastDistanceFromV2, Integer.MAX_VALUE);
		leastDistanceFromV2[v2] = 0;
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(v2, 0));
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			int nowNum = now.dest;
			int nowDistance = now.distance;
			
			if (leastDistanceFromV2[nowNum] < nowDistance) continue;
			
			for (Pair next : pathLists.get(nowNum)) {
				int nextNum = next.dest;
				int nextDistance = nowDistance + next.distance;
				
				if (leastDistanceFromV2[nextNum] > nextDistance) { 
					leastDistanceFromV2[nextNum] = nextDistance;
					pq.add(new Pair(nextNum, nextDistance));
				}
			}
		}
		
//		System.out.println(">> leastDistanceFromV1");
//		for (int i = 1; i < N + 1; i++) System.out.print(leastDistanceFromV1[i]+" ");
//		System.out.println("------------------");
//		
//		System.out.println(">> leastDistanceFromV2");
//		for (int i = 1; i < N + 1; i++) System.out.print(leastDistanceFromV2[i]+" ");
//		System.out.println("------------------");
		
		if (leastDistanceFromV1[1] == Integer.MAX_VALUE || leastDistanceFromV1[N] == Integer.MAX_VALUE) bw.write("-1\n");
		else {
			int answer = leastDistanceFromV1[v2];
			int pathV1V2Distance = leastDistanceFromV1[1] + leastDistanceFromV2[N];
			int pathV2V1Distance = leastDistanceFromV2[1] + leastDistanceFromV1[N];
			answer += (pathV1V2Distance < pathV2V1Distance) ? pathV1V2Distance : pathV2V1Distance;
			
			bw.write(answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
