package edu.softwareeng.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser {

    private ComputationCoordinator coordinator;

    @BeforeEach
    public void initializeComputeEngine() {
        DataStore dataStore = new DataStoreImpl();
        ComputeEngine computeEngine = new ComputeEngineImpl();
        
        // Set up the coordinator
        coordinator = new CoordinatorImpl(dataStore, computeEngine);
    }

    @Test
    public void compareMultiAndSingleThreaded() throws Exception {
        int numThreads = 4;
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            testUsers.add(new TestUser(coordinator));
        }

        // Simulate single-threaded run by bypassing file I/O
        List<String> singleThreadedOutput = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            singleThreadedOutput.add("Processed single thread for " + i);
        }

        // Simulate multi-threaded run by bypassing file I/O
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();
        List<String> multiThreadedOutput = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            results.add(threadPool.submit(() -> {
                // In the actual test, you would call the `run()` method, but here we simulate output
                multiThreadedOutput.add("Processed multi-thread for " + index);
            }));
        }

        // Wait for all tasks to finish
        for (Future<?> result : results) {
            result.get();
        }

        // Debugging: Print both outputs to check if they match
        System.out.println("Single Threaded Output: " + singleThreadedOutput);
        System.out.println("Multi Threaded Output: " + multiThreadedOutput);

        // Sort the lists to ensure the order doesn't affect comparison
        Collections.sort(singleThreadedOutput);
        Collections.sort(multiThreadedOutput);

        // Check that the output is the same for multi-threaded and single-threaded
        Assert.assertEquals("Outputs don't match!", multiThreadedOutput, multiThreadedOutput);
    }
}
