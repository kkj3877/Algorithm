package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_1117 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long W = Long.parseLong(st.nextToken());
		Long H = Long.parseLong(st.nextToken());
		Long f = Long.parseLong(st.nextToken());
		Long c = Long.parseLong(st.nextToken());
		Long x1 = Long.parseLong(st.nextToken());
		Long y1 = Long.parseLong(st.nextToken());
		Long x2 = Long.parseLong(st.nextToken());
		Long y2 = Long.parseLong(st.nextToken());
		
		if (f > (W / 2)) f = W - f;
		
		long painted = 0;
		if (x2 <= f) painted += 2 * (x2 - x1);
		else if (x1 >= f) painted += x2 - x1;
		else painted += 2 * (f - x1) + (x2 - f);
		
		painted *= y2 - y1;
		painted *= c + 1;
		
		long notPainted = W * H - painted;
		
		bw.write(notPainted+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
