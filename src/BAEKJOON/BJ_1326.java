package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1326 {
	
	static class Jump {
		int cnt;
		int num;
		
		public Jump(int cnt, int num) {
			this.cnt = cnt;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ¡�˴ٸ��� ���� N �� �Է¹޴´�.
		int N = Integer.parseInt(br.readLine());
		int[] bridge = new int[N + 1];
		
		// ¡�˴ٸ��� ������ ���ڸ� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) bridge[i] = Integer.parseInt(st.nextToken());
		
		// �������� a �� ��ǥ���� b �� �Է¹޴´�.
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int minCnt = -1;
		// ���� ���������� ��ǥ������ ���ٸ� �ּ� ���� Ƚ���� 0�̴�.
		if (a == b) minCnt = 0;
		else {
			// BFS �� ����Ͽ� ��ǥ�������� �ɸ��� �ּ� ���� Ƚ���� ���Ѵ�.
			Queue<Jump> queue = new LinkedList<Jump>();
			boolean[] checked = new boolean[N + 1];
			queue.add(new Jump(0, a));
			checked[a] = true;
			
			while (!queue.isEmpty()) {
				Jump now = queue.poll();
				int cnt = now.cnt;
				int nowNum = now.num;
				
				int jumpLength = bridge[nowNum];
				// ���� �̹� ĭ�� 0 �̶�� ���̻� ������ �� �����Ƿ� ���� ¡�˴ٸ��� ����.
				if (jumpLength == 0) continue;
				
				// ���� ���� �������� �ٷ� ���� �� �ִ� ��쿡�� Ž���� �����Ѵ�.
				if (jumpLength == 1) {
					minCnt = cnt + 1;
					break;
				}
				if ((b - nowNum) % jumpLength == 0) {
					minCnt = cnt + 1;
					break;
				}
				
				// �� ���� ������ ������ �ʴ� ��쿡�� Ž���� �߰��� �Ѵ�.
				//// ���� �������� Ž��
				for (int next = nowNum + jumpLength; next <= N; next += jumpLength) {
					if (checked[next]) continue;
					
					checked[next] = true;
					queue.add(new Jump(cnt + 1, next));
				}
				//// ���� �������� Ž��
				for (int next = nowNum - jumpLength; next >= 0; next -= jumpLength) {
					if (checked[next]) continue;
					
					checked[next] = true;
					queue.add(new Jump(cnt + 1, next));
				}
			}
			
			bw.write(minCnt+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
