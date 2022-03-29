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
		// 정점의 수 N, 간선의 수 M, 시작 정점 R 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 각 정점에서 연결된 노드들의 번호를 저장할 Set 를 선언한다.
		// 이 때, 내림차순 방문을 위해 TreeSet(reverseOrder) 를 사용한다.
		connSetList = new ArrayList<Set<Integer>>();
		for (int i = 0; i < N + 1; i++) connSetList.add(new TreeSet<Integer>(Collections.reverseOrder()));
		
		// 간선들의 정보를 입력받는다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			connSetList.get(u).add(v);
			connSetList.get(v).add(u);
		}
		
		// 정답을 저장할 배열을 선언한다.
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
