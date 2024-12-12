package edu.softwareeng.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CoordinatorImpl implements ComputationCoordinator {

    private final DataStore ds;
    private final ComputeEngine ce;

    public CoordinatorImpl(DataStore ds, ComputeEngine ce) {
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
        if (request == null) {
            throw new IllegalArgumentException("ComputeRequest cannot be null");
        }
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

            // Execute tasks and handle results
            List<Future<Void>> results = executor.invokeAll(tasks);
            for (Future<Void> result : results) {
                try {
                    result.get(); // Check for exceptions
                } catch (ExecutionException ee) {
                    throw new RuntimeException("Task execution failed", ee.getCause());
                }
            }

            return ComputeResult.SUCCESS;

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new RuntimeException("Computation interrupted", ie);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Computation error", e);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate in time.");
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                System.err.println("Executor termination interrupted.");
            }
        }
    }
}
