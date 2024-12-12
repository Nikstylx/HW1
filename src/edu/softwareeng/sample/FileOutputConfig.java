package edu.softwareeng.sample;

import java.io.File;

/**
 * Writes out computation results, separated by a specified delimiter.
 */
public class FileOutputConfig implements OutputConfig {

    private final String fileName;

    public FileOutputConfig(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        File file = new File(fileName);
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Provided path is a directory, not a file: " + fileName);
        }
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
