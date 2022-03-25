package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_2210 {
	
	static int[][] grid;
	static Set<Integer> set;
	
	static void init(BufferedReader br) throws IOException {
		grid = new int[5][5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) grid[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() throws IOException {
		set = new HashSet<Integer>();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
//				System.out.println("i, j = " + i + ", " + j);
				BFS(i, j, 0, 1);
			}
		}
	}
	
	static void BFS(int r, int c, int num, int count) {
		num += grid[r][c];
		
		if (count == 6) {
			if (!set.contains(num)) {
//				System.out.println(num);
				set.add(num);
			}
			return;
		}
		
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		for (int k = 0; k < 4; k++) {
			int nextR = r + dr[k];
			if (nextR < 0 || nextR > 4) continue;
			
			int nextC = c + dc[k];
			if (nextC < 0 || nextC > 4) continue;
			
			BFS(nextR, nextC, num * 10, count + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		solve();
		bw.write(set.size()+"\n");
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
