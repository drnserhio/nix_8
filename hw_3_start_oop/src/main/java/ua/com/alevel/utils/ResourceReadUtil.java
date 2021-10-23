package ua.com.alevel.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ResourceReadUtil {

    private ResourceReadUtil() {}

    public static Map<String, String> getPropertieToMap(ClassLoader classLoader) {
        URL prop = classLoader.getSystemResource("prop.properties");
        String path = prop.getPath();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String[] arr = reader.readLine().split("=");

            Map<String, String> map = new HashMap<>();
            map.put(arr[0],arr[1]);
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("Error...");
        }
        return null;
    }
}
