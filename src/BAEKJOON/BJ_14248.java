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
		
		// ���ٸ��� ���� N�� �Է¹޴´�.
		int N = Integer.parseInt(br.readLine());
		int[] rocks = new int[N + 1];
		
		// ���ٸ��� ���¸� �Է¹޴´�.(���� ���� �Ÿ�)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) rocks[i] = Integer.parseInt(st.nextToken());
		
		// ���� ���� S �� �Է¹޴´�.
		int S = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(S);
		
		// ť�� �� �� ���� Ž���� �Ѵ�(BFS)
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
