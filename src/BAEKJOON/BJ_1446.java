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

public class BJ_1446 {
	
	public static class Road {
		int destination;
		int distance;
		
		public Road(int destination, int distance) {
			this.destination = destination;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// �������� ���� N �� ��ӵ����� ���� D�� �Է¹޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		// ��ӵ����� ���� D�� �����ϴ� �Ÿ� �迭�� ������ְ�
		// �������� ������ ������ ������ �� �ִ� �迭�� ������ش�.
		int[] distances = new int[D + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		List<List<Road>> shortcutLists = new ArrayList<List<Road>>();
		for (int i = 0; i < D; i++) shortcutLists.add(new ArrayList<Road>());
		
		// �������� ������ �Է¹޾� �����Ѵ�.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// ������ �Ұ����ϹǷ�, �������� ��ӵ����� ���̺��� �� �������� �����Ѵ�.
			if (to > D) continue;
			
			// '������-������' ���� �� �� ���̸� ���� �������� �����Ѵ�.
			if (dist > to - start) continue;
			
			List<Road> shortcutList = shortcutLists.get(start);
			shortcutList.add(new Road(to, dist));
		}
		
		Queue<Road> queue = new LinkedList<Road>();
		queue.add(new Road(0, 0));
		
		while (!queue.isEmpty()) {
			Road now = queue.poll();
			int nowSpot = now.destination;
			int nowDist = now.distance;
			
			// ���� ������� �˷��� �Ÿ��� �� ª�ٸ� �ش� ����Ŭ�� �����Ѵ�.
			if (distances[nowSpot] < nowDist) continue;
			distances[nowSpot] = nowDist;
			
			// ���� ���� ĭ�� �̹� ��������� ���̻� �� �ʿ䰡 ���� ������ �ش� ����Ŭ�� �Ѿ��.
			if (nowSpot == D) continue;
			
			// ���� ĭ�� �������� �ִٸ� �������� ������� ���� �Ÿ���
			// ������� �˷��� �Ÿ� �� �� ª�� �Ÿ��� �ִܰŸ��� ������Ʈ�Ѵ�.
			for (Road sc : shortcutLists.get(nowSpot)) {
				int nextDest = sc.destination;
				int nextDist = sc.distance + nowDist;
				
				if (nextDist < distances[nextDest]) {
					distances[nextDest] = nextDist;
					queue.add(new Road(nextDest, nextDist));
				}
			}
			
			// ���� ĭ�� ���� ĭ������ �ִ� �Ÿ��� ������Ʈ�Ѵ�.
			if (nowSpot < D) {
				if (nowDist + 1 < distances[nowSpot + 1]) {
					distances[nowSpot + 1] = nowDist + 1;
					queue.add(new Road(nowSpot + 1, nowDist + 1));
				}
			}
		}
		
		bw.write(distances[D]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
