package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_10026 {
	
	static int N;
	static char[][] map;
	
	static class AreaSet {
		int normal, colorWeak;
		
		public AreaSet(int normal, int colorWeak) {
			this.normal = normal;
			this.colorWeak = colorWeak;
		}
	}
	
	static class Pair {
		int r, c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static AreaSet calcArea() {
		int normal = 0;
		int colorWeak = 0;
		
		boolean[][] checkNormal = new boolean[N][N];
		boolean[][] checkWeak = new boolean[N][N];
		Queue<Pair> queueNormal = new LinkedList<Pair>();
		Queue<Pair> queueWeak = new LinkedList<Pair>();
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (checkNormal[i][j]) continue;
//				System.out.println("r, c = "+i+","+j);
				char color = map[i][j];
				checkNormal[i][j] = true;
				
				if (color == 'B') {
					normal++;
					colorWeak++;
					checkWeak[i][j] = true;
					
					queueNormal.add(new Pair(i, j));
					
					while (!queueNormal.isEmpty()) {
						Pair now = queueNormal.poll();
						int nowR = now.r;
						int nowC = now.c;
						
						for (int k = 0; k < 4; k++) {
							int nextR = nowR + dr[k];
							if (nextR < 0 || nextR > N - 1) continue;
							
							int nextC = nowC + dc[k];
							if (nextC < 0 || nextC > N - 1) continue;
							
							if (checkNormal[nextR][nextC]) continue;
							
							if (map[nextR][nextC] == 'B') {
								checkNormal[nextR][nextC] = true;
								checkWeak[nextR][nextC] = true;
								queueNormal.add(new Pair(nextR, nextC));
							}
						}
					}
				}
				else {
					normal++;
					if (!checkWeak[i][j]) colorWeak++;
					checkWeak[i][j] = true;
					
					queueNormal.add(new Pair(i, j));
					
					while (!queueNormal.isEmpty()) {
						Pair now = queueNormal.poll();
						int nowR = now.r;
						int nowC = now.c;
						
						for (int k = 0; k < 4; k++) {
							int nextR = nowR + dr[k];
							if (nextR < 0 || nextR > N - 1) continue;
							
							int nextC = nowC + dc[k];
							if (nextC < 0 || nextC > N - 1) continue;
							
							if (checkNormal[nextR][nextC]) continue;
							
							if (map[nextR][nextC] == color) {
								checkNormal[nextR][nextC] = true;
								checkWeak[nextR][nextC] = true;
								queueNormal.add(new Pair(nextR, nextC));
							}
							else if (map[nextR][nextC] != 'B' && !checkWeak[nextR][nextC]) {
								checkWeak[nextR][nextC] = true;
								queueWeak.add(new Pair(nextR, nextC));
							}
						}
					}
					
					if (!queueWeak.isEmpty()) {
						while (!queueWeak.isEmpty()) {
							Pair now = queueWeak.poll();
							int nowR = now.r;
							int nowC = now.c;
							
							for (int k = 0; k < 4; k++) {
								int nextR = nowR + dr[k];
								if (nextR < 0 || nextR > N - 1) continue;
								
								int nextC = nowC + dc[k];
								if (nextC < 0 || nextC > N - 1) continue;
								
								if (checkWeak[nextR][nextC]) continue;
								
								if (map[nextR][nextC] != 'B') {
									checkWeak[nextR][nextC] = true;
									queueWeak.add(new Pair(nextR, nextC));
								}
							}
						}
					}
				}
			}
		}
		
		return new AreaSet(normal, colorWeak);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 그리드의 한 변의 길이를 입력받고 N x N 그리드를 만든다.
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			map[i] = line;
		}
		
		AreaSet areaSet = calcArea();
		bw.write(areaSet.normal+" "+areaSet.colorWeak+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
