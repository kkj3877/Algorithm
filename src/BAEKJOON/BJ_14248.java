package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14248 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 돌다리의 길이 N을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		int[] rocks = new int[N + 1];
		
		// 돌다리의 상태를 입력받는다.(점프 가능 거리)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) rocks[i] = Integer.parseInt(st.nextToken());
		
		// 시작 지점 S 를 입력받는다.
		int S = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(S);
		
		// 큐가 빌 때 까지 탐색을 한다(BFS)
		boolean[] checked = new boolean[N + 1];
		checked[S] = true;
		int cnt = 1;
		while (!queue.isEmpty()) {
			int idx = queue.poll();
			int jumpLength = rocks[idx];
			
			int next = idx - jumpLength;
			if (next > 0 && !checked[next]) {
				checked[next] = true;
				queue.add(next);
				cnt++;
			}
			next = idx + jumpLength;
			if (next <= N && !checked[next]) {
				checked[next] = true;
				queue.add(next);
				cnt++;
			}
		}
		
		bw.write(cnt+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
