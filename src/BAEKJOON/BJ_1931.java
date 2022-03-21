package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1931 {
	
	static int N;
	static Meeting[] meetings;
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end) {
				return (this.start < o.start) ? -1 : 1;
			}
			else return (this.end < o.end) ? -1 : 1;
		}
	}
	
	static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end);
		}
	}
	
	static int solve() {
		Arrays.sort(meetings);
		
		int meetingCount = 0;
		int meetingEndTime = 0;
		
		for (int i = 0; i < N; i++) {
			if (meetingEndTime <= meetings[i].start) {
				meetingCount++;
				meetingEndTime = meetings[i].end;
			}
		}
		
		return meetingCount;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		int answer = solve();
		bw.write(answer+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
