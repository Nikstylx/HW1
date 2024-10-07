package edu.softwareeng.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import edu.softwareeng.sample.WriteResult.WriteResultStatus;

public class DataStoreImpl implements DataStore {

    @Override
    public Iterable<Integer> read(InputConfig input) {
        // Validate input parameter
        if (input == null) {
            throw new IllegalArgumentException("InputConfig cannot be null");
        }

        return InputConfig.visitInputConfig(input, fileConfig -> {
            // Validate the file name
            String fileName = fileConfig.getFileName();
            validateFile(fileName);

            return new Iterable<Integer>() {
                @Override
                public Iterator<Integer> iterator() {
                    return getFileBasedIterator(fileName);
                }
            };
        });
    }

    private Iterator<Integer> getFileBasedIterator(String fileName) {
        try {
            return new Iterator<Integer>() {
                BufferedReader buff = new BufferedReader(new FileReader(fileName));
                String line = buff.readLine();
                boolean closed = false;

                @Override
                public Integer next() {
                    try {
                        // Ensure line is read before parsing
                        if (line == null) {
                            throw new IllegalStateException("No more elements to read");
                        }
                        int result = Integer.parseInt(line);
                        line = buff.readLine();
                        if (!hasNext()) {
                            buff.close();
                            closed = true;
                        }
                        return result;
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading from file: " + e.getMessage(), e);
                    }
                }

                @Override
                public boolean hasNext() {
                    return line != null;
                }

                
            };
        } catch (IOException e) {
            throw new RuntimeException("Error creating file iterator: " + e.getMessage(), e);
        }
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig output, String result, char delimiter) {
        // Validate parameters
        if (output == null) {
            throw new IllegalArgumentException("OutputConfig cannot be null");
        }
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null");
        }

        OutputConfig.visitOutputConfig(output, config -> {
            // Validate the file name
            String fileName = config.getFileName();
            validateFile(fileName);
            writeToFile(fileName, result + delimiter);
        });

        return () -> WriteResultStatus.SUCCESS; 
    }

    private void writeToFile(String fileName, String line) {
        try (FileWriter writer = new FileWriter(new File(fileName), true)) {
            writer.append(line);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
    }

    private void validateFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("File does not exist or cannot be read: " + fileName);
        }
    }
}
