package edu.softwareeng.sample;

import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public void compute(InputConfig input, OutputConfig output, char delimiter) {
        // Get the list of inputs
        List<Integer> inputs = ((InMemoryInputConfig) input).getInputs();
        
        // Get the mutable list of outputs
        List<String> outputList = ((InMemoryOutputConfig) output).getOutputMutable();
        
        // Iterate over each input, check if it's prime, and append result to output list
        for (Integer num : inputs) {
            try {
                if (num < 0) {
                    throw new IllegalArgumentException("Input cannot be negative: " + num);
                }
                if (isPrime(num)) {
                    outputList.add(num + " is prime");
                } else {
                    outputList.add(num + " is not prime");
                }
            } catch (IllegalArgumentException e) {
                outputList.add("Error: " + e.getMessage());
            } catch (Exception e) {
                outputList.add("Unexpected error with input " + num + ": " + e.getMessage());
            }
        }
    }

    // Helper method to check if a number is prime
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Found a divisor, so it's not prime
            }
        }
        return true; // No divisors found, it's prime
    }
}
