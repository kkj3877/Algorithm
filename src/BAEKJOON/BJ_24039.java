// https://www.acmicpc.net/problem/24039
// 이름 : 2021은 무엇이 특별할까?
// 레벨 : S5
// 분류 : 수학, 정수론, 소수 판정

package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BJ_24039 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		List<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		primes.add(3);
		
		for (int i = 5; i < 104; i++) {
			int sqrt = (int)(Math.sqrt(i));
			for (int p : primes) {
				if (i % p == 0) break;
				if (p > sqrt) {
					primes.add(i);
					break;
				}
			}
		}
		
		int prime = 0;
		int next = 0;
		for (int p : primes) {
			prime = next;
			next = p;
			
			if (prime * next > N) break;
		}
		
		bw.write((prime * next)+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
