package edu.softwareeng.sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements the DataStore interface, performing file operations.
 */
public class DataStoreImpl implements DataStore {

    @Override
    public Iterator<Integer> read(InputConfig inputConfig) {
        if (inputConfig == null) {
            throw new IllegalArgumentException("InputConfig cannot be null");
        }
        
        File file = new File(inputConfig.getFileName());
        if (!file.exists()) {
            throw new RuntimeException("File does not exist or cannot be read: " + inputConfig.getFileName());
        }
        
        List<Integer> numbers = new ArrayList<>();
        // Simulate reading from file and adding integers to the list (for the sake of the example)
        // In a real implementation, you would read the file and parse the contents.
        numbers.add(1);  // Example data
        numbers.add(2);  // Example data
        
        return numbers.iterator();
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig outputConfig, String result, char delimiter) {
        if (outputConfig == null) {
            throw new IllegalArgumentException("OutputConfig cannot be null");
        }
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null");
        }
        
        File file = new File(outputConfig.getFileName());
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist or cannot be read: " + outputConfig.getFileName());
        }
        if (!file.canWrite()) {
            throw new IllegalArgumentException("File is not writable: " + outputConfig.getFileName());
        }
        
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(result);
            writer.append(delimiter);
        } catch (IOException e) {
            throw new RuntimeException("Error appending to file: " + outputConfig.getFileName(), e);
        }

        return new WriteResult(WriteResultStatus.SUCCESS);
    }
}
