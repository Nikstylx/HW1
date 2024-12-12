import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import compute.ComputeServiceGrpc;
import compute.ComputeRequest;
import compute.ComputeResponse;

public class ComputeClient {

    public static void main(String[] args) {
        // Connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()  // Disable encryption for simplicity
            .build();

        // Create a stub to call the remote method
        ComputeServiceGrpc.ComputeServiceBlockingStub stub = ComputeServiceGrpc.newBlockingStub(channel);

        // Create a request (replace with user input or file reading logic)
        ComputeRequest request = ComputeRequest.newBuilder()
            .setInputType("list")  // Could be "file" or "list"
            .setInputData("1,2,3,4,5")  // Example input (could be a file name)
            .setOutputFile("output.txt")
            .setDelimiter(",")
            .build();

        // Make the RPC call
        ComputeResponse response = stub.computeNumbers(request);

        // Print the result
        System.out.println("Computation Status: " + response.getSuccess());
        System.out.println("Message: " + response.getMessage());

        // Shut down the channel
        channel.shutdown();
    }
}
