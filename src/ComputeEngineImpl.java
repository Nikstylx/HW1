package src;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int range) {
        List<Integer> primeNumbers = findPrimesInRange(range);
        return formatPrimeNumbers(primeNumbers);
    }

    private List<Integer> findPrimesInRange(int range) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int num) {
        if (num <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private String formatPrimeNumbers(List<Integer> primes) {
        if (primes.isEmpty()) {
            return "No prime numbers found.";
        }
        return "Prime numbers: " + primes.toString();
    }
}
