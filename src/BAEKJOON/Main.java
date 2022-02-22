package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	// 맵의 포인터와 크기, 시작좌표를 입력받아 재귀적으로 패턴 생성
	static void makePattern(boolean[][] map, int N, int r, int c) {
		// 현재 사이즈가 1 이면 (r, c) 좌표를 true 로 바꿈(나중에 * 로 처리됨)
		if (N == 1) {
			map[r][c] = true;
			return;
		}
		
		// 현재 사이즈의 1/3 을 갭으로 맵을 관찰
		int gap = N / 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// 만약 현재 맵의 중간 줄의 중간 구역이면 패스함
				if (i == 1 && j == 1) continue;
				
				// 중간 줄의 중간 칸이 아니라면 해당 구역에 대해 패턴 생성 재귀 호출
				makePattern(map, gap, r + gap * i, c + gap * j);
			}
		}
	}
	
	static boolean[][] map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 지도의 크기를 입력받아 N * N 맵을 생성한다.
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		// false 로 초기화 되어 있는 맵에서 별이 찍힐 자리만 true 로 바꾸는 함수를 호출
		// 해당 함수는 재귀적으로 별이 찍힐 좌표에 해당하는 index만 true 로 바꾼다.
		makePattern(map, N, 0, 0);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// true 로 바뀐 칸은 * 이 찍힐 자리이며,
				// true 로 바뀌지 못한(false) 칸은 공백이 찍힐 자리이다.
				if (map[i][j]) bw.write("*");
				else bw.write(" ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
