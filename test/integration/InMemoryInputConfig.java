package integration;

import java.util.List;

public class InMemoryInputConfig {
    private List<Integer> inputData;

    public InMemoryInputConfig(List<Integer> inputData) {
        this.inputData = inputData;
    }

    public List<Integer> getInputData() {
        return inputData;
    }
}
