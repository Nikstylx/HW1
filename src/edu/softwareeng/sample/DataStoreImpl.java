package edu.softwareeng.sample;

import java.util.List;

public class DataStoreImpl implements DataStore {

    @Override
    public WriteResult appendSingleResult(OutputConfig outputConfig, String result, char delimiter) {
        List<String> outputResults = new ArrayList<>();
        outputResults.add(result);  // Add result to list
        outputConfig.appendResults(outputResults, delimiter);  // Append result to OutputConfig
        return WriteResult.SUCCESS;
    }
}
