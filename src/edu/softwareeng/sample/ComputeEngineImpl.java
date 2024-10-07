package edu.softwareeng.sample;

public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int value) {
        // No specific validation needed for the integer value,
        // as all integer values are valid inputs.
        // However, if there are specific business rules (e.g., positive values only),
        // you could validate accordingly.

        return String.valueOf(value); // Using String.valueOf for clarity
    }

}
