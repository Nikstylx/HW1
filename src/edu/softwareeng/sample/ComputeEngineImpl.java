package edu.softwareeng.sample;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int value) {
        try {
            if (value < 0) {
                throw new IllegalArgumentException("Value must not be negative.");
            }

            int start = 2; // Default starting point for primes
            int end = value; // The upper bound for the range

            // Validate the range before processing
            validateRange(start, end);

            // Find prime numbers in the range [start, end]
            List<Integer> primes = findPrimesInRange(start, end);

            // Return the prime numbers as a string representation of the list
            return primes.toString();

        } catch (IllegalArgumentException | InvalidRangeException e) {
            System.err.println("Input validation error: " + e.getMessage());
            throw e; // Re-throw to propagate the exception to the caller
        } catch (Exception e) {
            System.err.println("Error computing prime numbers: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
        }
    }

    // Helper method to validate the range
    private void validateRange(int start, int end) {
        if (start > end) {
            throw new InvalidRangeException("Start of range cannot be greater than the end.");
        }
        if (end < 2) {
            throw new InvalidRangeException("End of range must be at least 2 to include prime numbers.");
        }
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

    // Helper method to check if a number is prime
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

    // Custom exception class for invalid ranges
    private static class InvalidRangeException extends RuntimeException {
        public InvalidRangeException(String message) {
            super(message);
        }
    }
}
