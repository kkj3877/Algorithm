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
			// ���� �ش� �ǹ��� ���� �ð� ����� ���� ���¶�� �� �ð��� ��ȯ�Ѵ�.
			if (canBuild) return this.time;
			
			// �ش� �ǹ��� ���µ� �ɸ��� �ð��� ���� �� ��쿡��
			// �ǹ� �Ǽ��� �ʿ��� �ǹ����� �Ǽ� �ð� �� �ִ� �ð��� ���ϰ�,
			// �ش� �ǹ� �Ǽ� �ð��� �� �ð��� �����ش�.
			int needTime = 0;
			for (int num : this.needs) {
				Building needBuilding = Buildings[num];
				
				int buildTime;
				if (needBuilding.canBuild) buildTime = needBuilding.time;
				else buildTime = needBuilding.calcTime();
				
				if (buildTime > needTime) needTime = buildTime;
			}
			
			// �ǹ��� ���� ���� ���� �۾� �ð� ����� �����ٸ�
			// ������ �ش� �ǹ� �Ǽ��ð��� ���� �۾� �ð��� �����ְ� ��� �Ϸ� ǥ�ø� �Ѵ�.
			this.time += needTime;
			this.canBuild = true;
			
			// �ش� �ǹ��� ���� ���� �� �ð��� ��ȯ�Ѵ�.
			return this.time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// �׽�Ʈ ���̽��� ���� �Է¹ް� �׽�Ʈ ���̽���ŭ�� ������ �����Ѵ�.
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// �ǹ��� ���� N�� �Ǽ� ���� ��Ģ K�� �Է¹޴´�.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 1������ N�� ������ ������ ����� �ǹ����� �Ǽ��ð��� �Է¹޴´�.
			Buildings = new Building[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				Buildings[i] = new Building(Integer.parseInt(st.nextToken()));
			}
			
			// �Ǽ����� ��Ģ�� �Է¹޴´�.
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int needBuilding = Integer.parseInt(st.nextToken());
				Building target = Buildings[Integer.parseInt(st.nextToken())];
				target.addNeed(needBuilding);
			}
			
			// �¸��� ���� ��ǥ �ǹ��� ��ȣ�� �Է¹޴´�.
			int W = Integer.parseInt(br.readLine());
			
			// �¸� ��ǥ �ǹ��� ���� ���� �ð��� ����ϰ� ����Ѵ�.
			int timeToWin = Buildings[W].calcTime();
			bw.write(timeToWin+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
