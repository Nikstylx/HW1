package edu.softwareeng.sample;

import edu.softwareeng.sample.ComputeEngine;  // Import ComputeEngine interface
import edu.softwareeng.sample.InputConfig;    // Import InputConfig interface
import edu.softwareeng.sample.OutputConfig;   // Import OutputConfig interface
import java.util.ArrayList;                   // Import ArrayList
import java.util.List;                        // Import List

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
        List<Integer> inputs = inputConfig.getInputs();
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

        outputConfig.appendResults(results, delimiter);

        return "Computation complete.";
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

