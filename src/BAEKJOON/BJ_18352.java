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
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_18352 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ������ ���� N, ������ ���� M, �Ÿ� ���� K, ��� ������ ��ȣ X�� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// ���� ���¸� ������ List �� �����Ѵ�.
		List<List<Integer>> Lists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) Lists.add(new ArrayList<Integer>());
		
		// ���ð��� ������¸� �Է¹޴´�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			Lists.get(src).add(dest);
		}
		
		// üũ ���θ� ����� �迭�� �����Ѵ�.
		int[] distances = new int[N + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		// BFS �� ����Ͽ� ������ �Ǵ� ���õ��� ���Ѵ�.
		// �� ��, TreeSet �� ����Ͽ� �ڵ����� ������ �ǵ��� �Ѵ�.
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> answer = new TreeSet<Integer>();
		
		distances[X] = 0;
		queue.add(X);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int dist = distances[now];
			
			List<Integer> conn = Lists.get(now);
			for (int next : conn) {
				// ���� �̹� �ִܰŸ� ����� ���� ���ö�� �н��Ѵ�.
				if (distances[next] != Integer.MAX_VALUE) continue;
				
				distances[next] = dist + 1;
				if (dist == K - 1) answer.add(next);
				else queue.add(next);
			}
		}
		
		// TreeSet �� ����� �����͸� ���������� ����Ͽ� ������ ����Ѵ�.
		if (answer.size() == 0) bw.write("-1\n");
		else {
			for (int i : answer) bw.write(i+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
