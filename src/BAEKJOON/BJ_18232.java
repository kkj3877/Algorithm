package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_18232 {
	
	static int N, M;
	static int S, E;
	static List<List<Integer>> teleportLists;
	static int[] leastTime;
	
	static class Pair {
		int dest;
		int time;
		
		public Pair(int dest, int time) {
			this.dest = dest;
			this.time = time;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		teleportLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) teleportLists.add(new ArrayList<Integer>());
		leastTime = new int[N + 1];
		Arrays.fill(leastTime, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		leastTime[S] = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			teleportLists.get(A).add(B);
			teleportLists.get(B).add(A);
		}
	}
	
	static int solve(int start) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(start, 0));
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowLocation = now.dest;
			int nextTime = now.time + 1;
//			System.out.println("now, nextTime = " + nowLocation + ", " + nextTime);
			
			for (int teleportDest : teleportLists.get(nowLocation)) {
				if (teleportDest == E) return nextTime;
				if (teleportDest != 0 && leastTime[teleportDest] == Integer.MAX_VALUE) {
					leastTime[teleportDest] = nextTime;
					queue.add(new Pair(teleportDest, nextTime));
				}
			}
			
			if (nowLocation > 1) {
				if (nowLocation - 1 == E) return nextTime;
				if (leastTime[nowLocation - 1] == Integer.MAX_VALUE) {
					leastTime[nowLocation - 1] = nextTime;
					queue.add(new Pair(nowLocation - 1, nextTime));
				}
			}
			
			if (nowLocation < N) {
				if (nowLocation + 1 == E) return nextTime;
				if (leastTime[nowLocation + 1] == Integer.MAX_VALUE) {
					leastTime[nowLocation + 1] = nextTime;
					queue.add(new Pair(nowLocation + 1, nextTime));
				}
			}
//			for (int i = 1; i < N + 1; i++) System.out.print(leastTime[i] + "\t");
//			System.out.println();
		}
		
		return leastTime[E];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int time = solve(S);
		bw.write(time+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
