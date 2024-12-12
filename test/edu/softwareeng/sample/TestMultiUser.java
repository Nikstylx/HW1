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
        DataStore dataStore = new DataStoreImpl();
        ComputeEngine computeEngine = new ComputeEngineImpl();
        
        coordinator = new CoordinatorImpl(dataStore, computeEngine);
    }

    @Test
public void compareMultiAndSingleThreaded() throws Exception {
    int numThreads = 4; // Number of threads or tasks to run
    List<TestUser> testUsers = new ArrayList<>();
    
    String filePath = getClass().getResource("/testInputFile.test").getPath();
    // Initialize test users (one for each task)
    for (int i = 0; i < numThreads; i++) {
        testUsers.add(new TestUser(coordinator));
    }

    // Step 1: Run single-threaded execution
    String singleThreadFilePrefix = "testSingleUser.compareMultiAndSingleThreaded.singleThreadOut.tmp";
    for (int i = 0; i < numThreads; i++) {
        File singleThreadedOut = new File(singleThreadFilePrefix + i);
        singleThreadedOut.deleteOnExit(); // Ensure the file is deleted after test
        testUsers.get(i).run(singleThreadedOut.getAbsolutePath()); // Run each task sequentially
    }

    // Step 2: Run multi-threaded execution
    ExecutorService threadPool = Executors.newCachedThreadPool(); // Create a thread pool for multi-threaded execution
    List<Future<?>> results = new ArrayList<>();
    String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.multiThreadOut.tmp";
    for (int i = 0; i < numThreads; i++) {
        File multiThreadedOut = new File(multiThreadFilePrefix + i);
        multiThreadedOut.deleteOnExit(); // Ensure the file is deleted after test
        String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
        TestUser testUser = testUsers.get(i);
        results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath))); // Run tasks concurrently
    }

    // Wait for all threads to finish
    for (Future<?> future : results) {
        future.get(); // Ensure all tasks have completed
    }

    // Step 3: Load output files from single-threaded execution
    List<String> singleThreadedOutput = loadAllOutput(singleThreadFilePrefix, numThreads);

    // Step 4: Load output files from multi-threaded execution
    List<String> multiThreadedOutput = loadAllOutput(multiThreadFilePrefix, numThreads);

    // Step 5: Compare both outputs
    Assert.assertEquals("The output of single-threaded and multi-threaded execution should be the same.", 
                        singleThreadedOutput, multiThreadedOutput);
}

// Helper method to load all output lines from multiple files
private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < numThreads; i++) {
        File outputFile = new File(prefix + i); // Load the output file for each thread
        result.addAll(Files.readAllLines(outputFile.toPath())); // Read all lines from the file
    }
    return result; // Return the accumulated output
}
}
