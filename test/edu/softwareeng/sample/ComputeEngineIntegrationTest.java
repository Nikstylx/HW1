package edu.softwareeng.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputeEngineIntegrationTest {

    private ComputeEngineImpl computeEngine;
    private InMemoryInputConfig inputConfig;
    private InMemoryOutputConfig outputConfig;

    @BeforeEach
    public void setUp() {
        // Initialize the ComputeEngine and configurations
        computeEngine = new ComputeEngineImpl();
        inputConfig = new InMemoryInputConfig(1, 15, 10, -5, 2, 3, 8); // Test data with a negative number
        outputConfig = new InMemoryOutputConfig();
    }

    @Test
    public void testComputeWorkflow() throws IOException {
        // Call the compute method
        computeEngine.compute(inputConfig, outputConfig, ',');

        // Get the output and check that the results are correct
        List<String> output = outputConfig.getOutputMutable();

        // Check for the correct results
        assertEquals("1 is not prime", output.get(0));
        assertEquals("15 is not prime", output.get(1));
        assertEquals("10 is not prime", output.get(2));
        assertTrue(output.get(3).startsWith("Error: Input cannot be negative"), "Expected error message for negative input");
        assertEquals("2 is prime", output.get(4));
        assertEquals("3 is prime", output.get(5));
        assertEquals("8 is not prime", output.get(6));
    }

    @Test
    public void testPrimeNumberComputation() {
        // Testing prime numbers only
        inputConfig = new InMemoryInputConfig(2, 3, 5, 7, 11, 13);
        outputConfig = new InMemoryOutputConfig();

        computeEngine.compute(inputConfig, outputConfig, ',');

        List<String> output = outputConfig.getOutputMutable();
        assertEquals("2 is prime", output.get(0));
        assertEquals("3 is prime", output.get(1));
        assertEquals("5 is prime", output.get(2));
        assertEquals("7 is prime", output.get(3));
        assertEquals("11 is prime", output.get(4));
        assertEquals("13 is prime", output.get(5));
    }

    @Test
    public void testEdgeCase() {
        // Testing edge case of numbers less than or equal to 1
        inputConfig = new InMemoryInputConfig(0, 1, -10);
        outputConfig = new InMemoryOutputConfig();

        computeEngine.compute(inputConfig, outputConfig, ',');

        List<String> output = outputConfig.getOutputMutable();
        assertEquals("0 is not prime", output.get(0));
        assertEquals("1 is not prime", output.get(1));
        assertTrue(output.get(2).startsWith("Error: Input cannot be negative"), "Expected error message for negative input");
    }

    @Test
    public void testLargePrime() {
        // Testing a large prime number
        inputConfig = new InMemoryInputConfig(7919); // 7919 is a prime number
        outputConfig = new InMemoryOutputConfig();

        computeEngine.compute(inputConfig, outputConfig, ',');

        List<String> output = outputConfig.getOutputMutable();
        assertEquals("7919 is prime", output.get(0));
    }
}
