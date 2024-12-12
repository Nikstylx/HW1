package edu.softwareeng.sample;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import datastoreservice.DataStoreServiceGrpc;
import datastoreservice.StoreDataRequest;
import datastoreservice.StoreDataResponse;
import datastoreservice.GetDataRequest;
import datastoreservice.GetDataResponse;

import java.io.IOException;

public class DataStoreServer {
    private final int port = 50051;
    private final Server server;

    public DataStoreServer() {
        server = ServerBuilder.forPort(port)
                .addService(new DataStoreServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("DataStoreServer started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final DataStoreServer server = new DataStoreServer();
        server.start();
        server.server.awaitTermination();
    }
}

// Implementing the DataStoreService
class DataStoreServiceImpl extends DataStoreServiceGrpc.DataStoreServiceImplBase {
    private final InMemoryDataStore dataStore = new InMemoryDataStore();

    @Override
    public void storeData(StoreDataRequest request, StreamObserver<StoreDataResponse> responseObserver) {
        String key = request.getKey();
        String value = request.getValue();
        boolean success = dataStore.store(key, value);
        StoreDataResponse response = StoreDataResponse.newBuilder().setSuccess(success).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getData(GetDataRequest request, StreamObserver<GetDataResponse> responseObserver) {
        String key = request.getKey();
        String value = dataStore.get(key);
        GetDataResponse response = GetDataResponse.newBuilder().setValue(value).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

// In-memory Data Store for testing
class InMemoryDataStore {
    private final java.util.Map<String, String> store = new java.util.HashMap<>();

    public boolean store(String key, String value) {
        store.put(key, value);
        return true;
    }

    public String get(String key) {
        return store.get(key);
    }
}
