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

public class BJ_16928 {
	
	static class Pair {
		int dest;
		int count;
		
		public Pair(int dest, int count) {
			this.dest = dest;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ��ٸ��� �� N �� ���� �� M �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// ��ٸ��� ���� �Է¹޴´�. �Ϲ����� ĭ�� 0�� ����,
		// ��ٸ��� �ִ� ĭ�� ���������� ����, ���� �ִ� ĭ�� -(��������)�� ���� ������.
		int[] status = new int[101];
		
		// ��ٸ��� �Է¹޴´�.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			status[from] = to;
		}
		
		// ���� �Է¹޴´�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			status[from] = -to;
		}
		
		// �������� �� ĭ�� �� �� �ִ� �ִܰŸ��� ������ �迭�� �����Ѵ�.
		int[] board = new int[101];
		Arrays.fill(board, Integer.MAX_VALUE);
		
		// BFS �� ����Ͽ� ������������ ���µ� �ʿ��� �ּ��� �ֻ��� Ƚ���� ���Ѵ�.
		board[1] = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(1, 0));
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int num = now.dest;
			int count = now.count;
			
			for (int i = 1; i <= 6; i++) {
				int next = num + i;
				if (next > 100) continue;
				if (board[next] != Integer.MAX_VALUE) continue;
				
				// ���� ĭ���� �ֻ��� �� ������ �� �� �ִ� ĭ �߿� ���� �湮���� ���� ĭ�� ���Ͽ�
				// �� ĭ�� ���µ� �ʿ��� �ֻ��� Ƚ���� �Է¹ް�, ��ٸ� Ȥ�� ���� �ִٸ�
				// ��Ģ�� �µ��� �ൿ�ϵ��� �Ѵ�.
				board[next] = count + 1;
				
				if (status[next] == 0) {
					queue.add(new Pair(next, count + 1));
				}
				else if (status[next] > 0) {
					next = status[next];
					if (board[next] != Integer.MAX_VALUE) continue;
					board[next] = count + 1;
					queue.add(new Pair(next, count + 1));
				}
				else {
					next = -status[next];
					if (board[next] != Integer.MAX_VALUE) continue;
					board[next] = count + 1;
					queue.add(new Pair(next, count + 1));
				}
			}
		}
		
		bw.write(board[100]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
