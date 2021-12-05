package ua.com.alevel.resources.impl;

import lombok.Data;

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
        String h = hour > 9? String.valueOf(hour) : "0" + hour;
        String m = minute > 9? String.valueOf(minute) : "0" + minute;
        String s = second > 9? String.valueOf(second) : "0" + second;
        String ms = millsecond > 9? String.valueOf(millsecond) : "0" + millsecond;
        return h + COLON + m + COLON + s + COLON + ms;
    }
}
