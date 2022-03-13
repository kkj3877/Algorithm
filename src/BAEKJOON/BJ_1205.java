package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_1205 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		if ( N == 0 ) bw.write("1\n");
		else {
			int[] scores = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
			}
			
			int rank = 1;
			int under = 0;
			for (int i = 0; i < N; i++) {
				if (score < scores[i]) rank++;
				if (score > scores[i]) under++;
			}
			
			if (under == 0 && N == P) bw.write("-1\n");
			else bw.write(rank+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
