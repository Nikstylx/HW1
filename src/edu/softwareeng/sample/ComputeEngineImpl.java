package edu.softwareeng.sample;

import java.util.List;
import java.util.ArrayList;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(InputConfig inputConfig, OutputConfig outputConfig, char delimiter) {
        List<Integer> inputs = inputConfig.getInputs();  // Get the inputs from the FileInputConfig
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
    public String compute(int input) {
        // Implement the method that computes based on an integer input
        if (isPrime(input)) {
            return input + " is prime";
        } else {
            return input + " is not prime";
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;  // 0 and 1 are not prime numbers
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;  // Found a divisor, so it's not prime
            }
        }
        return true;  // No divisors found, it's prime
    }
}
