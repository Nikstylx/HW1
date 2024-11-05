import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class ComputeClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                                                      .usePlaintext()
                                                      .build();
        ComputeServiceGrpc.ComputeServiceBlockingStub stub = ComputeServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a file path or numbers for computation (comma separated):");
        String input = scanner.nextLine();

        List<String> numbers;
        if (input.endsWith(".txt")) {
           
            File file = new File(input);
            numbers = readNumbersFromFile(file);
        } else {
            
            numbers = Arrays.asList(input.split(","));
        }

        
        ComputationRequest request = ComputationRequest.newBuilder()
                                                      .addAllNumbers(numbers)
                                                      .build();
        ComputationResponse response = stub.performComputation(request);

        
        System.out.println("Computation Result: " + response.getResult());
        
        
        System.out.println("Enter output file path:");
        String outputPath = scanner.nextLine();
        saveOutputToFile(outputPath, response.getResult());

        channel.shutdown();
    }

    private static List<String> readNumbersFromFile(File file) {
        
        return Arrays.asList("1", "2", "3", "4");  
    }

    private static void saveOutputToFile(String filePath, String result) {
        System.out.println("Saving result to file: " + filePath);
        
    }
}
