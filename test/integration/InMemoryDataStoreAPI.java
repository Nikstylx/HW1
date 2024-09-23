package integration;

import java.util.List;

public class InMemoryDataStoreAPI {
    private InMemoryInputConfig inputConfig;
    private InMemoryOutputConfig outputConfig;

    public InMemoryDataStoreAPI(InMemoryInputConfig inputConfig, InMemoryOutputConfig outputConfig) {
        this.inputConfig = inputConfig;
        this.outputConfig = outputConfig;
    }

    public void processData() {
        for (Integer input : inputConfig.getInputData()) {
            outputConfig.writeOutput(String.valueOf(input));
        }
    }
}
