package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_18870 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 좌표의 개수를 입력받고 좌표의 값들을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		// 좌표를 복사하여 새로운 배열을 만들고 정렬한다.
		int[] sorted = new int[N];
		for (int i = 0; i < N; i++) sorted[i] = nums[i];
		Arrays.sort(sorted);
		
		// 정렬된 좌표들을 사용하여 좌표들에 대하여
		// 자신보다 작은 좌표가 몇 개 있는지에 대한 정보를 맵에 저장한다.
		Map<Integer, Integer> compressed = new HashMap<Integer, Integer>();
		int lastNum = Integer.MIN_VALUE;
		int comp = -1;
		for (int i = 0; i < N; i++) {
			if (sorted[i] > lastNum) {
				lastNum = sorted[i];
				comp++;
				compressed.put(sorted[i], comp);
			}
		}
		
		// 정답을 출력한다.
		for (int i = 0; i < N; i++) {
			if (i != N - 1) bw.write(compressed.get(nums[i])+" ");
			else bw.write(compressed.get(nums[i])+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
