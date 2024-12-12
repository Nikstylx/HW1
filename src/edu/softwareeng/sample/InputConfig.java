package edu.softwareeng.sample;

import java.util.List;

public interface InputConfig {
    void visitInputConfig(Consumer<FileConfig> consumer);
    List<Integer> getInputs();
}
