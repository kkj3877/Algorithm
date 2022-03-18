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

public class BJ_6593 {
	
	static int L, R, C;
	static int SL, SR, SC;
	static int EL, ER, EC;
	
	static int[][][] building;
	
	static boolean isTestCase(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		if (L == 0) return false;
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		return true;
	}
	
	static void init(BufferedReader br) throws IOException {
		building = new int[L][R][C];
		
		for (int l = 0; l < L; l++) {
			for (int r = 0; r < R; r++) {
				char[] line = br.readLine().toCharArray();
				for (int c = 0; c < C; c++) {
					if (line[c] == '#') building[l][r][c] = -1;
					else {
						if (line[c] == 'S') {
							SL = l;
							SR = r;
							SC = c;
						}
						else if (line[c] == 'E') {
							EL = l;
							ER = r;
							EC = c;
						}
					}
				}
			}
			br.readLine();
		}
		
//		System.out.println("Start Location : (" + SL +", "+ SR +", "+ SC+")");
//		System.out.println("End Location : (" + EL +", "+ ER +", "+ EC+")");
		
	}
	
	static class Pair {
		int l, r, c;
		public Pair(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}
	
	static int solve() {
		building[SL][SR][SC] = 1;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(SL, SR, SC));
		
		int[] dl = { -1, 0, 0, 0, 0, 1 };
		int[] dr = { 0, -1, 0, 1, 0, 0 };
		int[] dc = { 0, 0, 1, 0, -1, 0 };
		
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int nowL = now.l;
			int nowR = now.r;
			int nowC = now.c;
			int nextTime = building[nowL][nowR][nowC] + 1;
			
//			System.out.println("now Location = ("+nowL+", "+nowR+", "+nowL+")");
			
			for (int k = 0; k < 6; k++) {
				int nextL = nowL + dl[k];
				if (nextL < 0 || nextL > L - 1) continue;
				
				int nextR = nowR + dr[k];
				if (nextR < 0 || nextR > R - 1) continue;
				
				int nextC = nowC + dc[k];
				if (nextC < 0 || nextC > C - 1) continue;
				
				if (building[nextL][nextR][nextC] == 0) {
					building[nextL][nextR][nextC] = nextTime;
					queue.add(new Pair(nextL, nextR, nextC));
				}
			}
		}
		
		if (building[EL][ER][EC] == 0) return -1;
		else return building[EL][ER][EC] - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (isTestCase(br)) {
//			System.out.println("L, R, C = "+L+","+R+","+C);
			init(br);
			int answer = solve();
			if (answer == -1) bw.write("Trapped!\n");
			else bw.write("Escaped in "+answer+" minute(s).\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
