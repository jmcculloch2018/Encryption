import java.math.BigInteger;
import java.security.SecureRandom;
/**
 * Generates private keys and stores information necessary to encrypt and decrypt
 * @author Jeremy McCulloch
 * 
 */

public class PrivateKey {
	BigInteger N, e, d;
	public String decrypt(String m) {
		BigInteger M = Utility.stringToInt(m);
		System.out.println(M);
		M = M.modPow(d, N);
		System.out.println(M);
		return Utility.intToString(M);
	}
	public PrivateKey(int length) {
		BigInteger p = BigInteger.probablePrime(length, new SecureRandom());
		BigInteger q = BigInteger.probablePrime(length, new SecureRandom());
		N = p.multiply(q);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = new BigInteger(phi.bitLength()-1, new SecureRandom());
		BigInteger[] coefficients = new BigInteger[3];
		Utility.extended_gcd(e, phi, coefficients);
		e = e.divide(coefficients[2]);
		Utility.extended_gcd(e, phi, coefficients);
		d = coefficients[1];
	}
	public PrivateKey(String s) {
		N = new BigInteger(s.split("-")[0]);
		e = new BigInteger(s.split("-")[1]);
		d = new BigInteger(s.split("-")[2]);
	}
	public PublicKey getPublicKey() {
		return new PublicKey(N, e);
	}
	public String toString() {
		return N.toString() + "-" + e.toString() + "-" + d.toString();
	}
	
}
