package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_7662 {
	
	static int T;
	static int K;
	static TreeMap<Integer, Integer> doublePQ;
	
	static class Answer {
		int max, min;
		public Answer(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		K = Integer.parseInt(br.readLine());
		
		doublePQ = new TreeMap<Integer, Integer>();
	}
	
	static Answer solve(BufferedReader br) throws IOException {
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("I")) {
				int num = Integer.parseInt(st.nextToken());
				doublePQ.put(num, doublePQ.getOrDefault(num, 0) + 1);
			}
			else {
				if (doublePQ.isEmpty()) continue;
				
				int num = st.nextToken().equals("1") ? doublePQ.lastKey() : doublePQ.firstKey();
				if (doublePQ.put(num, doublePQ.get(num) - 1) == 1) {
					doublePQ.remove(num);
				}
			}
		}
		
		if (doublePQ.isEmpty()) return null;
		else return new Answer(doublePQ.lastKey(), doublePQ.firstKey());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init(br);
			Answer answer = solve(br);
			if (answer == null) bw.write("EMPTY\n");
			else bw.write(answer.max+" "+answer.min+"\n");			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
