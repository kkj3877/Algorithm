package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427 {
	
	static int W, H;
	static char[][] building;
	
	static void init(BufferedReader br) throws IOException {
		// ���� ������ �ʺ� W �� ���� H �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// ������ ���¸� �Է¹ޱ� ���� ������ �����Ѵ�.
		building = new char[H][];
		
		// ������ ���¸� �Է¹޴´�.
		// . : �����, # ��, @ ����� ���� ��ġ, * ��
		for (int i = 0; i < H; i++) {
			building[i] = br.readLine().toCharArray();
		}
		
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) System.out.print(building[i][j]);
//			System.out.println();
//		}
	}
	
	static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int solve() throws IOException {
		int time = 0;
		
		// �ҵ��� ��ġ�� ����̰� Ư�� �ð��� �� �� �ִ� ��ġ�� ������ ť�� �����Ѵ�.
		Queue<Pair> queueSG = new LinkedList<Pair>();
		Queue<Pair> queueFire = new LinkedList<Pair>();
		
		// �ҵ��� ��ġ�� ������� ��ġ�� �ľ��Ѵ�.
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (building[i][j] == '*') queueFire.add(new Pair(i, j));
				else if (building[i][j] == '@') queueSG.add(new Pair(i, j));
			}
		}
		
		// �Ұ� ������� ��ġ�� �Űܰ��� �ùķ��̼��� �����Ѵ�.
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		boolean isExit = false;
		
		while (!queueFire.isEmpty() || !queueSG.isEmpty()) {
			time++;
			
			// ���� �Űܰ� ��ġ�鿡 ���� ������ �����Ѵ�.
			Queue<Pair> queueTemp = new LinkedList<Pair>();
			while (!queueFire.isEmpty()) {
				Pair now = queueFire.poll();
				int nowR = now.r;
				int nowC = now.c;
				
				for (int k = 0; k < 4; k++) {
					int nextR = nowR + dr[k];
					if (nextR < 0 || nextR > H - 1) continue;
					
					int nextC = nowC + dc[k];
					if (nextC < 0 || nextC > W - 1) continue;
					
					if (building[nextR][nextC] == '.' || building[nextR][nextC] == '@') {
						building[nextR][nextC] = '*';
						queueTemp.add(new Pair(nextR, nextC));
					}
				}
			}
			queueFire = queueTemp;
			
			queueTemp = new LinkedList<Pair>();
			while (!queueSG.isEmpty()) {
				Pair now = queueSG.poll();
				int nowR = now.r;
				int nowC = now.c;
				
				if (nowR == 0 || nowR == H - 1 || nowC == 0 || nowC == W - 1) {
					isExit = true;
					break;
				}
				
				for (int k = 0; k < 4; k++) {
					int nextR = nowR + dr[k];
					if (nextR < 0 || nextR > H - 1) continue;
					
					int nextC = nowC + dc[k];
					if (nextC < 0 || nextC > W - 1) continue;
					
					if (building[nextR][nextC] == '.') {
						
						building[nextR][nextC] = '@';
						queueTemp.add(new Pair(nextR, nextC));
					}
				}
				
				if (isExit) break;
			}
			if (isExit) break;
			
			queueSG = queueTemp;
		}
		
		if (isExit) return time;
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init(br);
			int answer = solve();
			if (answer == -1 ) bw.write("IMPOSSIBLE\n");
			else bw.write(answer+"\n");			
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
