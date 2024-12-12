package edu.softwareeng.sample;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        }

        if (isPrime(num)) {
            return num + " is prime";
        } else {
            return num + " is not prime";
        }
    }

    @Override
    public String compute(InputConfig inputConfig, OutputConfig outputConfig, char delimiter) {
        List<Integer> inputs = inputConfig.getInputs(); // Ensure getInputs() is correctly defined in InputConfig
        List<String> results = new ArrayList<>();

        for (Integer num : inputs) {
            if (num < 0) {
                throw new IllegalArgumentException("Input number cannot be negative");
            }

            if (isPrime(num)) {
                results.add(num + " is prime");
            } else {
                results.add(num + " is not prime");
            }
        }

        outputConfig.appendResults(results, delimiter); // Ensure appendResults() is defined in OutputConfig
        return "Computation complete.";
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false; // Numbers less than 2 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Found a divisor, it's not prime
            }
        }
        return true; // No divisors found, it's prime
    }
}

