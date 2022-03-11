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
		
		// 사다리의 수 N 과 뱀의 수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 사다리와 뱀을 입력받는다. 일반적인 칸은 0의 값을,
		// 사다리가 있는 칸은 도착지점의 값을, 뱀이 있는 칸은 -(도착지점)의 값을 가진다.
		int[] status = new int[101];
		
		// 사다리를 입력받는다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			status[from] = to;
		}
		
		// 뱀을 입력받는다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			status[from] = -to;
		}
		
		// 게임판의 각 칸에 갈 수 있는 최단거리를 저장할 배열을 선언한다.
		int[] board = new int[101];
		Arrays.fill(board, Integer.MAX_VALUE);
		
		// BFS 를 사용하여 도착지점까지 가는데 필요한 최소의 주사위 횟수를 구한다.
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
				
				// 현재 칸에서 주사위 한 번으로 갈 수 있는 칸 중에 아직 방문되지 않은 칸에 대하여
				// 그 칸에 가는데 필요한 주사위 횟수를 입력받고, 사다리 혹은 뱀이 있다면
				// 규칙에 맞도록 행동하도록 한다.
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
