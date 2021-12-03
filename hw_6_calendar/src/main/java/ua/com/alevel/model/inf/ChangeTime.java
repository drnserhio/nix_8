package ua.com.alevel.model.inf;

public interface ChangeTime {

    void plusHours(long hours);
    void plusMinute(long minutes);
    void plusSeconds(long seconds);
    void plusMilliseconds(long milliseconds);
}
