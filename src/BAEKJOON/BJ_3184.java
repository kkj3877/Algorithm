package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_3184 {
	
	public static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// R�� C �� �Է¹ް� R * C ũ���� �츮�� �����.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][];
		
		// �츮�� ���¸� �Է¹޴´�.
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
		
		// �츮�� ���������� �ѷ�����.
		boolean[][] check = new boolean[R][C];
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		int liveSheep = 0;
		int liveWolf = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// ���� ���� ĭ�� �̹� üũ�� ĭ�̶�� ����ĭ���� �Ѿ��.
				if (check[i][j]) continue;
				
				// ���� ���� ĭ�� ��Ÿ����� üũ ǥ�ø� �ϰ� ����ĭ���� �Ѿ��.
				if (map[i][j] == '#') {
					check[i][j] = true;
					continue;
				}
				
				// ���� ĭ�� ���̳� ���밡 �ִٸ� Ž���� �����Ѵ�.
				if (map[i][j] == 'o' || map[i][j] == 'v') {
					int sheep = 0;
					int wolf = 0;
					
					if (map[i][j] == 'o') sheep++;
					else if (map[i][j] == 'v') wolf++;
					
					List<Pair> queue = new LinkedList<Pair>();
					queue.add(new Pair(i, j));
					check[i][j] = true;
					
					while (!queue.isEmpty()) {
						Pair now = queue.remove(0);
						int nowR = now.r;
						int nowC = now.c;
						
						for (int k = 0; k < 4; k++) {
							int nextR = nowR + dr[k];
							if (nextR < 0 || nextR > R - 1) continue;
							
							int nextC = nowC + dc[k];
							if (nextC < 0 || nextC > C - 1) continue;
							
							if (check[nextR][nextC]) continue;
							
							if (map[nextR][nextC] == '#') {
								check[nextR][nextC] = true;
								continue;
							}
							
							if (map[nextR][nextC] == 'o') sheep++;
							else if (map[nextR][nextC] == 'v') wolf++;
							
							check[nextR][nextC] = true;
							queue.add(new Pair(nextR, nextC));
						}
					}
					if (sheep > wolf) liveSheep += sheep;
					else liveWolf += wolf;
				}
			}
		}
		
		bw.write(liveSheep+" "+liveWolf+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
