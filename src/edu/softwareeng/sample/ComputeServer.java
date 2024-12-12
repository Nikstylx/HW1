import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ComputeServer {
    private final int port = 50051;
    private final Server server;

    public ComputeServer() {
        this.server = ServerBuilder.forPort(port)
            .addService(new ComputeServiceImpl())
            .build();
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Server started on port: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            server.shutdown();
        }));
        server.awaitTermination();
    }

    public static void main(String[] args) throws Exception {
        final ComputeServer server = new ComputeServer();
        server.start();
    }
}

class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {
    @Override
    public void computeTask(ComputeRequest req, StreamObserver<ComputeResult> responseObserver) {
        // Extract the parameters from the request
        String inputConfig = req.getInputConfig();
        String outputConfig = req.getOutputConfig();
        String delimiter = req.getDelimiter();

        // Call your existing CoordinatorImpl to process the computation
        ComputeResult computeResult = processComputation(inputConfig, outputConfig, delimiter);

        // Build the response and send it back
        responseObserver.onNext(computeResult);
        responseObserver.onCompleted();
    }

    private ComputeResult processComputation(String inputConfig, String outputConfig, String delimiter) {
        // Call your existing computation logic (from the CoordinatorImpl)
        // Example:
        try {
            // Assuming CoordinatorImpl already handles input/output and computation logic
            CoordinatorImpl coordinator = new CoordinatorImpl(new DataStoreImpl(), new ComputeEngineImpl());
            ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, delimiter);
            return coordinator.compute(request);
        } catch (Exception e) {
            return ComputeResult.newBuilder()
                    .setStatus("FAILURE")
                    .setMessage("Error: " + e.getMessage())
                    .build();
        }
    }
}
