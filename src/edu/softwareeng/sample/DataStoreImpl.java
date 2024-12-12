package edu.softwareeng.sample;

import java.io.File;

public class DataStoreImpl implements DataStore {
    
    @Override
    public void processInput(InputConfig input) {
        // Make sure visitInputConfig is defined in the InputConfig interface/class
        input.visitInputConfig(fileConfig -> {
            // Handle fileConfig here
            // Example: you can perform operations with the fileConfig
        });
    }
}
