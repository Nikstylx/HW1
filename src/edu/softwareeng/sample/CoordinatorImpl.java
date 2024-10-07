package edu.softwareeng.sample;

public class CoordinatorImpl implements ComputationCoordinator {
    
    private final DataStore ds;
    private final ComputeEngine ce;

    public CoordinatorImpl(DataStore ds, ComputeEngine ce) {
        // Validate parameters
        if (ds == null) {
            throw new IllegalArgumentException("DataStore cannot be null");
        }
        if (ce == null) {
            throw new IllegalArgumentException("ComputeEngine cannot be null");
        }

        this.ds = ds;
        this.ce = ce;
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        // Validate request parameter
        if (request == null) {
            throw new IllegalArgumentException("ComputeRequest cannot be null");
        }
        
        // Validate input and output configurations
        if (request.getInputConfig() == null) {
            throw new IllegalArgumentException("InputConfig cannot be null");
        }
        if (request.getOutputConfig() == null) {
            throw new IllegalArgumentException("OutputConfig cannot be null");
        }

        // No validation needed for delimiter as it is a char type
        // (Any char can be used as a delimiter)

        try {
            Iterable<Integer> integers = ds.read(request.getInputConfig());
            for (int val : integers) {
                ds.appendSingleResult(request.getOutputConfig(), ce.compute(val), request.getDelimiter());
            }
            return ComputeResult.SUCCESS;
        } catch (Exception e) {
            // Log the exception (use a logging framework in production code)
            System.err.println("Error during computation: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
        }
    }
}
