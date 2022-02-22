package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	// ���� �����Ϳ� ũ��, ������ǥ�� �Է¹޾� ��������� ���� ����
	static void makePattern(boolean[][] map, int N, int r, int c) {
		// ���� ����� 1 �̸� (r, c) ��ǥ�� true �� �ٲ�(���߿� * �� ó����)
		if (N == 1) {
			map[r][c] = true;
			return;
		}
		
		// ���� �������� 1/3 �� ������ ���� ����
		int gap = N / 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// ���� ���� ���� �߰� ���� �߰� �����̸� �н���
				if (i == 1 && j == 1) continue;
				
				// �߰� ���� �߰� ĭ�� �ƴ϶�� �ش� ������ ���� ���� ���� ��� ȣ��
				makePattern(map, gap, r + gap * i, c + gap * j);
			}
		}
	}
	
	static boolean[][] map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ������ ũ�⸦ �Է¹޾� N * N ���� �����Ѵ�.
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		// false �� �ʱ�ȭ �Ǿ� �ִ� �ʿ��� ���� ���� �ڸ��� true �� �ٲٴ� �Լ��� ȣ��
		// �ش� �Լ��� ��������� ���� ���� ��ǥ�� �ش��ϴ� index�� true �� �ٲ۴�.
		makePattern(map, N, 0, 0);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// true �� �ٲ� ĭ�� * �� ���� �ڸ��̸�,
				// true �� �ٲ��� ����(false) ĭ�� ������ ���� �ڸ��̴�.
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
