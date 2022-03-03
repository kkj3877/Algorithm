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
		// ����� ��ǻ�͵��� ����Ʈ
		List<Integer> conn = null;
		
		public Computer() {
			conn = new ArrayList<Integer>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ��ǻ���� ���� �Է¹޴´�.
		int N = Integer.parseInt(br.readLine());
		
		// N ���� ��ǻ�� �ν��Ͻ��� �ʱ�ȭ���ش�.
		Computer[] computers = new Computer[N + 1];
		for (int i = 0; i < N + 1; i++) computers[i] = new Computer();
		
		// ������ ���� M �� �Է¹޴´�.
		int M = Integer.parseInt(br.readLine());
		
		// ���� ���¸� �Է� �޴´�.
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			computers[A].conn.add(B);
			computers[B].conn.add(A);
		}
		
		// DFS �� ����� 1���� ���� �����Ǵ� ��ǻ���� ������ ����Ѵ�.
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		
		List<Integer> stack = new ArrayList<Integer>();
		stack.add(1);
		
		int infested = 0;
		// ������ �� �� ���� Ž���� �����Ѵ�.
		while (!stack.isEmpty()) {
			int num = stack.remove(stack.size() - 1);
			Computer now = computers[num];
			
			// ���� Ȯ���ϴ� ��ǻ�Ϳ� ����� ��ǻ�͵��� üũ�Ѵ�.
			// ���� ���� üũ���� ���� ����� ��ǻ�Ͱ� �ִٸ�
			// üũ ���¸� ������Ʈ ���ְ� ���ÿ� �ش� ��ǻ�� ��ȣ�� �߰��Ѵ�.
			for (int next : now.conn) {
				if (visited[next]) continue;
				else {
					visited[next] = true;
					stack.add(next);
					infested++;
				}
			}
		}
		
		// ������ ��ǻ�� ���� ������ش�.
		System.out.println(infested);
		
		bw.flush();
		bw.close();
		br.close();
	}
}

