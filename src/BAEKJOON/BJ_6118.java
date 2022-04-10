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
		// 헛간의 개수 N 과 길의 개수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 각 헛간별로 열결된 헛간의 번호를 저장할 리스트를 생성한다.
		connLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) connLists.add(new ArrayList<Integer>());
		
		// 헛간의 연결 상태를 입력받는다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A_i = Integer.parseInt(st.nextToken());
			int B_i = Integer.parseInt(st.nextToken());
			connLists.get(A_i).add(B_i);
			connLists.get(B_i).add(A_i);
		}
	}
	
	static int[] solve() {
		// BFS 를 사용하여 1번 헛간부터 탐색을 시작한다.
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		queue.add(1);
		
		int distance = -1;
		int hideLocation = 1;
		int hideLocationNum = 0;
		while (!queue.isEmpty()) {
			// 숨을 수 있는 거리가 더 먼 헛간을 발견했는지를 체크할 flag 변수
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
