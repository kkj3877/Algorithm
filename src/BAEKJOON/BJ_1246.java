package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1246 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// �ް��� ���� N �� ���� �� M �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// ���� Pi �� �Է¹޴´�.
		int[] Prices = new int[M];
		for (int i = 0; i < M; i++) Prices[i] = Integer.parseInt(br.readLine());
		
		// ������ Pi �� �����Ѵ�.
		Arrays.sort(Prices);
		
		// ���� ���ݺ��� �ȸ� ������ üũ�Ѵ�.
		int maxMoney = 0;
		int danga = 0;
		for (int i = 0; i < M; i++) {
			// �ǸŰ� = i ��° ����
			int sellPrice = Prices[i];
			
			// �� �� �ִ� ��� ���� üũ�Ѵ�.
			int canBuy = M - i;
			
			// ����, �ް��� �� �� �ִ� ������� �ް� ���� ���ٸ�?
			// �׷��ϱ� ����, �ް� ��(N) < �� �� �ִ� ��� ��(canBuy)
			// �� �� �ִ� ��� �� = �ް� ��
			if (N < canBuy) canBuy = N;
			
			// �Ǹ� ���� ��� : �Ǹ� ���� = �ܰ� X ��� ��� ��
			int totalMoney = sellPrice * canBuy;
			
			// ���������� �ִ� ���Ͱ� �̹� �ܰ������� ������ ���Ѵ�.
			// �̹� ������ �� ���ٸ�, �ܰ��� ���� �ܰ��� �����Ѵ�.
			if (maxMoney < totalMoney) {
				maxMoney = totalMoney;
				danga = sellPrice;
			}
		}
		
		// ���� ������ ���� ���� �ܰ��� �ִ� ������ ����Ѵ�.
		bw.write(danga+" "+maxMoney+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
