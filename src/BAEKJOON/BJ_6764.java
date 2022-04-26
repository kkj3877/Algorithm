package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_6764 {
	
	static int[] depth;
	
	static void init(BufferedReader br) throws IOException {
		depth = new int[4];
		for (int i = 0; i < 4; i++) depth[i] = Integer.parseInt(br.readLine());
	}
	
	static String solve() {
		if (depth[0] == depth[1]) {
			if (depth[0] == depth[2] && depth[0] == depth[3]) return "Fish At Constant Depth";
			else return "No Fish";
		}
		else {
			int raise = (depth[1] - depth[0] > 0) ? 1 : -1;
			if ((depth[2] - depth[1]) * raise > 0 && (depth[3] - depth[2]) * raise > 0) {
				return (raise > 0) ? "Fish Rising" : "Fish Diving";
			}
			else return "No Fish";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		String answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
