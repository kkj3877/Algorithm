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
		// 동기의 수 n 과 리스트의 길이 m 을 입력받는다.
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 친구들의 연결 상태를 저장할 리스트를 구성해준다.
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
		
		// 탐색을 위해 스택을 선언한 후, 상근이의 친구들의 학번을 모두 스택에 넣는다.
		// 동시에 결혼식에 초대할 동기의 수를 카운트한다.
		Stack<Integer> stack = new Stack<Integer>();
		check = new boolean[N + 1];
		check[1] = true;
		for (int num : friendLists.get(1)) {
			check[num] = true;
			stack.add(num);
			answer++;
		}
		
		// 현재 스택에 상근이의 친구들이 들어가있으므로,
		// 스택에 있는 친구들의 친구들을 보며 아직 초대되지 않은 친구친구는 초대한다.
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
