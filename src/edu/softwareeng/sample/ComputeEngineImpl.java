package edu.softwareeng.sample;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public List<String> compute(int value) {
        try {
            if (value < 0) {
                throw new IllegalArgumentException("Value must not be negative.");
            }
            // Assuming the input 'value' is the upper bound of the range,
            // and the lower bound is 2 (first prime number).
            int start = 2;
            int end = value;  // End is the value passed to the method

            // Find prime numbers in the range [start, end]
            List<Integer> primes = findPrimesInRange(start, end);
            List<String> result = new ArrayList<>();
            for (Integer prime : primes) {
                result.add(prime.toString()); // Convert each prime to a string
            }
            return result; // Return a list of strings
        } catch (IllegalArgumentException e) {
            // Handle the case where the value is negative
            System.err.println("Input validation error: " + e.getMessage());
            throw e; // Re-throw the exception to notify the caller
        } catch (Exception e) {
            // Log the exception and throw a custom runtime exception
            // to prevent uncaught exceptions from reaching process boundaries.
            System.err.println("Error computing prime numbers: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
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
            return false; // Numbers less than or equal to 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { // Check divisibility up to the square root of the number
            if (num % i == 0) {
                return false; // If divisible by any number, it's not prime
            }
        }
        return true; // The number is prime
    }
}
