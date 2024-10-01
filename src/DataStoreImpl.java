package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStoreImpl implements DataStore {

    @Override
    public Iterable<Integer> read(InputConfig input) {
        List<Integer> numbers = new ArrayList<>();
        
        // Here, you can directly get the file path from wherever it's defined
        // For this example, assume that the path is hardcoded or provided elsewhere
        String filePath = "input.txt"; // Replace with the actual path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String numStr : line.split(",")) {
                    numbers.add(Integer.parseInt(numStr.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return numbers.isEmpty() ? Collections.emptyList() : numbers;
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig output, String result) {
        // Here, you can directly get the file path from wherever it's defined
        // For this example, assume that the path is hardcoded or provided elsewhere
        String filePath = "output.txt"; // Replace with the actual path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(result);
            writer.newLine();
            return () -> WriteResult.WriteResultStatus.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return () -> WriteResult.WriteResultStatus.FAILURE;
        }
    }
}
