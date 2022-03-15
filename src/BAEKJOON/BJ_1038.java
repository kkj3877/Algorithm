package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BJ_1038 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		for (int i = 0; i < 10; i++) pq.add((long)i);
		int cnt = -1;
		long answer = -1;
		while (!pq.isEmpty()) {
			long num = pq.poll();
			if (++cnt == N) {
				answer = num;
				break;
			}
			int lastDigit = (int)(num % 10);
			for (int i = 0; i < lastDigit; i++) pq.add(num * 10 + i);
		}
		
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
