package edu.softwareeng.sample;

import java.util.List;
import java.util.ArrayList;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(InputConfig inputConfig, OutputConfig outputConfig, char delimiter) {
        List<Integer> inputs = inputConfig.getInputs();  // Get the inputs from the InputConfig
        List<String> results = new ArrayList<>();

        for (int input : inputs) {
            if (isPrime(input)) {
                results.add(input + " is prime");
            } else {
                results.add(input + " is not prime");
            }
        }

        outputConfig.appendResults(results, delimiter);  // Append results to OutputConfig
        return "Computation complete";
    }

    @Override
    public String compute(int value) {
        try {
            if (value < 0) {
                throw new IllegalArgumentException("Value must not be negative.");
            }

            int start = 2;
            int end = value;  // The value passed is the upper bound for the range

            // Find prime numbers in the range [start, end]
            List<Integer> primes = findPrimesInRange(start, end);

            // Return the prime numbers as a string representation of the list
            return primes.toString();

        } catch (IllegalArgumentException e) {
            System.err.println("Input validation error: " + e.getMessage());
            throw e; // Re-throw to propagate the exception to the caller
        } catch (Exception e) {
            System.err.println("Error computing prime numbers: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false; // Numbers <= 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { // Check divisibility up to square root
            if (num % i == 0) {
                return false; // If divisible, it's not prime
            }
        }
        return true; // The number is prime
    }

    // Helper method to find prime numbers in a given range
    private List<Integer> findPrimesInRange(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }
}
