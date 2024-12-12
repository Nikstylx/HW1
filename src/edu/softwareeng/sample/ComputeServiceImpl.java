package edu.softwareeng.sample;

import io.grpc.stub.StreamObserver;
import compute.ComputeServiceGrpc;
import compute.ComputeRequest;
import compute.ComputeResponse;

public class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {

    @Override
    public void computeNumbers(ComputeRequest request, StreamObserver<ComputeResponse> responseObserver) {
        try {
            // Get the input value from the request
            int inputValue = request.getInputValue();
            
            // Create the compute engine to calculate primes
            ComputeEngine engine = new ComputeEngineImpl();
            String result = engine.compute(inputValue);
            
            // Prepare the response
            ComputeResponse response = ComputeResponse.newBuilder()
                .setSuccess(true)
                .setResult(result)
                .setMessage("Computation successful")
                .build();
            
            // Send the response back to the client
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            // Handle any errors and send back the failure response
            ComputeResponse response = ComputeResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
