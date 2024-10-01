package src;

public class CoordinatorImpl implements ComputationCoordinator {
    
    private final DataStore dataStore;
    private final ComputeEngine computeEngine;

    public CoordinatorImpl(DataStore dataStore, ComputeEngine computeEngine) {
        this.dataStore = dataStore;
        this.computeEngine = computeEngine;
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        // Step a: Receive the request
        InputConfig inputConfig = request.getInputConfig();
        OutputConfig outputConfig = request.getOutputConfig();

        // Step b: Request that the data storage component read integers
        Iterable<Integer> inputNumbers = dataStore.read(inputConfig);
        if (inputNumbers == null) {
            return ComputeResult.FAILURE; // Handle null input gracefully
        }

        // Step c: Pass the integers to the compute component
        String computationResult = executeComputation(inputNumbers);

        // Step d: Request that the data storage component write the results
        WriteResult writeResult = dataStore.appendSingleResult(outputConfig, computationResult);
        if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
            return ComputeResult.FAILURE; // Writing results failed
        }

        return ComputeResult.SUCCESS; // Successful completion of the process
    }

    private String executeComputation(Iterable<Integer> inputNumbers) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int number : inputNumbers) {
            String result = computeEngine.compute(number);
            resultBuilder.append(result).append("\n");
        }
        return resultBuilder.toString();
    }
}
