package edu.softwareeng.benchmark;

import edu.softwareeng.sample.CoordinatorImplV1;
import edu.softwareeng.sample.CoordinatorImplV2;
import edu.softwareeng.sample.FileInputConfig;
import edu.softwareeng.sample.FileOutputConfig;
import edu.softwareeng.sample.ComputeEngine;
import edu.softwareeng.sample.ComputeEngineImpl;
import edu.softwareeng.sample.DataStore;
import edu.softwareeng.sample.DataStoreImpl;
import edu.softwareeng.sample.ComputeRequest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class Benchmarktest {

    private ComputeRequest requestV1;
    private ComputeRequest requestV2;
    private CoordinatorImplV1 coordinatorV1;
    private CoordinatorImplV2 coordinatorV2;

    @Setup(Level.Trial)
    public void setup() {
        // Create concrete instances of FileInputConfig and FileOutputConfig
        FileInputConfig inputConfig = new FileInputConfig("test-data-source.txt");
        FileOutputConfig outputConfig = new FileOutputConfig("test-output.txt");

        // Create ComputeRequest instances for both V1 and V2
        requestV1 = new ComputeRequest(inputConfig, outputConfig);
        requestV2 = new ComputeRequest(inputConfig, outputConfig);

        // Initialize ComputeEngine and DataStore
        ComputeEngine computeEngine = new ComputeEngineImpl(); // Use ComputeEngineImpl
        DataStore dataStore = new DataStoreImpl(); // Use DataStoreImpl for reading and writing data

        // Initialize the coordinators with the compute engine and data store
        coordinatorV1 = new CoordinatorImplV1(dataStore, computeEngine);
        coordinatorV2 = new CoordinatorImplV2(dataStore, computeEngine);
    }

    @Benchmark
    public void testCoordinatorV1() {
        // Execute the computation with CoordinatorImplV1
        coordinatorV1.compute(requestV1);
    }

    @Benchmark
    public void testCoordinatorV2() {
        // Execute the computation with CoordinatorImplV2
        coordinatorV2.compute(requestV2);
    }
}

