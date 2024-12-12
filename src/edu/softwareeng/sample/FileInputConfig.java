package edu.softwareeng.sample;

import java.util.List;
import java.util.ArrayList;

public class FileInputConfig implements InputConfig {
    private FileConfig fileConfig;  // Use FileConfig to hold the file path or related data
    private List<Integer> inputs;

    public FileInputConfig(FileConfig fileConfig, List<Integer> inputs) {
        this.fileConfig = fileConfig;
        this.inputs = inputs;
    }

    public FileConfig getFileConfig() {
        return fileConfig;
    }

    @Override
    public List<Integer> getInputs() {
        return inputs;
    }

    // You can add additional methods if needed to handle file operations
}
