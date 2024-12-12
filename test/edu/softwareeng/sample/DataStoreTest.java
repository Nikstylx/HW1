package edu.softwareeng.sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataStoreTest {

    @Test
    public void smokeTestWrite() throws Exception {
        // Test writing data to a file
        File file = new File("dataStoreTest.smokeTestWrite.txt.temp");
        file.deleteOnExit();
        OutputConfig outputConfig = new FileOutputConfig(file.getCanonicalPath());
        
        DataStore dataStore = new DataStoreImpl();
        
        // Validate if writing succeeds
        Assertions.assertEquals(WriteResult.WriteResultStatus.SUCCESS, 
            dataStore.appendSingleResult(outputConfig, "result", ';').getStatus());
    }

    @Test
    public void testAppend() throws Exception {
        // Test appending data to a file
        File file = new File("dataStoreTest.testAppend.txt.temp");
        file.deleteOnExit();
        OutputConfig outputConfig = new FileOutputConfig(file.getCanonicalPath());
        
        DataStore dataStore = new DataStoreImpl();
        
        // Append data and verify result
        Assertions.assertEquals(WriteResult.WriteResultStatus.SUCCESS, 
            dataStore.appendSingleResult(outputConfig, "result1", ';').getStatus());
        Assertions.assertEquals(WriteResult.WriteResultStatus.SUCCESS, 
            dataStore.appendSingleResult(outputConfig, "result2", ';').getStatus());
        
        String expected = "result1;result2;";
        List<String> allLines = Files.readAllLines(file.toPath());
        
        // Validate the file contents
        Assertions.assertEquals(1, allLines.size()); 
        Assertions.assertEquals(expected, allLines.get(0)); 
    }
}
