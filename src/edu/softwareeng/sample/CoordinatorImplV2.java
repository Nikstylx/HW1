package edu.softwareeng.sample;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;

public class CoordinatorImplV2 implements ComputationCoordinator {
    
    private final DataStore ds;
    private final ComputeEngine ce;

    public CoordinatorImplV2(DataStore ds, ComputeEngine ce) {
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

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Void>> tasks = new ArrayList<>();

        try {
            Iterable<Integer> integers = ds.read(request.getInputConfig());
            for (int val : integers) {
                tasks.add(() -> {
                    ds.appendSingleResult(request.getOutputConfig(), ce.compute(val), request.getDelimiter());
                    return null;
                });
            }

            // Use invokeAll to handle tasks more efficiently
            executor.invokeAll(tasks);

            return ComputeResult.SUCCESS;
        } catch (Exception e) {
            System.err.println("Error during computation: " + e.getMessage());
            throw new RuntimeException("Computation error: " + e.getMessage(), e);
        } finally {
            executor.shutdown(); // Properly shut down the executor
        }
    }
}
