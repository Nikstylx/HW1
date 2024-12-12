package edu.softwareeng.sample;

import java.io.File;

public class TestUser {

    private final ComputationCoordinator coordinator;

    public TestUser(ComputationCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void run(String outputPath) {
        char delimiter = ';';
        String inputPath = "test" + File.separatorChar + "testInputFile.test";
        
        // Call the appropriate method(s) on the coordinator to run the compute job
        ComputeRequest request = new ComputeRequestImpl(inputPath, outputPath, delimiter);
        coordinator.compute(request);
    }
}
