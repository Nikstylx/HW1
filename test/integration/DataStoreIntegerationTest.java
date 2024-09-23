package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class DataStoreIntegrationTest {
    @Test
    void testProcessData() {
        List<Integer> inputData = List.of(1, 2, 3);
        List<String> outputData = new ArrayList<>();
        
        InMemoryInputConfig inputConfig = new InMemoryInputConfig(inputData);
        InMemoryOutputConfig outputConfig = new InMemoryOutputConfig(outputData);
        InMemoryDataStoreAPI dataStore = new InMemoryDataStoreAPI(inputConfig, outputConfig);
        
        dataStore.processData();
        
        assertEquals(3, outputData.size());
        assertEquals("1", outputData.get(0));
        assertEquals("2", outputData.get(1));
        assertEquals("3", outputData.get(2));
    }
}

