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
		return ComputeResult.FAILURE;
	}

}
