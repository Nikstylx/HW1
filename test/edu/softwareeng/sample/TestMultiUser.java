package edu.softwareeng.sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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
        // Create an instance of the coordinator component
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
        
        // Run single-threaded
        String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";
        for (int i = 0; i < numThreads; i++) {
        File multiThreadedOut = new File(multiThreadFilePrefix + i);
        multiThreadedOut.deleteOnExit();
        String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
        TestUser testUser = testUsers.get(i);
        results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath)));
}
        
        // Run multi-threaded
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();
        String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";
        for (int i = 0; i < numThreads; i++) {
            File multiThreadedOut = new File(multiThreadFilePrefix + i);
            multiThreadedOut.deleteOnExit();
            String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
            TestUser testUser = testUsers.get(i);
            results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath)));
        }
        
        results.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace for better debugging
                throw new RuntimeException(e);
            }
        });
        
        // Check that the output is the same for multi-threaded and single-threaded
        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);
        Assert.assertEquals(singleThreaded, multiThreaded);
    }

    private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            File multiThreadedOut = new File(prefix + i);
            if (!multiThreadedOut.exists()) {
                System.err.println("Output file " + multiThreadedOut.getCanonicalPath() + " does not exist.");
            } else {
                result.addAll(Files.readAllLines(multiThreadedOut.toPath()));
            }
        }
        return result;
    }
}
