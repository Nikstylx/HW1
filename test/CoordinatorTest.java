package src;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoordinatorTest {

	@Test
	public void smokeTest() {
		// mock out the dependencies so that we're just checking the ComputationCoordinator
		DataStore dataStore = Mockito.mock(DataStore.class);
		ComputeEngine computeEngine = Mockito.mock(ComputeEngine.class);
		
		ComputationCoordinator coord = new CoordinatorImpl(dataStore, computeEngine);
		
		// mock out the parameters
		ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
		ComputeResult result = coord.compute(mockRequest);
		
		// simple check for right now - just say the result must be successful
		Assertions.assertEquals(result.getStatus(), ComputeResult.ComputeResultStatus.SUCCESS);
	}
}
