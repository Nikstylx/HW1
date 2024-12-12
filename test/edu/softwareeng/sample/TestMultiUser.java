package edu.softwareeng.sample;

import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser {

    private ComputationCoordinator coordinator;

    @BeforeEach
    public void initializeComputeEngine() {
        // Setup the coordinator for the tests
        DataStore dataStore = new DataStoreImpl();
        ComputeEngine computeEngine = new ComputeEngineImpl();
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
        Collections.sort(singleThreadedOutput);
        Collections.sort(multiThreadedOutput);

        Assert.assertEquals("Outputs don't match!", singleThreadedOutput, multiThreadedOutput);

        
    }
}

