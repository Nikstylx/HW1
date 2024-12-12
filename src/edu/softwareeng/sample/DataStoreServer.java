import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class DataStoreServer {
    private final int port = 50052;  // Different port for the Data Store service
    private final Server server;

    public DataStoreServer() {
        // Create the server and add the DataStoreServiceImpl as a service
        this.server = ServerBuilder.forPort(port)
            .addService(new DataStoreServiceImpl())  // Use DataStoreServiceImpl to handle requests
            .build();
    }

    // Start the DataStore server
    public void start() throws Exception {
        server.start();
        System.out.println("Data Store Server started on port: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down Data Store gRPC server...");
            server.shutdown();
        }));
        server.awaitTermination();
    }

    // Main method to start the DataStore server
    public static void main(String[] args) throws Exception {
        final DataStoreServer server = new DataStoreServer();
        server.start();
    }
}
