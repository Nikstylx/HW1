package edu.softwareeng.sample;
public interface WriteResult {
	WriteResultStatus getStatus();
	
	public static enum WriteResultStatus {
		SUCCESS,
		FAILURE;
	}
}
