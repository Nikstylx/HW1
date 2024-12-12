package edu.softwareeng.sample;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import compute.ComputeServiceGrpc;
import compute.ComputeRequest;
import compute.ComputeResponse;

public class ComputeServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
            .addService(new ComputeServiceImpl())
            .build();
        server.start();
        System.out.println("Server started...");
        server.awaitTermination();
    }
}

public class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {
    @Override
    public void computeNumbers(ComputeRequest request, StreamObserver<ComputeResponse> responseObserver) {
        try {
            // Extract data from the gRPC request
            int inputValue = request.getInputValue();
            ComputeEngine engine = new ComputeEngineImpl();
            
            // Perform the computation (you can call the compute method from ComputeEngine)
            String result = engine.compute(inputValue);
            
            // Construct the response
            ComputeResponse response = ComputeResponse.newBuilder()
                .setResult(result)
                .setSuccess(true)
                .setMessage("Computation successful")
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            ComputeResponse response = ComputeResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
