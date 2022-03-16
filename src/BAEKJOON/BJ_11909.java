package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11909 {
	
	static int N;
	static int[][] arr;
	static int[][] leastCost;
	
	static void init(BufferedReader br) throws IOException {
		// 배열의 한 변의 길이를 입력받고, 배열의 상태를 입력받는다.
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		leastCost = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(leastCost[i], Integer.MAX_VALUE);
		leastCost[0][0] = 0;
	}
	
	static int solve() {
		// 방향성이 확실하게 존재하므로, 이중 for 문으로도 최소비용을 계산할 수 있다.
		// 규칙 1) 각 지점에서 갈 수 있는 지점은 열번호 혹은 행번호가 더 큰 곳이다.
		// 규칙 2) 각 지점에서 더 높은 지점으로 가려면 (높이차 + 1) 의 코스트가 든다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1) break;
				
				// 다음 행은 아직 한 번도 업데이트가 되지 않은 부분이므로, 높이만을 확인하며 업데이트 한다.
				if (i != N - 1) {
					if (arr[i + 1][j] < arr[i][j]) {
						leastCost[i + 1][j] = leastCost[i][j];
					}
					else {
						leastCost[i + 1][j] = leastCost[i][j] + (arr[i + 1][j] - arr[i][j] + 1);
					}
				}
				
				// 다음 열은 이미 이전 행에서 업데이트를 수행한 곳이므로, [이미 알고 있는 최소 비용]과
				//  [현재 칸까지의 최소비용 + 다음 열로 가기 위한 최소비용] 중 작은 값으로 업데이트 한다.
				if (j != N - 1) {
					if (arr[i][j + 1] < arr[i][j]) {
						leastCost[i][j + 1] = (leastCost[i][j] < leastCost[i][j + 1]) ? leastCost[i][j] : leastCost[i][j + 1];
					}
					else {
						int diff = arr[i][j + 1] - arr[i][j] + 1;
						leastCost[i][j + 1] = (leastCost[i][j] + diff < leastCost[i][j + 1]) ?
								leastCost[i][j] + diff : leastCost[i][j + 1];
					}
				}
			}
		}
		
		return leastCost[N - 1][N - 1];
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
