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

public class BJ_18126 {
	
	static int N;
	static Room[] rooms;
	static boolean[] check;
	
	static class Path {
		int dest;
		long distance;
		
		public Path(int dest, long distance) {
			this.dest = dest;
			this.distance = distance;
		}
	}
	
	static class Room {
		List<Path> connRooms;
		
		public Room() {
			this.connRooms = new ArrayList<Path>();
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		// 방의 개수를 입력받고, 방의 개수만큼의 방 인스턴스를 생성 및 초기화 해준다.
		N = Integer.parseInt(br.readLine());
		rooms = new Room[N + 1];
		for (int i = 1; i < N + 1; i++) rooms[i] = new Room();
		
		// 연결된 방의 정보를 입력받고 각 방에 정보를 저장한다.
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			rooms[A].connRooms.add(new Path(B, C));
			rooms[B].connRooms.add(new Path(A, C));
		}
	}
	
	static long solve() {
		// DFS 를 사용하여 입구로부터 끝방(더이상 들어갈 곳이 없는 방) 까지의 거리를 구한다.
		// 이 때, 최대거리를 갱신해가며 모든 탐색이 끝났을 때의 최대거리를 정답으로 반환한다.
		check = new boolean[N + 1];
		check[1] = true;
		Stack<Path> stack = new Stack<Path>();
		stack.add(new Path(1, 0));
		
		long maxDistance = 0;
		while (!stack.isEmpty()) {
			Path now = stack.pop();
			int nowNum = now.dest;
			long nowDistance = now.distance;
			
			boolean isEnd = true;
			for (Path nextPath : rooms[nowNum].connRooms) {
				int nextNum = nextPath.dest;
				if (check[nextNum]) continue;
				else check[nextNum] = true;
				
				isEnd = false;
				long nextDistance = nowDistance + nextPath.distance;
				stack.add(new Path(nextNum, nextDistance));
			}
			
			if (isEnd) {
//				System.out.print("End Of Path :: "+nowNum);
				if (maxDistance < nowDistance) maxDistance = nowDistance;
//				System.out.println(">>, distance, max = "+nowDistance+", " + maxDistance);
			}
		}
		
		return maxDistance;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		long distance = solve();
		bw.write(distance + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
