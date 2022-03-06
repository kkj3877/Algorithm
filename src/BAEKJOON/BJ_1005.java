package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1005 {
	
	static Building[] Buildings = null;;
	
	public static class Building {
		int time = 0;
		boolean canBuild = true;
		
		List<Integer> needs = null;
		
		public Building(int time) {
			this.time = time;
		}
		
		public void addNeed(int num) {
			if (needs == null) {
				needs = new ArrayList<Integer>();
				this.canBuild = false;
			}
			needs.add(num);
		}
		
		public int calcTime() {
			// 만약 해당 건물에 대한 시간 계산이 끝난 상태라면 그 시간을 반환한다.
			if (canBuild) return this.time;
			
			// 해당 건물을 짓는데 걸리는 시간을 아직 모를 경우에는
			// 건물 건설에 필요한 건물들의 건설 시간 중 최대 시간을 구하고,
			// 해당 건물 건설 시간에 그 시간을 더해준다.
			int needTime = 0;
			for (int num : this.needs) {
				Building needBuilding = Buildings[num];
				
				int buildTime;
				if (needBuilding.canBuild) buildTime = needBuilding.time;
				else buildTime = needBuilding.calcTime();
				
				if (buildTime > needTime) needTime = buildTime;
			}
			
			// 건물을 짓기 위한 사전 작업 시간 계산이 끝났다면
			// 기존의 해당 건물 건설시간에 사전 작업 시간을 더해주고 계산 완료 표시를 한다.
			this.time += needTime;
			this.canBuild = true;
			
			// 해당 건물을 짓기 위한 총 시간을 반환한다.
			return this.time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테스트 케이스의 수를 입력받고 테스트 케이스만큼의 실행을 수행한다.
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// 건물의 개수 N과 건설 순서 규칙 K를 입력받는다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 1번부터 N번 까지의 빌딩을 만들고 건물들의 건설시간을 입력받는다.
			Buildings = new Building[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				Buildings[i] = new Building(Integer.parseInt(st.nextToken()));
			}
			
			// 건설순서 규칙을 입력받는다.
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int needBuilding = Integer.parseInt(st.nextToken());
				Building target = Buildings[Integer.parseInt(st.nextToken())];
				target.addNeed(needBuilding);
			}
			
			// 승리를 위한 목표 건물의 번호를 입력받는다.
			int W = Integer.parseInt(br.readLine());
			
			// 승리 목표 건물을 짓기 위한 시간을 계산하고 출력한다.
			int timeToWin = Buildings[W].calcTime();
			bw.write(timeToWin+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
