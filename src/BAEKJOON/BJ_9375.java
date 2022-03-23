package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_9375 {
	
	static int N;
	
	static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
	}
	
	static int solve(BufferedReader br) throws IOException {
		int answer = 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			String type = st.nextToken();
			if (map.get(type) == null) map.put(type, 1);
			else map.put(type, map.get(type) + 1);
		}
		
		Set<String> keySet = map.keySet();
		for (String type : keySet) {
			answer *= map.get(type) + 1;
		}
		
		return answer - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init(br);
			int answer = solve(br);
			bw.write(answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
