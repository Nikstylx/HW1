package src;

public class ComputeEngineImpl implements ComputeEngine {

	@Override
	public String compute(int value) {
		return "";
	}
	public static String findPrimesUpTo(int num) {
	    StringBuilder primes = new StringBuilder();
	    for (int i = 2; i <= num; i++) {
	        if (isPrime(i)) {
	            if (primes.length() > 0) {
	                primes.append(", ");
	            }
	            primes.append(i);
	        }
	    }
	    return primes.toString();
	}

	public static boolean isPrime(int num) {
	    if (num <= 1) return false;
	    for (int i = 2; i <= Math.sqrt(num); i++) {
	        if (num % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
}
