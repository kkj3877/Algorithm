package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BJ_11286 {
	
	static class Node implements Comparable<Node> {
		int abs;
		int num;
		
		public Node(int num) {
			this.num = num;
			if (num > 0) abs = num;
			else abs = -num;
		}

		@Override
		public int compareTo(Node o) {
			if (this.abs == o.abs) {
				return (this.num < o.num ) ? -1 : 1;
			}
			else return (this.abs < o.abs) ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.isEmpty()) bw.write("0\n");
				else {
					int poll = pq.poll().num;
					bw.write(poll+"\n");
				}
			}
			else {
				pq.add(new Node(num));
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
