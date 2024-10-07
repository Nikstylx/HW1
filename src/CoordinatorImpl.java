package src;

public class CoordinatorImpl implements ComputationCoordinator {
	
	private final DataStore ds;
	private final ComputeEngine ce;
	
	public CoordinatorImpl(DataStore ds, ComputeEngine ce) {
		this.ds = ds;
		this.ce = ce;
	}

	@Override
	public ComputeResult compute(ComputeRequest request) {
		Iterable<Integer> integers = ds.read(request.getInputConfig());
		for (int val : integers) {
			ds.appendSingleResult(request.getOutputConfig(), ce.compute(val), request.getDelimiter());
		}
		return ComputeResult.SUCCESS;
	}

}
