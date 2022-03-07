package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1326 {
	
	static class Jump {
		int cnt;
		int num;
		
		public Jump(int cnt, int num) {
			this.cnt = cnt;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 징검다리의 개수 N 을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		int[] bridge = new int[N + 1];
		
		// 징검다리에 쓰여진 숫자를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) bridge[i] = Integer.parseInt(st.nextToken());
		
		// 시작지점 a 와 목표지점 b 를 입력받는다.
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int minCnt = -1;
		// 만약 시작지점과 목표지점이 같다면 최소 점프 횟수는 0이다.
		if (a == b) minCnt = 0;
		else {
			// BFS 를 사용하여 목표지점까지 걸리는 최소 점프 횟수를 구한다.
			Queue<Jump> queue = new LinkedList<Jump>();
			boolean[] checked = new boolean[N + 1];
			queue.add(new Jump(0, a));
			checked[a] = true;
			
			while (!queue.isEmpty()) {
				Jump now = queue.poll();
				int cnt = now.cnt;
				int nowNum = now.num;
				
				int jumpLength = bridge[nowNum];
				// 만약 이번 칸이 0 이라면 더이상 진행할 수 없으므로 다음 징검다리를 본다.
				if (jumpLength == 0) continue;
				
				// 만약 다음 점프에서 바로 끝날 수 있는 경우에는 탐색을 종료한다.
				if (jumpLength == 1) {
					minCnt = cnt + 1;
					break;
				}
				if ((b - nowNum) % jumpLength == 0) {
					minCnt = cnt + 1;
					break;
				}
				
				// 한 번의 점프로 끝나지 않는 경우에는 탐색을 추가로 한다.
				//// 양의 방향으로 탐색
				for (int next = nowNum + jumpLength; next <= N; next += jumpLength) {
					if (checked[next]) continue;
					
					checked[next] = true;
					queue.add(new Jump(cnt + 1, next));
				}
				//// 음의 방향으로 탐색
				for (int next = nowNum - jumpLength; next >= 0; next -= jumpLength) {
					if (checked[next]) continue;
					
					checked[next] = true;
					queue.add(new Jump(cnt + 1, next));
				}
			}
			
			bw.write(minCnt+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
