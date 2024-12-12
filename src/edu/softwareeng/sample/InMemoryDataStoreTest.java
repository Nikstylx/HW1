package edu.softwareeng.sample;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryDataStoreTest {

    @Test
    public void testStoreData() {
        InMemoryDataStore store = new InMemoryDataStore();
        boolean result = store.store("testKey", "testValue");
        assertTrue(result);
    }

    @Test
    public void testGetData() {
        InMemoryDataStore store = new InMemoryDataStore();
        store.store("testKey", "testValue");
        String value = store.get("testKey");
        assertEquals("testValue", value);
    }
}
