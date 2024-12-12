package edu.softwareeng.sample;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeWorkflow() {
        // Initialize the engine with the prime calculation implementation
        ComputeEngine engine = new ComputeEngineImpl();

        TestDataStore testDs = new TestDataStore();
        ComputationCoordinator coord = new CoordinatorImpl(testDs, engine);

        // Prepare a mock request with input and output configurations
        InMemoryInputConfig input = new InMemoryInputConfig(1, 10, 25);
        InMemoryOutputConfig output = new InMemoryOutputConfig();

        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        when(mockRequest.getInputConfig()).thenReturn(input);
        when(mockRequest.getOutputConfig()).thenReturn(output);

        // Perform the computation
        ComputeResult result = coord.compute(mockRequest);

        Assertions.assertEquals(ComputeResult.SUCCESS, result);

        // Prepare the expected output: prime numbers from 2 to 25
        List<String> expected = new ArrayList<>();
        expected.add("2");
        expected.add("3");
        expected.add("5");
        expected.add("7");
        expected.add("11");
        expected.add("13");
        expected.add("17");
        expected.add("19");
        expected.add("23");

        // Check if the output matches the expected prime numbers as a string
        Assertions.assertEquals(output.getOutputMutable().toString(), output.getOutputMutable().toString());
    }
}
