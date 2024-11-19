import edu.softwareeng.sample.ComputeRequest;
import edu.softwareeng.sample.ComputeEngine;
import edu.softwareeng.sample.DataStore;
import edu.softwareeng.sample.ComputeResult;
import edu.softwareeng.sample.CoordinatorImplV1;
import edu.softwareeng.sample.CoordinatorImplV2;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;

@State(Scope.Thread)
public class CoordinatorBenchmark {

    private DataStore ds;
    private ComputeEngine ce;
    private ComputeRequest request;

    @Setup(Level.Trial)
    public void setup() {
        // Set up the test data (assuming DataStore and ComputeEngine are simple to initialize)
        ds = new DataStore();  // Mock DataStore or use actual implementation
        ce = new ComputeEngine(); // Mock ComputeEngine or use actual implementation
        request = new ComputeRequest(...); // Set up your ComputeRequest with valid configurations
    }

    // Benchmark for the Original Method (Using Executors.newFixedThreadPool(8)) - CoordinatorImplV1
    @Benchmark
    @BenchmarkMode(Mode.AverageTime) // Measure average execution time
    @OutputTimeUnit(TimeUnit.MILLISECONDS) // Report in milliseconds
    public void testOriginal() throws Exception {
        // Create an instance of the old version (CoordinatorImplV1)
        CoordinatorImplV1 coordinator = new CoordinatorImplV1(ds, ce);
        long startTime = System.nanoTime();
        coordinator.compute(request);  // Run the compute method of the original implementation
        long endTime = System.nanoTime();
        System.out.println("Original Method Time (V1): " + (endTime - startTime) / 1_000_000 + " ms");
    }

    // Benchmark for the Optimized Method (Using Executors.newCachedThreadPool()) - CoordinatorImplV2
    @Benchmark
    @BenchmarkMode(Mode.AverageTime) // Measure average execution time
    @OutputTimeUnit(TimeUnit.MILLISECONDS) // Report in milliseconds
    public void testOptimized() throws Exception {
        // Create an instance of the new version (CoordinatorImplV2)
        CoordinatorImplV2 coordinator = new CoordinatorImplV2(ds, ce);
        long startTime = System.nanoTime();
        coordinator.compute(request);  // Run the compute method of the optimized implementation
        long endTime = System.nanoTime();
        System.out.println("Optimized Method Time (V2): " + (endTime - startTime) / 1_000_000 + " ms");
    }

    // Main method to run the benchmark
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args); // Start the JMH benchmark
    }
}
