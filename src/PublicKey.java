import java.math.BigInteger;

/**
 * Stores information necessary to encrypt
 * @author Jeremy McCulloch
 * 
 */
public class PublicKey {
	BigInteger N, e;
	public PublicKey(BigInteger N, BigInteger e) {
		this.N = N;
		this.e = e;
	}
	public PublicKey(String s) {
		N = new BigInteger(s.split("-")[0]);
		e = new BigInteger(s.split("-")[1]);
	}
	public String encrypt(String m) {
		BigInteger M = Utility.stringToInt(m);
		M = M.modPow(e, N);
		return Utility.intToString(M);
	}
	public String toString() {
		return N.toString() + "-" + e.toString();
	}
}
