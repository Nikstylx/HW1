import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.StatusRuntimeException;

public class ComputeServer {

    private final int port = 50051;
    private final Server server;

    // Add gRPC client components for Data Store communication
    private final ManagedChannel channel;
    private final DataStoreServiceGrpc.DataStoreServiceBlockingStub blockingStub;

    public ComputeServer() {
        // Set up gRPC server
        this.server = ServerBuilder.forPort(port)
            .addService(new ComputeServiceImpl())  // Use ComputeServiceImpl
            .build();

        // Set up gRPC channel to connect to the Data Store service
        channel = ManagedChannelBuilder.forAddress("localhost", 50052)  // Assume Data Store runs on port 50052
            .usePlaintext()  // Disable encryption (only for development)
            .build();

        // Create the blocking stub to interact with Data Store service
        blockingStub = DataStoreServiceGrpc.newBlockingStub(channel);
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Server started on port: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            server.shutdown();
            channel.shutdown();  // Don't forget to shutdown the channel
        }));
        server.awaitTermination();
    }

    // Example method to store data into Data Store
    private void storeData(String key, String value) {
        // Build request to store data
        StoreDataRequest request = StoreDataRequest.newBuilder()
                .setKey(key)
                .setValue(value)
                .build();

        try {
            // Send request to Data Store
            StoreDataResponse response = blockingStub.storeData(request);
            if (response.getSuccess()) {
                System.out.println("Data successfully stored.");
            } else {
                System.out.println("Failed to store data.");
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    // Example method to get data from Data Store
    private String getData(String key) {
        // Build request to get data
        GetDataRequest request = GetDataRequest.newBuilder()
                .setKey(key)
                .build();

        try {
            // Send request to Data Store
            GetDataResponse response = blockingStub.getData(request);
            return response.getValue();  // Return the data from the Data Store
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        final ComputeServer server = new ComputeServer();
        // Store and retrieve some data for demonstration
        server.storeData("key1", "value1");
        String value = server.getData("key1");
        System.out.println("Retrieved value: " + value);

        // Start the server
        server.start();
    }
}
