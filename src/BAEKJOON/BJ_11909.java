package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11909 {
	
	static int N;
	static int[][] arr;
	static int[][] leastCost;
	
	static void init(BufferedReader br) throws IOException {
		// �迭�� �� ���� ���̸� �Է¹ް�, �迭�� ���¸� �Է¹޴´�.
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		leastCost = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(leastCost[i], Integer.MAX_VALUE);
		leastCost[0][0] = 0;
	}
	
	static int solve() {
		// ���⼺�� Ȯ���ϰ� �����ϹǷ�, ���� for �����ε� �ּҺ���� ����� �� �ִ�.
		// ��Ģ 1) �� �������� �� �� �ִ� ������ ����ȣ Ȥ�� ���ȣ�� �� ū ���̴�.
		// ��Ģ 2) �� �������� �� ���� �������� ������ (������ + 1) �� �ڽ�Ʈ�� ���.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1) break;
				
				// ���� ���� ���� �� ���� ������Ʈ�� ���� ���� �κ��̹Ƿ�, ���̸��� Ȯ���ϸ� ������Ʈ �Ѵ�.
				if (i != N - 1) {
					if (arr[i + 1][j] < arr[i][j]) {
						leastCost[i + 1][j] = leastCost[i][j];
					}
					else {
						leastCost[i + 1][j] = leastCost[i][j] + (arr[i + 1][j] - arr[i][j] + 1);
					}
				}
				
				// ���� ���� �̹� ���� �࿡�� ������Ʈ�� ������ ���̹Ƿ�, [�̹� �˰� �ִ� �ּ� ���]��
				//  [���� ĭ������ �ּҺ�� + ���� ���� ���� ���� �ּҺ��] �� ���� ������ ������Ʈ �Ѵ�.
				if (j != N - 1) {
					if (arr[i][j + 1] < arr[i][j]) {
						leastCost[i][j + 1] = (leastCost[i][j] < leastCost[i][j + 1]) ? leastCost[i][j] : leastCost[i][j + 1];
					}
					else {
						int diff = arr[i][j + 1] - arr[i][j] + 1;
						leastCost[i][j + 1] = (leastCost[i][j] + diff < leastCost[i][j + 1]) ?
								leastCost[i][j] + diff : leastCost[i][j + 1];
					}
				}
			}
		}
		
		return leastCost[N - 1][N - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
