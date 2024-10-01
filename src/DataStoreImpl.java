package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStoreImpl implements DataStore {

    @Override
    public Iterable<Integer> read(InputConfig input) {
        List<Integer> numbers = new ArrayList<>();
        // Assuming input is an instance of FileInputConfig
        if (input instanceof FileInputConfig) {
            String filePath = ((FileInputConfig) input).getFilePath();
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
        }
        return numbers.isEmpty() ? Collections.emptyList() : numbers;
    }

    @Override
    public WriteResult appendSingleResult(OutputConfig output, String result) {
        // Assuming output is an instance of FileOutputConfig
        if (output instanceof FileOutputConfig) {
            String filePath = ((FileOutputConfig) output).getFilePath();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(result);
                writer.newLine();
                return () -> WriteResult.WriteResultStatus.SUCCESS;
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions appropriately
                return () -> WriteResult.WriteResultStatus.FAILURE;
            }
        }
        return () -> WriteResult.WriteResultStatus.FAILURE; // If output config is not valid
    }
}
