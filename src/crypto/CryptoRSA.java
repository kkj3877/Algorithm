package crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class CryptoRSA {
	private BigInteger p, q;
	
	public void makeKey(String id) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		FileReader fr = null;
		
		try {
			// 
			try {
				String dir = CryptoUtil.keyDataDir();
				File file = new File(dir);
				if (!file.exists()) {
					file.mkdir();
					System.out.println("Directory Created");
				}
				
				file = new File(dir+"keyData.csv");
				if (!file.exists()) {
					file.mkdir();
					System.out.println("[keyData.csv] File Created");
					file = new File(dir+"keyData.csv");
				}
				
				fr = new FileReader(file);
				
				br = new BufferedReader(fr);
				String line = null;
				String key = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					StringTokenizer st = new StringTokenizer(line,",");
					if (id.equals(st.nextToken())) {
						key = st.nextToken();
						System.out.println("id::"+id+" -> key::"+key);
					}
				}
				if (key == null) {
					System.out.println("id::"+id+" is not found");
				}
//				fw = new FileWriter(file, true);
//				fw.write(String.valueOf((int)(Math.random() * 10)) + "," +String.valueOf((int)(Math.random() * 1000))+"\n");
//				fw.flush();
				
//				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (br != null) br.close();
				if (fw != null) fw.close();
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		finally {
			
		}
		
	}
	
	
}
