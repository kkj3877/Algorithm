package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_11060 {
	
	static int N;
	static int[] maze;
	static int[] distance;
	
	static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		maze = new int[N];
		distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) maze[i] = Integer.parseInt(st.nextToken());
	}
	
	static int solve() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		distance[0] = 0;
		while (!queue.isEmpty()) {
			int nowNum = queue.poll();
			int jumpLength = maze[nowNum];
			int nextDistance = distance[nowNum] + 1;
			
			for (int i = 1; i <= jumpLength; i++) {
				int nextNum = nowNum + i;
				if (nextNum > N - 1) break;
				if (distance[nextNum] != Integer.MAX_VALUE) continue;
				
				distance[nextNum] = nextDistance;
				queue.add(nextNum);
			}
		}
		
		return (distance[N - 1] == Integer.MAX_VALUE) ? -1 : distance[N - 1];
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
