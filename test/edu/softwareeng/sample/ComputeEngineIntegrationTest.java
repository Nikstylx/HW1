package edu.softwareeng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeWorkflow() {
        // Create instances of ComputeEngine and necessary configurations
        ComputeEngine engine = new ComputeEngineImpl();
        TestDataStore testDs = new TestDataStore();
        ComputationCoordinator coord = new CoordinatorImpl(testDs, engine);

        // Setup the input configuration
        InMemoryInputConfig input = new InMemoryInputConfig(1, 10, 25, -5, 7); // Adding negative number for validation
        InMemoryOutputConfig output = new InMemoryOutputConfig();

        // Mock the compute request
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        Mockito.when(mockRequest.getInputConfig()).thenReturn(input);
        Mockito.when(mockRequest.getOutputConfig()).thenReturn(output);

        // Perform the computation using the coordinator
        ComputeResult result = coord.compute(mockRequest);

        // Assert the result is successful
        Assertions.assertEquals(ComputeResult.SUCCESS, result);

        // Check the output to ensure it handles both valid and invalid numbers
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("10");
        expected.add("25");
        expected.add("Invalid input: -5");  // Expecting an error message for the negative number
        expected.add("7");

        // Assert the computed result matches the expected output
        Assertions.assertEquals(expected, output.getOutputMutable());
    }
}
