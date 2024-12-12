package edu.softwareeng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoordinatorTest {

    @Test
    public void smokeTest() {
        // Mock out the dependencies to test the ComputationCoordinator
        DataStore dataStore = Mockito.mock(DataStore.class);
        ComputeEngine computeEngine = Mockito.mock(ComputeEngine.class);
        
        ComputationCoordinator coord = new CoordinatorImpl(dataStore, computeEngine);
        
        // Mock the compute request
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        
        // Test the compute workflow
        ComputeResult result = coord.compute(mockRequest);
        
        // Validate that the result status is SUCCESS
        Assertions.assertEquals(result.getStatus(), ComputeResult.ComputeResultStatus.SUCCESS);
    }
}
