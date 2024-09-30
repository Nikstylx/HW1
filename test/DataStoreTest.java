package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import test.WriteResult.WriteResultStatus;

/*
 * In this case, I've split out the smoke tests into two tests, one per method. It's also fine to consolidate
 * these into a single test
 */
public class DataStoreTest {

	@Test
	public void smokeTestRead() {
		// While there aren't any dependencies for this component, we can mock out the parameters
		InputConfig inputConfig = Mockito.mock(InputConfig.class);
		
		DataStore dataStore = new DataStoreImpl();
		Assertions.assertEquals(1L, dataStore.read(inputConfig));
	}
	
	@Test
	public void smokeTestWrite() {
		// While there aren't any dependencies for this component, we can mock out the parameters
		// This is optional; for simple parameters, if it's easier to use a real one (like String),
		// feel free to do that
		OutputConfig outputConfig = Mockito.mock(OutputConfig.class);
		
		DataStore dataStore = new DataStoreImpl();
		
		// assertEquals will use .equals, so this type of call is generally safe for any Object, but for enums you can also use ==
		// Note that if your dummy implementation returns null, this will NPE - that's fine! As long as the test fails,
		// the exact failure doesn't matter
		Assertions.assertEquals(WriteResultStatus.SUCCESS, dataStore.appendSingleResult(outputConfig, "result").getStatus());
	}
}
