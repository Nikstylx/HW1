package edu.softwareeng.sample;

/**
 * Writes out computation results, separated by a specified delimiter
 *
 */
public class FileOutputConfig implements OutputConfig {
	
	private final String fileName;

	public FileOutputConfig(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	@Override
public void appendResults(List<String> results, char delimiter) {
    // Implement the method to handle appending results
    // Example: write the results to a file or append to an internal list
}

}
