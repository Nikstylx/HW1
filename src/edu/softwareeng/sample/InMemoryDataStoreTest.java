package edu.softwareeng.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertTrue;
// and other necessary imports explicitly


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
