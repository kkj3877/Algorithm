package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_24445 {
	
	static int N, M, R;
	static List<Set<Integer>> connSetList;
	static int[] answer;
	
	static void init(BufferedReader br) throws IOException {
		// ������ �� N, ������ �� M, ���� ���� R �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// �� �������� ����� ������ ��ȣ�� ������ Set �� �����Ѵ�.
		// �� ��, �������� �湮�� ���� TreeSet(reverseOrder) �� ����Ѵ�.
		connSetList = new ArrayList<Set<Integer>>();
		for (int i = 0; i < N + 1; i++) connSetList.add(new TreeSet<Integer>(Collections.reverseOrder()));
		
		// �������� ������ �Է¹޴´�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			connSetList.get(u).add(v);
			connSetList.get(v).add(u);
		}
		
		// ������ ������ �迭�� �����Ѵ�.
		answer = new int[N + 1];
	}
	
	static boolean[] visited;
	
	static void solve() {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited = new boolean[N + 1];
		int idx = 1;
		
		queue.add(R);
		visited[R] = true;
		
		while (!queue.isEmpty()) {
			int nowNum = queue.poll();
			answer[nowNum] = idx++;
			
			Set<Integer> connSet = connSetList.get(nowNum);
			for (int next : connSet) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
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
