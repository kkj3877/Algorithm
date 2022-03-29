package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_24446 {
	
	static int N, M, R;
	static List<List<Integer>> connLists;
	static int[] answer;
	
	static void init(BufferedReader br) throws IOException {
		// ������ �� N, ������ �� M, ���� ���� R �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// �� �������� ����� ������ ��ȣ�� ������ List �� �����Ѵ�.
		
		connLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) connLists.add(new ArrayList<Integer>());
		
		// �������� ������ �Է¹޴´�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			connLists.get(u).add(v);
			connLists.get(v).add(u);
		}
		
		// ������ ������ �迭�� �����Ѵ�.
		answer = new int[N + 1];
		Arrays.fill(answer, -1);
	}
	
	static void solve() {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(R);
		answer[R] = 0;
		
		while (!queue.isEmpty()) {
			int nowNum = queue.poll();
			int nextDistance = answer[nowNum] + 1;
			
			List<Integer> connList = connLists.get(nowNum);
			for (int nextNum : connList) {
				if (answer[nextNum] == -1) {
					answer[nextNum] = nextDistance;
					queue.add(nextNum);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		solve();
		for (int i = 1; i < N + 1; i++) bw.write(answer[i]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
