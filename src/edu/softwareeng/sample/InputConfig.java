package edu.softwareeng.sample;

import java.util.function.Consumer;

public interface InputConfig {
    // Define visitInputConfig as an abstract method
    void visitInputConfig(Consumer<FileConfig> consumer);
}
