package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_13565 {
	
	static int M, N;
	
	static int[][] fiber;
	static boolean[][] check;
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static boolean DFS(int r, int c) {
//		System.out.println("DFS("+r+","+c+")");
		boolean isFlow = false;
		
		for (int k = 0; k < 4; k++) {
			int nextR = r + dr[k];
			if (nextR < 0 || nextR > M - 1) continue;
			
			int nextC = c + dc[k];
			if (nextC < 0 || nextC > N - 1) continue;
			
			if (check[nextR][nextC]) continue;
			check[nextR][nextC] = true;
			
			if (fiber[nextR][nextC] == '0') {
				if (nextR == M - 1) {
					isFlow = true;
					break;
				}
				isFlow = DFS(nextR, nextC);
				if (isFlow) break;
			}
		}
		
		return isFlow;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 섬유의 세로, 가로 크기 M, N 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 섬유의 상태를 입력받는다.
		fiber = new int[M][N];
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) fiber[i][j] = line.charAt(j);
		}
		
		// 가장 윗줄만을 시작점으로 하여 DFS 를 수행한다.
		// 한 번이라도 가장 아랫줄에 닿으면 탐색을 종료하고 전류가 전달되는 것으로 생각한다.
		boolean isFlow = false;
		check = new boolean[M][N];
		for (int j = 0; j < N; j++) {
			if (isFlow) break;
			if (check[0][j]) continue;
			
			if (fiber[0][j] == '0') {
				check[0][j] = true;
				isFlow = DFS(0, j);
			}
		}
		
		if (isFlow) bw.write("YES\n");
		else bw.write("NO\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
