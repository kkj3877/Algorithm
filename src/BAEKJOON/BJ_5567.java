package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_5567 {
	
	static int N;
	static int M;
	static List<List<Integer>> friendLists;
	
	static void init(BufferedReader br) throws IOException {
		// ������ �� n �� ����Ʈ�� ���� m �� �Է¹޴´�.
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// ģ������ ���� ���¸� ������ ����Ʈ�� �������ش�.
		friendLists = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) friendLists.add(new ArrayList<Integer>());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friendLists.get(a).add(b);
			friendLists.get(b).add(a);
		}
	}
	
	static boolean[] check;
	
	static int solve() {
		int answer = 0;
		
		// Ž���� ���� ������ ������ ��, ������� ģ������ �й��� ��� ���ÿ� �ִ´�.
		// ���ÿ� ��ȥ�Ŀ� �ʴ��� ������ ���� ī��Ʈ�Ѵ�.
		Stack<Integer> stack = new Stack<Integer>();
		check = new boolean[N + 1];
		check[1] = true;
		for (int num : friendLists.get(1)) {
			check[num] = true;
			stack.add(num);
			answer++;
		}
		
		// ���� ���ÿ� ������� ģ������ �������Ƿ�,
		// ���ÿ� �ִ� ģ������ ģ������ ���� ���� �ʴ���� ���� ģ��ģ���� �ʴ��Ѵ�.
		while (!stack.isEmpty()) {
			int num = stack.pop();
			for (int next : friendLists.get(num)) {
				if (check[next]) continue;
				
				check[next] = true;
				answer++;
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
