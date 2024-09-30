package src;

import java.util.Collections;

import edu.softwareeng.sample.WriteResult.WriteResultStatus;

public class DataStoreImpl implements DataStore {

	@Override
	public Iterable<Integer> read(InputConfig input) {
		return Collections.emptyList(); // eventually this will be a stream, but for now always return 0 elements
	}

	@Override
	public WriteResult appendSingleResult(OutputConfig output, String result) {
		/* 
		 * Using lambda syntax to create an instance of WriteResult. This is an alternative to the ComputeResult approach of providing
		 * constants for success/failure.
		 */
		return () -> WriteResultStatus.FAILURE; 
	}

}
