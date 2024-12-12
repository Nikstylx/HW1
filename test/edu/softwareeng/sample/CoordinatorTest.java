package edu.softwareeng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoordinatorTest {

    @Test
    public void smokeTest() {
        // Mock the dependencies so that we're just testing the ComputationCoordinator
        DataStore dataStore = Mockito.mock(DataStore.class);
        ComputeEngine computeEngine = Mockito.mock(ComputeEngine.class);
        
        // Mock the ComputeRequest object and its methods
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        
        // Mock input and output configurations
        InMemoryInputConfig inputConfig = new InMemoryInputConfig(1, 10, 25);
        InMemoryOutputConfig outputConfig = new InMemoryOutputConfig();
        
        // When the mock request is called, return the mocked configurations
        Mockito.when(mockRequest.getInputConfig()).thenReturn(inputConfig);
        Mockito.when(mockRequest.getOutputConfig()).thenReturn(outputConfig);
        
        // Create the ComputationCoordinator with the mocked dependencies
        ComputationCoordinator coord = new CoordinatorImpl(dataStore, computeEngine);
        
        // Perform the computation (this will not actually compute anything as we are mocking)
        ComputeResult result = coord.compute(mockRequest);
        
        // Simple check for right now - just check that the result is successful
        Assertions.assertEquals(ComputeResult.ComputeResultStatus.SUCCESS, result.getStatus());
    }
}
