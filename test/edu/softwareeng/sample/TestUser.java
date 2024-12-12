package edu.softwareeng.sample;

import java.io.File;

public class TestUser {
    
    private final ComputationCoordinator coordinator;

    public TestUser(ComputationCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void run(String outputPath) {
        char delimiter = ';';
        String inputPath = "test" + File.separatorChar + "testInputFile.test"; // Ensure this file exists
        
        // Log file paths
        System.out.println("Input Path: " + inputPath);
        System.out.println("Output Path: " + outputPath);
        
        // Check if the input file exists and is readable
        File inputFile = new File(inputPath);
        if (!inputFile.exists() || !inputFile.canRead()) {
            throw new IllegalArgumentException("Input file does not exist or cannot be read: " + inputPath);
        }

        InputConfig inputConfig = new FileInputConfig(inputPath);
        OutputConfig outputConfig = new FileOutputConfig(outputPath);

        // Create the compute request
        ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, delimiter);

        // Call the compute method
        try {
            ComputeResult result = coordinator.compute(request);
            if (result.getStatus() != ComputeResult.ComputeResultStatus.SUCCESS) {
                System.err.println("Computation failed: " + result.getStatus());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error during computation: " + e.getMessage());
            throw e; // Rethrow to propagate the failure
        }
    }
}
