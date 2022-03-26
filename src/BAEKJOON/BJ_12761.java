package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_12761 {
	
	static int A, B, N, M;
	static int[] distance;
	static int[] jumpLength;
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		distance = new int[100001];
		distance[N] = 0;
		jumpLength = new int[6];
		jumpLength[0] = -A;
		jumpLength[1] = -B;
		jumpLength[2] = -1;
		jumpLength[3] = 1;
		jumpLength[4] = A;
		jumpLength[5] = B;
	}
	
	static int solve() {
		if (N == M) return 0;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int nextDistance = distance[now] + 1;
			
//			System.out.println("now>> "+now+", distance = "+(nextDistance - 1));
			
			if (now * A < 100001) {
				if (now * A == M) return nextDistance;
				if (distance[now * A] == 0) {
					distance[now * A] = nextDistance;
					queue.add(now * A);
				}
			}
			if (now * B < 100001) {
				if (now * B == M) return nextDistance;
				if (distance[now * B] == 0) {
					distance[now * B] = nextDistance;
					queue.add(now * B);
				}
			}
			
			for (int k = 0; k < 6; k++) {
				int nextDest = now + jumpLength[k];
				if (nextDest < 0 || nextDest > 100000) continue;
				
				if (nextDest == M) return nextDistance;
				
				if (distance[nextDest] == 0) {
					distance[nextDest] = nextDistance;
					queue.add(nextDest);
				}
			}
		}
		
		return -1;
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
