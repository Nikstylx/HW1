package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorEdgeCaseTest {

    @Test
    public void testComputeWithEmptyInput() {
        // Mock the dependencies
        DataStore dataStore = Mockito.mock(DataStore.class);
        ComputeEngine computeEngine = new ComputeEngineImpl();
        
        ComputationCoordinator coord = new CoordinatorImpl(dataStore, computeEngine);
        
        // Create an InMemoryInputConfig with no numbers
        InMemoryInputConfig input = new InMemoryInputConfig();
        
        InMemoryOutputConfig output = new InMemoryOutputConfig();
        
        // Mock the ComputeRequest to return the empty input
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        Mockito.when(mockRequest.getInputConfig()).thenReturn(input);
        Mockito.when(mockRequest.getOutputConfig()).thenReturn(output);
        
        // Execute the compute method
        ComputeResult result = coord.compute(mockRequest);
        
        // Assert that the computation result is successful
        Assertions.assertEquals(ComputeResult.SUCCESS, result);
        
        // Check that the output is empty as no prime numbers should be found
        List<String> expected = new ArrayList<>();
        Assertions.assertEquals(expected, output.getOutputMutable());
    }
    
    @Test
    public void testComputeWithNoPrimesInRange() {
        // Mock the dependencies
        DataStore dataStore = Mockito.mock(DataStore.class);
        ComputeEngine computeEngine = new ComputeEngineImpl();
        
        ComputationCoordinator coord = new CoordinatorImpl(dataStore, computeEngine);
        
        // Create an InMemoryInputConfig with a range that contains no primes (e.g., 4 to 4)
        InMemoryInputConfig input = new InMemoryInputConfig(4, 4);
        
        InMemoryOutputConfig output = new InMemoryOutputConfig();
        
        // Mock the ComputeRequest to return the specific input
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        Mockito.when(mockRequest.getInputConfig()).thenReturn(input);
        Mockito.when(mockRequest.getOutputConfig()).thenReturn(output);
        
        // Execute the compute method
        ComputeResult result = coord.compute(mockRequest);
        
        // Assert that the computation result is successful
        Assertions.assertEquals(ComputeResult.SUCCESS, result);
        
        // Check that the output is empty as no prime numbers should be found
        List<String> expected = new ArrayList<>();
        Assertions.assertEquals(expected, output.getOutputMutable());
    }
}
