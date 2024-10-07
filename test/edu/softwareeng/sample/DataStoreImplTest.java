package edu.softwareeng.sample;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

public class DataStoreImplTest {

    private final DataStoreImpl dataStore = new DataStoreImpl();

    @Test
    public void testReadFileNotFound() {
        InputConfig inputConfig = new FileInputConfig("nonexistentfile.txt");
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            dataStore.read(inputConfig);
        });
        
        assertTrue(exception.getMessage().contains("File does not exist or cannot be read: nonexistentfile.txt"));
    }

    @Test
    public void testAppendToInvalidFile() throws IOException {
        // Create an invalid OutputConfig (e.g., using a directory instead of a file)
        OutputConfig outputConfig = new FileOutputConfig("invalid_directory/");
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            dataStore.appendSingleResult(outputConfig, "test", ',');
        });

        assertTrue(exception.getMessage().contains("File does not exist or cannot be read: invalid_directory/"));
    }

    @Test
    public void testNullInputConfig() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataStore.read(null);
        });

        assertEquals("InputConfig cannot be null", exception.getMessage());
    }

    @Test
    public void testNullOutputConfig() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataStore.appendSingleResult(null, "result", ',');
        });

        assertEquals("OutputConfig cannot be null", exception.getMessage());
    }

    @Test
    public void testNullResultInAppend() {
        OutputConfig outputConfig = new FileOutputConfig("output.txt");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataStore.appendSingleResult(outputConfig, null, ',');
        });

        assertEquals("Result cannot be null", exception.getMessage());
    }

    // Cleanup method to delete any test files created (if needed)
    private void cleanupTestFiles() {
        File testFile = new File("output.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
        // Add any additional cleanup as necessary
    }
}
