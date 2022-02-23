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
		
		// PatternMaker �� ������. ���� N �� ��� ��Ʈ�� bw �� �Ű������� ���´�.
		public PatternMaker(int N, BufferedWriter bw) {
			this.outlineLength = 4 * N - 3;
			this.map = new boolean[4 * N - 3][4 * N - 3];
			this.bw = bw;
		}
		
		// ������ �����ϴ� �Լ�. �ֻ�� ������ ��ǥ���� �� ���� ���̸� �Է¹޴´�.
		public void makePattern(int r, int c, int length) {
			// ���Ʒ��� �������� * �� �ٲٱ�
			for (int i = 0; i < length; i++) map[r][c + i] = true;
			for (int i = 0; i < length; i++) map[r + length - 1][c + i] = true;
			
			// �¿��� �������� * �� �ٲٱ�
			for (int i = 0; i < length; i++) map[r + i][c + length - 1] = true;
			for (int i = 0; i < length; i++) map[r + i][c] = true;
			
			// ���� ��ǥ�� ��� �� ���� +2 ��Ű�� �� �� ���̸� ���ҽ�Ų �� ��������� ȣ��
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
		
		// N �� �Է¹��� ��, ���� ����Ŀ �ν��Ͻ��� �����ϰ�
		// (�����ڸ� �����) ���μ��� ���� ���̰� 2 * N + 1�� �迭�� �����Ѵ�.
		int N = Integer.parseInt(br.readLine());
		PatternMaker pm = new PatternMaker(N, bw);
		
		// ������ �����Ѵ�.
		pm.makePattern(0, 0, 4 * N - 3);
		
		// ������ ����Ѵ�.
		pm.printPattern();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
