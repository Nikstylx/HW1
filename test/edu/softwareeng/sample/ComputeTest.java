package edu.softwareeng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeTest {

    @Test
    public void smokeTestCompute() {
        // Test the compute method with a simple input
        ComputeEngine engine = new ComputeEngineImpl();
        Assertions.assertEquals("1", engine.compute(1));
    }
}
