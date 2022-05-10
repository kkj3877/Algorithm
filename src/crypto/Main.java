package crypto;

public class Main {
	public static void main(String[] args) {
		
		String id = "kkj3877";
		
		CryptoRSA RSApro = new CryptoRSA();
		try {
			RSApro.makeKey(id);
		}
		catch (Exception e) { e.printStackTrace(); }
		finally {
			
		}
	}
}
