package edu.softwareeng.sample;

import java.io.File;

/**
 * Input configuration that reads from a file.
 */
public class FileInputConfig implements InputConfig {
    
    private final String fileName;

    public FileInputConfig(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
