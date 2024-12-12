package edu.softwareeng.sample;

import java.util.List;
import java.util.function.Consumer;


public interface InputConfig {
    void visitInputConfig(Consumer<FileConfig> consumer);
    List<Integer> getInputs();
}
