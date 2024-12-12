package edu.softwareeng.sample;

import java.util.function.Consumer;

public class InMemoryInputConfig implements InputConfig {
    private FileConfig fileConfig;

    public InMemoryInputConfig(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Override
    public void visitInputConfig(Consumer<FileConfig> consumer) {
        consumer.accept(fileConfig);
    }
    @Override
public List<Integer> getInputs() {
    // Return the list of inputs; assuming `inputs` is a member variable
    return this.inputs;
}

    
}
