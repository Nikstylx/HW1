import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ComputeServer {
    private final int port = 50051;
    private final Server server;

    public ComputeServer() {
        this.server = ServerBuilder.forPort(port)
            .addService(new ComputeServiceImpl())  // Use ComputeServiceImpl
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
