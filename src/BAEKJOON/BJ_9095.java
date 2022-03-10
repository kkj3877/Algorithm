package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_9095 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] cnt = new int[12];
		cnt[1] = 1;
		cnt[2] = 2;
		cnt[3] = 4;
		
		for (int i = 4; i < 12; i++) cnt[i] = cnt[i - 3] + cnt[i - 2] + cnt[i - 1];
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) bw.write(cnt[Integer.parseInt(br.readLine())]+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
