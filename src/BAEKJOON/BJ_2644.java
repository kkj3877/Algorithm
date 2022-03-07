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

public class BJ_2644 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> connLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) connLists.add(new ArrayList<Integer>());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			connLists.get(a).add(b);
			connLists.get(b).add(a);
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] checked = new boolean[N + 1];
		int[] relations = new int[N + 1];
		Arrays.fill(relations, Integer.MAX_VALUE);
		
		queue.add(x);
		checked[x] = true;
		relations[x] = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int relation = relations[now];
			
			List<Integer> connList = connLists.get(now);
			for (int next : connList ) {
				if (checked[next]) continue;
				
				relations[next] = relation + 1;
				checked[next] = true;
				queue.add(next);
			}
		}
		
		if (relations[y] == Integer.MAX_VALUE) bw.write("-1\n");
		else bw.write(relations[y]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
