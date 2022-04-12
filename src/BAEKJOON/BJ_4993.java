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
		// 격자의 가로길이 W 와 세로길이 H 를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 마지막 입력 (W == H == 0) 인 경우에는 더이상 입력을 받지 않는다.
		if (H == 0) return;
		
		// 격자를 생성하고 격자 상태를 입력받는다.
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
		// 만약 마지막 입력을 받은 상태라면 문제풀이가 종료되었다는 표시를 위해 -1을 반환한다.
		if (H == 0) return -1;
		
		//// 문제 해결 시작
		
		// 남자가 서있는 위치를 찾는다.
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
		
		// BFS 를 사용하여 남자가 서있는 칸으로부터 이동가능한 칸의 개수를 구한다.
		// . : 검은칸(이동 가능)
		// # : 빨간칸(이동 불가능)
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
