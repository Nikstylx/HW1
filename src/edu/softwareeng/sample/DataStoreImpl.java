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
                    int result = Integer.parseInt(line);
                    try {
                        line = buff.readLine();
                        if (!hasNext()) {
                            buff.close();
                            closed = true;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return result;
                }

                @Override
                public boolean hasNext() {
                    return line != null;
                }

                @Override
                public void finalize() {
                    if (!closed) {
                        try {
                            buff.close();
                            closed = true;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        // No validation needed for delimiter as it is a char type
        // (Any char can be used as a delimiter)

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
            throw new RuntimeException(e);
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
