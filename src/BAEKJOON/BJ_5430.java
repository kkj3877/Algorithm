package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_5430 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// ��ɾ �Է¹޴´�.
			char[] order = br.readLine().toCharArray();
			
			// ������ ������ �Է¹ް�, �Է¹��� �������� �����Ѵ�.
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];
			String numString = br.readLine();
			StringTokenizer st = new StringTokenizer(numString.substring(1, numString.length() - 1), ",");
			for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			// ��ɾ �м��Ͽ� ������ �� ����� ����Ѵ�.
			boolean direct = true;
			int front = 0;
			int rear = 0;
			for (int i = 0; i < order.length; i++) {
				if (order[i] == 'R') direct = direct ? false : true;
				else {
					if (direct) front++;
					else rear++;
				}
			}
			
			if (front + rear > N) bw.write("error\n");
			else {
				bw.write("[");
				if (direct) {
					for (int i = front; i < N - rear; i++) {
						bw.write(String.valueOf(nums[i]));
						if (i != N - rear - 1) bw.write(",");
					}
				}
				else {
					for (int i = N - rear - 1; i >= front; i--) {
						bw.write(String.valueOf(nums[i]));
						if (i != front) bw.write(",");
					}
				}
				bw.write("]\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
