import java.math.BigInteger;

/**
 * Utility functions for converting strings  to BigInts and euclid's algorithm
 * @author Jeremy McCulloch
 *
 */
public class Utility {
	public static BigInteger stringToInt(String x) {
		BigInteger ret = BigInteger.ZERO;
		for (int i = 0; i < x.length(); i++) {
			ret = ret.multiply(new BigInteger("95"));
			ret = ret.add(new BigInteger("" + ((int) x.charAt(i) - 32)));
		}
		return ret;
	}
	public static String intToString(BigInteger x) {
		String s = "";
		while (!x.equals(BigInteger.ZERO)) {
			s = (char)(x.mod(new BigInteger("95")).intValue() + 32) + s;
			x = x.divide(new BigInteger("95"));
		}
		return s;
	}
	public static void extended_gcd(BigInteger a, BigInteger b, BigInteger[] coefficients) {//coefficients[0] <= coefficients[1]
		BigInteger s = BigInteger.ZERO, old_s = BigInteger.ONE, t = BigInteger.ONE, old_t = BigInteger.ZERO, r = a, old_r = b;
		while (!r.equals(BigInteger.ZERO)) {
			BigInteger quotient = old_r.divide(r);
			BigInteger temp = r;
			r = old_r.subtract(quotient.multiply(r));
			old_r = temp;
			temp = s;
			s = old_s.subtract(quotient.multiply(s));
			old_s = temp;
			temp = t;
			t = old_t.subtract(quotient.multiply(t));
			old_t = temp;
		}
		if (old_s.compareTo(old_t) > 0) {
			old_s = old_s.subtract(a);
			old_t = old_t.add(b);
		}

		coefficients[0] = old_s;
		coefficients[1] = old_t;
		coefficients[2] = old_r;
	}
}
