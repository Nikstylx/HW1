package edu.softwareeng.sample;

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

        // Create a temporary test input file dynamically
        File testInputFile = new File("testInputFile.test");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testInputFile))) {
            writer.write("1\n15\n10\n5\n2\n3\n8\n");  // Example test data
        }

        for (int i = 0; i < numThreads; i++) {
            testUsers.add(new TestUser(coordinator));
        }

        // Run single-threaded
        String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
        for (int i = 0; i < numThreads; i++) {
            File singleThreadedOut = new File(singleThreadFilePrefix + i);
            singleThreadedOut.deleteOnExit();
            testUsers.get(i).run(singleThreadedOut.getCanonicalPath());
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
                throw new RuntimeException(e);
            }
        });

        // Check that the output is the same for multi-threaded and single-threaded
        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);
        Assert.assertEquals(singleThreaded, multiThreaded);

        // Clean up the test input file after the test
        testInputFile.delete();
    }

    private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            File multiThreadedOut = new File(prefix + i);
            result.addAll(java.nio.file.Files.readAllLines(multiThreadedOut.toPath()));
        }
        return result;
    }
}

