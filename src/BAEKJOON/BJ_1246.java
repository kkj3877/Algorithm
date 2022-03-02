package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1246 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 달걀의 개수 N 과 고객의 수 M 을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 고객별 Pi 를 입력받는다.
		int[] Prices = new int[M];
		for (int i = 0; i < M; i++) Prices[i] = Integer.parseInt(br.readLine());
		
		// 고객들의 Pi 를 정렬한다.
		Arrays.sort(Prices);
		
		// 낮은 가격부터 팔며 수익을 체크한다.
		int maxMoney = 0;
		int danga = 0;
		for (int i = 0; i < M; i++) {
			// 판매가 = i 번째 가격
			int sellPrice = Prices[i];
			
			// 살 수 있는 사람 수를 체크한다.
			int canBuy = M - i;
			
			// 만약, 달걀을 살 수 있는 사람보다 달걀 수가 적다면?
			// 그러니까 만약, 달걀 수(N) < 살 수 있는 사람 수(canBuy)
			// 살 수 있는 사람 수 = 달걀 수
			if (N < canBuy) canBuy = N;
			
			// 판매 가격 계산 : 판매 가격 = 단가 X 사는 사람 수
			int totalMoney = sellPrice * canBuy;
			
			// 이전까지의 최대 수익과 이번 단가에서의 수익을 비교한다.
			// 이번 수익이 더 높다면, 단가를 현재 단가로 조정한다.
			if (maxMoney < totalMoney) {
				maxMoney = totalMoney;
				danga = sellPrice;
			}
		}
		
		// 위의 과정을 통해 구한 단가와 최대 수익을 출력한다.
		bw.write(danga+" "+maxMoney+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
