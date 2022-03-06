package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_9694 {
	
	static class Connect implements Comparable<Connect> {
		int dest;
		int dist;
		public Connect(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}
		@Override
		public int compareTo(Connect o) {
			return (this.dist < o.dist) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// �׽�Ʈ ���̽��� ���� �Է¹ް�, �� ����ŭ ������ �ݺ��Ѵ�.
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			// ������ ���� N �� ��ġ���� �� M �� �Է¹޴´�.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// �� ��ġ�κ� ������踦 ������ �迭�� �����Ѵ�.
			List<List<Connect>> Lists = new ArrayList<List<Connect>>();
			for (int i = 0; i < M; i++) Lists.add(new ArrayList<Connect>());
			
			// ��ġ�ε鰣�� ������踦 �Է¹޴´�.
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				
				Lists.get(x).add(new Connect(y, z));
				Lists.get(y).add(new Connect(x, z));
			}
			
			// ��ġ�ε�� �ѽ��̷κ����� ģ�е� ���� ����� �迭�� �����Ѵ�.
			int[] friendship = new int[M];
			Arrays.fill(friendship, Integer.MAX_VALUE);
			
			// �ѽ��̰� Ư�� ��ġ���� ������ ���� �������� ��ġ���� ������ �迭�� �����Ѵ�.
			int[] preMeet = new int[M];
			preMeet[0] = -1;
			
			friendship[0] = 0;
			PriorityQueue<Connect> pq = new PriorityQueue<Connect>();
			pq.add(new Connect(0, 0));
			
			while (!pq.isEmpty()) {
				Connect now = pq.poll();
				int nowNum = now.dest;
				int nowDist = now.dist;
				
				if (friendship[nowNum] < nowDist) continue;
				
				List<Connect> conn = Lists.get(nowNum);
				for (Connect next : conn) {
					int nextNum = next.dest;
					int nextDist = nowDist + next.dist;
					
					if (nextDist < friendship[nextNum]) {
						pq.add(new Connect(nextNum, nextDist));
						friendship[nextNum] = nextDist;
						preMeet[nextNum] = nowNum;
					}
				}
			}
			
			bw.write("Case #" + t + ":");
			if (friendship[M - 1] == Integer.MAX_VALUE) bw.write(" -1\n");
			else {
				List<Integer> path = new ArrayList<Integer>();
				int num = M - 1;
				while (num != -1) {
					path.add(num);
					num = preMeet[num];
				}
				for (int i = path.size() - 1; i >= 0; i--) {
					bw.write(" "+path.get(i));
				}
				bw.write("\n");
			}
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
