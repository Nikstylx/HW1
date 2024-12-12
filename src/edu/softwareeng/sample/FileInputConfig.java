package edu.softwareeng.sample;

import java.util.List;
import java.util.function.Consumer;

public class FileInputConfig implements InputConfig {
    private FileConfig fileConfig;  // Assuming FileConfig exists
    private List<Integer> inputs;

    public FileInputConfig(FileConfig fileConfig, List<Integer> inputs) {
        this.fileConfig = fileConfig;
        this.inputs = inputs;
    }

    @Override
    public void visitInputConfig(Consumer<FileConfig> consumer) {
        // Visit the fileConfig using the consumer
        consumer.accept(fileConfig);
    }

    @Override
    public List<Integer> getInputs() {
        return inputs;
    }
}
