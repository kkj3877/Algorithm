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

public class BJ_13549 {
	
	static int N, K;
	static int[] load;
	
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
		K = Integer.parseInt(st.nextToken());
		
		load = new int[100001];
		Arrays.fill(load, Integer.MAX_VALUE);
		load[N] = 0;
	}
	
	static int solve() {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(N, 0));
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowNum = now.dest;
			int nowTime = now.time;
			
//			System.out.println("nowNum, nowTime = " + nowNum + ", " + nowTime);
			if (nowNum == K) break;
			
			int nextDest = nowNum * 2;
			while (nextDest < 100001) {
				if (load[nextDest] != Integer.MAX_VALUE) break;
				
				load[nextDest] = nowTime;
				queue.add(new Pair(nextDest, nowTime));
				
				nextDest *= 2;
			}
			
			int nextTime = nowTime + 1;
			if (nowNum > 0) {
				if (load[nowNum - 1] == Integer.MAX_VALUE) {
					load[nowNum - 1] = nextTime;
					queue.add(new Pair(nowNum - 1, nextTime));
				}
			}
			
			if (nowNum < 100000) {
				if (load[nowNum + 1] == Integer.MAX_VALUE) {
					load[nowNum + 1] = nextTime;
					queue.add(new Pair(nowNum + 1, nextTime));
				}
			}
		}
		
		return load[K];
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
