package ua.com.alevel.model.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.alevel.model.inf.ChangeTime;

@Data

public class DrnTime {
    private final static String COLON = ":";
    private long hour = 0;
    private long minute = 0;
    private long second = 0;
    private long millsecond = 0;

    public DrnTime(long hour, long minute, long second, long millsecond) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millsecond = millsecond;
    }

    @Override
    public String toString() {
        return hour + COLON + minute + COLON + second + COLON + millsecond;
    }
}
