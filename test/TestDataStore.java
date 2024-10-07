package edu.softwareeng.sample;

/** 
 * Test-only implementation of DataStore that handles in-memory inputs and outputs
 */
public class TestDataStore implements DataStore {

	@Override
	public Iterable<Integer> read(InputConfig input) {
		// Test code is allowed to assume it's getting the right types; this will fail with a ClassCastException if it gets
		// another type of input. For production code, we'd want some better user input validation
		return ((InMemoryInputConfig)input).getInputs();
	}

	@Override
	public WriteResult appendSingleResult(OutputConfig output, String result, char delimiter) {
		// Test code is allowed to assume it's getting the right types; this will fail with a ClassCastException if it gets
		// another type of input. For production code, we'd want some better user input validation
		((InMemoryOutputConfig)output).getOutputMutable().add(result);
		return () -> WriteResult.WriteResultStatus.SUCCESS;
	}

}
