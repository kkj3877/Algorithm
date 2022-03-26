package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13265 {
	
	static int N, M;
	static List<List<Integer>> connLists;
	static int[] color;
	
	static class Pair {
		int dest, color;
		public Pair(int dest, int color) {
			this.dest = dest;
			this.color = color;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		connLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) connLists.add(new ArrayList<Integer>());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			connLists.get(n1).add(n2);
			connLists.get(n2).add(n1);
		}
		
		color = new int[N + 1];
	}
	
	static boolean solve() {
		boolean isCan = true;
		
		Queue<Pair> queue = new LinkedList<Pair>();
		
		for (int i = 1; i <= N; i++) {
			if (color[i] != 0) continue;
			
			queue.add(new Pair(i, 1));
			color[i] = 1;
			
			while (!queue.isEmpty()) {
				boolean canDraw = true;
				
				Pair now = queue.poll();
				int nowNum = now.dest;
				int nowColor = now.color;
				int nextColor = (nowColor == 1) ? 2 : 1;
				
				List<Integer> connList = connLists.get(nowNum);
				for (int next : connList) {
					if (color[next] == 0) {
						color[next] = nextColor;
						queue.add(new Pair(next, nextColor));
					}
					else if (nowColor == color[next]) {
						isCan = false;
						canDraw = false;
					}
				}
				if (!canDraw) break;
			}
			if (!isCan) break;
		}
		
		return isCan;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init(br);
			boolean answer = solve();
			if (answer) bw.write("possible\n");
			else bw.write("impossible\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
