package ua.com.alevel.model.inf;

public interface ChangeTime {

    void plusHours(long hours);
    void plusMinutes(long minutes);
    void plusSeconds(long seconds);
    void plusMilliseconds(long milliseconds);

    void minusHours(long hours);
    void minusMinutes(long minutes);
    void minusSeconds(long seconds);
    void minusMilliseconds(long milliseconds);
}
