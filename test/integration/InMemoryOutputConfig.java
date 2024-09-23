package integration;

import java.util.List;

public class InMemoryOutputConfig {
    private List<String> outputData;

    public InMemoryOutputConfig(List<String> outputData) {
        this.outputData = outputData;
    }

    public List<String> getOutputData() {
        return outputData;
    }

    public void writeOutput(String output) {
        outputData.add(output);
    }
}
