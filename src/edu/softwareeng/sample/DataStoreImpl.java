package edu.softwareeng.sample;

import java.util.List;
import java.util.ArrayList;

public class DataStoreImpl implements DataStore {

    @Override
    public void read(InputConfig inputConfig) {
        // Example: Load data from the inputConfig, like reading from a file or fetching data
        System.out.println("Reading data using the provided inputConfig.");
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig outputConfig, String result, char delimiter) {
        List<String> outputResults = new ArrayList<>();
        outputResults.add(result);  // Add result to list
        outputConfig.appendResults(outputResults, delimiter);  // Append result to OutputConfig
        return WriteResult.SUCCESS;
    }
    @Override
public Iterable<Integer> read(InputConfig inputConfig) {
    // Assuming InputConfig has a method getInputs() that returns a list of integers
    return inputConfig.getInputs();
}

}
