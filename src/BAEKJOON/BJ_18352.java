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
		
		// 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// 연결 상태를 저장할 List 를 선언한다.
		List<List<Integer>> Lists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) Lists.add(new ArrayList<Integer>());
		
		// 도시간의 연결상태를 입력받는다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			Lists.get(src).add(dest);
		}
		
		// 체크 여부를 기록할 배열을 선언한다.
		int[] distances = new int[N + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		// BFS 를 사용하여 정답이 되는 도시들을 구한다.
		// 이 때, TreeSet 을 사용하여 자동으로 정렬이 되도록 한다.
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> answer = new TreeSet<Integer>();
		
		distances[X] = 0;
		queue.add(X);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int dist = distances[now];
			
			List<Integer> conn = Lists.get(now);
			for (int next : conn) {
				// 만약 이미 최단거리 계산이 끝난 도시라면 패스한다.
				if (distances[next] != Integer.MAX_VALUE) continue;
				
				distances[next] = dist + 1;
				if (dist == K - 1) answer.add(next);
				else queue.add(next);
			}
		}
		
		// TreeSet 에 저장된 데이터를 순차적으로 출력하여 정답을 출력한다.
		if (answer.size() == 0) bw.write("-1\n");
		else {
			for (int i : answer) bw.write(i+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
