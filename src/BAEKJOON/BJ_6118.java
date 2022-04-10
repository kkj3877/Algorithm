package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6118 {
	
	static int N, M;
	static List<List<Integer>> connLists;
	
	static void init(BufferedReader br) throws IOException {
		// �갣�� ���� N �� ���� ���� M �� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// �� �갣���� ����� �갣�� ��ȣ�� ������ ����Ʈ�� �����Ѵ�.
		connLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) connLists.add(new ArrayList<Integer>());
		
		// �갣�� ���� ���¸� �Է¹޴´�.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A_i = Integer.parseInt(st.nextToken());
			int B_i = Integer.parseInt(st.nextToken());
			connLists.get(A_i).add(B_i);
			connLists.get(B_i).add(A_i);
		}
	}
	
	static int[] solve() {
		// BFS �� ����Ͽ� 1�� �갣���� Ž���� �����Ѵ�.
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		queue.add(1);
		
		int distance = -1;
		int hideLocation = 1;
		int hideLocationNum = 0;
		while (!queue.isEmpty()) {
			// ���� �� �ִ� �Ÿ��� �� �� �갣�� �߰��ߴ����� üũ�� flag ����
			boolean flag = false;
			distance++;
			
			Queue<Integer> tempQueue = new LinkedList<Integer>();
			while (!queue.isEmpty()) {
				int now = queue.poll();
				for (int next : connLists.get(now)) {
					if (!visited[next]) {
						visited[next] = true;
						tempQueue.add(next);
						
						if (!flag) {
							flag = true;
							hideLocation = next;
							hideLocationNum = 1;
						}
						else {
							if (hideLocation > next) hideLocation = next;
							hideLocationNum++;
						}
					}
				}
			}
			
			queue = tempQueue;
		}
		
		
		int[] answer = new int[3];
		answer[0] = hideLocation;
		answer[1] = distance;
		answer[2] = hideLocationNum;
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int[] answer = solve();
		for (int i = 0; i < 3; i++) {
			if (i == 2) bw.write(answer[i]+"\n");
			else bw.write(answer[i]+" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
