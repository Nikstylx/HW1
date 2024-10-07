package edu.softwareeng.sample;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int value) {
        try {
            // No specific validation needed for the integer value,
            // as all integer values are valid inputs.
            // However, if there are specific business rules (e.g., positive values only),
            // you could validate accordingly.

            return String.valueOf(value); // Using String.valueOf for clarity
        } catch (Exception e) {
            // Log the exception and throw a custom runtime exception
            // to prevent uncaught exceptions from reaching process boundaries.
            // (Assuming a logging framework is available)
            System.err.println("Error computing value: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
        }
    }
}
