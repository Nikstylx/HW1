import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore implements DataStore {
    private final Map<String, String> store = new HashMap<>();

    @Override
    public boolean store(String key, String value) {
        store.put(key, value);
        return true;
    }

    @Override
    public String get(String key) {
        return store.get(key);
    }
}
