package edu.softwareeng.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import edu.softwareeng.sample.WriteResult.WriteResultStatus;

public class DataStoreImpl implements DataStore {

    private static final String NULL_FILE_NAME_ERROR = "File name cannot be null or empty";
    private static final String FILE_NOT_FOUND_ERROR = "File does not exist or cannot be read: ";
    private static final String DIRECTORY_ERROR = "The specified path is a directory, not a file: ";
    private static final String FILE_WRITE_ERROR = "File cannot be written to: ";

    @Override
    public Iterable<Integer> read(InputConfig input) {
        if (input == null) {
            throw new IllegalArgumentException("InputConfig cannot be null");
        }

        return InputConfig.visitInputConfig(input, fileConfig -> {
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            return new Iterator<Integer>() {
                String line = bufferedReader.readLine();

                @Override
                public boolean hasNext() {
                    return line != null;
                }

                @Override
                public Integer next() {
                    try {
                        if (line == null) {
                            throw new IllegalStateException("No more elements to read");
                        }
                        int result = Integer.parseInt(line);
                        line = bufferedReader.readLine();
                        return result;
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading from file: " + e.getMessage(), e);
                    }
                }

                @Override
                protected void finalize() throws Throwable {
                    super.finalize();
                    bufferedReader.close();
                }
            };

        } catch (IOException e) {
            throw new RuntimeException("Error creating file iterator: " + e.getMessage(), e);
        }
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig output, String result, char delimiter) {
        if (output == null) {
            throw new IllegalArgumentException("OutputConfig cannot be null");
        }
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null");
        }

        OutputConfig.visitOutputConfig(output, config -> {
            String fileName = config.getFileName();
            validateFile(fileName);
            writeToFile(fileName, result + delimiter);
        });

        return () -> WriteResultStatus.SUCCESS;
    }

    private void writeToFile(String fileName, String line) {
        File tempFile = new File(fileName + ".tmp");
        try (FileWriter writer = new FileWriter(tempFile, true)) {
            writer.append(line);
            writer.flush();
            if (!tempFile.renameTo(new File(fileName))) {
                throw new IOException("Failed to replace the original file with updated data.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        } finally {
            if (tempFile.exists() && !tempFile.delete()) {
                System.err.println("Warning: Temporary file could not be deleted: " + tempFile.getPath());
            }
        }
    }

    private void validateFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException(NULL_FILE_NAME_ERROR);
        }

        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR + fileName);
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(DIRECTORY_ERROR + fileName);
        }
        if (!file.canWrite()) {
            throw new IllegalArgumentException(FILE_WRITE_ERROR + fileName);
        }
    }
}
