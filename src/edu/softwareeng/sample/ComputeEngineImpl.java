public class ComputeEngineImpl implements ComputeEngine {

    @Override
    public String compute(int num) {
        // Implement the logic to handle a single integer, for example, check if it's prime
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
        // Existing logic for processing inputs and outputs using the provided InputConfig and OutputConfig
        List<Integer> inputs = inputConfig.getInputs();
        List<String> results = new ArrayList<>();
        
        // Process each number and check if it's prime
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

        // Write the results to the outputConfig
        outputConfig.appendResults(results, delimiter);

        return "Computation complete.";
    }

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
