package edu.softwareeng.sample;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import data_store.DataStoreServiceGrpc;
import data_store.AppendResultRequest;
import data_store.AppendResultResponse;
import data_store.ReadDataRequest;
import data_store.ReadDataResponse;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataStoreServer {

    public static void main(String[] args) throws Exception {
        
        int port = 50052; // Different from the Compute Engine's port
        Server server = ServerBuilder.forPort(port)
                .addService(new DataStoreServiceImpl())
                .build();

        System.out.println("Data Store Server starting on port " + port);
        server.start();
        server.awaitTermination();
    }

    
    static class DataStoreServiceImpl extends DataStoreServiceGrpc.DataStoreServiceImplBase {

        @Override
        public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
            String fileName = request.getFileName();
            List<Integer> numbers = readFromFile(fileName);
            
            ReadDataResponse response = ReadDataResponse.newBuilder()
                    .addAllNumbers(numbers)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void appendResult(AppendResultRequest request, StreamObserver<AppendResultResponse> responseObserver) {
            String fileName = request.getFileName();
            String result = request.getResult();
            String delimiter = request.getDelimiter();
            
            appendToFile(fileName, result + delimiter);

            AppendResultResponse response = AppendResultResponse.newBuilder()
                    .setStatus("SUCCESS")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        
        private List<Integer> readFromFile(String fileName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                return reader.lines()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return Arrays.asList();
            }
        }

        
        private void appendToFile(String fileName, String result) {
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.append(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
