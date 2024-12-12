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
}
