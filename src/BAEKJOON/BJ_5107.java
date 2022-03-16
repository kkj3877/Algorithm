package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_5107 {
	
	static int N;
	static int[][] arr;
	static int[][] leastCost;
	static Map<String, String> manittoMap;
	
	static boolean init(BufferedReader br) throws IOException {
		// 사람의 명수 N을 계산한다.
		N = Integer.parseInt(br.readLine());
		
		if (N == 0) return false;
		
		// 마니또 관계를 입력받는다.
		manittoMap = new HashMap<String, String>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String from = st.nextToken();
			String to = st.nextToken();
			manittoMap.put(from, to);
		}
		return true;
	}
	
	static int solve() {
		int circuit = 0;
		Set<String> names = manittoMap.keySet();
		Set<String> check = new HashSet<String>();
		for (String name : names) {
			if (check.contains(name)) continue;
			
			circuit++;
			String now = name;
			while (!check.contains(now)) {
				check.add(now);
				now = manittoMap.get(now);
			}
		}
		
		return circuit;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = 0;
		while (init(br)) {
			cnt++;
			int answer = solve();
			bw.write(cnt+" "+answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
