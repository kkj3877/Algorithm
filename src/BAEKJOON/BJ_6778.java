package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BJ_6778 {
	
	static int antenna;
	static int eyes;
	
	static void init(BufferedReader br) throws IOException {
		antenna = Integer.parseInt(br.readLine());
		eyes = Integer.parseInt(br.readLine());
	}
	
	static List<String> solve() {
		List<String> list = new ArrayList<String>();
		if (antenna >= 3 && eyes <= 4) list.add("TroyMartian");
		if (antenna <= 6 && eyes >= 2) list.add("VladSaturnian");
		if (antenna <= 2 && eyes <= 3) list.add("GraemeMercurian");
		
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		init(br);
		List<String> list = solve();
		for (String alien : list) bw.write(alien+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
