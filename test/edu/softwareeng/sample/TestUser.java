package edu.softwareeng.sample;

import java.io.File;

public class TestUser {
    
    // Use the correct type for your User <-> ComputeEngine API
    private final ComputationCoordinator coordinator;

    public TestUser(ComputationCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void run(String outputPath) {
        char delimiter = ';';
        String inputPath = "test" + File.separatorChar + "testInputFile.test"; // Make sure the file exists or change the path accordingly
        
        // Create the input and output configurations
        InputConfig inputConfig = new FileInputConfig(inputPath);  // Input configuration
        OutputConfig outputConfig = new FileOutputConfig(outputPath);  // Output configuration

        // Create the compute request with the necessary configurations
        ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, delimiter);

        // Call the compute method of the coordinator
        ComputeResult result = coordinator.compute(request);

        // Handle result if needed (e.g., checking the status or logging)
        if (result.getStatus() != ComputeResult.ComputeResultStatus.SUCCESS) {
            // Log or handle errors if the computation wasn't successful
            System.err.println("Computation failed: " + result.getStatus());
        }
    }
}
