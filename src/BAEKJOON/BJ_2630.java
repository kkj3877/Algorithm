package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2630 {
	
	static int N;
	static int[][] paper;
	
	static int whitePaper = 0;
	static int bluePaper = 0;
	
	static int getColor(int r, int c, int size) {
		if (size == 1) return paper[r][c];
		
		int gap = size / 2;
		int[] colors = new int[4];
		int white = 0;
		int blue = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				colors[i] = getColor(r + i * gap, c + j * gap, gap);
				if (colors[i] == 0) white++;
				else if (colors[i] == 1) blue++;
			}
		}
		
		if (white == 4) {
			if (size == N) whitePaper++;
			return 0;
		}
			
		else if (blue == 4) {
			if (size == N) bluePaper++;
			return 1;
		}
		else {
			whitePaper += white;
			bluePaper += blue;
			return -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 종이의 크기 N 를 입력받아 종이의 상태를 입력받을 배열을 선언하고
		// 종이의 상태를 입력받는다.
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		getColor(0, 0, N);
		
		bw.write(whitePaper+"\n");
		bw.write(bluePaper+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
