package edu.softwareeng.sample;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.softwareeng.sample.WriteResult.WriteResultStatus;

public class DataStoreTest {

    @Test
    public void smokeTestRead() throws Exception {
        // Create a temporary file for testing
        File file = new File("dataStoreTest.smokeTestRead.txt.temp");
        file.createNewFile();
        file.deleteOnExit(); // Ensure the file is deleted after the test
        InputConfig inputConfig = new FileInputConfig(file.getCanonicalPath());

        DataStore dataStore = new DataStoreImpl();

        // Ensure no elements are read from an empty file
        Assertions.assertFalse(dataStore.read(inputConfig).iterator().hasNext());
    }

    @Test
    public void smokeTestWrite() throws Exception {
        // Create a temporary file for testing
        File file = new File("dataStoreTest.smokeTestWrite.txt.temp");
        file.deleteOnExit(); // Ensure the file is deleted after the test
        System.out.println("File path for writing: " + file.getCanonicalPath()); // Debugging line

        OutputConfig outputConfig = new FileOutputConfig(file.getCanonicalPath());

        DataStore dataStore = new DataStoreImpl();

        // Perform the write operation and verify the result
        WriteResult result = dataStore.appendSingleResult(outputConfig, "result", ';');
        Assertions.assertEquals(WriteResultStatus.SUCCESS, result.getStatus());

        // Verify the file contains the expected result
        List<String> allLines = Files.readAllLines(file.toPath());
        Assertions.assertEquals(1, allLines.size());
        Assertions.assertEquals("result;", allLines.get(0));
    }

    @Test
    public void testAppend() throws Exception {
        // Add tests to verify that our append logic is in fact appending
        File file = new File("dataStoreTest.testAppend.txt.temp");
        file.deleteOnExit();
        OutputConfig outputConfig = new FileOutputConfig(file.getCanonicalPath());

        DataStore dataStore = new DataStoreImpl();

        Assertions.assertEquals(WriteResultStatus.SUCCESS, dataStore.appendSingleResult(outputConfig, "result1", ';').getStatus());
        Assertions.assertEquals(WriteResultStatus.SUCCESS, dataStore.appendSingleResult(outputConfig, "result2", ';').getStatus());

        String expected = "result1;result2;";
        List<String> allLines = Files.readAllLines(file.toPath());

        // It's good to check all assumptions about the output
        Assertions.assertEquals(1, allLines.size());
        Assertions.assertEquals(expected, allLines.get(0));
    }

    @Test
    public void testRead() throws Exception {
        // Create a file with some test data
        File file = new File("dataStoreTest.testRead.txt.temp");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file, true);
        writer.append("1\n");
        writer.append("2\n");
        writer.close();

        InputConfig inputConfig = new FileInputConfig(file.getCanonicalPath());

        DataStore dataStore = new DataStoreImpl();
        Iterator<Integer> iterator = dataStore.read(inputConfig).iterator();
        
        // Check that we can read the values from the file
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next().intValue());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next().intValue());
        Assertions.assertFalse(iterator.hasNext());
    }
}
