package edu.softwareeng.sample;

public interface ComputeResult {
	static ComputeResult SUCCESS = () -> ComputeResultStatus.SUCCESS;
	static ComputeResult FAILURE = () -> ComputeResultStatus.FAILURE;

	ComputeResultStatus getStatus();
	
	public static enum ComputeResultStatus {
		SUCCESS(true),
		FAILURE(false);
		
		private final boolean success;
		
		private ComputeResultStatus(boolean success) {
			this.success = success;
		}
		
		public boolean isSuccess() {
			return success;
		}
	}
}
