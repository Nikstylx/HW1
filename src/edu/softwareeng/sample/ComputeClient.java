package edu.softwareeng.sample;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import compute.ComputeServiceGrpc;
import compute.ComputeRequest;
import compute.ComputeResponse;

import java.util.Scanner;

public class ComputeClient {
    public static void main(String[] args) {
        // Set up the gRPC channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()  // For simplicity, no encryption
            .build();
        
        ComputeServiceGrpc.ComputeServiceBlockingStub stub = ComputeServiceGrpc.newBlockingStub(channel);
        
        // Prompt user for input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number for computation: ");
        int inputValue = scanner.nextInt();
        
        // Build the request object
        ComputeRequest request = ComputeRequest.newBuilder()
            .setInputValue(inputValue)
            .build();
        
        // Send the request to the server and get the response
        ComputeResponse response = stub.computeNumbers(request);
        
        // Print the result
        if (response.getSuccess()) {
            System.out.println("Computation successful. Result: " + response.getResult());
        } else {
            System.err.println("Computation failed: " + response.getMessage());
        }

        // Close the channel
        channel.shutdown();
    }
}
