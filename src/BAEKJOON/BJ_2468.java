package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468 {
	
	// 행과 열의 개수 N, N * N 사이즈의 높이 정보를 저장할 배열
	static int N;
	static int[][] map;
	
	// BFS를 위한 dr, dc 배열
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 }; 
	
	// 큐에 들어갈 노드
	static class Pair {
		int r, c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// 특정 높이만큼 물에 잠길 경우 안전 영역의 개수를 구하여 반환하는 함수
	static int countSafePlace(int height) {
		int safe = 0;
		
		boolean[][] check = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check[i][j]) continue;
				
				if (map[i][j] > height) {
					safe++;
					
					Queue<Pair> queue = new LinkedList<Pair>();
					
					check[i][j] = true;
					queue.add(new Pair(i, j));
					while (!queue.isEmpty()) {
						Pair now = queue.poll();
						int nowR = now.r;
						int nowC = now.c;
						
						for (int k = 0; k < 4; k++) {
							int nextR = nowR + dr[k];
							if (nextR < 0 || nextR > N - 1) continue;
							
							int nextC = nowC + dc[k];
							if (nextC < 0 || nextC > N - 1) continue;
							
							if (check[nextR][nextC]) continue;
							
							check[nextR][nextC] = true;
							if (map[nextR][nextC] > height) queue.add(new Pair(nextR, nextC));
						}
					}
				}
			}
		}
		
		return safe;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 행과 열의 길이인 N을 입력받는다.
		N = Integer.parseInt(br.readLine());
		
		// N * N 사이즈의 높이를 저장할 배열을 선언한다.
		map = new int[N][N];
		
		// 특정 높이의 섬이 있는지를 체크할 배열을 선언한다.
		boolean[] check = new boolean[101];
		
		// 지역의 높이 상태를 입력받는다.
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int height = Integer.parseInt(st.nextToken());
				check[height] = true;
				map[i][j] = height;
			}
		}
		
		// 존재하는 높이들을 대상으로 잠기는 높이를 변경하며 안전한 영역의 개수를 계산한다.
		int maxSafe = 1;
		for (int h = 1; h < 101; h++) {
			if (check[h]) {
				int safe = countSafePlace(h);
				if (safe > maxSafe) maxSafe = safe;
			}
		}
		
		bw.write(maxSafe+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
