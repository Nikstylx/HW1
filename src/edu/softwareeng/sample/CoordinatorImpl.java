package edu.softwareeng.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        ExecutorService executor = Executors.newFixedThreadPool(8);

    
    List<Future<Void>> futures = new ArrayList<>();

    try {
        Iterable<Integer> integers = ds.read(request.getInputConfig());
        for (int val : integers) {
            // Submit each task to the executor
            futures.add(executor.submit(() -> {
                ds.appendSingleResult(request.getOutputConfig(), ce.compute(val), request.getDelimiter());
                return null; // Return null as the method requires a return type
            }
                                       ));
        }

        // Wait for all tasks to complete
        for (Future<Void> future : futures) {
            future.get(); // This will block until the task is complete
        }
        return ComputeResult.SUCCESS;
    } catch (Exception e) {
        System.err.println("Error during computation: " + e.getMessage());
        throw new RuntimeException("Computation error: " + e.getMessage(), e);
    } finally {
        executor.shutdown(); // Properly shut down the executor
    }
}
