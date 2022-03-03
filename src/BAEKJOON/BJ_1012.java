package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1012 {
	
	public static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테스트 케이스의 개수 T 를 입력받고 T 번의 횟수를 반복한다.
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 세로 N, 가로 M 의 땅을 만들고, 배추의 위치를 입력받는다.
			int[][] map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[c][r] = 1;
			}
			
			// 땅을 둘러보며 필요한 지렁이 마릿수를 계산한다.
			int warm = 0;
			int[] dr = { -1, 0, 1, 0 };
			int[] dc = { 0, 1, 0, -1 };
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 만약 특정 칸에 배추가 있다면 지렁이를 놓고 탐색을 시작한다.
					if (map[i][j] == 1) {
						warm++;
						map[i][j] = 0;
						List<Pair> stack = new ArrayList<Pair>();
						stack.add(new Pair(i, j));
						while (!stack.isEmpty()) {
							Pair now = stack.remove(stack.size() - 1);
							int nowR = now.r;
							int nowC = now.c;
							
							// 현재 칸을 기준으로 4방향을 둘러본다.
							for (int k = 0; k < 4; k++) {
								// 다음 행이 out of range 인 경우 무시한다.
								int nextR = nowR + dr[k];
								if (nextR < 0 || nextR > N - 1) continue;
								
								// 다음 열이 out of range 인 경우 무시한다.
								int nextC = nowC + dc[k];
								if (nextC < 0 || nextC > M - 1) continue;
								
								if (map[nextR][nextC] == 1) {
									map[nextR][nextC] = 0;
									stack.add(new Pair(nextR, nextC));
								}
							}
						}
					}
				}
			}
			
			bw.write(warm+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
