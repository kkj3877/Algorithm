package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class BJ_2606 {
	
	public static class Computer {
		int num;
		// 연결된 컴퓨터들의 리스트
		List<Integer> conn = null;
		
		public Computer() {
			conn = new ArrayList<Integer>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 컴퓨터의 수를 입력받는다.
		int N = Integer.parseInt(br.readLine());
		
		// N 대의 컴퓨터 인스턴스를 초기화해준다.
		Computer[] computers = new Computer[N + 1];
		for (int i = 0; i < N + 1; i++) computers[i] = new Computer();
		
		// 연결의 개수 M 을 입력받는다.
		int M = Integer.parseInt(br.readLine());
		
		// 연결 상태를 입력 받는다.
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			computers[A].conn.add(B);
			computers[B].conn.add(A);
		}
		
		// DFS 를 사용해 1번을 통해 감염되는 컴퓨터의 개수를 계산한다.
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		
		List<Integer> stack = new ArrayList<Integer>();
		stack.add(1);
		
		int infested = 0;
		// 스택이 빌 때 까지 탐색을 진행한다.
		while (!stack.isEmpty()) {
			int num = stack.remove(stack.size() - 1);
			Computer now = computers[num];
			
			// 지금 확인하는 컴퓨터와 연결된 컴퓨터들을 체크한다.
			// 만약 아직 체크하지 않은 연결된 컴퓨터가 있다면
			// 체크 상태를 업데이트 해주고 스택에 해당 컴퓨터 번호를 추가한다.
			for (int next : now.conn) {
				if (visited[next]) continue;
				else {
					visited[next] = true;
					stack.add(next);
					infested++;
				}
			}
		}
		
		// 감염된 컴퓨터 개수 출력해준다.
		System.out.println(infested);
		
		bw.flush();
		bw.close();
		br.close();
	}
}

