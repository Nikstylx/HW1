package computeapi;
public class ComputeImplentation{
	private CompAPI computeApi;
	
	public ComputeImplentation(CompAPI computeApi) {
		this.setComputeApi(computeApi);
	}
	public Object runComputation(Object inputData) {
		return null;
	}
	public Object getComputationResult(String computationId) {
		return null;
	}
	public boolean cancelComputation(String computionId) {
		return false;
	}
	public Object listComputations() {
		return new Object[] {};
	}
	public CompAPI getComputeApi() {
		return computeApi;
	}
	public void setComputeApi(CompAPI computeApi) {
		this.computeApi = computeApi;
	}
}
