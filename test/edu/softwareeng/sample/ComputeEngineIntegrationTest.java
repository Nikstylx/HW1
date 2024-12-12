package edu.softwareeng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeWorkflow() throws Exception {
        // Create an InMemoryInputConfig and OutputConfig for the test
        InMemoryInputConfig inputConfig = new InMemoryInputConfig(1, 15, 10, 5, 2, 3, 8);
        InMemoryOutputConfig outputConfig = new InMemoryOutputConfig();

        // Initialize the ComputeEngine and run computation
        ComputeEngine computeEngine = new ComputeEngineImpl();
        computeEngine.compute(inputConfig, outputConfig, ',');

        // Validate the output - expected output should match the computed output
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("10");
        expected.add("25");
        Assertions.assertEquals(expected, outputConfig.getOutputMutable());
    }
}
