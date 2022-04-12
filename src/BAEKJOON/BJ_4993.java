package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4993 {
	
	static int W, H;
	static char[][] board;
	
	static void init(BufferedReader br) throws IOException {
		// ������ ���α��� W �� ���α��� H �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// ������ �Է� (W == H == 0) �� ��쿡�� ���̻� �Է��� ���� �ʴ´�.
		if (H == 0) return;
		
		// ���ڸ� �����ϰ� ���� ���¸� �Է¹޴´�.
		board = new char[H][];
		for (int i = 0; i < H; i++) {
			board[i] = br.readLine().toCharArray();
		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int solve() {
		// ���� ������ �Է��� ���� ���¶�� ����Ǯ�̰� ����Ǿ��ٴ� ǥ�ø� ���� -1�� ��ȯ�Ѵ�.
		if (H == 0) return -1;
		
		//// ���� �ذ� ����
		
		// ���ڰ� ���ִ� ��ġ�� ã�´�.
		int startR = -1;
		int startC = -1;
		for (int i = 0; i < H; i++) {
			if (startR != -1) break;
			for (int j = 0; j < W; j++) {
				if (board[i][j] == '@') {
					board[i][j] = '#';
					startR = i;
					startC = j;
					break;
				}
			}
		}
		
		// BFS �� ����Ͽ� ���ڰ� ���ִ� ĭ���κ��� �̵������� ĭ�� ������ ���Ѵ�.
		// . : ����ĭ(�̵� ����)
		// # : ����ĭ(�̵� �Ұ���)
		int answer = 1;
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(startR, startC));
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowR = now.r;
			int nowC = now.c;
			
			for (int k = 0; k < 4; k++) {
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > H - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > W - 1) continue;
				
				if (board[nextR][nextC] == '.') {
					board[nextR][nextC] = '#';
					queue.add(new Pair(nextR, nextC));
					
					answer++;
				}
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			init(br);
			int answer = solve();
			
			if (answer == -1) break;
			else bw.write(answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
