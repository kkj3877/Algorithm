package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_10994 {
	
	static class PatternMaker {
		BufferedWriter bw = null;
		
		boolean[][] map = null;
		Integer outlineLength = null;
		
		// PatternMaker 의 생성자. 정수 N 과 출력 스트림 bw 를 매개변수로 갖는다.
		public PatternMaker(int N, BufferedWriter bw) {
			this.outlineLength = 4 * N - 3;
			this.map = new boolean[4 * N - 3][4 * N - 3];
			this.bw = bw;
		}
		
		// 패턴을 생성하는 함수. 최상단 좌측의 좌표값과 한 변의 길이를 입력받는다.
		public void makePattern(int r, int c, int length) {
			// 위아래의 가로줄을 * 로 바꾸기
			for (int i = 0; i < length; i++) map[r][c + i] = true;
			for (int i = 0; i < length; i++) map[r + length - 1][c + i] = true;
			
			// 좌우의 세로줄을 * 로 바꾸기
			for (int i = 0; i < length; i++) map[r + i][c + length - 1] = true;
			for (int i = 0; i < length; i++) map[r + i][c] = true;
			
			// 시작 좌표를 행과 열 각각 +2 시키고 변 의 길이를 감소시킨 후 재귀적으로 호출
			if (length != 1) this.makePattern(r + 2, c + 2, length - 4);
		}
		
		public void printPattern() throws IOException {
			for (int i = 0; i < outlineLength; i++) {
				for (int j = 0; j < outlineLength; j++) {
					if (map[i][j]) bw.write("*");
					else bw.write(" ");
				}
				bw.write("\n");
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N 을 입력받은 후, 패턴 메이커 인스턴스를 생성하고
		// (생성자를 사용해) 가로세로 각각 길이가 2 * N + 1인 배열을 생성한다.
		int N = Integer.parseInt(br.readLine());
		PatternMaker pm = new PatternMaker(N, bw);
		
		// 패턴을 생성한다.
		pm.makePattern(0, 0, 4 * N - 3);
		
		// 패턴을 출력한다.
		pm.printPattern();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
