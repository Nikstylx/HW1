import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ComputeServer {
    public static void main(String[] args) throws Exception {
        // Build the server and add the service implementation
        Server server = ServerBuilder.forPort(50051)
            .addService(new ComputeServiceImpl())  // Add the implementation of the service
            .build();
        
        // Start the server
        server.start();
        System.out.println("Server started on port 50051...");
        
        // Keep the server running
        server.awaitTermination();
    }
}
