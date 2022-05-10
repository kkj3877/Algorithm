package crypto;

import java.math.BigInteger;

public class CryptoUtil {
	
	public static int GCD(int a, int b) {
		if (a % b == 0) return b;
		else return GCD(b, a % b);
	}
	
	public static BigInteger GCD(BigInteger a, BigInteger b) {
		return a.gcd(b);
	}
	
	public static String keyDataDir() {
		String t = System.getProperty("os.name");
		
		String upload = "/kkj3877/cryptoData/";
		if ( t.indexOf("indows") != -1) {
			upload = "C:\\CryptoPractice\\";
		}
		
		return upload;
	}
	
}
