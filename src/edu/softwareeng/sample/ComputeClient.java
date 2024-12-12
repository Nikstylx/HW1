import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

public class ComputeClient {
    private final ManagedChannel channel;
    private final ComputeServiceGrpc.ComputeServiceStub asyncStub;

    public ComputeClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        asyncStub = ComputeServiceGrpc.newStub(channel);
    }

    public void close() {
        channel.shutdownNow();
    }

    public void computeTask(String inputConfig, String outputConfig, String delimiter) {
        ComputeRequest request = ComputeRequest.newBuilder()
                .setInputConfig(inputConfig)
                .setOutputConfig(outputConfig)
                .setDelimiter(delimiter)
                .build();

        asyncStub.computeTask(request, new StreamObserver<ComputeResult>() {
            @Override
            public void onNext(ComputeResult value) {
                System.out.println("Computation Status: " + value.getStatus());
                System.out.println("Message: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Computation complete.");
            }
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input file or numbers (comma-separated): ");
        String inputConfig = scanner.nextLine();

        System.out.print("Enter output file: ");
        String outputConfig = scanner.nextLine();

        System.out.print("Enter delimiter (default is ','): ");
        String delimiter = scanner.nextLine();
        if (delimiter.isEmpty()) {
            delimiter = ",";
        }

        ComputeClient client = new ComputeClient("localhost", 50051);
        client.computeTask(inputConfig, outputConfig, delimiter);
        client.close();
    }
}
