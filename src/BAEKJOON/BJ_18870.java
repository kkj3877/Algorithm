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
		
		// ��ǥ�� ������ �Է¹ް� ��ǥ�� ������ �Է¹޴´�.
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		// ��ǥ�� �����Ͽ� ���ο� �迭�� ����� �����Ѵ�.
		int[] sorted = new int[N];
		for (int i = 0; i < N; i++) sorted[i] = nums[i];
		Arrays.sort(sorted);
		
		// ���ĵ� ��ǥ���� ����Ͽ� ��ǥ�鿡 ���Ͽ�
		// �ڽź��� ���� ��ǥ�� �� �� �ִ����� ���� ������ �ʿ� �����Ѵ�.
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
		
		// ������ ����Ѵ�.
		for (int i = 0; i < N; i++) {
			if (i != N - 1) bw.write(compressed.get(nums[i])+" ");
			else bw.write(compressed.get(nums[i])+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
