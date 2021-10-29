package store;

import java.util.HashMap;
import java.util.Map;

public class BeanStore {
    private static BeanStore instance;
    private Map<Class<?>, Object> store;

    private BeanStore() {
        store = new HashMap<>();
    }

    public static BeanStore getInstance() {
        if (instance == null) {
            instance = new BeanStore();
        }
        return instance;
    }

    public Map<Class<?>, Object> getStore() {
        return store;
    }

}
