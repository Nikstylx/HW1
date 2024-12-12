import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeWorkflow() {
        // Create actual implementations of components
        ComputeEngine engine = new ComputeEngineImpl();
        TestDataStore testDs = new TestDataStore();
        ComputationCoordinator coord = new CoordinatorImpl(testDs, engine);

        // Prepare input and output configurations
        InMemoryInputConfig input = new InMemoryInputConfig(1, 10, 25);
        InMemoryOutputConfig output = new InMemoryOutputConfig();

        // Mock ComputeRequest
        ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
        when(mockRequest.getInputConfig()).thenReturn(input);
        when(mockRequest.getOutputConfig()).thenReturn(output);

        // Perform computation
        ComputeResult result = coord.compute(mockRequest);

        // Assert that the computation was successful
        Assertions.assertEquals(ComputeResult.SUCCESS, result);

        // Expected output after computation
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("10");
        expected.add("25");

        // Log the output to debug any discrepancies
        System.out.println("Output List: " + output.getOutputMutable());

        // Assert that the expected output matches the actual output
        Assertions.assertEquals(expected, output.getOutputMutable());
    }
}
