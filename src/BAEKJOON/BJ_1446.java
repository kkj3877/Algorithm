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
		
		// 지름길의 개수 N 과 고속도로의 길이 D를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		// 고속도로의 길이 D에 대응하는 거리 배열을 만들어주고
		// 지점마다 지름길 정보를 저장할 수 있는 배열을 만들어준다.
		int[] distances = new int[D + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		List<List<Road>> shortcutLists = new ArrayList<List<Road>>();
		for (int i = 0; i < D; i++) shortcutLists.add(new ArrayList<Road>());
		
		// 지름길의 정보를 입력받아 저장한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// 역행은 불가능하므로, 목적지가 고속도로의 길이보다 긴 지름길은 무시한다.
			if (to > D) continue;
			
			// '시작점-도착점' 보다 더 긴 길이를 가진 지름길은 무시한다.
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
			
			// 만약 현재까지 알려진 거리가 더 짧다면 해당 사이클은 무시한다.
			if (distances[nowSpot] < nowDist) continue;
			distances[nowSpot] = nowDist;
			
			// 현재 보는 칸이 이미 목적지라면 더이상 볼 필요가 없기 때문에 해당 사이클은 넘어간다.
			if (nowSpot == D) continue;
			
			// 현재 칸에 지름길이 있다면 지름길을 사용했을 때의 거리와
			// 현재까지 알려진 거리 중 더 짧은 거리를 최단거리로 업데이트한다.
			for (Road sc : shortcutLists.get(nowSpot)) {
				int nextDest = sc.destination;
				int nextDist = sc.distance + nowDist;
				
				if (nextDist < distances[nextDest]) {
					distances[nextDest] = nextDist;
					queue.add(new Road(nextDest, nextDist));
				}
			}
			
			// 현재 칸의 다음 칸까지의 최단 거리를 업데이트한다.
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
