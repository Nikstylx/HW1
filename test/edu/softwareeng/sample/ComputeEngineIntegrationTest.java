package edu.softwareeng.sample;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeWorkflow() {
        // Setup
        FileConfig fileConfig = new FileConfig("test-data-source.txt");
        List<Integer> inputs = List.of(2, 3, 4, 5, 6);
        FileInputConfig inputConfig = new FileInputConfig(fileConfig, inputs);
        OutputConfig outputConfig = new MockOutputConfig();
        ComputeEngineImpl computeEngine = new ComputeEngineImpl();

        // Execution
        String result = computeEngine.compute(inputConfig, outputConfig, ',');

        // Verify results
        assertNotNull(result);
        assertEquals("Computation complete", result);
    }
}
