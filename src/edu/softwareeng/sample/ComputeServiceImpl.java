import io.grpc.stub.StreamObserver;
import compute.ComputeServiceGrpc;
import compute.ComputeRequest;
import compute.ComputeResponse;

public class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {

    // Override the computeNumbers method to handle incoming requests
    @Override
    public void computeNumbers(ComputeRequest request, StreamObserver<ComputeResponse> responseObserver) {
        boolean success = true;
        String message = "Computation completed successfully";

        try {
            // Extract request data
            String inputType = request.getInputType();
            String inputData = request.getInputData();
            String outputFile = request.getOutputFile();
            String delimiter = request.getDelimiter();

            // Here, implement your computation logic based on the request details.
            // You can read the input file, process the numbers, and write the result to the output file.

            // For now, we simulate success
            // If something goes wrong, set success to false and update the message accordingly

        } catch (Exception e) {
            success = false;
            message = "Error during computation: " + e.getMessage();
        }

        // Create the response
        ComputeResponse response = ComputeResponse.newBuilder()
            .setSuccess(success)
            .setMessage(message)
            .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
