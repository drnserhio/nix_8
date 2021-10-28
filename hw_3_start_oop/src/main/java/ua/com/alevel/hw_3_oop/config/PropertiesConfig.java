package ua.com.alevel.hw_3_oop.config;

public enum PropertiesConfig {

    APPLICATION_PROPERTIES("application.properties");

    PropertiesConfig(String properties) {
        this.properties = properties;
    }

    private final String properties;

    public String getProperties() {
        return properties;
    }
}
